package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.StatusHandler;
import Logic.Command;

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
        flag = StatusHandler.postTemplateStatusAprovado(templateId,userId);
    }

    @Override
    public Boolean Return() {
        return flag;
    }

}
