package Logic;

import Entities.Entity;
import Logic.M01_Login.GetUser;
import Logic.M09_Statistics.GetStatistics;

public class CommandsFactory {

    public static Command instanciateGetUser ( Entity user) {
        return new GetUser(user);
    }


    // region M09
    public static Command instanciateGetStatistics (Entity Statistic) {return new GetStatistics(Statistic); }
    //end region
}
