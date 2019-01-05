package Logic.M07_Template;

import Entities.Entity;
import Logic.Command;

public class CommandGetTemplatePrivilegesByUser extends Command {

    private static Entity us;

    public CommandGetTemplatePrivilegesByUser(Entity u){
        this.us = u;
    }

    public CommandGetTemplatePrivilegesByUser() {
    }

    @Override
    public void execute() throws Exception {

    }

    @Override
    public Entity Return() {
        return null;
    }

}
