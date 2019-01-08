package Logic.M06_DataOrigin;

import Entities.M06_DataOrigin.Application;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M06_DataOrigin.IDAOApplication;
import java.util.ArrayList;

public class GetApplicationCommand extends Command{

    ArrayList<Application> applicationList = new ArrayList<>();

    @Override
    public void execute() throws Exception {
        IDAOApplication _dao= DAOFactory.instanciateDaoApplication();
        applicationList=_dao.getApplication();
    }

    @Override
    public ArrayList<Application> Return() {
        return applicationList;
    }
}
