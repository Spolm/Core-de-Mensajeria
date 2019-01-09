package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.ParameterHandler;
import Entities.M07_Template.MessagePackage.Parameter;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M07_Template.DAOParameter;

import java.util.ArrayList;

public class CommandGetParameters extends Command {
    private int companyId;
    private ArrayList<Parameter> parameterList;

    public CommandGetParameters(int companyId) {
        this.companyId = companyId;
    }

    public CommandGetParameters() {
    }

    @Override
    public void execute() throws Exception {
        //ParameterHandler parameterHandler = new ParameterHandler();
        //parameterList = parameterHandler.getParameters(companyId);
        DAOParameter daoParameter = DAOFactory.instaciateDaoParameter();
        parameterList = daoParameter.getParameters(companyId);
    }

    @Override
    public ArrayList<Parameter> Return() {
        return parameterList;
    }

}
