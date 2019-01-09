package Logic.M02_Company;

import Entities.Entity;
import Entities.M02_Company.Company;
import Exceptions.M02_Company.CompanyInvalidDataException;
import Exceptions.M07_Template.InvalidParameterException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.DAOCompany;
import Persistence.M02_Company.IDAOCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ChangeStatusCommand extends Command {

    final static Logger _log = LogManager.getLogger("CoreMensajeria");
    private static Company _co;

    /**
     * Constructor de la clase.
     * @param _company instancia de la Compania que se desea agregar
     */
    public  ChangeStatusCommand ( Company _company ){
        this._co = _company;
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws CompanyInvalidDataException, UnexpectedErrorException {

        try {
            if ( _co.get_idCompany() == 0){
                throw new CompanyInvalidDataException();
            }
            _log.debug( "Objeto Compañia recibido en ChangeStatus: "+ _co.get_idCompany()+","+ _co.get_status() );
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany();
            _dao.changeStatus(_co);
        }
        catch(NullPointerException e ){
            _log.error( "Se ha lanzado un CompanyInvalidDataException en;"+ getClass().getName() );
            throw new CompanyInvalidDataException("Datos Invalidos", e);
        }catch(Exception e ){
            _log.error( "Se ha lanzado un UnexpectedErrorException en;"+ getClass().getName() );
            throw new UnexpectedErrorException(e);
        }
    }

    @Override
    public Entity Return() {
        return null;
    }

    //@Override
    public ArrayList<Entity> ReturnList() { return null ; }



}
