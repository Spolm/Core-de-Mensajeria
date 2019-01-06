package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Logic.Command;

public class CommandUpdateTemplate extends Command {
    private String json;
    private Boolean rest;

    public CommandUpdateTemplate(String json) {
        this.json = json;
    }

    public CommandUpdateTemplate() {
    }

    @Override
    public void execute() throws Exception {
        TemplateHandler templateHandler = new TemplateHandler();
        rest = templateHandler.updateTemplateData(json);
    }

    @Override
    public Boolean Return() {
        return rest;
    }

}
