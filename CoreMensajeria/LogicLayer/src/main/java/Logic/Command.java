package Logic;

import Entities.Entity;

public abstract class Command {

    public abstract void execute() throws Exception;

    public abstract Entity Return() ;

}
