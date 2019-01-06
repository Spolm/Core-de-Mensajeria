package Logic.M07_Template;

import Entities.Entity;
import Logic.Command;

public class CommandPostTemplateStatus extends Command {

    private static Entity us;

    public CommandPostTemplateStatus(Entity u){
        this.us = u;
    }

    public CommandPostTemplateStatus() {
    }

    @Override
    public void execute() throws Exception {

    }

    @Override
    public Entity Return() {
        return null;
    }

}
