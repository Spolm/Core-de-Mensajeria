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

public class GetAllCompaniesCommand extends Command {

    final static Logger _log = LogManager.getLogger("CoreMensajeria");
    private static ArrayList<Entity> _coList;

    /**
     * Constructor de la clase.
     * instancia de la Lista de Compania que se desea agregar
     */
    public GetAllCompaniesCommand( ) {

        _coList = new ArrayList<Entity>();

    }

    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws CompanyNotFoundException, UnexpectedErrorException {
        try {
            _log.info( "Comando GetAllCompanies activado " );
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany();
           _coList = _dao.allCompanies();

        }catch(NullPointerException e) {
            _log.error( "Se ha lanzado un CompanyNotFoundException en;"+ getClass().getName() );
            throw new CompanyNotFoundException("Compañia no encontrada al Actualizar",e);
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
    public ArrayList<Entity> ReturnList() { return _coList; }

}
