package Logic;

import Entities.Entity;

public abstract class Command<T> {

    public Entity getEntity() { return entity; }

    public void setEntity(Entity entity) { this.entity = entity; }

    public abstract void execute() throws Exception;

    public abstract T Return() ;

    //public abstract ArrayList<Entity> ReturnList() ;

}
