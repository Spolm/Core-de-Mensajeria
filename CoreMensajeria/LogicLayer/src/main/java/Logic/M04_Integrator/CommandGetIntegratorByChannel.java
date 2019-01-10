package Logic.M04_Integrator;

import Entities.Entity;
import Exceptions.M05_Channel.ChannelNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M04_Integrator.IDAOIntegrator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class CommandGetIntegratorByChannel extends Command {

    private int _id;
    private ArrayList<Entity> _entityList;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    public CommandGetIntegratorByChannel(int id) {
        //region Instrumentation Debug
        log.debug("Entrando al constructor CommandGetIntegratorByChannel");
        //endregion
        this._id = id;
        this._entityList = null;
    }

    @Override
    public void execute()throws DatabaseConnectionProblemException,ChannelNotFoundException {
        //region Instrumentation Debug
        log.debug("Entrando al metodo execute CommandGetIntegratorByChannel");
        //endregion
        IDAOIntegrator _dao =DAOFactory.instanciateDaoIntegrator();
        _entityList = _dao.listIntegratorByChannel(_id);
    }

    @Override
    public Entity Return() {
        return null;
    }

    public ArrayList<Entity> returnList() { return _entityList; }
}
