package Logic.M09_Statistics;

import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class GetYearsCommand extends Command<ArrayList<Integer>> {

    private IDAO_StatisticEstrella dao;
    private ArrayList<Integer> years;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    @Override
    public void execute() throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de GetYearsCommand" );
        //endregion
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
        years = dao.getYears();
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de GetYearsCommand" );
        //endregion
    }

    @Override
    public ArrayList<Integer> Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de GetYearsCommand" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de GetYearsCommand" );
        //endregion
        return years; }
}
