package Classes.M07_Template;

import Classes.M07_Template.MessagePackage.Message;
import Classes.M07_Template.PlanningPackage.Planning;
import Classes.M07_Template.StatusPackage.IStatus;

public class Template {
    private Message _message;
    private String creationDate;
    private int templateId;
    // private Planning _planning;
    private IStatus _status;
    //private Campaign _campaing;
    //private Channel _channel;
    //private Integrator _integrator
    //Private Origin _origin
    //Private User _user

    /**
     * Constructor Vacio
     */
    public Template() {
    }

    public Template(Message message, IStatus status,String creationDate,int templateId) {
        this._message = message;
        //   this._planning = planning;
        this._status = status;
        this.creationDate = creationDate;
        this.templateId = templateId;
    }

    public int getTemplateId(){
        return templateId;
    }
    public void setTemplateId(int templateId){
        this.templateId = templateId;
    }

    public String getCreationDate(){
        return creationDate;
    }

    public void setCreationDate(String creationDate){
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return _status.getIStatusTemplate();
    }

    public void setStatus(IStatus status) { this._status = status; }

    public String getMessage(){ return _message.getMessage(); }

    public void setMessage(Message _message) { this._message = _message; }

    // public Planning get_planning() { return _planning; }
}

