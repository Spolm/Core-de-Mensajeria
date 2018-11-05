package Classes.M07_Template;

import Classes.M07_Template.MessagePackage.Message;
import Classes.M07_Template.PlanningPackage.Planning;
import Classes.M07_Template.StatusPackage.IStatus;
import Classes.M07_Template.StatusPackage.IStatus;

public class Template {
    private Message message;
    private Planning planning;
    private IStatus status;
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

    public Template(Message message, Planning planning, IStatus status) {
        this.message = message;
        this.planning = planning;
        this.status = status;
    }

    public String getStatus() {
        return status.getIStatusTemplate();
    }

    public void setStatus(IStatus status) {

        this.status = status;
    }
}

