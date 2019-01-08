package Logic.M03_Campaign;

import Entities.Entity;
import Entities.M03_Campaign.Campaign;
import Exceptions.M03_Campaign.CampaignNotFoundException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.DAOCampaign;

import java.util.ArrayList;

public class GetCampaignCommand extends Command {
    private static Campaign _ca;

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
            DAOCampaign _dao = DAOFactory.instanciateDaoCampaign();
            _ca = _dao.campaignById( _ca );

            if (_ca.get_idCampaign()==0){
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
        return _ca;
    }

    //@Override
    public ArrayList<Entity> ReturnList() { return null ;}
}
