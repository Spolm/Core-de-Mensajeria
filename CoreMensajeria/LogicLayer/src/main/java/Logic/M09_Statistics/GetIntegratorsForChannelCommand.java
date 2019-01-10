package Logic.M09_Statistics;

import Entities.Entity;
import Exceptions.M05_Channel.ChannelNotFoundException;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_Statistic;

import java.util.ArrayList;
import java.util.List;

public class GetIntegratorsForChannelCommand extends Command {

    ArrayList<Entity> integrators;
    IDAO_Statistic dao;
    List<Integer> channelIds;

    public GetIntegratorsForChannelCommand(List<Integer> channelIds){ this.channelIds = channelIds;}

    @Override
    public void execute() throws ChannelNotFoundException {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDAOStatistic();
        integrators = dao.getIntegratorsForChannel(channelIds);
    }

    @Override
    public ArrayList<Entity> Return() {
        return integrators;
    }
}
