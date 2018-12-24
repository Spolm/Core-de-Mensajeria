package Persistence.M01_Login;

import Entities.Entity;
import Persistence.IDAO;

import java.sql.SQLException;

public interface IDAOUser extends IDAO {

    Entity user(Entity e) throws SQLException;

}
