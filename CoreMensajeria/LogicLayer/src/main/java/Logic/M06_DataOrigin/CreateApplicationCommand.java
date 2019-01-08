package Logic.M06_DataOrigin;

import Entities.M06_DataOrigin.AddApplicationData;
import Entities.M06_DataOrigin.Application;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M06_DataOrigin.IDAOApplication;

public class CreateApplicationCommand extends Command {

    private  static AddApplicationData _appData;
    private  static Application _app;


    public CreateApplicationCommand(AddApplicationData _app) {

        this._appData = _app ;
    }


    @Override
    public void execute() throws Exception {
        IDAOApplication _dao= DAOFactory.instanciateDaoApplication();
        _app =_dao.createApplication(this._appData);

    }

    @Override
    public Application Return() {
        return _app;
    }

   /* public Application ReturnApp(){
        return _app;
    }*/
}
