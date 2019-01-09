package Logic.M01_Login;

import Entities.Entity;
import Entities.M01_Login.Privilege;
import Persistence.M01_Login.DAOPrivilege;
import Logic.Command;

import java.sql.SQLException;
import java.util.ArrayList;


public class FindPrivilegeByUserIdCommand extends Command {
    private static int _id;
    private static Entity _us;

    public FindPrivilegeByUserIdCommand(int id) {this._id = id;}

    @Override
    public void execute() throws SQLException {

    }
    @Override
    public ArrayList<Privilege> Return() {
        ArrayList<Privilege> priList = new ArrayList<>();

        try{

            DAOPrivilege dao = new DAOPrivilege();
            priList = dao.findPrivilegesByUserId(_id);

        }

        catch (Exception e){

        }
        return priList;
    }
}
