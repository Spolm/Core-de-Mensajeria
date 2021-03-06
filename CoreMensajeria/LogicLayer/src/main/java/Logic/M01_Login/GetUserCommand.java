package Logic.M01_Login;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M01_Login.DAOUser;

import java.sql.SQLException;

public class GetUserCommand extends Command {

    private static Entity us;

    public GetUserCommand(Entity u){
        this.us = u;
    }

    @Override
    public void execute() throws SQLException {
        try {
            DAOUser dao = DAOFactory.instanciateDaoUser();
        }
        catch (Exception e){

        }
    }

    @Override
    public Entity Return() {
        return us;
    }

}
