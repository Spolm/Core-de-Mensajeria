package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatisticEstrella;

import java.util.ArrayList;

public class GetSecondsCommand extends Command {

    private DAOStatisticEstrella dao;
    private ArrayList<Integer> seconds;

    @Override
    public void execute() throws Exception {
        dao = DAOFactory.instanciateDaoStatisticsEstrella();
        seconds = dao.getSeconds();
    }

    @Override
    public Entity Return() {
        return null;
    }

    public ArrayList<Integer> ReturnList() {
        return seconds;
    }
}
