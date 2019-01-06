package Logic;

import Entities.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Command < T >{
    private Entity entity ;

    public Entity getEntity() { return entity; }

    public void setEntity(Entity entity) { this.entity = entity; }

    public abstract void execute() throws Exception;

    public abstract T Return() ;

    //public abstract ArrayList<Entity> ReturnList() ;

}
