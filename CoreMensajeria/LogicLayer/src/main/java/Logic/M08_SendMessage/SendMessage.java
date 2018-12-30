package Logic.M08_SendMessage;

import Entities.Entity;
import Logic.Command;

public class SendMessage extends Command {

    public void execute() {
        System.out.println("Debería enviar el mensaje");
    }

    public Entity Return() {
        return null;
    }
}
