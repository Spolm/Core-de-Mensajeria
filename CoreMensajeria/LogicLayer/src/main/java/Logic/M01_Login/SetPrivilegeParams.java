package Logic.M01_Login;

import Entities.Entity;
import Entities.M01_Login.Privilege;
import Entities.M01_Login.PrivilegeDao;
import Logic.Command;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SetPrivilegeParams extends Command {
    private static ResultSet _result;
    private static Privilege _privilege;
    private static Entity _us;

    public SetPrivilegeParams (ResultSet result, Privilege privilege){
        this._result = result;
        this._privilege = privilege;
    }
    @Override
    public void execute() throws SQLException {
        try{
            PrivilegeDao dao = new PrivilegeDao();
            dao.setPrivilegeParams(_result,_privilege);
        }
        catch (Exception e){

        }
    }
    @Override
    public Entity Return() { return _us;}
}
}
