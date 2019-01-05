package Logic.M07_Template;

import Entities.Entity;
import Logic.Command;

public class CommandPostParameter extends Command {

    private static Entity us;

    public CommandPostParameter(Entity u){
        this.us = u;
    }

    public CommandPostParameter() {
    }

    @Override
    public void execute() throws Exception {

    }

    @Override
    public Entity Return() {
        return null;
    }

}
