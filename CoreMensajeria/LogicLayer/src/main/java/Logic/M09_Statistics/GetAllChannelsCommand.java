package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;

import java.util.ArrayList;

public class GetAllChannelsCommand extends Command<ArrayList<Entity>> {

    private ArrayList<Entity> channels;
    private IDAO_StatisticEstrella dao;

    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
        channels = dao.getAllChannels();
    }

    @Override
    public ArrayList<Entity> Return() {
        return channels;
    }

}
