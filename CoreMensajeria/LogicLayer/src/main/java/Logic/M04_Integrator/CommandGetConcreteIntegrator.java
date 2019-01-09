package Logic.M04_Integrator;

import Entities.Entity;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.IntegratorNotFoundException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M04_Integrator.IDAOIntegrator;

import java.util.ArrayList;

public class CommandGetConcreteIntegrator extends Command {

    private static  Entity _entity;
    private static int _id;
    /**
     *
     */
    public CommandGetConcreteIntegrator(int id) {
        this._id = id;
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
