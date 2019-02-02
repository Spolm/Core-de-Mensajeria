package Logic.M09_Statistics;

import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class GetDaysofYearCommand extends Command<ArrayList<Integer>> {

    private IDAO_StatisticEstrella dao;
    private ArrayList<Integer> daysofyear;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    @Override
    public void execute() throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de GetDaysofYearCommand" );
        //endregion
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
        daysofyear = dao.getDaysofYear();
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de GetDaysofYearCommand" );
        //endregion
    }

    @Override
    public ArrayList<Integer> Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de GetDaysofYearCommand" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de GetDaysofYearCommand" );
        //endregion
        return daysofyear;
    }
}
