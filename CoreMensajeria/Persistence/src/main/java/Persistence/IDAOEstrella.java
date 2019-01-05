package Persistence;

import Entities.Entity;

public interface IDAOEstrella {

    public void create(Entity e) ;

    public Entity read(Entity e) ;

    public Entity update(Entity e);
}
