package Logic.M09_Statistics;

import Entities.Entity;
import Entities.M04_Integrator.Integrator;
import Exceptions.ChannelNotFoundException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatistic;

import java.util.ArrayList;
import java.util.List;

public class getIntegratorsForChannelCommand extends Command {

    ArrayList<Integrator> integrators;
    DAOStatistic dao;
    List<Integer> channelIds;

    public getIntegratorsForChannelCommand(List<Integer> channelIds){ this.channelIds = channelIds;}

    @Override
    public void execute() throws ChannelNotFoundException {
        dao = DAOFactory.instanciateDAOStatistic();
        integrators = dao.getIntegratorsForChannel(channelIds);
    }

    @Override
    public Entity Return() {
        return null;
    }

    public ArrayList<Integrator> returnList() {
        return integrators;
    }
}
