package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Logic.Command;

import java.util.ArrayList;

public class CommandGetTemplates extends Command {

    private int userId;
    private int companyId;
    ArrayList templateArrayList;

    public CommandGetTemplates() {
    }

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
    public ArrayList Return() {
        return templateArrayList;
    }

}
