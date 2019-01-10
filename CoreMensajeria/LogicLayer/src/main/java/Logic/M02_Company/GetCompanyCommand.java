package Logic.M02_Company;

import Entities.Entity;
import Exceptions.M02_Company.CompanyNotFoundException;
import Exceptions.UnexpectedErrorException;
import Entities.M02_Company.Company;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.DAOCompany;
import Persistence.M02_Company.IDAOCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class GetCompanyCommand extends Command {
    private static Company _co;
    final static Logger _log = LogManager.getLogger("CoreMensajeria");

    /**
     * Constructor de la clase.
     * @param _company instancia de la Compania que se desea obtener
     */
    public GetCompanyCommand( Company _company ) {
        this._co = _company;
    }

    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws CompanyNotFoundException, UnexpectedErrorException {
        try {
            _log.info("Objeto Company recibido en GetCompany"+_co.get_id() );
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany();
            _co = _dao.companyById( _co );

        }catch(NullPointerException e) {
            _log.error( "Se ha lanzado un CompanyNotFoundException en;"+ getClass().getName() );
            throw new CompanyNotFoundException("Compañia no encontrada",e);
        }catch ( Exception e ){
            _log.error( "Se ha lanzado un UnexpectedErrorException en;"+ getClass().getName() );
        throw new UnexpectedErrorException( e );
    }


    }

    @Override
    public Company Return() {
        return _co;
    }

    //@Override
    public ArrayList<Entity> ReturnList() { return null ; }


}
