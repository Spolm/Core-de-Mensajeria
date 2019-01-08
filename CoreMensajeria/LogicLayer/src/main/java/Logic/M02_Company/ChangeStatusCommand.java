package Logic.M02_Company;

import Entities.Entity;
import Entities.M02_Company.Company;
import Exceptions.M02_Company.CompanyInvalidDataException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.DAOCompany;
import Persistence.M02_Company.IDAOCompany;

import java.util.ArrayList;

public class ChangeStatusCommand extends Command {

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
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany();
            _dao.changeStatus(_co);
        }
        catch(NullPointerException e ){
            throw new CompanyInvalidDataException("Datos Invalidos", e);
        }catch(Exception e ){
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
