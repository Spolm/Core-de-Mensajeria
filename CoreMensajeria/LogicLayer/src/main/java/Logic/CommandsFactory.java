package Logic;

import Entities.Entity;
import Entities.M08_Validation.XMLManagement.CommandGetMessage;
import Entities.M08_Validation.XMLManagement.CommandGetParameter;
import Entities.M08_Validation.XMLManagement.CommandGetTagValue;
import Entities.M08_Validation.XMLManagement.CommandProcessXML;
import Logic.M01_Login.GetUser;
import Logic.M08_SendMessage.ScheduleMessage;
import Logic.M08_SendMessage.SendMessage;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class CommandsFactory {

    public static Command instanciateGetUser ( Entity user) {
        return new GetUser(user);
    }

    public static Command createSendMessage() { return new SendMessage(); }

    public static Command createScheduleMessage() { return new ScheduleMessage(); }
}
