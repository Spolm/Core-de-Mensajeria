package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatisticEstrella;

import java.util.ArrayList;

public class GetWeeksofYearCommand extends Command<ArrayList<Integer>> {

    private DAOStatisticEstrella dao;
    private ArrayList<Integer> weeksofyear;

    @Override
    public void execute() throws Exception {
        dao = DAOFactory.instanciateDaoStatisticsEstrella();
        weeksofyear = dao.getWeeksofYear();
    }

    @Override
    public ArrayList<Integer> Return() { return weeksofyear; }
}
