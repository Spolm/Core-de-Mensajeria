package Classes.M07_Template.HandlerPackage;

import Classes.M01_Login.Privilege;
import Classes.M01_Login.UserDAO;
import Classes.M03_Campaign.Campaign;
import Classes.M03_Campaign.CampaignDAO;
import Classes.M04_Integrator.IntegratorDAO;
import Classes.M06_DataOrigin.Application;
import Classes.M06_DataOrigin.ApplicationDAO;
import Classes.M07_Template.StatusPackage.Status;
import Classes.M07_Template.Template;
import Classes.Sql;
import Exceptions.ApplicationNotFoundException;
import Exceptions.CampaignDoesntExistsException;
import Exceptions.MessageDoesntExistsException;
import Classes.M05_Channel.Channel;
import Classes.M05_Channel.ChannelFactory;
import Classes.M04_Integrator.Integrator;
import Classes.Sql;
import Exceptions.ParameterDoesntExistsException;
import Exceptions.TemplateDoesntExistsException;
import com.google.gson.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * TemplateHanlder class used for the management of information
 * search in the database with respect to templates.
 */
public class TemplateHandler {
    /**
     * connection to the database
     */
    private Sql sql;

    private static final String GET_CAMAPIGN_BY_ID =
            "select* from public.campaign where cam_id = ? ";


