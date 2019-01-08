package Logic.M03_Campaign;

import Entities.Entity;
import Entities.M02_Company.Company;
import Exceptions.M03_Campaign.CampaignNotFoundException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.DAOCampaign;

import java.util.ArrayList;

public class CampaignUserCompanyCommand extends Command {

    private static Company _comp;
    private static ArrayList< Entity > _caList ;


    /**
     * Constructor de la clase.
     * @param _company instancia de la Campana que se desea conocer
     */
    public  CampaignUserCompanyCommand (  Company _company ){
        this._comp = _company;
        _caList = new ArrayList<Entity>();
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws CampaignNotFoundException, UnexpectedErrorException {
        try {
            DAOCampaign _dao = DAOFactory.instanciateDaoCampaign();
            _caList = _dao.campaignListByUserCompany( _comp );

            if (_caList.size() <= 0) {
                throw new CampaignNotFoundException();
            }
        } catch (CampaignNotFoundException e) {
            throw new CampaignNotFoundException("Campaña no encontrada", e);
        } catch (NullPointerException e) {
            throw new CampaignNotFoundException("Campaña no encontrada", e);
        } catch (Exception e) {
            throw new UnexpectedErrorException(e);
        }
    }

    @Override
    public Entity Return() {

        return null;

    }


   //@Override
    public ArrayList<Entity> ReturnList() { return _caList; }

}

