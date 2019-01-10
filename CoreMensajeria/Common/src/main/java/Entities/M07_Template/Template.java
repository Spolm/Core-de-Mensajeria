package Entities.M07_Template;

import Entities.Entity;
import Entities.M01_Login.User;
import Entities.M03_Campaign.Campaign;
import Entities.M06_DataOrigin.Application;
import Entities.M05_Channel.Channel;
import Entities.M07_Template.MessagePackage.*;
import Entities.M07_Template.PlanningPackage.Planning;
import Entities.M07_Template.StatusPackage.*;

import java.sql.Date;
import java.util.*;

/**
 * Clase Template guarda informacion que constituyen la plantilla
 */
public class Template extends Entity {
    /**
     * Mensaje de la plantilla (con los parametros)
     */
    private Message _message;
    /**
     * Dia de creacion de la plantilla
     */
    private Date _creationDate;
    /**
     * Estatus de la plantilla (aprobado o no aprobado)
     */
    private Status _status;
    /**
     * Canales (sms y/o email) de una plantilla(con el integrador)
     */
    private ArrayList<Channel> _channels;
    /**
     * Campana de la plantilla
     */
    private Campaign _campaign;
    /**
     * Origen de la aplicacion de la plantilla
     */
    private Application _application;
    /**
     * Usuario creador de la plantilla
     */
    private User _user;
    /**
     * Planficacion de la plantilla
     */
    private Planning _planning;
    /**
     * Id de la compania
     */
    private int _companyId;

    /**
     * Constructor vacio
     */
    public Template() {}

    /**
     * Constructor con solo el id de la plantilla
     * @param templateId template id
     */
    public Template(int templateId) {
        this.set_id(templateId);
    }

    /**
     * Constructor con mensaje,status,day of creation y id de la plantilla.
     * @param message mensaje de la plantilla
     * @param status estatus de la plantilla
     * @param creationDate dia de creacion de la plantilla
     * @param templateId id de la plantilla
     */
    public Template(Message message, Status status, Date creationDate, int templateId) {
        this._message = message;
        this._status = status;
        this._creationDate = creationDate;
        this.set_id(templateId);
    }

    /**
     * Constructor con  message, status, day of creation y id de la plantilla.
     * @param message mensaje de la plantilla
     * @param status estatus de la plantilla
     * @param creationDate dia de creacion de la plantilla
     * @param templateId id de la plantilla
     */
    public Template(Message message, Status status, String creationDate, int templateId) {
        this._message = message;
        this._status = status;
        this._creationDate = Date.valueOf(creationDate);
        this.set_id(templateId);
    }

    public Template(int id, Message message, Date creationDate, Status status, ArrayList<Channel> channels, Campaign campaign, Application application, User user, Planning planning) {
        this._message = message;
        this._creationDate = creationDate;
        this._status = status;
        this._channels = channels;
        this._campaign = campaign;
        this._application = application;
        this._user = user;
        this._planning = planning;
        this.set_id(id);
    }

    /**
     * Muestra el dia de creacion de la plantilla
     * @return date of creation
     */
    public Date getCreationDate(){
        return _creationDate;
    }

    /**
     * Anade dia de creacion de la plantilla
     * @param creationDate new date of creation
     */
    public void setCreationDate(Date creationDate){
        this._creationDate = creationDate;
    }

    /**
     * Muestra el estatus actual de la plantilla
     * @return status (approved or not approved)
     */
    public Status getStatus() {
        return _status;
    }

    /**
     * Anade o modifica el estatus de la plantilla
     * @param status new status
     */
    public void setStatus(Status status) { this._status = status; }

    /**
     * Muestra el mensaje de la plantilla con los parametros
     * @return mensaje y parametros de la plantilla
     */
    public Message getMessage(){ return _message; }

    /**
     * Anade o modifica mensaje y parametros de la plantilla
     * @param message new message and parameters
     */
    public void setMessage(Message message) { this._message = message; }

    /**
     * Muestra los canales (sms o/y email) con los integradores
     * @return channels and integrators of the template
     */
    public ArrayList<Channel> getChannels() {
        return _channels;
    }

    /**
     * Anade/modifica los canales (sms o/y email) y los integradores
     * @param channels new channels and integrators
     */
    public void setChannels(ArrayList<Channel> channels) {
        this._channels = channels;
    }

    public void addChannel(Channel channel){
        _channels.add(channel);
    }

    /**
     * Muestra la campana de la plantilla
     * @return campaign of the template
     */
    public Campaign getCampaign() {
        return _campaign;
    }

    /**
     * Anade/modifica la campana de la plantilla
     * @param campaign new campaign
     */
    public void setCampaign(Campaign campaign) {
        this._campaign = campaign;
    }

    /**
     * Muestra el origen de la aplicacion de la plantilla
     * @return origin of application of the template
     */
    public Application getApplication() {
        return _application;
    }

    /**
     * Anade/modifica el origen de la aplicacion de la plantilla
     * @param application new origin of application
     */
    public void setApplication(Application application) {
        this._application = application;
    }

    /**
     * Muestra el usuario creador de la plantilla
     * @return  user creator of the template
     */
    public User getUser() {
        return _user;
    }

    /**
     * Anade usuario creador de la plantilla
     * @param user  new user
     */
    public void setUser(User user) {
        this._user = user;
    }

    /**
     * Muestra la planificacion de la plantilla
     * @return planning of the template
     */
    public Planning getPlanning() {
        return _planning;
    }

    /**
     * Anade/modifica la planificacion de la plantilla
     * @param planning new planning
     */
    public void setPlanning(Planning planning) {
        this._planning = planning;
    }

    public int getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(int companyId) {
        this._companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Template)) return false;
        Template template = (Template) o;
        return get_id() == template.get_id() &&
                Objects.equals(_message, template._message) &&
                Objects.equals(getCreationDate(), template.getCreationDate()) &&
                Objects.equals(_status, template._status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_message, getCreationDate(), get_id(), _status);
    }

}