    /**
     * this method returns all the templates filtering by the company to which
     * a user belongs.
     * @param userId user id
     * @param companyId company id
     * @return ArrayList of templates
     */
    public ArrayList<Template> getTemplates(int userId,int companyId){
        ArrayList<Template> templateArrayList = new ArrayList<>();
        ArrayList<Campaign> campaignArrayList = null;
        Connection connection = Sql.getConInstance();
        UserDAO userDAO = new UserDAO();
        try{
            campaignArrayList = getCampaignsByUserOrCompany(userId,companyId);
            for(int x = 0; x < campaignArrayList.size(); x++){
                PreparedStatement preparedStatement = connection.prepareCall("{call m07_select_templates_by_campaign(?)}");
                preparedStatement.setInt(1,campaignArrayList.get(x).get_idCampaign());
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    Template template = new Template();
                    template.setTemplateId(resultSet.getInt("tem_id"));
                    template.setCreationDate(resultSet.getString("tem_creation_date"));
                    Status status = Status.createStatus(resultSet.getInt("sta_id"),
                            resultSet.getString("sta_name"));
                    template.setStatus(status);
                    template.setChannels(getChannelsByTemplate(template.getTemplateId()));
                    template.setCampaign(campaignArrayList.get(x));
                    template.setApplication(getApplicationByTemplate(template.getTemplateId()));
                    template.setUser(userDAO.findByUsernameId(resultSet.getInt("tem_user_id")));
                    template.setMessage(MessageHandler.getMessage(template.getTemplateId()));
                    template.setPlanning(PlanningHandler.getPlanning(template.getTemplateId()));
                    templateArrayList.add(template);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Sql.bdClose(connection);
            return templateArrayList;
        }
    }

    /**
     * this method returns all templates without any type of filtering.
     * @return ArrayList of templates
     */
    public ArrayList<Template> getTemplates(){
        ArrayList<Template> templateArrayList = new ArrayList<>();
        Connection connection = Sql.getConInstance();
        try{
            PreparedStatement preparedStatement = connection.prepareCall("{call m07_select_all_templates()}");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Template template = new Template();
                template.setTemplateId(resultSet.getInt("tem_id"));
                template.setCreationDate(resultSet.getString("tem_creation_date"));
                Status status = Status.createStatus(resultSet.getInt("sta_id"),
                        resultSet.getString("sta_name"));
                template.setStatus(status);
                template.setChannels(getChannelsByTemplate(template.getTemplateId()));
                template.setCampaign(getCampaingByTemplate(template.getTemplateId()));
                ApplicationDAO applicationService = new ApplicationDAO();
                template.setApplication(applicationService.getApplication
                        (template.getTemplateId()));
                template.setPlanning(PlanningHandler.getPlanning(template.getTemplateId()));
                templateArrayList.add(template);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Sql.bdClose(sql.getConn());
            return templateArrayList;
        }
    }

    /**
     * this method returns a template filtering
     * by the id of the template that is desired.
     * @param id template id
     * @return template
     * @throws TemplateDoesntExistsException
     */
    public Template getTemplate(int id) throws TemplateDoesntExistsException{
        CampaignDAO campaignsService = new CampaignDAO();
        Template template = new Template();
        String query = "select tem_id,ts_id,tem_user_id,tem_application_id,tem_campaign_id, tem_creation_date, sta_name\n" +
                "from template_status,template,status\n" +
                "where tem_id = " + id + " and tem_id = ts_template and sta_id = ts_status\n" +
                "order by ts_id desc limit 1";
        try {
            ResultSet resultSet = sql.sqlConn(query);
            if (resultSet.next()) {
                //asignamos los datos basicos del propio template
                template.setTemplateId(resultSet.getInt("tem_id"));
                template.setCreationDate(resultSet.getString("tem_creation_date"));
                //asignamos el mensaje y status del template
                template.setMessage(MessageHandler.getMessage(template.getTemplateId()));
                template.setStatus(Status.createStatus(resultSet.getInt("ts_id"),
                        resultSet.getString("sta_name")));
                //asignamos canales, campaña y aplicacion
                template.setChannels(getChannelsByTemplate(template.getTemplateId()));
                template.setCampaign(campaignsService.getDetails(resultSet.getInt("tem_campaign_id")));

                UserDAO userDAO = new UserDAO();
                template.setUser(userDAO.findByUsernameId(resultSet.getInt("tem_user_id")));
                //template.setCampaign(getCampaignsById(resultSet.getInt("tem_campaign_id")));
                template.setApplication(getApplicationByTemplate(template.getTemplateId()));
                template.setPlanning(PlanningHandler.getPlanning(template.getTemplateId()));
            }

        }catch (ParameterDoesntExistsException e) {
            //logg
        }catch (MessageDoesntExistsException e){
            //logg
        } catch(SQLException e){
            throw new TemplateDoesntExistsException
                    ("Error: la plantilla " + id + " no existe", e, id);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            Sql.bdClose(sql.getConn());
            return template;
        }
    }

    public Campaign getCampaignsById(int campaignId){
        Campaign campaign =  new Campaign();
        Connection connection = Sql.getConInstance();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CAMAPIGN_BY_ID);
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
            Sql.bdClose(connection);
            return campaign;
        }
    }

    /**
     * This method returns the campaigns filtering
     * by the company to which the user belongs.
     * @param userId user id
     * @param companyId company id
     * @return ArrayList of campaings
     */
    public ArrayList<Campaign> getCampaignsByUserOrCompany(int userId, int companyId){
        ArrayList<Campaign> campaignArrayList = new ArrayList<>();
        Connection connection = Sql.getConInstance();
        try{
            PreparedStatement preparedStatement = connection.prepareCall("{call m07_select_campaign_by_user_company(?,?,?)}");
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
            Sql.bdClose(connection);
            return campaignArrayList;
        }
    }

    /**
     * This method returns the channels and integrators associated
     * with a specific template.
     * @param templateId template id
     * @return ArrayList of channels
     */
    public ArrayList<Channel> getChannelsByTemplate(int templateId){
        ArrayList<Channel> channels = new ArrayList<>();
        Connection connection = Sql.getConInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareCall("{call m07_select_channels_by_template(?)}");
            preparedStatement.setInt(1,templateId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
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
            Sql.bdClose(connection);
            return channels;
        }
    }

    /**
     * This method returns the campaign that is associated
     * with a template in specifies.
     * @param templateId template id
     * @return campaign
     */
    public Campaign getCampaingByTemplate(int templateId){
        Campaign campaign = new Campaign();
        try{
            //query que obtiene el id de la campana que tiene asociada la plantilla
            ResultSet resultSet = sql.sqlConn(
                    "SELECT tem_campaign_id\n"
                            + "FROM Template\n"
                            + "WHERE tem_id = " + templateId + ";");
            //instanciando el api de campana
            CampaignDAO campaignsService = new CampaignDAO();
            //obtener objeto campana con el id de campana del query anterior
            campaign = campaignsService.getDetails
                    (resultSet.getInt("tem_campaign_id"));
        } catch (SQLException e){
            e.printStackTrace();
            throw new TemplateDoesntExistsException
                    ("Error: la plantilla " + templateId + " no existe", e, templateId);
        } catch (CampaignDoesntExistsException e) {
            //logg
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (sql.getConn() != null) {
                Sql.bdClose(sql.getConn());
            }
            return campaign;
        }
    }

    /**
     * This method returns the origin by application that is associated
     * with a template in specifies.
     * @param templateId template id
     * @return application
     */
    public Application getApplicationByTemplate(int templateId){
        Application application = new Application();
        Connection connection = Sql.getConInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareCall("{call m07_select_applicantion_by_template(?)}");
            preparedStatement.setInt(1,templateId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            ApplicationDAO applicationService = new ApplicationDAO();
            application = applicationService.getApplication
                    (resultSet.getInt("applicationId"));
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            Sql.bdClose(connection);
            return application;
        }
    }

    /**
     * This method returns the privileges that a specific user has in a
     * specific company.
     * @param userId user id
     * @param companyId company id
     * @return ArrayList of privileges
     */
    public ArrayList<Privilege> getTemplatePrivilegesByUser(int userId, int companyId){
        ArrayList<Privilege> privileges = new ArrayList<>();
        Connection connection = Sql.getConInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareCall("{call m07_select_privileges_by_user_company(?,?)}");
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
        return privileges;
    }

    /**
     * This method is responsible for saving a template in specific.
     * @param json string json with information
     * @return If the template was saved successfully it returns true,
     * otherwise it returns false.
     */
    public boolean postTemplateData(String json){
        try {
            Gson gson = new Gson();
            //recibimos el objeto json
            JsonParser parser = new JsonParser();
            JsonObject gsonObj = parser.parse(json).getAsJsonObject();
            //hay que extraer campaña y aplicacion, parametros por defecto
            //se crea el template y se retorna su id
            int templateId = postTemplate(gsonObj.get("campaign").getAsInt(),gsonObj.get("applicationId").getAsInt(), gsonObj.get("userId").getAsInt());
            //se establece el template  como no aprobado
            StatusHandler.postTemplateStatusNoAprovado(templateId);
            //insertamos los nuevos parametros
            String[] parameters = gson.fromJson(gsonObj.get("newParameters").getAsJsonArray(),String[].class);
            ParameterHandler.postParameter(parameters,gsonObj.get("company").getAsInt());
            //obtenemos el valor del mensaje,y parametros
            parameters = gson.fromJson(gsonObj.get("parameters").getAsJsonArray(),String[].class);
            String message = gsonObj.get("messagge").getAsString();
            MessageHandler.postMessage(message,templateId,parameters,gsonObj.get("company").getAsInt());

            //obtenemos los valores de los canales e integradores
            JsonArray channelIntegrator = gsonObj.get("channel_integrator").getAsJsonArray();
            postChannelIntegrator(channelIntegrator,templateId);

            //planning
            String[] planning = gson.fromJson(gsonObj.get("planning").getAsJsonArray(),String[].class);
            PlanningHandler.postPlanning(planning,templateId);

            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    private void postChannelIntegrator(JsonArray channelIntegratorList,int templateId) {
        String query= "";
        JsonObject channelIntegrator;
        int channel;
        int integrator;
        sql = new Sql();
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

    public int postTemplate(int campaignId,int applicationId, int userId){
        String query = "";
        if (applicationId != 0) {
            query = "INSERT INTO public.Template (tem_creation_date, tem_campaign_id, tem_application_id, tem_user_id) \n" +
                    "VALUES (CURRENT_DATE," + campaignId + "," + applicationId + "," + userId + ") RETURNING tem_id";
        } else {
            query = "INSERT INTO public.Template (tem_creation_date, tem_campaign_id, tem_user_id) \n" +
                    "VALUES (CURRENT_DATE," + campaignId + "," + userId + ") RETURNING tem_id";
        }
        int templateId=0;
        try{
            ResultSet resultSet = sql.sqlConn(query);
            if (resultSet.next())
                templateId=resultSet.getInt("tem_id");

        }catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            Sql.bdClose(sql.getConn());
            return templateId;
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
            ParameterHandler.postParameter(parameters,gsonObj.get("company").getAsInt());
            //update de mensaje
            parameters = gson.fromJson(gsonObj.get("parameters").getAsJsonArray(),String[].class);
            String message = gsonObj.get("messagge").getAsString();
            MessageHandler.updateMessage(gsonObj.get("messagge").getAsString(),gsonObj.get("templateId").getAsInt(),parameters,gsonObj.get("company").getAsInt());

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

    private void updateChannelIntegrator(JsonArray channelIntegratorList, int templateId) {
        String query = "DELETE FROM public.template_channel_integrator\n" +
                "WHERE tci_template_id = " + templateId;
        try{
            sql.sqlNoReturn(query);
            postChannelIntegrator(channelIntegratorList,templateId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            Sql.bdClose(sql.getConn());
        }
    }

    private void updateTemplate(int campaign, int applicationId, int templateId) {
        String query = "";
        if (applicationId > 0) {
            query = "UPDATE public.template\n" +
                    "SET tem_campaign_id = " + campaign + ", tem_application_id = " + applicationId + "\n" +
                    " WHERE tem_id = " + templateId;
        } else {
            query = "UPDATE public.template\n" +
                    "SET tem_campaign_id = " + campaign + ", tem_application_id = null " +
                    "WHERE tem_id = " + templateId;
        }
        try{
            sql.sqlConn(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * constructor without receiving parameters that instantiates
     * the sql attribute of the ParameterHandler class.
     */
    public TemplateHandler() {
        sql = new Sql();
    }

    /**
     * get of the connection of the database
     * @return
     */
    public Sql getSql() {
        return sql;
    }
}
