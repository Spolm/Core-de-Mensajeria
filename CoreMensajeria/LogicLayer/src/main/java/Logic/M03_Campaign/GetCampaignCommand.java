package Logic.M03_Campaign;

import Entities.Entity;
import Entities.M03_Campaign.Campaign;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.DAOCampaign;

public class GetCampaignCommand extends Command {
    private static Entity _ca;

    public GetCampaignCommand( Entity _campaign ){
        this._ca = _campaign;

    }


    @Override
    public void execute() throws Exception {
        try {
            DAOCampaign _dao = DAOFactory.instanciateDaoCampaign();
            _dao.campaignById( _ca );

        }catch( Exception exc ) {

        }
    }

    @Override
    public Entity Return() {
        return null;
    }
}
