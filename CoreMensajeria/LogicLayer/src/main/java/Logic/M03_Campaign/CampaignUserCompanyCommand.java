package Logic.M03_Campaign;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.DAOCampaign;

import java.util.ArrayList;

public class CampaignUserCompanyCommand extends Command {

    private static Entity _comp;
    private static ArrayList< Entity > _caList ;


    /**
     * Constructor de la clase.
     * @param _company instancia de la Campana que se desea conocer
     */
    public  CampaignUserCompanyCommand (  Entity _company ){
        this._comp = _company;
        _caList = new ArrayList<Entity>();
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws Exception {
        try {
            DAOCampaign _dao = DAOFactory.instanciateDaoCampaign();
            _caList = _dao.campaignListByUserCompany( _comp );


        }catch(Exception exc) {

        }

    }

    @Override
    public Entity Return() {

        return null;

    }


   //@Override
    public ArrayList<Entity> ReturnList() { return _caList; }

}

