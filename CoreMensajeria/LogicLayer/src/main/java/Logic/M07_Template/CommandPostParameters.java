package Logic.M07_Template;

import DTO.M07_Template.NewParameter;
import Entities.Entity;
import Entities.M07_Template.HandlerPackage.ParameterHandler;
import Logic.Command;

public class CommandPostParameters extends Command {

    private NewParameter newParameter;

    public CommandPostParameters(NewParameter newParameter){
        this.newParameter = newParameter;
    }

    public CommandPostParameters() {
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
