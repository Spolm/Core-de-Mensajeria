package Persistence.M01_Login;

import Entities.Entity;
import Persistence.IDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDAOPrivilege extends IDAO {

    Entity privilege(Entity e) throws SQLException;
    ArrayList<Entity> getPrivileges() throws SQLException;

}

