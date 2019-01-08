package Logic.M04_Integrator;

import Entities.Entity;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.IntegratorNotFoundException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M04_Integrator.IDAOIntegrator;

public class CommandEnableIntegrator extends Command {

    private static  Entity _entity;
    private static  int _id;

    public CommandEnableIntegrator(int id) {
        this._id = id;
    }

    @Override
    public void execute() throws IntegratorNotFoundException, DatabaseConnectionProblemException {

            IDAOIntegrator _dao = DAOFactory.instanciateDaoIntegrator();
            _entity = _dao.enableIntegrator(_id);

    }

    @Override
    public Entity Return() {
        return _entity;
    }
}
