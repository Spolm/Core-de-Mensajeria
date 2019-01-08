package Logic.M04_Integrator;

import Entities.Entity;
import Exceptions.DatabaseConnectionProblemException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M04_Integrator.IDAOIntegrator;

import java.util.ArrayList;

public class CommandGetAllIntegrator extends Command {

    private static ArrayList< Entity > _entityList;

    /**
     *
     */
    public CommandGetAllIntegrator() {

        _entityList = new ArrayList<Entity>();
    }

    @Override
    public void execute() throws DatabaseConnectionProblemException {

        IDAOIntegrator _dao = DAOFactory.instanciateDaoIntegrator();
        _entityList = _dao.listIntegrator();

    }

    @Override
    public Entity Return() {
        return null;
    }

    public ArrayList<Entity> ReturnList() { return _entityList; }
}
