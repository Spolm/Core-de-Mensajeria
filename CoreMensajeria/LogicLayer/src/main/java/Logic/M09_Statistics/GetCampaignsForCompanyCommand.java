package Logic.M09_Statistics;

import Entities.Entity;
import Exceptions.CampaignDoesntExistsException;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GetCampaignsForCompanyCommand extends Command<ArrayList<Entity>> {
    private List<Integer> companyIds;
    private ArrayList<Entity> Campaigns;
    private IDAO_StatisticEstrella dao;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    public GetCampaignsForCompanyCommand(List<Integer> companyIds){this.companyIds = companyIds;}

    @Override
    public void execute() throws CampaignDoesntExistsException {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de GetCampaignsForCompanyCommand" );
        //endregion
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
            Campaigns = dao.CampaignsForCompany(this.companyIds);
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de GetCampaignsForCompanyCommand" );
        //endregion
    }

    @Override
    public ArrayList<Entity> Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de GetCampaignsForCompanyCommand" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de GetCampaignsForCompanyCommand" );
        //endregion
        return Campaigns;
    }
}
