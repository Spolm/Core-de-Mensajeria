package Logic.M03_Campaign;

import Entities.Entity;
import Entities.M02_Company.Company;
import Exceptions.M03_Campaign.CampaignNotFoundException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.DAOCampaign;
import Persistence.M03_Campaign.IDAOCampaign;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class CampaignUserCompanyCommand extends Command {

    final static Logger _log = LogManager.getLogger("CoreMensajeria");
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
            _log.info("Objeto Campaña recibido en CampaignUserCompany"
                       +_comp.get_idCompany()+ ","+_comp.get_idUser() );
            IDAOCampaign _dao = DAOFactory.instanciateDaoCampaign();
            _caList = _dao.campaignListByUserCompany( _comp );

            if (_caList.size() <= 0) {
                throw new CampaignNotFoundException();
            }
        } catch (CampaignNotFoundException e) {
            _log.error( "Se ha lanzado un CampaignNotFoundException en:"+ getClass().getName() );
            throw new CampaignNotFoundException("Campaña no encontrada", e);
        } catch (NullPointerException e) {
            _log.error( "Se ha lanzado un CampaignNotFoundException en:"+ getClass().getName() );
            throw new CampaignNotFoundException("Campaña no encontrada", e);
        } catch (Exception e) {
            _log.error( "Se ha lanzado un UnexpectedErrorException en:"+ getClass().getName() );
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

