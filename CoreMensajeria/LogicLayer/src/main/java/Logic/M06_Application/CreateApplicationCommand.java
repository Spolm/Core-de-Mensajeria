package Logic.M06_Application;

import Logic.Command;
import Entities.Entity;
import Persistence.DAOFactory;
import Persistence.M06_Application.AddApplicationData;
import Persistence.M06_Application.DAOAplication;

public class CreateApplicationCommand extends Command {

    //private static  Entity _ap;

  /*  public CreateApplicationCommand( AddApplicationData _application) {

       this._ap = _application;

    }*/

     public AddApplicationData recibirAplication(AddApplicationData application){
         return application;
    }

    @Override
    public void execute() throws Exception {

        DAOAplication _dao = DAOFactory.instanciateDaoApplication();
       // _dao.createApplication();
    }

    @Override
    public Object Return() {
        return null;
    }
}
