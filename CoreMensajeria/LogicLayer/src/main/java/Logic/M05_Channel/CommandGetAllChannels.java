package Logic.M05_Channel;

import Entities.Entity;
import Exceptions.DatabaseConnectionProblemException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M05_Channel.DAOChannel;
import java.util.ArrayList;

public class CommandGetAllChannels extends Command {
    private ArrayList<Entity> _channelList;

    public CommandGetAllChannels(){
        this._channelList = null;
    }

    @Override
    public void execute() throws DatabaseConnectionProblemException {
        DAOChannel daoChannel = DAOFactory.instanciateDaoChannel();
        _channelList = daoChannel.listChannel();
    }

    @Override
    public Entity Return() {
        return null;
    }

    public ArrayList<Entity> returnList(){
        return _channelList;
    }
}