package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Logic.Command;

public class CommandPostTemplate extends Command {

    private String json;
    private Boolean rest;

    public CommandPostTemplate(String json) {
        this.json = json;
    }

    public CommandPostTemplate() {
    }

    @Override
    public void execute() throws Exception {
        TemplateHandler templateHandler = new TemplateHandler();
        rest = templateHandler.postTemplateData(json);
    }

    @Override
    public Boolean Return() {
        return rest;
    }

}
