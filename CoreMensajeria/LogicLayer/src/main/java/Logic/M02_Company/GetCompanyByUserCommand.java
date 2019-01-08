package Logic.M02_Company;

import Entities.Entity;
import Exceptions.M02_Company.CompanyNotFoundException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.DAOCompany;

import java.util.ArrayList;

public class GetCompanyByUserCommand  extends Command {
    private static Entity _co;
    private static ArrayList< Entity > _coList ;

    /**
     * Constructor de la clase.
     * @param _company instancia de la Compañia que se desea conocer
     */
    public  GetCompanyByUserCommand ( Entity _company ){

        this._co = _company;
        _coList = new ArrayList<Entity>();
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws CompanyNotFoundException, UnexpectedErrorException {
        try {
            DAOCompany _dao = DAOFactory.instanciateDaoCompany();
            _coList =  _dao.companiesByUser( _co );

        }catch(NullPointerException e) {
            throw new CompanyNotFoundException("Compañia no encontrada",e);
        }catch ( Exception e ){
            throw new UnexpectedErrorException( e );
        }

    }

    @Override
    public Entity Return() {
        return null;
    }

    //@Override
    public ArrayList<Entity> ReturnList(){ return _coList; }

}




