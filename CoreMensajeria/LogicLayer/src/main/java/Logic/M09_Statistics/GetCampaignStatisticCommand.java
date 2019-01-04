package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatisticEstrella;

public class GetCampaignStatisticCommand extends Command {

    private DAOStatisticEstrella dao;
    private Entity statistic;

    @Override
    public void execute() throws Exception {
        dao = DAOFactory.instanciateDaoStatisticsEstrella();
        statistic = dao.getCampaignStatistic();
    }

    @Override
    public Entity Return() {
        return statistic;
    }
}
