package Logic.M03_Campaign;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.DAOCampaign;

import java.util.ArrayList;

public class UpdateCampaignCommand extends Command {
    private static Entity _ca;

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
    public void execute() throws Exception {
        try {
            DAOCampaign _dao = DAOFactory.instanciateDaoCampaign ( );
            _ca = _dao.update( _ca );
        }

        catch ( Exception e ){
            e.printStackTrace();
        }

    }

    @Override
    public Entity Return() {
        return null;
    }

    //@Override
    public ArrayList<Entity> ReturnList() { return null ; }
}

