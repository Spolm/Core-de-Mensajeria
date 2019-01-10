package Logic.M01_Login;

import Entities.Entity;
import Entities.M01_Login.LoginIntent;
import Entities.M01_Login.User;
import Logic.Command;
import Persistence.DAO;
import Persistence.DAOFactory;
import Persistence.IDAO;
import Persistence.M01_Login.DAOUser;

public class LogUserCommand extends Command {

    private static LoginIntent _log;
    private static User _use;


    public LogUserCommand( Entity _login  ) {

        this._log = (LoginIntent) _login;
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws Exception {
        try {
            IDAO _dao = DAOFactory.instanciateDaoUser();
            _use = ((DAOUser) _dao).logUser(_log.get_username(), _log.get_password());

        }

        catch ( Exception e ){
            e.printStackTrace();
        }
    }

    @Override
    public Entity Return() {
        return _use;
    }
}
