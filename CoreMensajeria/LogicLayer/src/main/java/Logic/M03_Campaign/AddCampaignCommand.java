package Logic.M03_Campaign;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.DAOCampaign;

public class AddCampaignCommand extends Command {

    private static  Entity _ca;

    public AddCampaignCommand( Entity _campaign  ) {

        this._ca = _campaign ;
    }


    @Override
    public void execute() throws Exception {
        try {
            DAOCampaign _dao = DAOFactory.instanciateDaoCampaign();
            _dao.create( _ca );
        }

        catch ( Exception e ){

        }



    }

    @Override
    public Entity Return() {
        return null;
    }
}
