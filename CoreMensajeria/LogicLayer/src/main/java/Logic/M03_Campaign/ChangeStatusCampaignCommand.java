package Logic.M03_Campaign;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.IDAOCompany;

import java.util.ArrayList;

public class ChangeStatusCampaignCommand extends Command {

    private static Entity _ca;

    public ChangeStatusCampaignCommand(Entity _campaign ){
        this._ca = _campaign;
    }


    @Override
    public void execute() throws Exception {
        try {
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany ( );
            _dao.changeStatus( _ca );
        }

        catch ( Exception e ){

        }


    }

    @Override
    public Entity Return() {
        return null;
    }

    //@Override
    public ArrayList<Entity> ReturnList() { return null;}

}