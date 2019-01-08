package Logic.M01_Login;

import Entities.Entity;
import Entities.M01_Login.LoginIntent;
import Logic.Command;
import Persistence.DAO;
import Persistence.DAOFactory;
import Persistence.M01_Login.DAOUser;

public class IsBlockedUserCommand extends Command {

    private static LoginIntent _log;

    public IsBlockedUserCommand( Entity _login  ) {

        this._log = (LoginIntent) _login;
    }

    @Override
    public void execute() throws Exception {
        DAO _dao = DAOFactory.instanciateDaoUser();
        ((DAOUser) _dao).isBlockedUser(_log.get_username());
    }

    @Override
    public Object Return() {
        return _log;
    }
}
