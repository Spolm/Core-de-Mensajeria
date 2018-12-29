package Persistence;

import Entities.Entity;

public interface IDAO {

    public Entity create(Entity e) ;

    public Entity read(Entity e) ;

    public Entity update(Entity e);
}
