package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;

public class GetIntegratorStatisticCommand extends Command<Entity> {

    IDAO_StatisticEstrella dao;
    Entity statistic;

    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
        statistic = dao.getIntegratorStatistic();
    }

    @Override
    public Entity Return() {
        return statistic;
    }
}
