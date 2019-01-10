package Logic.M04_Integrator;

import Entities.Entity;
import Exceptions.DatabaseConnectionProblemException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M04_Integrator.IDAOIntegrator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class CommandGetAllIntegrator extends Command {
    private ArrayList<Entity> _entityList;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    public CommandGetAllIntegrator() {
        //region Instrumentation Debug
        log.debug("Entrando al constructor CommandGetAllIntegrator");
        //endregion
        this._entityList = null;
    }

    @Override
    public void execute() throws DatabaseConnectionProblemException {
        //region Instrumentation Debug
        log.debug("Entrando al metodo execute CommandGetAllIntegrator");
        //endregion
        IDAOIntegrator dao = DAOFactory.instanciateDaoIntegrator();
        _entityList = dao.listIntegrator();
    }

    @Override
    public Entity Return() {
        return null;
    }

    public ArrayList<Entity> returnList() { return _entityList; }
}
