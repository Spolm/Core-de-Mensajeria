package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatisticEstrella;

import java.util.ArrayList;

public class GetMinutesCommand extends Command {

    private DAOStatisticEstrella dao;
    private ArrayList<Integer> minutes;

    @Override
    public void execute() throws Exception {
        dao = DAOFactory.instanciateDaoStatisticsEstrella();
        minutes = dao.getMinutes();
    }

    @Override
    public Entity Return() {
        return null;
    }

    public ArrayList<Integer> ReturnList() {
        return minutes;
    }
}
