package Logic.M01_Login;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M01_Login.IDAOUser;

import java.util.ArrayList;

public class GetAllUsersCommand extends Command {

    private static ArrayList<Entity> _usList;

    public GetAllUsersCommand( ) {

        _usList = new ArrayList<Entity>();

    }

    @Override
    public void execute() throws Exception {
        try {
            IDAOUser _dao = DAOFactory.instanciateDaoUser();
            _usList = _dao.getUsers();


        }catch(Exception exc) {

        }
    }

    @Override
    public Entity Return() {
        return null;
    }

    //@Override
    public ArrayList<Entity> ReturnList() { return _usList; }
}
