package Logic.M09_Statistics;

import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;

import java.util.ArrayList;

public class GetYearsCommand extends Command<ArrayList<Integer>> {

    private IDAO_StatisticEstrella dao;
    private ArrayList<Integer> years;

    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
        years = dao.getYears();
    }

    @Override
    public ArrayList<Integer> Return() { return years; }
}
