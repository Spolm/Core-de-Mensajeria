package Logic.M09_Statistics;

import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatisticEstrella;

import java.util.ArrayList;

public class GetDaysofWeekCommand extends Command<ArrayList<Integer>> {

    private DAOStatisticEstrella dao;
    private ArrayList<Integer> daysofweek;

    @Override
    public void execute() throws Exception {
        dao = DAOFactory.instanciateDaoStatisticsEstrella();
        daysofweek = dao.getDaysofWeek();
    }

    @Override
    public ArrayList<Integer> Return() {
        return daysofweek;
    }
}
