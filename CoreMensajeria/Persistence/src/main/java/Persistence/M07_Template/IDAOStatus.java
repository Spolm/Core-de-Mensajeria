package Persistence.M07_Template;

import Entities.Entity;
import Entities.M07_Template.Template;
import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;

import java.util.ArrayList;

public interface IDAOStatus {
    public Boolean postTemplateStatusApproved(int templateId, int userId);
    public Boolean postTemplateStatusNotApproved(int templateId);
    public void deleteStatusTemplate(int id);
}
