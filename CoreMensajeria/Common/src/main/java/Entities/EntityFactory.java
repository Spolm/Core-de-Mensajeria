package Entities;

import Entities.M01_Login.Privilege;
import Entities.M01_Login.User;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;
import Entities.M04_Integrator.*;
import Entities.M05_Channel.Channel;
import Entities.M05_Channel.ChannelEmail;
import Entities.M05_Channel.ChannelSms;
import Entities.M06_DataOrigin.Application;
import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.PlanningPackage.Planning;
import Entities.M07_Template.StatusPackage.ApprovedStatus;
import Entities.M07_Template.StatusPackage.NotApprovedStatus;
import Entities.M07_Template.StatusPackage.Status;
import Entities.M07_Template.Template;
import Entities.M08_Validation.SentMessage;
import Entities.M09_Statistics.Statistics;

import java.sql.Date;
import java.util.ArrayList;

public class EntityFactory {


    static public Entity CreateUser(int id, String _passwordUser, String _usernameUser, int _typeUser, String _emailUser, String _phoneUser, java.sql.Date _dayOfBirthUser, String _countryUser, String _addressUser, String _genreUser, String _cityUser ) {
        return new User(id, _passwordUser, _usernameUser, _typeUser, _emailUser, _phoneUser, _dayOfBirthUser, _countryUser, _addressUser, _genreUser, _cityUser);
    }

    /**
     * Fabrica para compañias sin id y sin link
     ** @param id nombre de la compañia
     * @return un objeto del tipo Company con los parametros name, desc, status
     */

    public static Company CreateCompanyOnlyId(int id ){
        return new Company(id);
    }


    /**
     * Fabrica para compañias sin id y sin link
     ** @param name nombre de la compañia
     * @param desc descripcion de la compañia
     * @param status el estatus de la compañia, puede ser activada o desactivada
     * @return un objeto del tipo Company con los parametros name, desc, status
     */

    public static Company CreateCompanyWithOutID( String name, String desc, boolean status ){
        return new Company( name, desc, status );
    }
    /**
     * Fabrica para compañias solo con id y estatus
     ** @param id nombre de la compañia
     * @param status el estatus de la compañia, puede ser activada o desactivada
     * @return un objeto del tipo Company con los parametros name, desc, status
     */

    public static Company CreateCompanyIDStatus( int id, boolean status ){
        return new Company( id, status );
    }

    /**
     * Fabrica para compañias sin link
     * @param id id de una Compañia
     * @param name nombre de la compañia
     * @param desc descripcion de la compañia
     * @param status el estatus de la compañia, puede ser activada o desactivada
     * @return un objeto del tipo Company con los parametros id, name, desc, status
     */
    public static Company CreateCompanyWithOutLink( int id, String name, String desc, boolean status ){
        return new Company( id, name, desc, status );
    }

    /**
     * Fabrica para compañias con todos los atributos
     * @param id id de una Compañia
     * @param name nombre de la compañia
     * @param desc descripcion de la compañia
     * @param status el estatus de la compañia, puede ser activada o desactivada
     * @param link el enlace de la compañia
     * @return un objeto del tipo Company con los parametros id, name, desc, status, link
     */
    public static Company CreateCompanyWithOutUserID( int id, String name, String desc,
                                                      boolean status, String link ){
        return new Company( id, name, desc, status, link );
    }

    /**
     * Fabrica para compañias con todos los atributos
     * @param id id de una Compañia
     * @param name nombre de la compañia
     * @param desc descripcion de la compañia
     * @param status el estatus de la compañia, puede ser activada o desactivada
     * @param link el enlace de la compañia
     * @param userId id del usuario
     * @return un objeto del tipo Company con los parametros id, name, desc, status, link, userid
     */
    public static Company CreateFullCompany( int id, String name, String desc,
                                             boolean status, String link, int userId ){
        return new Company( id, name, desc, status, link,userId );
    }
    /**
     * Fabrica para campañas sin id y sin Company
     * @param nameCampaign nombre para una campaña recien creada
     * @param descCampaign descripcion de la campaña
     * @param statusCampaign el estatus de la campaña, puede ser activada o desactivada
     * @param startCampaign fecha de inicio de la campaña
     * @param endCampaign fecha de finalización de la campaña
     * @return un objeto del tipo Campaign con los parametros nameCampaign, descCampaign, statusCampaign, startCampaign
     * endCampaign
     */
    public static Campaign CreateCampaignWithOut_ID_Company( String nameCampaign, String descCampaign,
                                                             boolean statusCampaign, java.util.Date startCampaign,
                                                             java.util.Date endCampaign ){

        return new Campaign( nameCampaign, descCampaign, statusCampaign, startCampaign, endCampaign );
    }

