package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M07_Template.DAOTemplate;

public class CommandGetTemplate extends Command {

    private int templateId;
    private Template template;

    public CommandGetTemplate(int templateId){
        this.templateId = templateId;
    }

    public CommandGetTemplate() {
    }

    @Override
    public void execute() throws Exception {
        TemplateHandler templateHandler = new TemplateHandler();
        template = templateHandler.getTemplate(templateId);
        DAOTemplate daoTemplate = DAOFactory.instaciateDaoTemplate();
        template = (Template) daoTemplate.get(templateId);

    }

    @Override
    public Template Return() {
        return template;
    }

}
