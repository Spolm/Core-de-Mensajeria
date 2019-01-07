package Persistence.M07_Template;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M01_Login.Privilege;
import Entities.M01_Login.User;
import Entities.M01_Login.UserDAO;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;
import Entities.M03_Campaign.CampaignDAO;
import Entities.M04_Integrator.Integrator;
import Entities.M04_Integrator.IntegratorDAO;
import Entities.M05_Channel.Channel;
import Entities.M05_Channel.ChannelFactory;
import Entities.M06_DataOrigin.Application;
import Entities.M06_DataOrigin.ApplicationDAO;
import Entities.M07_Template.HandlerPackage.*;
import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.PlanningPackage.Planning;
import Entities.M07_Template.StatusPackage.Status;
import Entities.M07_Template.Template;
import Entities.Sql;
import Exceptions.CampaignDoesntExistsException;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Persistence.DAO;
import Persistence.DAOFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTemplate extends DAO implements IDAOTemplate {

    final String CREATE_TEMPLATE_WITH_APP= "{CALL m07_posttemplate(?,?,?)}";
    final String CREATE_TEMPLATE_WITHOUT_APP= "{CALL m07_posttemplate2(?,?)}";
    final String GET_TEMPLATE= "{CALL m07_get_template(?)}";
    final String GET_ALL_TEMPLATES= "{m07_select_all_templates()}";
    final String GET__CAMPAIGN_BY_TEMPLATE = "{ CALL m07_getcampaignbytemplate(?) }";
    final String GET_CAMPAIGN_BY_USER_COMPANY = "{call m07_select_campaign_by_user_company(?,?,?)}}";
    final String GET_APPLICATION_BY_TEMPLATE = "{call m07_select_applicantion_by_template(?)}";
    final String GET_CHANNEL_BY_TEMPLATE = "{call m07_select_channels_by_template(?)}";
    final String GET_PRIVILEGES_TEMPLATE = "{call m07_select_privileges_by_user_company(?,?)}";
    private static final String GET_CAMAPIGN_BY_ID = "select* from public.campaign where cam_id = ? "; //Cambiar por SP

    @Override
    public void create(Entity e) {
    }

    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }

    /**
     * Adds
     * @param e
     * @return
     */
    @Override
    public boolean postTemplateData(Entity e){

        Template _t = (Template) e;
        DAOParameter _daoParameter = DAOFactory.instaciateDaoParameter();
        DAOMessage _daoMessage = DAOFactory.instaciateDaoMessage();
        DAOPlanning _daoPlanning = DAOFactory.instaciateDaoPlanning();

        try {
            int templateId = postTemplate(_t.getCampaign().get_id(), _t.getApplication().get_idApplication(), _t.getUser().get_id() );
            //se establece el template  como no aprobado
            StatusHandler.postTemplateStatusNoAprovado(templateId);
            //insertamos los nuevos parametros
            _daoParameter.postParameter( _t.getMessage().getParameterArrayList(), _t.getCompanyId() ); //Este codigo no va aqui
            //obtenemos el valor del mensaje,y parametros
            _daoMessage.postMessage(_t.getMessage(), _t.getCompanyId());

            //obtenemos los valores de los canales e integradores
            //JsonArray channelIntegrator = gsonObj.get("channel_integrator").getAsJsonArray();
            //postChannelIntegrator(channelIntegrator,templateId);

            //planning
            _daoPlanning.postPlanning( _t.getPlanning(), _t.get_id());
        } catch (Exception ex){
            System.out.println(ex);
            return false;
        }

        this.closeConnection();
        return true;
    }

    @Override
    public int postTemplate(int campaignId,int applicationId, int userId) {

        Connection _conn = this.getBdConnect();

        PreparedStatement preparedStatement = null;

        try {
            if( applicationId != 0){
                preparedStatement = _conn.prepareCall( CREATE_TEMPLATE_WITH_APP );
                preparedStatement.setInt(1, campaignId );
                preparedStatement.setInt(2, applicationId );
                preparedStatement.setInt(3, userId );
            }
            else{
                preparedStatement = _conn.prepareCall( CREATE_TEMPLATE_WITHOUT_APP );
                preparedStatement.setInt(1, campaignId );
                preparedStatement.setInt(2, userId );
            }

            preparedStatement.execute();

        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }

        this.closeConnection();
        return 0;
    }

    /**
     * Get specific  template
     * @param templateId
     * @return template
     */
    @Override
    public Entity get(int templateId) {
        //Entity to Return
        Entity _t  = null;
        Connection _conn = this.getBdConnect();

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = _conn.prepareCall( GET_TEMPLATE );
            preparedStatement.setInt( 1, templateId );
            ResultSet _rs = preparedStatement.executeQuery();

            _t = this.createTemplate(_rs);


        }
        catch (SQLException el){
            el.printStackTrace();
        }

        this.closeConnection();
        return _t;
    }

    /**
     * Get all templates
     * @return
     */
    @Override
    public ArrayList<Entity> getAll() {
        //Entity to Return
        ArrayList<Entity> _t  = null;
        Connection _conn = this.getBdConnect();

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = _conn.prepareCall( GET_ALL_TEMPLATES );
            ResultSet _rs = preparedStatement.executeQuery();

            while( _rs.next() ){
                Entity _template = this.createTemplate(_rs);
                _t.add(_template);
            }


        }
        catch (SQLException el){
            el.printStackTrace();
        }

        this.closeConnection();
        return _t;
    }

    /**
     * Get campaing by template
     * @param id
     * @return
     */
    @Override
    public Campaign getCampaignByTemplate(int id) {

        Connection _conn = this.getBdConnect();
        Campaign campaign = new Campaign();
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = _conn.prepareCall( GET__CAMPAIGN_BY_TEMPLATE );
            preparedStatement.setInt(1, id );
            ResultSet _rs = preparedStatement.executeQuery();
            //instanciando el api de campana
            CampaignDAO campaignsService = new CampaignDAO();
            //obtener objeto campana con el id de campana del query anterior
            campaign = campaignsService.getDetails
                    ( _rs.getInt("tem_campaign_id") );
        } catch (SQLException e){
            e.printStackTrace();
            throw new TemplateDoesntExistsException
                    ("Error: la plantilla " + id + " no existe", e, id);
        } catch (CampaignDoesntExistsException e) {
            //logg
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.closeConnection();
            return campaign;
        }
    }

    /**
     * Get template by company
     * @param userID
     * @param companyId
     * @return
     */
    @Override
    public ArrayList<Company> getTemplatesByCompany(int userID, int companyId) {
        return null;
    }

    /**
     * Get application by template
     * @param templateId
     * @return
     */
    @Override
    public Application getApplicationByTemplate(int templateId) {
        //Entity to Return
        Application application = new Application();
        Connection _conn = this.getBdConnect();

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = _conn.prepareCall( GET_APPLICATION_BY_TEMPLATE );
            preparedStatement.setInt( 1, templateId );
            ResultSet _rs = preparedStatement.executeQuery();
            _rs.next();
            ApplicationDAO applicationService = new ApplicationDAO();
            application = applicationService.getApplication
                    (_rs.getInt("applicationId"));

        }
        catch (SQLException el){
            el.printStackTrace();
        }catch (Exception e){
        e.printStackTrace();
        }

        this.closeConnection();
        return application;
    }

    @Override
    public ArrayList<Channel> getChannelsByTemplate(int templateId) {
        ArrayList<Channel> channels = new ArrayList<>();
        Connection _conn = this.getBdConnect();
        try {

            // Search the Channel
            PreparedStatement preparedStatement = _conn.prepareCall( GET_CHANNEL_BY_TEMPLATE );
            preparedStatement.setInt(1,templateId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ){
                ArrayList<Integrator> integrators = new ArrayList<>();
                IntegratorDAO integratorDAO = new IntegratorDAO();

                Integrator integrator = integratorDAO.getConcreteIntegrator(
                        resultSet.getInt("ci_integrator_id")
                );

                integrators.add(integrator);
                Channel channel = new ChannelFactory().getChannel(
                        resultSet.getInt("ci_channel_id"),
                        resultSet.getString("cha_name"),
                        resultSet.getString("cha_description"),
                        integrators
                );
                channels.add(channel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally{
            this.closeConnection();
            return channels;
        }
    }

    /**
     * Get privileges
     * @param userId
     * @param companyId
     * @return
     */
    @Override
    public ArrayList<Privilege> getTemplatePrivilegesByUser(int userId, int companyId) {
        ArrayList<Privilege> privileges = new ArrayList<>();
        Connection _conn = this.getBdConnect();
        try {
            PreparedStatement preparedStatement = _conn.prepareCall(GET_PRIVILEGES_TEMPLATE);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,companyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Privilege privilege = new Privilege();
                privilege.set_idPrivileges(resultSet.getInt("pri_id"));
                privilege.set_codePrivileges(resultSet.getString("pri_code"));
                privilege.set_actionPrivileges(resultSet.getString("pri_action"));
                privileges.add(privilege);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.closeConnection();
        return privileges;
    }

    /**
     *
     * @param campaignId
     * @return
     */
    public Campaign getCampaignsById(int campaignId){
        Campaign campaign =  new Campaign();
        Connection _conn = this.getBdConnect();
        try{
            PreparedStatement preparedStatement = _conn.prepareStatement(GET_CAMAPIGN_BY_ID);
            preparedStatement.setInt(1,campaignId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                campaign.set_idCampaign(resultSet.getInt("cam_id"));
                campaign.set_nameCampaign(resultSet.getString("cam_name"));
                campaign.set_descCampaign(resultSet.getString("cam_description"));
                campaign.set_statusCampaign(resultSet.getBoolean("cam_status"));
                campaign.set_startCampaign(resultSet.getDate("cam_start_date"));
                campaign.set_endCampaign(resultSet.getDate("cam_end_date"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            this.closeConnection();
            return campaign;
        }
    }

    public ArrayList<Campaign> getCampaignsByUserOrCompany(int userId, int companyId){
        ArrayList<Campaign> campaignArrayList = new ArrayList<>();
        Connection connection = this.getBdConnect();
        try{
            PreparedStatement preparedStatement = connection.prepareCall(GET_CAMPAIGN_BY_USER_COMPANY);
            if((userId!=0)&&(companyId!=0)){
                preparedStatement.setInt(1,0);
                preparedStatement.setInt(2,userId);
                preparedStatement.setInt(3,companyId);
            }else if(userId!=0){
                preparedStatement.setInt(1,userId);
                preparedStatement.setInt(2,0);
                preparedStatement.setInt(3,0);
            }else{
                return null;
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Campaign campaign = new Campaign();
                campaign.set_idCampaign(resultSet.getInt("cam_id"));
                campaign.set_nameCampaign(resultSet.getString("cam_name"));
                campaign.set_descCampaign(resultSet.getString("cam_description"));
                campaign.set_statusCampaign(resultSet.getBoolean("cam_status"));
                campaign.set_startCampaign(resultSet.getDate("cam_start_date"));
                campaign.set_endCampaign(resultSet.getDate("cam_end_date"));
                campaignArrayList.add(campaign);
            }
        }catch(SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            this.closeConnection();
            return campaignArrayList;
        }
    }

    /**
     * WHY THIS?!?!?
     * @param channelIntegratorList
     * @param templateId
     */
    private void postChannelIntegrator(JsonArray channelIntegratorList, int templateId) {
        String query= "";
        JsonObject channelIntegrator;
        int channel;
        int integrator;
        Sql sql = new Sql();
        try {
            for (JsonElement list : channelIntegratorList){
                channelIntegrator = list.getAsJsonObject();
                channel = channelIntegrator.get("channel").getAsJsonObject().get("idChannel").getAsInt();
                integrator = channelIntegrator.get("integrator").getAsJsonObject().get("idIntegrator").getAsInt();

                query = query + "insert into public.template_channel_integrator (tci_template_id,tci_ci_id) " +
                        "values (" + templateId + ",(select ci_id from public.channel_integrator " +
                        "where ci_channel_id = " + channel + " and ci_integrator_id = " + integrator +"));";
            }
            sql.sqlNoReturn(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            Sql.bdClose(sql.getConn());
        }
    }

    private Entity createTemplate(ResultSet _rs){
        Entity _t = null;
        try{
            int templateId = _rs.getInt("tem_id");

            //Campaign
            Campaign _campaign = this.getCampaignByTemplate( templateId );

            //application
            Application _app = this.getApplicationByTemplate( templateId );

            //Message
            Message _message = DAOFactory.instaciateDaoMessage().getMessageByTemplate( templateId );

            //user
            UserDAO _userDao = new UserDAO();
            User _user = _userDao.findByUsernameId( _rs.getInt("tem_user_id") );

            //Channel
            ArrayList<Channel> _channels = this.getChannelsByTemplate( templateId );

            //Planning
            Planning _planning = (Planning) DAOFactory.instaciateDaoPlanning().getPlanning( templateId );

             _t = EntityFactory.CreateTemplate(
                    _rs.getInt("tem_id"),
                    _message,
                    _rs.getDate("tem_creation_date"),
                    _channels,
                    _campaign,
                    _app,
                    _user,
                    _planning
            );
        }catch( SQLException ex ){
            ex.printStackTrace();
        }

        return _t;
    }
}