    /**
     * Fabrica para campañas sin Company
     * @param idCampaign id de una campaña
     * @param nameCampaign nombre para una campaña recien creada
     * @param descCampaign descripcion de la campaña
     * @param statusCampaign el estatus de la campaña, puede ser activada o desactivada
     * @param startCampaign fecha de inicio de la campaña
     * @param endCampaign fecha de finalización de la campaña
     * @return un objeto del tipo Campaign con los parametros idCampaign, nameCampaign, descCampaign, statusCampaign,
     * startCampaign, endCampaign
     */

    public static Campaign CreateCampaignWithOut_Company(int idCampaign, String nameCampaign, String descCampaign,
                                                         boolean statusCampaign, java.util.Date startCampaign, java.util.Date endCampaign ){

        return new Campaign( idCampaign, nameCampaign, descCampaign, statusCampaign, startCampaign, endCampaign );
    }

    /**
     * Fabrica para campañas solo con id
     ** @param id nombre de la campaña
     * @return un objeto del tipo Company con el parametro id
     */

    public static Campaign CreateCampaignId(int id ){
        return new Campaign( id );
    }


    /**
     * Fabrica para campañas con todos los atributos
     * @param idCampaign id de una campaña
     * @param nameCampaign nombre para una campaña recien creada
     * @param descCampaign descripcion de la campaña
     * @param statusCampaign el estatus de la campaña, puede ser activada o desactivada
     * @param startCampaign fecha de inicio de la campaña
     * @param endCampaign fecha de finalización de la campaña
     * @param idCompany id de una Company
     * @return un objeto del tipo Campaign con los parametros idCampaign, nameCampaign, descCampaign, statusCampaign,
     * startCampaign, endCampaign, company
     */

    public static Campaign CreateFullCampaign(int idCampaign, String nameCampaign, String descCampaign,
                                              boolean statusCampaign, java.util.Date startCampaign,
                                              java.util.Date endCampaign, int idCompany ){

        return new Campaign( idCampaign, nameCampaign, descCampaign, statusCampaign, startCampaign,
                endCampaign, idCompany );

    }


    /**
     * Fabrica para compañias solo con id e id usuario
     ** @param id  id de la compañia
     * @param user id del usuario
     * @return un objeto del tipo Company con los parametros name, desc, status
     */

    public static Company CreateCompanyIDCompUser( int id, int user ){
        return new Company( id, user );
    }

    /**
     * Fabrica para compañias solo con id y estatus
     ** @param idCampaign nombre de la campaña
     * @param statusCampaign el estatus de la compañia, puede ser activada o desactivada
     * @return un objeto del tipo Campaign con los parametros name, desc, status
     */

    public static Campaign CreateCampaignIDStatus( int idCampaign, boolean statusCampaign ){
        return new Campaign( idCampaign, statusCampaign );
    }

    /**
     * Fabrica para estadisticas sin atributos
     * @return un objeto del tipo Statistic sin datos
     */

    public static Statistics createStatistic(){ return new Statistics(); }

    /**
     * Fabrica para estadisticas
     * @param x una lista para el eje que indica el tipo de datos de la estadistica
     * @param y una lista para los valores correspondientes a cada dato de x
     * @return un objeto del tipo Statistic con sus valores fijados
     */

    public static Statistics createStatistic(ArrayList x, ArrayList<Integer> y){ return new Statistics(x,y); }

    static public Entity user() {
        return new User();
    }

    static public Entity privilege(int id,String _codePrivilege,String _actionPrivilege) {
        return new Privilege(id, _codePrivilege, _actionPrivilege);
    }

    static public Entity privilege() {
        return new Privilege();
    }

    // M07_Templates
    public static Parameter CreateParameter(int parameterId, String name, String description){
        return new Parameter(parameterId,name,description);
    }

    public static Message CreateMessage(int messageId, ArrayList<Parameter> parameterArrayList, String message){
        return new Message(messageId, parameterArrayList, message);
    }

