package Logic;

import Entities.Entity;
import Logic.M01_Login.AddUserCommand;
import Logic.M01_Login.GetUserCommand;

public class CommandsFactory {

    public static Command instanciateGetUser ( Entity user) {
        return new GetUserCommand(user);
    }

    public static Command instanciateAddUser ( Entity user) {
        return new AddUserCommand(user);
    }
}
