package Logic.M03_Campaign;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.DAOCampaign;

public class CampaignUserCompanyCommand extends Command {

    private static Entity _u;
    private static Entity _comp;


    public  CampaignUserCompanyCommand ( Entity _user, Entity _company ){
        this._u = _user;
        this._comp = _company;
    }


    @Override
    public void execute() throws Exception {
        try {
            DAOCampaign _dao = DAOFactory.instanciateDaoCampaign();
            _dao.campaignListByUserCompany( _u , _comp );


        }catch(Exception exc) {

        }

    }

    @Override
    public Entity Return() {

        return null;

    }

}

