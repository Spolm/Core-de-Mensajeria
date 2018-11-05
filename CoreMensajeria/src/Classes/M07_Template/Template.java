package Classes.M07_Template;

import Classes.M07_Template.MessagePackage.Message;
import Classes.M07_Template.PlanningPackage.Planning;
import Classes.M07_Template.StatusPackage.IStatus;

public class Template {
    private Message _message;
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

    public Template(Message message, IStatus status) {
        this._message = message;
        //   this._planning = planning;
        this._status = status;
    }



    public String getStatus() {
        return _status.getIStatusTemplate();
    }

    public void setStatus(IStatus status) { this._status = status; }

    public String getMessage(){ return _message.getMessage(); }

    public void set_message(Message _message) { this._message = _message; }

    // public Planning get_planning() { return _planning; }
}

