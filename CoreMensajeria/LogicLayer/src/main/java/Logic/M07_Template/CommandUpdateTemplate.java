package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M07_Template.DAOTemplate;

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
        //TemplateHandler templateHandler = new TemplateHandler();
        //rest = templateHandler.updateTemplateData(json);
        DAOTemplate daoTemplate = DAOFactory.instaciateDaoTemplate();
        rest = daoTemplate.updateTemplateData(json);
    }

    @Override
    public Boolean Return() {
        return rest;
    }

}
