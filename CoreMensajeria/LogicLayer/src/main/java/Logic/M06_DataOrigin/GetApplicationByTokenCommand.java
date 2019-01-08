package Logic.M06_DataOrigin;

import Entities.M06_DataOrigin.Application;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M06_DataOrigin.DAOApplication;
import Persistence.M06_DataOrigin.IDAOApplication;

public class GetApplicationByTokenCommand extends Command {

    public static String _tokens;
    public static Application _app;

    public GetApplicationByTokenCommand(String _token){
        this._tokens=_token;
    }


    @Override
    public void execute() throws Exception {
        IDAOApplication _dao= DAOFactory.instanciateDaoApplication();
        this._app=_dao.getApplication(_tokens);
    }

    @Override
    public Application Return() {
        return this._app;
    }

   /* public Application ReturnList(){
        return this._app;
    }*/
}
