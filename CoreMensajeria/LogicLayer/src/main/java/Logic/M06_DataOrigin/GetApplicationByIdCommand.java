package Logic.M06_DataOrigin;

import Entities.M06_DataOrigin.Application;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M06_DataOrigin.IDAOApplication;

public class GetApplicationByIdCommand extends Command{

    private static int _id;
    private Application _app = EntityFactory.emptyApplication();

    public GetApplicationByIdCommand(int _id ){
        this._id = _id;
    }


    @Override
    public void execute() throws Exception {
        IDAOApplication _dao= DAOFactory.instanciateDaoApplication();
        this._app=_dao.getApplication(_id);
    }

    @Override
    public Object Return() {
        return null;
    }

    public Application ReturnList(){ return _app;}

}
