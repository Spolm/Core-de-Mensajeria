package Logic;

import Entities.Entity;
import Logic.M01_Login.GetUser;

public class CommandsFactory {

    public static Command instanciateGetUser ( Entity user) {
        return new GetUser(user);
    }
}
