package Logic.M04_Integrator;

import Entities.Entity;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.M04_Integrator.IntegratorNotFoundException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M04_Integrator.IDAOIntegrator;

public class CommandGetConcreteIntegrator extends Command {
    private Entity _entity;
    private int _id;

    public CommandGetConcreteIntegrator(int id) {
        this._id = id;
        this._entity = null;
    }

    @Override
    public void execute() throws IntegratorNotFoundException, DatabaseConnectionProblemException {
        IDAOIntegrator dao = DAOFactory.instanciateDaoIntegrator();
        _entity = dao.getConcreteIntegrator(_id);
    }

    @Override
    public Entity Return() {
        return _entity;
    }
}
