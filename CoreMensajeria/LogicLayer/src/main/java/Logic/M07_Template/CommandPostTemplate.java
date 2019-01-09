package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M07_Template.DAOTemplate;

public class CommandPostTemplate extends Command {

    private String json;
    private Template rest;

    public CommandPostTemplate(String json) {
        this.json = json;
    }

    public CommandPostTemplate() {
    }

    @Override
    public void execute() throws Exception {
        //TemplateHandler templateHandler = new TemplateHandler();
        //rest = templateHandler.postTemplateData(json);
        DAOTemplate daoTemplate = DAOFactory.instaciateDaoTemplate();
        rest = (Template) daoTemplate.postTemplateData(json);
    }

    @Override
    public Entity Return() {
        return rest;
    }

}
