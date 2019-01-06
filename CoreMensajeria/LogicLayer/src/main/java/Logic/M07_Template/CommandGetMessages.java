package Logic.M07_Template;

import Entities.Entity;
import Logic.Command;

public class CommandGetMessages extends Command {

    private static Entity us;

    public CommandGetMessages(Entity u){
        this.us = u;
    }

    public CommandGetMessages(){}

    @Override
    public void execute() throws Exception {

    }

    @Override
    public Entity Return() {
        return null;
    }

}
