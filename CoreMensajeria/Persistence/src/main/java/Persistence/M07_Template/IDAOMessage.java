package Persistence.M07_Template;

import Entities.Entity;
import Entities.M07_Template.MessagePackage.Parameter;

import java.util.ArrayList;

public interface IDAOMessage {
    public ArrayList<Entity> getAll();
    public Entity get( int id );
    public Entity getMessageByTemplate( int id );
    public void postParametersOfMessage(int messageId, ArrayList<Parameter> parameters, int companyId );
    public void postMessage(Entity e, int companyId, int templateId);
    public void updateMessage(Entity e, int companyId, int templateId);
    public void updateParameterOfMessage(int messageId, ArrayList<Parameter> parameters, int companyId);
}
