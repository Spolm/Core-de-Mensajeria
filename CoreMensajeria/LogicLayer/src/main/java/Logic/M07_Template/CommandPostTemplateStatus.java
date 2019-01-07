package Logic.M07_Template;

import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M07_Template.DAOStatus;

public class CommandPostTemplateStatus extends Command {

    private int templateId;
    private int userId;
    private boolean flag;

    public CommandPostTemplateStatus(int templateId, int userId) {
        this.templateId = templateId;
        this.userId = userId;
        this.flag = false;
    }

    public CommandPostTemplateStatus() {
    }

    @Override
    public void execute() throws Exception {
        DAOStatus daoStatus = DAOFactory.createDAOStatus();
        //flag = StatusHandler.postTemplateStatusAprovado(templateId,userId);
        flag = daoStatus.postTemplateStatusApproved(templateId,userId);
    }

    @Override
    public Boolean Return() {
        return flag;
    }

}
