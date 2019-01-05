package Logic.M07_Template;

import Entities.Entity;
import Logic.Command;

public class CommandGetTemplate extends Command {

    private static Entity us;

    public CommandGetTemplate(Entity u){
        this.us = u;
    }

    public CommandGetTemplate() {
    }

    @Override
    public void execute() throws Exception {

    }

    @Override
    public Entity Return() {
        return null;
    }

}
