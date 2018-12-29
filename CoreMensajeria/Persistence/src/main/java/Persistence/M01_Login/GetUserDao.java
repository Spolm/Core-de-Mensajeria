package Persistence.M01_Login;

import Entities.Entity;
import Persistence.DAO;

import java.sql.SQLException;

public class GetUserDao extends DAO implements  IDAOUser {

    @Override
    public void create(Entity e) { }

    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }

    @Override
    public Entity user(Entity e) throws SQLException {
        return null;
    }
}
