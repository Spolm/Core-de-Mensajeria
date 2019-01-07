package Persistence.M07_Template;

import Entities.Entity;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.Template;
import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;

import java.util.ArrayList;

public interface IDAOMessage {
    public ArrayList<Template> getMessages(ArrayList<Template> templateArrayList) throws ParameterDoesntExistsException, MessageDoesntExistsException;
    public Entity getMessage( int id ) throws ParameterDoesntExistsException, MessageDoesntExistsException;
    public void postParametersOfMessage(int messageId, ArrayList<Parameter> parameters, int companyId );
    public void postMessage(Entity e, int companyId, int templateId);
    public void updateMessage(Entity e, int companyId, int templateId);
    public void updateParameterOfMessage(int messageId, ArrayList<Parameter> parameters, int companyId);
}
