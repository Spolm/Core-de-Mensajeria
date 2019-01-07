package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Logic.Command;

import java.util.ArrayList;

public class CommandGetTemplates extends Command {

    private int userId;
    private int companyId;
    ArrayList<Template> templateArrayList;

    public CommandGetTemplates(int userId, int companyId) {
        this.userId = userId;
        this.companyId = companyId;
    }

    @Override
    public void execute() throws Exception {
        TemplateHandler templateHandler = new TemplateHandler();
        templateArrayList = templateHandler.getTemplates(userId,companyId);
    }

    @Override
    public ArrayList<Template> Return() {
        return templateArrayList;
    }

}
