package Logic.M03_Campaign;

import Entities.Entity;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;
import Exceptions.M03_Campaign.CampaignNotFoundException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.IDAOCampaign;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class CampaignByCompanyCommand extends Command {
    final static Logger _log = LogManager.getLogger("CoreMensajeria");
    private static Company _ca;
    private static ArrayList<Entity> _caList ;

        /**
         * Constructor de la clase.
         * @param _campaign instancia de la Campana que se desea obtener
         */
    public CampaignByCompanyCommand( Company _campaign ){
        this._ca = _campaign;
        _caList = new ArrayList<  >();
        }


        /**
         * Metodo que ejecuta la Accion del comando
         */
        @Override
        public void execute() throws CampaignNotFoundException, UnexpectedErrorException {
            try {
                IDAOCampaign _dao = DAOFactory.instanciateDaoCampaign();
                _caList = _dao.campaignListCompany( _ca );

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
        public ArrayList<Entity> ReturnList() { return _caList ;}
    }



