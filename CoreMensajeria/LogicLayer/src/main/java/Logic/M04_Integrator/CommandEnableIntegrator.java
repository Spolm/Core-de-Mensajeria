package Logic.M04_Integrator;

import Entities.Entity;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.M04_Integrator.IntegratorNotFoundException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M04_Integrator.IDAOIntegrator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandEnableIntegrator extends Command {
    private Entity _entity;
    private int _id;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    public CommandEnableIntegrator(int id) {
        //region Instrumentation Debug
        log.debug("Entrando al constructor CommandEnableIntegrator(" + id +")");
        //endregion
        this._id = id;
        this._entity = null;
    }

    @Override
    public void execute() throws IntegratorNotFoundException, DatabaseConnectionProblemException {
        //region Instrumentation Debug
        log.debug("Entrando al metodo execute CommandEnableIntegrator");
        //endregion
        IDAOIntegrator dao = DAOFactory.instanciateDaoIntegrator();
        _entity = dao.enableIntegrator(this._id);
    }

    @Override
    public Entity Return() {
        return _entity;
    }
}
