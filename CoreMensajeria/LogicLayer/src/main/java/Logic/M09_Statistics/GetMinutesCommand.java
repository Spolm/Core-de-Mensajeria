package Logic.M09_Statistics;

import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;

import java.util.ArrayList;

public class GetMinutesCommand extends Command {

    private IDAO_StatisticEstrella dao;
    private ArrayList<Integer> minutes;

    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
        minutes = dao.getMinutes();
    }

    @Override
    public ArrayList<Integer> Return() {
        return minutes;
    }
}