    public static Planning CreatePlanning(Date startDate, Date endDate, String startTime, String endTime, int idPlanning){
        return new Planning(startDate, endDate, startTime, endTime, idPlanning);
    }

    public static ApprovedStatus CreateApprovedStatus(int statusId,String statusName){
        return new ApprovedStatus(statusId, statusName);
    }

    public static NotApprovedStatus CreateNotApprovedStatus(int statusId, String statusName){
        return new NotApprovedStatus(statusId, statusName);
    }

    public static Template CreateTemplate(int id, Message message, Date creationDate, Status status,
                                   ArrayList<Channel> channels, Campaign campaign, Application application,
                                   User user, Planning planning){
        return new Template(id, message, creationDate, status, channels, campaign, application, user, planning);
    }

// M04_region

    /**
     * Metodo que se encarga de crear un canal en concreto
     * La creacion de este tipo de canal dependerá del nameChannel
     * que es recibido por parametro
     *
     * @param idChannel          id del canal a crear
     * @param nameChannel        nombre del canal a crear
     * @param descriptionChannel Breve descripción del canal
     * @param integrators        Integradores que perteneces a esta canal
     * @return Un objeto Channel con las caracteristicas enviadas por parametro
     * @see Channel
     */
    public static Channel createChannel(int idChannel, String nameChannel, String descriptionChannel, ArrayList<Entity> integrators) {
        if (nameChannel == null)
            return null;
        if (nameChannel.equalsIgnoreCase("SMS"))
            return new ChannelSms(idChannel, nameChannel, descriptionChannel, integrators);
        else if (nameChannel.equalsIgnoreCase("EMAIL"))
            return new ChannelEmail(idChannel, nameChannel, descriptionChannel, integrators);
        return null;
    }

    /**
     * Metodo que se encarga de crear un canal en concreto
     * La creacion de este tipo de canal dependerá del nameChannel
     * que es recibido por parametro
     *
     * @param idChannel          id del canal a crear
     * @param nameChannel        nombre del canal a crear
     * @param descriptionChannel Breve descripción del canal
     * @return Un objeto Channel con las caracteristicas enviadas por parametro
     * @see Channel
     */
    public static Channel createChannel(int idChannel, String nameChannel, String descriptionChannel) {
        if (nameChannel == null)
            return null;
        if (nameChannel.equalsIgnoreCase("SMS"))
            return new ChannelSms(idChannel, nameChannel, descriptionChannel);
        else if (nameChannel.equalsIgnoreCase("EMAIL"))
            return new ChannelEmail(idChannel, nameChannel, descriptionChannel);
        return null;
    }

    /**
     * Método que se encarga de crear un integrador en concreto
     * La creación de este tipo de integrador dependera del integratorType
     * que es recibido por parametro
     *
     * @param integratorType el tipo de integrador, del cual dependera la creación del objeto
     * @param idIntegrator   el id del integrador a crear
     * @param nameIntegrator nombre del integrador
     * @param messageCost    costo por mensaje del integrador
     * @param threadCapacity capacidad de hilos que soportar el integrador
     * @param apiIntegrator  un código de seguridad que se requiere para validar que
     *                       el integrador sea el correcto
     * @param enabled        Que nos indica cual es el estado del integrador
     * @return Un objeto integrador con las caracteristicas enviadas por parametro
     * @see Integrator
     */

    public static Integrator CreateIntegrator(String integratorType, int idIntegrator, String nameIntegrator,
                                              float messageCost, int threadCapacity, String apiIntegrator, boolean enabled)
    {
        if (integratorType == null) {
            return null;
        }
        if (integratorType.equalsIgnoreCase("MOVISTAR")) {
            return new Movistar(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator, enabled);

        } else if (integratorType.equalsIgnoreCase("DIGITEL")) {
            return new Digitel(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator, enabled);

        } else if (integratorType.equalsIgnoreCase("MOVILNET")) {
            return new Movilnet(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator, enabled);

        } else if (integratorType.equalsIgnoreCase("MAILCHIMP")) {
            return new MailChimp(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator, enabled);

        } else if (integratorType.equalsIgnoreCase("AWEBER")) {
            return new Aweber(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator, enabled);

        } else if (integratorType.equalsIgnoreCase("INFUSIONSOFT")) {
            return new InfusionSoft(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator, enabled);
        }

        return null;
    }

//end M04_region

    //region M_08
    public static SentMessage createSendMessage() {
        return new SentMessage();
    }
}
