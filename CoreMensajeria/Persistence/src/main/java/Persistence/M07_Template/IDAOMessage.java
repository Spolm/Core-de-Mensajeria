package Persistence.M07_Template;

import Entities.Entity;

import java.util.ArrayList;

public interface IDAOMessage {
    public ArrayList<Entity> getAll();
    public Entity get( int id );
    public Entity getMessageByTemplate( int id );
    public void postParametersOfMessage( int messageId, String[] parameters, int companyId );
}
