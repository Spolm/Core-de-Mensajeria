package Logic.M07_Template;

import Entities.Entity;
import Logic.Command;

public class CommandGetTemplates extends Command {

    private static Entity us;

    public CommandGetTemplates(Entity u){
        this.us = u;
    }

    public CommandGetTemplates() {
    }

    @Override
    public void execute() throws Exception {

    }

    @Override
    public Entity Return() {
        return null;
    }

}
