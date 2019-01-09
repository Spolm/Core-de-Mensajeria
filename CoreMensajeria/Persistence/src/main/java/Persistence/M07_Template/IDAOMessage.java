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
    public boolean postParametersOfMessage(int messageId, String[] parameters, int companyId);
    public Entity postMessage(String message, int companyId,String[] parameters, int templateId );
    public void updateMessage( String message, int templateId, String[] parameters,int companyId );
    public void updateParameterOfMessage(int messageId, String[] parameters, int companyId);
}
