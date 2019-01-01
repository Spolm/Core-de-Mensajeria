package Logic;

import Entities.Entity;
import Logic.M01_Login.AddUserCommand;
import Logic.M01_Login.GetUserCommand;
import Logic.M01_Login.GetUser;
import Logic.M02_Company.AddCompanyCommand;

public class CommandsFactory {

    public static Command instanciateGetUser ( Entity user) {
        return new GetUserCommand(user);
    }

    public static Command instanciateAddUser ( Entity user) {
        return new AddUserCommand(user);
    }

    public static AddCompanyCommand createAddCompanyCommand( Entity _co ){ return new AddCompanyCommand( _co ); }



}
