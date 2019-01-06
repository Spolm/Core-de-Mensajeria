package Logic.M07_Template;

import Entities.Entity;
import Logic.Command;

public class CommandGetParameters extends Command {

    private static Entity us;

    public CommandGetParameters(Entity u){
        this.us = u;
    }

    public CommandGetParameters() {
    }

    @Override
    public void execute() throws Exception {

    }

    @Override
    public Entity Return() {
        return null;
    }

}
