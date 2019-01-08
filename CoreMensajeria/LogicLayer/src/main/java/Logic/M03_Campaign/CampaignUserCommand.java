package Logic.M03_Campaign;

import Entities.Entity;
import Entities.M02_Company.Company;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.DAOCampaign;

import java.util.ArrayList;

public class CampaignUserCommand extends Command {

    private static Company _ca;
    private static ArrayList< Entity > _caList ;

    /**
     * Constructor de la clase.
     * @param _campaign instancia de la Campana que se desea conocer
     */
    public  CampaignUserCommand ( Company _campaign ){

        this._ca = _campaign;
       _caList = new ArrayList<  >();
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws Exception {
        try {
            DAOCampaign _dao = DAOFactory.instanciateDaoCampaign();
            _caList =  _dao.campaignListByUser( _ca );

        }catch(Exception exc) {

        }

    }

    @Override
    public Entity Return() {
        return null;
    }

    //@Override
    public ArrayList<Entity> ReturnList(){ return _caList; }
}
