package Logic.M04_Integrator;

import Entities.Entity;
import Exceptions.ChannelNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M04_Integrator.IDAOIntegrator;

import java.util.ArrayList;

public class CommandGetIntegratorByChannel extends Command {

    private static int _id;
    private static ArrayList< Entity > _entityList;

    public CommandGetIntegratorByChannel(int id) {
        _id = id;
        _entityList = new ArrayList<Entity>();
    }

    @Override
    public void execute()throws DatabaseConnectionProblemException,ChannelNotFoundException {

            IDAOIntegrator _dao =DAOFactory.instanciateDaoIntegrator();
            _entityList = _dao.listIntegratorByChannel(_id);

    }

    @Override
    public Entity Return() {
        return null;
    }
    public ArrayList<Entity> ReturnList() { return _entityList; }
}
