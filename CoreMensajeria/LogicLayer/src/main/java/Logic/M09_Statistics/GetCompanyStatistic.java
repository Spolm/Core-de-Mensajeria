package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatisticEstrella;

public class GetCompanyStatistic extends Command {

    DAOStatisticEstrella dao;
    Entity statistic;

    @Override
    public void execute() throws Exception {
        dao = DAOFactory.instanciateDaoStatisticsEstrella();
        statistic = dao.getOverallCountForCompanyStatistic();
    }

    @Override
    public Entity Return() {
        return statistic;
    }
}
