package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.ParameterHandler;
import DTO.M07_Template.NewParameter;
import Logic.Command;

public class CommandPostParameter extends Command {

    private NewParameter newParameter;

    public CommandPostParameter(NewParameter newParameter){
        this.newParameter = newParameter;
    }

    public CommandPostParameter() {
    }

    @Override
    public void execute() throws Exception {
        ParameterHandler parameterHandler = new ParameterHandler();
        parameterHandler.postParameter(newParameter.getName(),newParameter.getCompanyId());
    }

    @Override
    public Entity Return() {
        return null;
    }

}
