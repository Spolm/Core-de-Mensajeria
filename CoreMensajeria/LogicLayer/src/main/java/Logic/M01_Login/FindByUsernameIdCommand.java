package Logic.M01_Login;

import Entities.Entity;
import Entities.M01_Login.User;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.IDAO;
import Persistence.M01_Login.DAOUser;

public class FindByUsernameIdCommand extends Command {

    private static int _id;
    private static User _user;

    public FindByUsernameIdCommand(int id) {
        _id = id;
    }

    @Override
    public void execute() throws Exception {
        IDAO _dao = DAOFactory.instanciateDaoUser();
        _user = ((DAOUser) _dao).findByUsernameId(_id);
    }

    @Override
    public Entity Return() {
        return _user;
    }
}
