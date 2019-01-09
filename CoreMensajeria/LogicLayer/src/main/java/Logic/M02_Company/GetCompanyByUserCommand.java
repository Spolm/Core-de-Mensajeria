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
import java.util.List;

public class GetCompanyByUserCommand  extends Command {
    private static Company _co;
    private static ArrayList< Entity > _coList ;
    final static Logger _log = LogManager.getLogger("CoreMensajeria");


    /**
     * Constructor de la clase.
     * @param _company instancia de la Compañia que se desea conocer
     */
    public  GetCompanyByUserCommand ( Company _company ){

        this._co = _company;
        _coList = new ArrayList<>();
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws CompanyNotFoundException, UnexpectedErrorException {
        try {
            _log.debug( "Id recibido en GetCompanyByUser: " +_co.get_idCompany() );
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany();
            _coList =  _dao.companiesByUser( _co );

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
    public List<Entity> ReturnList(){ return _coList; }

}




