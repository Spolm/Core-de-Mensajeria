package Logic.M09_Statistics;

import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;

import java.util.ArrayList;

public class GetDaysofWeekCommand extends Command<ArrayList<Integer>> {

    private IDAO_StatisticEstrella dao;
    private ArrayList<Integer> daysofweek;

    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
        daysofweek = dao.getDaysofWeek();
    }

    @Override
    public ArrayList<Integer> Return() {
        return daysofweek;
    }
}
