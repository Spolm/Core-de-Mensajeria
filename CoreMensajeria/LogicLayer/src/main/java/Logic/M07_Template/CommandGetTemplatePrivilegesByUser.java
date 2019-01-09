package Logic.M07_Template;

import Entities.Entity;
import Entities.M01_Login.Privilege;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M07_Template.DAOTemplate;

import java.util.ArrayList;

public class CommandGetTemplatePrivilegesByUser extends Command {

    private int userId;
    private int companyId;
    private ArrayList<Privilege> privileges;

    public CommandGetTemplatePrivilegesByUser(int userId, int companyId) {
        this.userId = userId;
        this.companyId = companyId;
    }

    public CommandGetTemplatePrivilegesByUser() {
    }

    @Override
    public void execute() throws Exception {
        //TemplateHandler templateHandler = new TemplateHandler();
        //privileges = templateHandler.getTemplatePrivilegesByUser(userId,companyId);
        DAOTemplate daoTemplate = DAOFactory.instaciateDaoTemplate();
        privileges = daoTemplate.getTemplatePrivilegesByUser(userId,companyId);
    }

    @Override
    public ArrayList<Privilege> Return() {
        return privileges;
    }

}
