package Logic.M02_Company;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.DAOCompany;

public class AddCompanyCommand extends Command {

    private static  Entity _co;

    public AddCompanyCommand( Entity _company  ) {

      this._co = _company ;
    }

    @Override
    public void execute() throws Exception {
        try {
            DAOCompany _dao = DAOFactory.instanciateDaoCompany ( );
            _dao.create( _co );
        }

        catch ( Exception e ){

            }




    }

    @Override
    public Entity Return() {
        return null;
    }
}
