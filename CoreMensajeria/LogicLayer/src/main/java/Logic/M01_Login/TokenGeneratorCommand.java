package Logic.M01_Login;

import Entities.Entity;
import Logic.Command;
import Persistence.DAO;
import Persistence.DAOFactory;
import Persistence.M01_Login.DAOUser;
import Persistence.M01_Login.IDAOUser;

public class TokenGeneratorCommand extends Command {

    private static String _hash;
    private static String _email;

    public TokenGeneratorCommand (String email) {
        this._email = email;
    }

    @Override
    public void execute() throws Exception {
        IDAOUser _dao = DAOFactory.instanciateDaoUser();
         _hash = ((DAOUser) _dao).tokenGenerator(_email);
    }

    @Override
    public Entity Return() {
        return null;
    }

    public String returnString() {
        return _hash;
    }
}
