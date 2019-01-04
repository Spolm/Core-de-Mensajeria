package Logic.M09_Statistics;

import Entities.Entity;
import Exceptions.CampaignDoesntExistsException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatisticEstrella;

import java.util.ArrayList;
import java.util.List;

public class GetCampaignsForCompanyCommand extends Command {
    private List<Integer> companyIds;
    private ArrayList<Entity> Campaigns;
    private DAOStatisticEstrella dao;

    public GetCampaignsForCompanyCommand(List<Integer> companyIds){this.companyIds = companyIds;}

    @Override
    public void execute() throws CampaignDoesntExistsException {
            dao = DAOFactory.instanciateDaoStatisticsEstrella();
            Campaigns = dao.CampaignsForCompany(this.companyIds);
    }

    @Override
    public Entity Return() {
        return null;
    }

    public ArrayList<Entity> returnList() {
        return Campaigns;
    }
}
