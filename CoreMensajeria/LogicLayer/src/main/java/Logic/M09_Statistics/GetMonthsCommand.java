package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.DAO;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatistic;
import Persistence.M09_Statistics.DAOStatisticEstrella;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetMonthsCommand extends Command<ArrayList<Integer>> {

    private DAOStatisticEstrella dao;
    private ArrayList<Integer> months;

    @Override
    public void execute() throws Exception {
        dao = DAOFactory.instanciateDaoStatisticsEstrella();
        months = dao.getMonths();
    }

    @Override
    public ArrayList<Integer> Return() { return months; }
}
