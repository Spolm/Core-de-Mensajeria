package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;

public class GetCampaignStatisticCommand extends Command<Entity> {

    private IDAO_StatisticEstrella dao;
    private Entity statistic;

    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
        statistic = dao.getCampaignStatistic();
    }

    @Override
    public Entity Return() {
        return statistic;
    }
}
