package Logic;

import Entities.Entity;
import Logic.M01_Login.GetUser;
import Logic.M08_SendMessage.SendMessage;

public class CommandsFactory {

    public static Command instanciateGetUser ( Entity user) {
        return new GetUser(user);
    }

    public static Command createSendMessage() { return new SendMessage(); }
}
