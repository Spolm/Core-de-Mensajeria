package Logic.M09_Statistics;

import Entities.Entity;
import Entities.M05_Channel.Channel;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatisticEstrella;

import java.util.ArrayList;

public class GetAllChannelsCommand extends Command {

    ArrayList<Channel> channels;
    DAOStatisticEstrella dao;

    @Override
    public void execute() throws Exception {
        dao = DAOFactory.instanciateDaoStatisticsEstrella();
        channels = dao.getAllChannels();
    }

    @Override
    public Entity Return() {
        return null;
    }

    public ArrayList<Channel> returnList() {
        return channels;
    }

}
