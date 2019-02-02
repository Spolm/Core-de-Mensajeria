package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateStarSchemaCommand extends Command {

    private IDAO_StatisticEstrella dao;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    @Override
    public void execute() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de UpdateStarSchemaCommand" );
        //endregion
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
        dao.updateStarSchema();
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de UpdateStarSchemaCommand" );
        //endregion
    }

    @Override
    public Entity Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de UpdateStarSchemaCommand" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de UpdateStarSchemaCommand" );
        //endregion
        return null;
    }
}
