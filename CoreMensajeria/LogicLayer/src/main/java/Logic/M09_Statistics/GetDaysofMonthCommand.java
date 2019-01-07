package Logic.M09_Statistics;

import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatisticEstrella;

import java.util.ArrayList;

public class GetDaysofMonthCommand extends Command<ArrayList<Integer>> {

    private DAOStatisticEstrella dao;
    private ArrayList<Integer> daysofmonth;

    @Override
    public void execute() throws Exception {
        dao = DAOFactory.instanciateDaoStatisticsEstrella();
        daysofmonth = dao.getDaysofMonth();
    }


    @Override
    public ArrayList<Integer> Return() {
        return daysofmonth;
    }
}
