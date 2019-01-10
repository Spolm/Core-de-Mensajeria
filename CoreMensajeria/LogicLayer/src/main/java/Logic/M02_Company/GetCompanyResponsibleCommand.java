package Logic.M02_Company;

import Entities.Entity;
import Exceptions.M02_Company.CompanyNotFoundException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.IDAOCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class GetCompanyResponsibleCommand extends Command {
    final static Logger _log = LogManager.getLogger("CoreMensajeria");
    private static ArrayList<Entity> _coList;
    private static Entity _u;


    public GetCompanyResponsibleCommand( Entity _user ) {

        _coList = new ArrayList<>();
        this._u = _user;

    }

    @Override
    public void execute() throws CompanyNotFoundException, UnexpectedErrorException {
        try {
            _log.info("Objeto Company recibido en CampaignResponsible" );
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany();
            _coList = _dao.companiesByResponsible( _u );

        }catch(NullPointerException e) {
            _log.error( "Se ha lanzado un CompanyNotFoundException en;"+ getClass().getName() );
            throw new CompanyNotFoundException("Compañia no encontrada",e);
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
    public ArrayList<Entity> ReturnList(){  return _coList; }
    }

