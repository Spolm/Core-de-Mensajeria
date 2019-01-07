package Logic.M01_Login;

import Entities.Entity;
import Entities.M01_Login.User;
import Logic.Command;
import Persistence.DAO;
import Persistence.DAOFactory;
import Persistence.M01_Login.DAOUser;

public class LogUserCommand extends Command {

    private static User _us;



    public LogUserCommand( Entity _user  ) {

        this._us = (User) _user;
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws Exception {
        try {
            DAO _dao = DAOFactory.instanciateDaoUser();
            ((DAOUser) _dao).logUser(_us.get_usernameUser(), _us.get_passwordUser());
        }

        catch ( Exception e ){
            e.printStackTrace();
        }
    }

    @Override
    public Entity Return() {
        return null;
    }
}
