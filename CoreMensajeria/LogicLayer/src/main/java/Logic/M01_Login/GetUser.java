package Logic.M01_Login;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M01_Login.GetUserDao;

import java.sql.SQLException;

public class GetUser extends Command {

    private static Entity us;

    public GetUser(Entity u){
        this.us = u;
    }

    @Override
    public void execute() throws SQLException {
        try {
            GetUserDao dao = DAOFactory.instanciateDaoUser();
        }
        catch (Exception e){

        }
    }

    @Override
    public Entity Return() {
        return us;
    }

}
