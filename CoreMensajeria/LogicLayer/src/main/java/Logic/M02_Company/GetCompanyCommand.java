package Logic.M02_Company;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.DAOCompany;

public class GetCompanyCommand extends Command {
    private static  Entity _co;

    public GetCompanyCommand( Entity _company ) {
        this._co = _company;
    }

    @Override
    public void execute() throws Exception {
        try {
            DAOCompany _dao = DAOFactory.instanciateDaoCompany();
            _dao.companyById( _co );


        }catch(Exception exc) {

        }


    }

    @Override
    public Entity Return() {
        return null;
    }
}
