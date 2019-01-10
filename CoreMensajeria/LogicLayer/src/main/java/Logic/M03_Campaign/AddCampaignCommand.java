package Logic.M03_Campaign;

import Entities.Entity;
import Entities.M03_Campaign.Campaign;
import Exceptions.M03_Campaign.CampaignInvalidDataException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.DAOCampaign;
import Persistence.M03_Campaign.IDAOCampaign;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class AddCampaignCommand extends Command {

    final static Logger _log = LogManager.getLogger("CoreMensajeria");
    private static  Campaign _ca;


    /**
     * Constructor de la clase.
     * @param _campaign instancia de la Campana que se desea agregar
     */
    public AddCampaignCommand( Campaign _campaign  ) {

        this._ca = _campaign ;
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws CampaignInvalidDataException, UnexpectedErrorException {
        try {
            if( _ca.get_nameCampaign() == ""
                    || _ca.get_startCampaign().toString()== ""
                    || _ca.get_endCampaign().toString()== ""
                    || _ca.get_idCompany()== 0
            ) {
                throw new CampaignInvalidDataException();
            }
            _log.info("Objeto Campaña recibido en AddCampaign" +_ca.get_nameCampaign() + ","+
                         _ca.get_statusCampaign() + "," + _ca.get_descCampaign()+""+
                         _ca.get_startCampaign()+","+_ca.get_endCampaign()+","+_ca.get_idCompany() );
            IDAOCampaign _dao = DAOFactory.instanciateDaoCampaign();
            _dao.create( _ca );

        }

        catch ( CampaignInvalidDataException e ){
            _log.error( "Se ha lanzado un CampaignInvalidDataException en:"+ getClass().getName() );
            throw new  CampaignInvalidDataException("Datos Invalidos",e);
        }
        catch ( NullPointerException e ){
            _log.error( "Se ha lanzado un CampaignInvalidDataException en:"+ getClass().getName() );
            throw new  CampaignInvalidDataException("Datos Invalidos",e);
        }catch ( Exception e ){
            _log.error( "Se ha lanzado un UnexpectedErrorException en:"+ getClass().getName() );
            throw new UnexpectedErrorException( e );
        }



    }

    @Override
    public Entity Return() {
        return null;
    }

    //@Override
    public ArrayList<Entity> ReturnList() { return null; }

}
