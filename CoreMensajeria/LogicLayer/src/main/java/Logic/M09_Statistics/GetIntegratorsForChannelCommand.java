package Logic.M09_Statistics;

import Entities.Entity;
import Exceptions.M05_Channel.ChannelNotFoundException;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_Statistic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GetIntegratorsForChannelCommand extends Command {

    ArrayList<Entity> integrators;
    IDAO_Statistic dao;
    List<Integer> channelIds;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    public GetIntegratorsForChannelCommand(List<Integer> channelIds){ this.channelIds = channelIds;}

    @Override
    public void execute() throws ChannelNotFoundException {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de GetIntegratorsForChannelCommand" );
        //endregion
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDAOStatistic();
        integrators = dao.getIntegratorsForChannel(channelIds);
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de GetIntegratorsForChannelCommand" );
        //endregion
    }

    @Override
    public ArrayList<Entity> Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de GetIntegratorsForChannelCommand" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de GetIntegratorsForChannelCommand" );
        //endregion
        return integrators;
    }
}
