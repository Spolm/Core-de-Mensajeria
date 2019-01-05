package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatisticEstrella;

import java.util.ArrayList;

public class GetQuartersofYearCommand extends Command {

    private DAOStatisticEstrella dao;
    private ArrayList<Integer> quartersofyear;

    @Override
    public void execute() throws Exception {
        dao = DAOFactory.instanciateDaoStatisticsEstrella();
        quartersofyear = dao.getQuartersofYear();
    }

    @Override
    public Entity Return() {
        return null;
    }

    public ArrayList<Integer> ReturnList() {
        return quartersofyear;
    }
}
