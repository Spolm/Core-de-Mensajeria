package Logic.M06_DataOrigin;

import Entities.Entity;
import Entities.M06_DataOrigin.Application;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M06_DataOrigin.IDAOApplication;
import java.util.ArrayList;

public class GetApplicationByIdCompanyCommand extends Command {

    public static ArrayList<Application> _listCompany = new ArrayList<>();
    private  static int _id;

    public GetApplicationByIdCompanyCommand(int id) {
        this._id=id;
    }

    @Override
    public void execute() throws Exception {
        IDAOApplication _dao= DAOFactory.instanciateDaoApplication();
        this._listCompany=_dao.getApplications(this._id);
    }

    @Override
    public Object Return() {
        return null;
    }

    public ArrayList<Application> returnList(){
        return this._listCompany;
    }
}
