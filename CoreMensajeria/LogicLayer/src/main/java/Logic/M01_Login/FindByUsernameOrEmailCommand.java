package Logic.M01_Login;

import Entities.Entity;
import Entities.M01_Login.User;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.IDAO;
import Persistence.M01_Login.DAOUser;
import Persistence.M01_Login.IDAOUser;

public class FindByUsernameOrEmailCommand extends Command {

    private static String _username;
    private static User _use;

    public FindByUsernameOrEmailCommand (String username) {
        _username = username;
    }

    @Override
    public void execute() throws Exception {
       try {
           IDAO _dao = DAOFactory.instanciateDaoUser();
           _use = ((DAOUser) _dao).findByUsernameOrEmail(_username);
       }
       catch (Exception e) {
           throw e;
       }
    }

    @Override
    public Entity Return() {
        return _use;
    }
}
