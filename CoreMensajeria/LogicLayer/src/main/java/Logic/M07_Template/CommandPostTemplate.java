package Logic.M07_Template;

import Entities.Entity;
import Logic.Command;

public class CommandPostTemplate extends Command {

    private static Entity us;

    public CommandPostTemplate(Entity u){
        this.us = u;
    }

    public CommandPostTemplate() {
    }

    @Override
    public void execute() throws Exception {

    }

    @Override
    public Entity Return() {
        return null;
    }

}
