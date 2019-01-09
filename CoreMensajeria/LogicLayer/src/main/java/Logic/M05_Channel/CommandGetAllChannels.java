package Logic.M05_Channel;

import Entities.Entity;
import Exceptions.DatabaseConnectionProblemException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M05_Channel.DAOChannel;
import java.util.ArrayList;

public class CommandGetAllChannels extends Command {

    ArrayList<Entity> _channelList;

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