package Logic.M03_Campaign;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.DAOCampaign;

public class CampaignUserCompanyCommand extends Command {

    private static Entity _u;
    private static Entity _comp;


    /**
     * Constructor de la clase.
     * @param _user instancia de la Campana que se desea conocer
     * @param _company instancia de la Campana que se desea conocer
     */
    public  CampaignUserCompanyCommand ( Entity _user, Entity _company ){
        this._u = _user;
        this._comp = _company;
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
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

