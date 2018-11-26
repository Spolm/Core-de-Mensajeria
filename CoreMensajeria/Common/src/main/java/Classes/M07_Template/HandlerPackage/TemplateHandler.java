package Classes.M07_Template.HandlerPackage;

import Classes.M03_Campaign.Campaign;
//import webService.M03_CampaingManagement.M03_Campaigns;
import Classes.M04_Channel_Integrator.ChannelPackage.Channel;
import Classes.M04_Channel_Integrator.ChannelPackage.ChannelFactory;
import Classes.M04_Channel_Integrator.IntegratorPackage.Integrator;
import Classes.M04_Channel_Integrator.IntegratorPackage.IntegratorService;
import Classes.M06_DataOrigin.Application;
import Classes.M06_DataOrigin.ApplicationDAO;
import Classes.M07_Template.StatusPackage.Status;
import Classes.M07_Template.Template;
import Classes.Sql;
import Exceptions.CampaignDoesntExistsException;
import Exceptions.MessageDoesntExistsException;
import Exceptions.TemplateDoesntExistsException;

import java.sql.*;
import java.util.ArrayList;

public class TemplateHandler {
    private Sql sql;

    public TemplateHandler() {
        sql = new Sql();
    }

    public Sql getSql() {
        return sql;
    }

    public ArrayList<Template> getTemplates(){
        ArrayList<Template> templateArrayList = new ArrayList<>();
        try{
            ResultSet resultSet = sql.sqlConn("select t.tem_id, t.tem_creation_date, s.sta_id, s.sta_name\n" +
                    "from template t\n" +
                    "inner join template_status ts\n" +
                    "on ts.ts_template = t.tem_id\n" +
                    "\tinner join\n" +
                    "\t(\n" +
                    "\t\tselect ts_template, max(ts_id) maxID from template_status \n" +
                    "\t\tgroup by ts_template\n" +
                    "\t)ts_ on ts_.ts_template = ts.ts_template\n" +
                    "\t\tand ts.ts_id = ts_.maxID\n" +
                    "inner join status s\n" +
                    "on ts.ts_status = s.sta_id\n" +
                    "order by t.tem_id");
            while(resultSet.next()){
                Template template = new Template();
                template.setTemplateId(resultSet.getInt("tem_id"));
                template.setCreationDate(resultSet.getString("tem_creation_date"));
                Status status = Status.createStatus(resultSet.getInt("sta_id"),
                        resultSet.getString("sta_name"));
                template.setStatus(status);
                template.setChannels(getChannelsByTemplate(template.getTemplateId()));
                template.setCampaign(getCampaingByTemplate(template.getTemplateId()));
                template.setApplication(getApplicationByTemplate(template.getTemplateId()));
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

    public Template getTemplate(int id) {
        Template template = new Template();
        String query = "select tem_id,ts_id, tem_creation_date, sta_name\n" +
                "from template_status,template,status\n" +
                "where tem_id = " + id + " and tem_id = ts_template and sta_id = ts_status\n" +
                "order by ts_id desc limit 1";
        try {
            ResultSet resultSet = sql.sqlConn(query);
            if (resultSet.next()) {
                //asignamos los datos basicos del propio template
                template.setTemplateId(resultSet.getInt("tem_id"));
                template.setCreationDate(resultSet.getString("tem_creation_date"));

                //asignamos el mensae y status del template
                template.setMessage(MessageHandler.getMessage(template.getTemplateId()));
                template.setStatus(Status.createStatus(resultSet.getInt("ts_id"),
                        resultSet.getString("sta_name")));

                //asignamos canales, campaña y aplicacion
                template.setChannels(getChannelsByTemplate(template.getTemplateId()));
                template.setCampaign(getCampaingByTemplate(template.getTemplateId()));
                template.setApplication(getApplicationByTemplate(template.getTemplateId()));

                //usuario creador
            }

        } catch(SQLException e){
            e.printStackTrace();
            throw new TemplateDoesntExistsException
                    ("Error: la plantilla " + id + " no existe", e, id);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            Sql.bdClose(sql.getConn());
            return template;
        }
    }

    public ArrayList<Channel> getChannelsByTemplate(int templateId){
        ArrayList<Channel> channels = new ArrayList<>();
        Connection connection = Sql.getConInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("select tci.tci_template_id, ci.ci_channel_id, ci.ci_integrator_id, \n"
                                    + "c.cha_name, cha_description\n"
                                    + "from channel_integrator ci\n"
                                    + "inner join template_channel_integrator tci\n"
                                    + "on tci.tci_ci_id = ci.ci_id\n"
                                    + "inner join channel c\n"
                                    + "on c.cha_id = ci.ci_channel_id\n"
                                    + "where tci.tci_template_id = " + templateId + "\n"
                                    + "order by ci.ci_channel_id;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ArrayList<Integrator> integrators = new ArrayList<>();
                IntegratorService integratorService = IntegratorService.getInstance();
                Integrator integrator = integratorService.getConcreteIntegrator(
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
     *
     * @param templateId
     * @return campaign
     *
     * Retorna una campana que tiene asociada la plantilla con el id = templateId
     */
    public Campaign getCampaingByTemplate(int templateId){
        Campaign campaign = new Campaign();
        try{
            //query que obtiene el id de la campana que tiene asociada la plantilla
            ResultSet resultSet = sql.sqlConn(
                    "SELECT tem_campaign_id \n"
                            + "FROM Template\n"
                            + "WHERE tem_id = " + templateId + ";");
            //instanciando el api de campana
           /* M03_Campaigns campaignsService = new M03_Campaigns();
            //obtener objeto campana con el id de campana del query anterior
            campaign = campaignsService.getDetails
                    (resultSet.getInt("tem_campaign_id")); */
        } catch (SQLException e){
            e.printStackTrace();
            throw new TemplateDoesntExistsException
                    ("Error: la plantilla " + templateId + " no existe", e, templateId);
       /* } catch (CampaignDoesntExistsException e) {
            */
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
     *
     * @param templateId
     * @return application
     *
     * Retornar una aplicacion que tiene asociada la plantilla con el id = templateId
     */
    public Application getApplicationByTemplate(int templateId){
        Application application = new Application();
        try {
            //query que obtiene el id de la aplicacion que tiene asociada la plantilla
            ResultSet resultSet = sql.sqlConn(
                    "SELECT tem_application_id \n" +
                            "FROM Template" +
                            "WHERE tem_id = " + templateId + ";");
            //instanciado el api ApplicationDAO
            ApplicationDAO applicationService = new ApplicationDAO();
            //Obtener objeto aplicacion con el id de aplicacion del query anterior
            application = applicationService.getApplication
                    (resultSet.getInt("tem_application_id"));
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (sql.getConn() != null) {
                Sql.bdClose(sql.getConn());
            }
            return application;
        }
    }

    public Boolean postTemplateStatus(int id){
        Boolean flag=false;
        Connection con = Sql.getConInstance();
        String query="insert into public.template_status (ts_date,ts_template,ts_status) values (CURRENT_TIMESTAMP,"+id+",(select sta_id from public.status where sta_name='Aprobado'))";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            flag=true;
        }  catch(SQLException e){
            e.printStackTrace();
            con.rollback();
            flag=false;
        } catch (Exception e){
            e.printStackTrace();
            flag=false;
        } finally {
            if (con != null) {
                Sql.bdClose(con);
            }
            return flag;
        }
    }
}
