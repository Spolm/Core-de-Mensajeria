package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.MessageHandler;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Logic.Command;

import java.util.ArrayList;

public class CommandGetMessages extends Command {

    private static ArrayList<Template> templateList;

    public CommandGetMessages(){}

    @Override
    public void execute() throws Exception {
        TemplateHandler templateHandler = new TemplateHandler();
        ArrayList<Template> templateArrayList = templateHandler.getTemplates();
        MessageHandler messageHandler = new MessageHandler();
        templateList = messageHandler.getMessages(templateArrayList);
    }

    @Override
    public ArrayList<Template> Return() {
        return templateList;
    }



}
