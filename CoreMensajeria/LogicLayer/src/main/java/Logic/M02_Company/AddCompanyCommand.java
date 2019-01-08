package Logic.M02_Company;

import Entities.Entity;
import Entities.M02_Company.Company;
import Exceptions.M02_Company.CompanyInvalidDataException;
import Exceptions.M02_Company.CompanyNotFoundException;
import Exceptions.M07_Template.InvalidParameterException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.DAOCompany;

import java.util.ArrayList;

public class AddCompanyCommand extends Command {

    private static Company _co;


    /**
     * Constructor de la clase.
     * @param _company instancia de la Compania que se desea agregar
     */
    public AddCompanyCommand( Company _company  ) {

      this._co = _company ;
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws CompanyInvalidDataException, UnexpectedErrorException {
        try {
            if( _co.get_name() == "" ) {
                throw new CompanyInvalidDataException();
            }
            DAOCompany _dao = DAOFactory.instanciateDaoCompany ( );
            _dao.create( _co );
        }

        catch ( CompanyInvalidDataException e ){
            throw new  CompanyInvalidDataException("Datos Invalidos",e);
        }
        catch ( NullPointerException e ){
            throw new  CompanyInvalidDataException("Datos Invalidos",e);
        }catch ( Exception e ){
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
