package Logic.M07_Template;

import Entities.Entity;
import Logic.Command;

public class CommandUpdateTemplate extends Command {

    private static Entity us;

    public CommandUpdateTemplate(Entity u){
        this.us = u;
    }

    public CommandUpdateTemplate() {
    }

    @Override
    public void execute() throws Exception {

    }

    @Override
    public Entity Return() {
        return null;
    }

}
