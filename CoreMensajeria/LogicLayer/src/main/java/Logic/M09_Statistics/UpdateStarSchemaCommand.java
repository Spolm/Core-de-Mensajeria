package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;

public class UpdateStarSchemaCommand extends Command {

    private IDAO_StatisticEstrella dao;

    @Override
    public void execute() {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
        dao.updateStarSchema();
    }

    @Override
    public Entity Return() {
        return null;
    }
}
