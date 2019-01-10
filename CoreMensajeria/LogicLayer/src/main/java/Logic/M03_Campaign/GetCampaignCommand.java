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

public class GetCampaignCommand extends Command {
    private static Campaign _ca;
    final static Logger _log = LogManager.getLogger("CoreMensajeria");
    /**
     * Constructor de la clase.
     * @param _campaign instancia de la Campana que se desea obtener
     */
    public GetCampaignCommand( Campaign _campaign ){
        this._ca = _campaign;

    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws CampaignNotFoundException, UnexpectedErrorException {
        try {
            _log.info("Objeto Campaign recibido en GetCampaign"+_ca.get_id() );
            IDAOCampaign _dao = DAOFactory.instanciateDaoCampaign();
            _ca = _dao.campaignById( _ca );

            if (_ca.get_idCampaign()==0){
                throw new CampaignNotFoundException();
            }

        } catch (CampaignNotFoundException e) {
            _log.error( "Se ha lanzado un CampaignNotFoundException en;"+ getClass().getName() );
            throw new CampaignNotFoundException("Campaña no encontrada", e);
        } catch (NullPointerException e) {
            _log.error( "Se ha lanzado un CampaignNotFoundException en;"+ getClass().getName() );
            throw new CampaignNotFoundException("Campaña no encontrada", e);
        } catch (Exception e) {
            _log.error( "Se ha lanzado un UnexpectedErrorException en;"+ getClass().getName() );
            throw new UnexpectedErrorException(e);
        }



    }

    @Override
    public Entity Return() {
        return _ca;
    }

    //@Override
    public ArrayList<Entity> ReturnList() { return null ;}
}
