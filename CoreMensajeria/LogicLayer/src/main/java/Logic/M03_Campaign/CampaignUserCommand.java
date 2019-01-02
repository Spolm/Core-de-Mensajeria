package Logic.M03_Campaign;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.DAOCampaign;

public class CampaignUserCommand extends Command {

    private static Entity _ca;

    /**
     * Constructor de la clase.
     * @param _campaign instancia de la Campana que se desea conocer
     */
    public  CampaignUserCommand ( Entity _campaign ){

        this._ca = _campaign;


    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws Exception {
        try {
            DAOCampaign _dao = DAOFactory.instanciateDaoCampaign();
            _dao.campaignListByUser( _ca );


        }catch(Exception exc) {

        }

    }

    @Override
    public Entity Return() {
        return null;
    }
}
