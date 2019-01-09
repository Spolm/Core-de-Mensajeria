package Logic.M01_Login;

import Logic.Command;
import Persistence.DAOFactory;
import Persistence.IDAO;
import Persistence.M01_Login.DAOUser;

public class ChangePasswordCommand extends Command {

    private static String _username;
    private static String _password;

    public ChangePasswordCommand(String username, String password) {
        _username = username;
        _password = password;
    }

    @Override
    public void execute() throws Exception {
        IDAO _dao = DAOFactory.instanciateDaoUser();
        ((DAOUser) _dao).changePassword(_username, _password);
    }

    @Override
    public Object Return() {
        return null;
    }
}
