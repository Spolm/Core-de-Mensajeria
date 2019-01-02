package Logic.M02_Company;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.DAOCompany;
import Persistence.M02_Company.IDAOCompany;

import java.util.ArrayList;

public class ChangeStatusCommand extends Command {

    private static Entity _co;

    public  ChangeStatusCommand ( Entity _company ){
        this._co = _company;
    }


    @Override
    public void execute() throws Exception {
        try {
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany ( );
            _dao.changeStatus( _co );
        }

        catch ( Exception e ){

        }


    }

    @Override
    public Entity Return() {
        return null;
    }


}
