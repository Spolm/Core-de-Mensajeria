package Logic.M03_Campaign;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.IDAOCampaign;

import java.util.ArrayList;

public class ChangeStatusCampaignCommand extends Command {

    private static Entity _ca;

    /**
     * Constructor de la clase.
     * @param _campaign instancia de la Campana que se desea agregar
     */

    public ChangeStatusCampaignCommand(Entity _campaign ){
        this._ca = _campaign;
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws Exception {
        try {
            IDAOCampaign _dao = DAOFactory.instanciateDaoCampaign ( );
            _dao.changeStatusCampaign( _ca );
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