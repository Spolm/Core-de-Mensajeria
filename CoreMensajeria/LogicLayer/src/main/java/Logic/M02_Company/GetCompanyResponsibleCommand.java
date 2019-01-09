package Logic.M02_Company;

import Entities.Entity;
import Exceptions.M02_Company.CompanyNotFoundException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.IDAOCompany;

import java.util.ArrayList;

public class GetCompanyResponsibleCommand extends Command {

    private static ArrayList<Entity> _coList;
    private static Entity _u;


    public GetCompanyResponsibleCommand( Entity _user ) {

        _coList = new ArrayList<Entity>();
        this._u = _user;

    }

    @Override
    public void execute() throws CompanyNotFoundException, UnexpectedErrorException {
        try {
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany();
            _coList = _dao.companiesByResponsible( _u );

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
    public ArrayList<Entity> ReturnList(){  return _coList; }
    }

