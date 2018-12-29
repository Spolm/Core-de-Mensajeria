package Persistence.M01_Login;

import Entities.Entity;
import Entities.M01_Login.User;
import Persistence.IDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDAOUser extends IDAO {

    Entity user(Entity e) throws SQLException;
    ArrayList<User> getUsers(Entity e) throws SQLException;


}
