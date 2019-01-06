package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatisticEstrella;

import java.sql.SQLException;

public class UpdateStarSchemaCommand extends Command {

    private DAOStatisticEstrella dao;

    @Override
    public void execute() {
        dao = DAOFactory.instanciateDaoStatisticsEstrella();
        dao.updateStarSchema();
    }

    @Override
    public Entity Return() {
        return null;
    }
}
