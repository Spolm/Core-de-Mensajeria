package Logic.M09_Statistics;

import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatisticEstrella;

import java.util.ArrayList;

public class GetDaysofYearCommand extends Command<ArrayList<Integer>> {

    private DAOStatisticEstrella dao;
    private ArrayList<Integer> daysofyear;

    @Override
    public void execute() throws Exception {
        dao = DAOFactory.instanciateDaoStatisticsEstrella();
        daysofyear = dao.getDaysofYear();
    }

    @Override
    public ArrayList<Integer> Return() {
        return daysofyear;
    }
}
