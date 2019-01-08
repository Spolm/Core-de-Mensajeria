package Logic.M02_Company;

import Entities.Entity;
import Exceptions.M02_Company.CompanyNotFoundException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.DAOCompany;
import Persistence.M02_Company.IDAOCompany;

import java.util.ArrayList;

public class GetCompanyCommand extends Command {
    private static  Entity _co;


    /**
     * Constructor de la clase.
     * @param _company instancia de la Compania que se desea obtener
     */
    public GetCompanyCommand( Entity _company ) {
        this._co = _company;
    }

    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws CompanyNotFoundException, UnexpectedErrorException {
        try {
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany();
            _co = _dao.companyById( _co );

        }catch(NullPointerException e) {
            throw new CompanyNotFoundException("Compañia no encontrada",e);
        }catch ( Exception e ){
        throw new UnexpectedErrorException( e );
    }


    }

    @Override
    public Entity Return() {
        return _co;
    }

    //@Override
    public ArrayList<Entity> ReturnList() { return null ; }


}
