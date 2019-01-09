package Logic.M02_Company;

import Entities.Entity;
import Entities.M02_Company.Company;
import Exceptions.M02_Company.CompanyNotFoundException;
import Exceptions.M07_Template.InvalidParameterException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.DAOCompany;
import Persistence.M02_Company.IDAOCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class UpdateCompanyCommand extends Command {
    private static Entity _co;
    final static Logger _log = LogManager.getLogger("CoreMensajeria");


    /**
     * Constructor de la clase.
     * @param _company instancia de la Compania que se desea actualizar
     */
    public  UpdateCompanyCommand ( Entity _company ){
        this._co = _company;
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws CompanyNotFoundException, UnexpectedErrorException {
        try {
            Company _company = (Company) _co;
            _log.debug( "Objeto Compañia recibido en AddCompany: "+ _company.get_idCompany()+ ","
                            +_company.get_name()+ ","+ _company.get_desc()+","+ _company.get_status()+","+
                             _company.get_idUser() );
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany ( );
           _co = _dao.update( _co );
        }catch(NullPointerException e) {
            _log.error( "Se ha lanzado un CompanyNotFoundException en;"+ getClass().getName() );
            throw new CompanyNotFoundException("Compañia no encontrada al Actualizar",e);
        }catch ( Exception e ){
            _log.error( "Se ha lanzado un UnexpectedErrorException en "+ getClass().getName() );
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
