package Logic.M01_Login;

import Logic.Command;
import Persistence.DAOFactory;
import Persistence.IDAO;
import Persistence.M01_Login.DAOUser;

public class FindByUsernameIdCommand extends Command {

    private static int _id;

    public FindByUsernameIdCommand(int id) {
        _id = id;
    }

    @Override
    public void execute() throws Exception {
        IDAO _dao = DAOFactory.instanciateDaoUser();
        ((DAOUser) _dao).findByUsernameId(_id);
    }

    @Override
    public Object Return() {
        return _id;
    }
}
