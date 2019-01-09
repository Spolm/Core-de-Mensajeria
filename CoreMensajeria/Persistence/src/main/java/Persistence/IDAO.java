package Persistence;

import Entities.Entity;

import java.sql.SQLException;

public interface IDAO {

    public void create(Entity e) throws SQLException;

    public Entity read(Entity e) ;

    public Entity update(Entity e);
}
