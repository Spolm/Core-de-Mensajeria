package Logic.M02_Company;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.IDAOCompany;

import java.util.ArrayList;

public class GetAllCompaniesCommand extends Command {

    private static ArrayList<Entity> _coList;

    public GetAllCompaniesCommand( ) {

        _coList = new ArrayList<Entity>();

    }

    @Override
    public void execute() throws Exception {
        try {
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany();
           _coList = _dao.allCompanies();


        }catch(Exception exc) {

        }
    }

    @Override
    public Entity Return() {
        return null;
    }

    //@Override
    public ArrayList<Entity> ReturnList() { return _coList; }

}
