package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.GetStatisticsDao;

import java.sql.SQLException;

public class GetStatistics extends Command {

    private static Entity statistic;

    public GetStatistics(Entity sta){ this.statistic = sta;}

    @Override
    public void execute () throws SQLException {
        try {
            GetStatisticsDao DAO = DAOFactory.instanciateDaoStatistics();
        }
        catch (Exception e){

        }
    }

    @Override
    public Entity Return() {
        return this.statistic;
    }

}
