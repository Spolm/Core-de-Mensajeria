package Logic.M04_Integrator;

import Entities.Entity;
import Exceptions.DatabaseConnectionProblemException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M04_Integrator.IDAOIntegrator;

import java.util.ArrayList;

public class CommandGetAllIntegrator extends Command {
    private ArrayList<Entity> _entityList;

    public CommandGetAllIntegrator() {
        this._entityList = null;
    }

    @Override
    public void execute() throws DatabaseConnectionProblemException {
        IDAOIntegrator dao = DAOFactory.instanciateDaoIntegrator();
        _entityList = dao.listIntegrator();
    }

    @Override
    public Entity Return() {
        return null;
    }

    public ArrayList<Entity> returnList() { return _entityList; }
}
