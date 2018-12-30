package Logic;

import Entities.Entity;
import Logic.M01_Login.GetUser;
import Logic.M02_Company.AddCompanyCommand;

public class CommandsFactory {

    public static Command instanciateGetUser ( Entity user) {
        return new GetUser(user);
    }

    public static AddCompanyCommand createAddCompanyCommand( Entity _co ){ return new AddCompanyCommand( _co ); }



}
