package Persistence.M07_Template;

import Entities.M07_Template.MessagePackage.Parameter;
import Exceptions.ParameterDoesntExistsException;
import Persistence.IDAO;

import java.util.ArrayList;

public interface IDAOParameter extends IDAO {
    void postParameter(String[] parameters, int companyId);
    void postParameter(String name, int companyId);
    ArrayList<Parameter> getParameters(int companyId)
            throws ParameterDoesntExistsException;
    ArrayList<Parameter> getParametersByMessage(int messageId)
            throws ParameterDoesntExistsException;
}
