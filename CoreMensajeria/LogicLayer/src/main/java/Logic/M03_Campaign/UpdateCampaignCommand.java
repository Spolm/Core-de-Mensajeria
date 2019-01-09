package Logic.M03_Campaign;

import Entities.Entity;
import Entities.M03_Campaign.Campaign;
import Exceptions.M03_Campaign.CampaignNotFoundException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.DAOCampaign;
import Persistence.M03_Campaign.IDAOCampaign;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class UpdateCampaignCommand extends Command {
    private static Entity _ca;
    final static Logger _log = LogManager.getLogger("CoreMensajeria");


    /**
     * Constructor de la clase.
     * @param _campaign instancia de la Compania que se desea actualizar
     */
    public  UpdateCampaignCommand ( Entity _campaign ){
        this._ca = _campaign;
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws CampaignNotFoundException, UnexpectedErrorException {
        try {
            Campaign _camp = (Campaign) _ca;
            _log.info("Objeto Campaña recibido en UpdateCampaign"+ _camp.get_idCampaign() +"," +
                      _camp.get_nameCampaign() + ","+ _camp.get_statusCampaign() + "," +
                      _camp.get_descCampaign()+""+ _camp.get_startCampaign()+","+
                      _camp.get_endCampaign()+","+_camp.get_idCompany() );
            IDAOCampaign _dao = DAOFactory.instanciateDaoCampaign ( );
            _ca = _dao.update( _ca );

        }catch(NullPointerException e) {
            _log.error( "Se ha lanzado un CampaignNotFoundException en;"+ getClass().getName() );
            throw new CampaignNotFoundException("Compañia no encontrada",e);
        }catch ( Exception e ){
            _log.error( "Se ha lanzado un UnexpectedErrorException en;"+ getClass().getName() );
            throw new UnexpectedErrorException( e );
        }

    }

    @Override
    public Entity Return() {
        return null;
    }

    //@Override
    public ArrayList<Entity> ReturnList() { return null ; }
}

