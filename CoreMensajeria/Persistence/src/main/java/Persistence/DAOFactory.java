package Persistence;

import Persistence.M01_Login.GetUserDao;
import Persistence.M09_Statistics.GetStatisticsDao;

public class DAOFactory {

    //region M01

    static public GetUserDao instanciateDaoUser () {
        return new GetUserDao();
    }

    //endregion

    //region M09

    static public GetStatisticsDao instanciateDaoStatistics(){ return new GetStatisticsDao(); }

    //endregion
}
