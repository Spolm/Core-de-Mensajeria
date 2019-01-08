package Logic.M08_SendMessage;

import Entities.Entity;
import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Logic.Command;

public class CommandSendMessage extends Command {

    private VerifiedParameter _verifiedParameter;

    public CommandSendMessage(VerifiedParameter verifiedParameter) {
        _verifiedParameter = verifiedParameter;
    }

    public void execute() {
        System.out.println("Debería enviar el mensaje");
    }

    public Entity Return() {
        return null;
    }
}
