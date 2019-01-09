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
//import Entities.M04_Integrator.IntegratorDAO;
import Entities.M05_Channel.Channel;
//import Entities.M05_Channel.ChannelFactory;
import Entities.M06_DataOrigin.Application;
import Entities.M06_DataOrigin.ApplicationDAO;
import Entities.M07_Template.HandlerPackage.*;
import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.PlanningPackage.Planning;
import Entities.M07_Template.StatusPackage.Status;
import Entities.M07_Template.Template;
import Entities.Sql;
import Exceptions.CampaignDoesntExistsException;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;
import Persistence.DAO;
import Persistence.DAOFactory;
import com.google.gson.*;
import Persistence.M03_Campaign.DAOCampaign;
import Persistence.M01_Login.DAOUser;
import Persistence.M05_Channel.DAOChannel;
import Persistence.M04_Integrator.DAOIntegrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTemplate extends DAO implements IDAOTemplate {

    final String CREATE_TEMPLATE_WITH_APP= "{CALL m07_posttemplate(?,?,?)}";
    final String CREATE_TEMPLATE_WITHOUT_APP= "{CALL m07_posttemplate2(?,?)}";
    final String GET_TEMPLATE= "{CALL m07_gettemplate(?)}";
    final String GET_ALL_TEMPLATES= "{ CALL m07_select_all_templates()}";
    final String GET_ALL_TEMPLATES_BY_CAMPAIGN= "{ call m07_select_templates_by_campaign(?) }";
    final String GET__CAMPAIGN_BY_TEMPLATE = "{ CALL m07_getcampaignbytemplate(?) }";
    final String GET_CAMPAIGN_BY_USER_COMPANY = "{call m07_select_campaign_by_user_company(?,?,?)}";
    final String GET_APPLICATION_BY_TEMPLATE = "{call m07_select_applicantion_by_template(?)}";
    final String GET_CHANNEL_BY_TEMPLATE = "{call m07_select_channels_by_template(?)}";
    final String GET_PRIVILEGES_TEMPLATE = "{call m07_select_privileges_by_user_company(?,?)}";
    final String UPDATE_TEMPLATE_WITH_APP = "{CALL m07_updatetemplate(?,?,?)}";
    final String UPDATE_TEMPLATE_WITHOUT_APP = "{CALL m07_updatetemplate2(?,?)}";
    final String CREATE_CHANNEL_INTEGRATOR = "{CALL m07_postChannelIntegrator(?,?,?)}";
    final String DELETE = "{CALL m07_deletetemplate(?)}";
    final String DELETE_CHANNEL_INTEGRATOR = "{CALL m07_deleteChannelIntegrator(?)}";

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
     * This method is responsible for saving a template in specific.
     * @param json string json with information
     * @return If the template was saved successfully it returns true,
     * otherwise it returns false.
     */
    public Entity postTemplateData(String json){
        try {
            Gson gson = new Gson();
            //recibimos el objeto json
            JsonParser parser = new JsonParser();
            JsonObject gsonObj = parser.parse(json).getAsJsonObject();
            //hay que extraer campaña y aplicacion, parametros por defecto
            //se crea el template y se retorna su id
            int templateId = this.postTemplate(gsonObj.get("campaign").getAsInt(),gsonObj.get("applicationId").getAsInt(), gsonObj.get("userId").getAsInt());
            //se establece el template  como no aprobado
            DAOStatus daoStatus = DAOFactory.createDAOStatus();
            //StatusHandler.postTemplateStatusNoAprovado(templateId);
            daoStatus.postTemplateStatusNotApproved(templateId);
            //insertamos los nuevos parametros
            String[] parameters = gson.fromJson(gsonObj.get("newParameters").getAsJsonArray(),String[].class);
            DAOFactory.instaciateDaoParameter().postParameter(parameters,gsonObj.get("company").getAsInt());
            //obtenemos el valor del mensaje,y parametros
            parameters = gson.fromJson(gsonObj.get("parameters").getAsJsonArray(),String[].class);

            String message = gsonObj.get("message").getAsString();
            DAOFactory.instaciateDaoMessage().postMessage(message,gsonObj.get("company").getAsInt(),parameters,templateId);

            //obtenemos los valores de los canales e integradores
            JsonArray channelIntegrator = gsonObj.get("channel_integrator").getAsJsonArray();
            postChannelIntegrator(channelIntegrator,templateId);

            //planning
            String[] planning = gson.fromJson(gsonObj.get("planning").getAsJsonArray(),String[].class);
            PlanningHandler.postPlanning(planning,templateId);

            return this.get(templateId);
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    /*@Override
    public boolean postTemplateData(Entity e){

        Template _t = (Template) e;
        DAOParameter _daoParameter = DAOFactory.instaciateDaoParameter();
        DAOMessage _daoMessage = DAOFactory.instaciateDaoMessage();
        DAOPlanning _daoPlanning = DAOFactory.instaciateDaoPlanning();

        try {
            //
            int templateId = postTemplate(_t.getCampaign().get_id(), _t.getApplication().get_idApplication(), _t.getUser().get_id() );
            //se establece el template  como no aprobado
            StatusHandler.postTemplateStatusNotApproved(templateId);
            //insertamos los nuevos parametros
            _daoParameter.postParameter( _t.getMessage().getParameterArrayList(), _t.getCompanyId() ); //Este codigo no va aqui
            //obtenemos el valor del mensaje,y parametros
            _daoMessage.postMessage(_t.getMessage(), _t.getCompanyId(), templateId);

            //obtenemos los valores de los canales e integradores
            //JsonArray channelIntegrator = gsonObj.get("channel_integrator").getAsJsonArray();
            //postChannelIntegrator(channelIntegrator,templateId);

            //planning
            _daoPlanning.postPlanning( _t.getPlanning(), templateId);
        } catch (Exception ex){
            System.out.println(ex);
            return false;
        }

        this.closeConnection();
        return true;
    }*/

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

           ResultSet _rs = preparedStatement.executeQuery();

            if( _rs.next() )
                return _rs.getInt(1);

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
    public Entity get(int templateId) throws TemplateDoesntExistsException, MessageDoesntExistsException, ParameterDoesntExistsException {
        //Entity to Return
        Entity _t  = null;
        Connection _conn = this.getBdConnect();

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = _conn.prepareCall( GET_TEMPLATE );
            preparedStatement.setInt( 1, templateId );
            ResultSet _rs = preparedStatement.executeQuery();

            if(_rs.next()){
                _t = this.createTemplate(_rs);
            }else{
                throw new TemplateDoesntExistsException("Error: la plantilla " + templateId + " no existe");
            }

        }
        catch (SQLException ex){
            ex.printStackTrace();
            throw new TemplateDoesntExistsException
                    ("Error: la plantilla " + templateId + " no existe", ex, templateId);
        } catch ( MessageDoesntExistsException ex ){
            ex.printStackTrace();
            throw new MessageDoesntExistsException();
        } catch ( ParameterDoesntExistsException ex ){
            ex.printStackTrace();
        }

        this.closeConnection();
        return _t;
    }

    /**
     * Get all templates
     * @return
     */
    @Override
    public ArrayList<Entity> getAll() throws MessageDoesntExistsException, ParameterDoesntExistsException {
        //Entity to Return
        ArrayList<Entity> _t  = new ArrayList<>();
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
     * @param templateId
     * @return
     */
    @Override
    public Campaign getCampaignByTemplate(int templateId) {

        Connection _conn = this.getBdConnect();
        Campaign campaign = new Campaign();
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = _conn.prepareCall( GET__CAMPAIGN_BY_TEMPLATE );
            preparedStatement.setInt(1, templateId );
            ResultSet _rs = preparedStatement.executeQuery();

            if( _rs.next() ){

                //instanciando el api de campana
                DAOCampaign daoCampaign = DAOFactory.instanciateDaoCampaign();
                CampaignDAO campaignsService = new CampaignDAO();
                Campaign c = EntityFactory.CreateCampaignId(_rs.getInt("tem_campaign_id"));
                //obtener objeto campana con el id de campana del query anterior
                campaign = daoCampaign.campaignById(c);

            }

        } catch (SQLException e){
            e.printStackTrace();
            throw new TemplateDoesntExistsException
                    ("Error: la plantilla " + templateId + " no existe", e, templateId);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.closeConnection();
            return campaign;
        }
    }


    @Override
    public ArrayList<Template> getTemplatesByCampaign(int userId, int companyId) {
        ArrayList<Template> templateArrayList = new ArrayList<>();
        ArrayList<Campaign> campaignArrayList = new ArrayList<>();
        Connection _conn = this.getBdConnect();
        try{
            campaignArrayList = this.getCampaignsByUserOrCompany(userId,companyId);
            for(int x = 0; x < campaignArrayList.size(); x++){
                PreparedStatement preparedStatement = _conn.prepareCall(GET_ALL_TEMPLATES_BY_CAMPAIGN);
                preparedStatement.setInt(1,campaignArrayList.get(x).get_idCampaign());
                ResultSet _rs = preparedStatement.executeQuery();
                while(_rs.next()){
                    Template template = (Template) this.createTemplate(_rs);
                    templateArrayList.add(template);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeConnection();
            return templateArrayList;
        }
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
                ArrayList<Entity> integrators = new ArrayList<>();
                DAOIntegrator integratorDAO = DAOFactory.instanciateDaoIntegrator();
                Integrator integrator = (Integrator) integratorDAO.getConcreteIntegrator(
                        resultSet.getInt("ci_integrator_id")
                );

                integrators.add(integrator);
                Channel channel = EntityFactory.createChannel(
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
     * Store Channel and Integrator NEED TO CHANGE THIS
     * @param channelIntegratorList
     * @param templateId
     */
    private void postChannelIntegrator(JsonArray channelIntegratorList, int templateId) {
        JsonObject channelIntegrator;
        int channel;
        int integrator;
        Connection _conn = this.getBdConnect();
        PreparedStatement _ps = null;
        try {
            for (JsonElement list : channelIntegratorList){
                channelIntegrator = list.getAsJsonObject();
                channel = channelIntegrator.get("channel").getAsJsonObject().get("idChannel").getAsInt();
                integrator = channelIntegrator.get("integrator").getAsJsonObject().get("idIntegrator").getAsInt();
                _ps = _conn.prepareCall(CREATE_CHANNEL_INTEGRATOR);
                _ps.setInt(1,templateId);
                _ps.setInt(2,channel);
                _ps.setInt(3,integrator);

                _ps.execute();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    /**
     * This method is responsible for modifying a specific template.
     * @param json string json with information
     * @return If the template was saved successfully it returns true,
     * otherwise it returns false.
     */
    public boolean updateTemplateData(String json){
        try {
            Gson gson = new Gson();
            //recibimos el objeto json
            JsonParser parser = new JsonParser();
            JsonObject gsonObj = parser.parse(json).getAsJsonObject();
            updateTemplate(gsonObj.get("campaign").getAsInt(), gsonObj.get("applicationId").getAsInt(), gsonObj.get("templateId").getAsInt());

            //insertamos los nuevos parametros
            String[] parameters = gson.fromJson(gsonObj.get("newParameters").getAsJsonArray(),String[].class);
            DAOFactory.instaciateDaoParameter().postParameter(parameters,gsonObj.get("company").getAsInt());
            //update de mensaje
            parameters = gson.fromJson(gsonObj.get("parameters").getAsJsonArray(),String[].class);
            String message = gsonObj.get("message").getAsString();
            DAOFactory.instaciateDaoMessage().updateMessage(gsonObj.get("message").getAsString(),gsonObj.get("templateId").getAsInt(),parameters,gsonObj.get("company").getAsInt());

            //update de Channel Integrator
            JsonArray channelIntegrator = gsonObj.get("channel_integrator").getAsJsonArray();
            updateChannelIntegrator(channelIntegrator,gsonObj.get("templateId").getAsInt());
            //planning
            String[] planning = gson.fromJson(gsonObj.get("planning").getAsJsonArray(),String[].class);
            PlanningHandler.updatePlanning(planning,gsonObj.get("templateId").getAsInt());

            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    /*@Override
    public boolean updateTemplateData(Entity e) {
        Template _t = (Template) e;
        DAOParameter _daoParameter = DAOFactory.instaciateDaoParameter();
        DAOMessage _daoMessage = DAOFactory.instaciateDaoMessage();
        DAOPlanning _daoPlanning = DAOFactory.instaciateDaoPlanning();

        try {
            updateTemplate(_t.getCampaign().get_id(), _t.getApplication().get_idApplication(), _t.getUser().get_id());

            //insertamos los nuevos parametros
            _daoParameter.postParameter( _t.getMessage().getParameterArrayList(), _t.getCompanyId() ); //Este codigo no va aqui

            //update de mensaje
            _daoMessage.updateMessage(_t.getMessage(), _t.getCompanyId(), _t.get_id());

            //update de Channel Integrator
            //JsonArray channelIntegrator = gsonObj.get("channel_integrator").getAsJsonArray();
            //updateChannelIntegrator(channelIntegrator,gsonObj.get("templateId").getAsInt());

            //planning
            _daoPlanning.updatePlanning( _t.getPlanning(), _t.get_id());


        } catch (Exception ex){
            System.out.println(ex);
            return false;
        }

        this.closeConnection();
        return true;
    }*/

    @Override
    public void updateTemplate(int campaignId, int applicationId, int templateId ) {
        Connection _conn = this.getBdConnect();

        PreparedStatement preparedStatement = null;

        try {
            //Buscar manera de quitar este IF
            if( applicationId > 0){
                preparedStatement = _conn.prepareCall( UPDATE_TEMPLATE_WITH_APP );
                preparedStatement.setInt(1, campaignId );
                preparedStatement.setInt(2, applicationId );
                preparedStatement.setInt(3, templateId );
            }
            else{
                preparedStatement = _conn.prepareCall( UPDATE_TEMPLATE_WITHOUT_APP );
                preparedStatement.setInt(1, campaignId );
                preparedStatement.setInt(2, templateId );
            }

            preparedStatement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        this.closeConnection();
    }

    /**
     * Update Channel and integrator, NEED TO CHANGE THIS
     * @param channelIntegratorList
     * @param templateId
     */
    private void updateChannelIntegrator(JsonArray channelIntegratorList, int templateId) {
        Connection _conn = this.getBdConnect();
        PreparedStatement _ps = null;
        try{
            _ps = _conn.prepareCall(DELETE_CHANNEL_INTEGRATOR);
            _ps.setInt(1,templateId);
            _ps.execute();
            postChannelIntegrator(channelIntegratorList,templateId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    /**
     * Delete Template
     * @param id
     */
    @Override
    public void deleteTemplate(int id){
        try{
            Connection _conn = this.getBdConnect();
            PreparedStatement _ps = _conn.prepareCall(DELETE);
            _ps.setInt(1,id);
            _ps.execute();
        }catch( SQLException ex ){
            ex.printStackTrace();
        }
    }

    /**
     * Private function for creating Template Entity out of ResultSet
     * @param _rs
     * @return
     */
    private Entity createTemplate(ResultSet _rs) throws ParameterDoesntExistsException, MessageDoesntExistsException{
        Entity _t = null;
        try{
            int templateId = _rs.getInt("tem_id");

            //Campaign
            Campaign _campaign = this.getCampaignByTemplate( templateId );

            //application
            Application _app = this.getApplicationByTemplate( templateId );

            //Message
            Message _message = ( Message )DAOFactory.instaciateDaoMessage().getMessage( templateId );

            //user
            DAOUser _userDao = DAOFactory.instanciateDaoUser();
            User _user = _userDao.findByUsernameId( _rs.getInt("tem_user_id") );

            //Channel
            ArrayList<Channel> _channels = this.getChannelsByTemplate( templateId );

            //Planning
            Planning _planning = (Planning) DAOFactory.instaciateDaoPlanning().getPlanning( templateId );

            //Status
            Status _status = Status.createStatus(_rs.getInt("tem_id"),
                    _rs.getString("sta_name"));

             _t = EntityFactory.CreateTemplate(
                    _rs.getInt("tem_id"),
                    _message,
                    _rs.getDate("tem_creation_date"),
                    _status,
                    _channels,
                    _campaign,
                    _app,
                    _user,
                    _planning
            );
        }catch( SQLException ex ){
            ex.printStackTrace();
        }catch (ParameterDoesntExistsException e) {
            throw new ParameterDoesntExistsException( e );
        }catch (MessageDoesntExistsException e){
            throw new MessageDoesntExistsException( e );
        }

        return _t;
    }
}
