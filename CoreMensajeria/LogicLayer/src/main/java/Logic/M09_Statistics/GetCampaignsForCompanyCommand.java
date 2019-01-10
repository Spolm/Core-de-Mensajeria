package Logic.M09_Statistics;

import Entities.Entity;
import Exceptions.CampaignDoesntExistsException;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;

import java.util.ArrayList;
import java.util.List;

public class GetCampaignsForCompanyCommand extends Command<ArrayList<Entity>> {
    private List<Integer> companyIds;
    private ArrayList<Entity> Campaigns;
    private IDAO_StatisticEstrella dao;

    public GetCampaignsForCompanyCommand(List<Integer> companyIds){this.companyIds = companyIds;}

    @Override
    public void execute() throws CampaignDoesntExistsException {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
            Campaigns = dao.CampaignsForCompany(this.companyIds);
    }

    @Override
    public ArrayList<Entity> Return() {
        return Campaigns;
    }
}
