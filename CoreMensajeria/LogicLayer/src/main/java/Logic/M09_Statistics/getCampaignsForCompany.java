package Logic.M09_Statistics;

import Entities.Entity;
import Entities.M03_Campaign.Campaign;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatistic;

import java.util.ArrayList;
import java.util.List;

public class getCampaignsForCompany extends Command {
    List<Integer> companyIds;

    public getCampaignsForCompany(List<Integer> companyIds){this.companyIds = companyIds;}

    @Override
    public void execute() throws Exception {
        DAOStatistic dao = DAOFactory.instanciateDaoStatistics();
        ArrayList<Campaign> Campaigns = dao.CampaignsForCompany(this.companyIds);
        //return Response.ok(gson.toJson(Campaigns)).build();
    }

    @Override
    public Entity Return() {
        return null;
    }
}
