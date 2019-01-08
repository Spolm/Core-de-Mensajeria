package Logic.M06_DataOrigin;

import Entities.Factory.EntityFactory;
import Entities.M06_DataOrigin.Application;
import Logic.Command;
import Logic.CommandsFactory;
import Persistence.DAOFactory;
import Persistence.M06_DataOrigin.DAOApplication;
import Persistence.M06_DataOrigin.IDAOApplication;

public class UpdateApplicationCommand extends Command {

    private static int _id;
    private static int _status;
    private static Application _app;

    public UpdateApplicationCommand(int id,int _status){
        this._id=id;
        this._status=_status;
    }

    @Override
    public void execute() throws Exception {
        IDAOApplication _dao= DAOFactory.instanciateDaoApplication();
        this._app=_dao.updateApplication(_id,_status);
    }

    @Override
    public Application Return() {
        return this._app;
    }

  /*  public Application ReturnList(){
        return this._app;
    }*/

}
