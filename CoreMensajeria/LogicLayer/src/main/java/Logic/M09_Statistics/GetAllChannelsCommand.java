package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class GetAllChannelsCommand extends Command<ArrayList<Entity>> {

    private ArrayList<Entity> channels;
    private IDAO_StatisticEstrella dao;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    @Override
    public void execute() throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de GetAllChannelsCommand" );
        //endregion
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
        channels = dao.getAllChannels();
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de GetAllChannelsCommand" );
        //endregion
    }

    @Override
    public ArrayList<Entity> Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de GetAllChannelsCommand" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de GetAllChannelsCommand" );
        //endregion
        return channels;
    }

}
