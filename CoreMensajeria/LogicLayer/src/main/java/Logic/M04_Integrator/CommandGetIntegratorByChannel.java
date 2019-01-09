package Logic.M04_Integrator;

import Entities.Entity;
import Exceptions.M05_Channel.ChannelNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M04_Integrator.IDAOIntegrator;

import java.util.ArrayList;

public class CommandGetIntegratorByChannel extends Command {
    private int _id;
    private ArrayList<Entity> _entityList;

    public CommandGetIntegratorByChannel(int id) {
        this._id = id;
        this._entityList = null;
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

    public ArrayList<Entity> returnList() { return _entityList; }
}
