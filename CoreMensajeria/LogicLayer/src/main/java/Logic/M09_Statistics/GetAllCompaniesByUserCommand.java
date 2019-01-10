package Logic.M09_Statistics;

import Entities.Entity;
import Exceptions.CompanyDoesntExistsException;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_Statistic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class GetAllCompaniesByUserCommand extends Command<ArrayList<Entity>> {

    private Integer userId;
    private ArrayList<Entity> companies;
    private IDAO_Statistic dao;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    public GetAllCompaniesByUserCommand(Integer userId){ this.userId = userId;}

    @Override
    public void execute () throws CompanyDoesntExistsException {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de GetAllCompaniesByUserCommand" );
        //endregion
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDAOStatistic();
        companies = dao.getAllCompanies(userId);
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de GetAllCompaniesByUserCommand" );
        //endregion
    }

    @Override
    public ArrayList<Entity> Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de GetAllCompaniesByUserCommand" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de GetAllCompaniesByUserCommand" );
        //endregion
        return companies;
    }

    private void setUserId(Integer userId) {
        this.userId = userId;
    }
}
