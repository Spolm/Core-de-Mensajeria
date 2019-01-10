package Persistence.M07_Template;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M03_Campaign.Campaign;
import Entities.M07_Template.HandlerPackage.ParameterHandler;
import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.Template;
import Entities.Sql;
import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;
import Persistence.DAO;
import Persistence.DAOFactory;
import Persistence.M03_Campaign.DAOCampaign;

import javax.ws.rs.POST;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOMessage extends DAO implements IDAOMessage {

    final String CREATE_MESSAGE= "{CALL m07_insertmessage(?,?)}";
    final String GET_MESSAGE_BY_TEMPLATE= "{CALL m07_select_message(?)}";
    final String GET_ALL_MESSAGES = "{CALL m07_select_messages(?)}";
    final String UPDATE_MESSAGE = "{CALL m07_updatemessage(?,?)}";
    final String POST_PARAMETER_OF_MESSAGE = "{CALL m07_postparameterofmessage(?,?,?)}";
    final String DELETE_MESSAGE = "{CALL m07_deletemessage(?)}";
    final String DELETE_MESSAGE_BY_ID = "{CALL m07_deletemessagebyid(?)}";

    /**
     * Add message to Database
     * @param e
     */
    @Override
    public void create(Entity e) {

    }

    /**
     * Post Message
     * @param message
     * @param companyId
     * @param parameters
     * @param templateId
     */
    @Override
    public Entity postMessage(String message, int companyId,String[] parameters, int templateId ) {
        Message _me = null;
        Connection _conn = this.getBdConnect();
        int messageId = 0;

        PreparedStatement preparedStatement = null;

        try {
            //Insert the new Message on DB
            preparedStatement = _conn.prepareCall( CREATE_MESSAGE );
            preparedStatement.setString( 1, message );
            preparedStatement.setInt( 2, templateId );
            ResultSet _rs = preparedStatement.executeQuery();

            //Get the ID of the inserted message and insert Parameters
            if(_rs.next())
            messageId = _rs.getInt(1);
            this.postParametersOfMessage(messageId, parameters,companyId);
            _me = ( Message )this.getMessage(templateId);

        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        } catch ( ParameterDoesntExistsException e ){
            e.printStackTrace();
        } catch ( MessageDoesntExistsException e ){
            e.printStackTrace();
        }finally{
            this.closeConnection();
            return _me;
        }
    }

    /**
     * Search a specific message on the DB
     * @param e
     * @return message
     */
    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }

    /**
     * Finds message by template
     * @param templateId
     * @return
     * @throws ParameterDoesntExistsException
     * @throws MessageDoesntExistsException
     */
    @Override
    public Entity getMessage(int templateId) throws ParameterDoesntExistsException, MessageDoesntExistsException{
        Entity _m  = null;
        Connection _conn = this.getBdConnect();
        DAOParameter _daoParameter = DAOFactory.instaciateDaoParameter();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = _conn.prepareCall( GET_MESSAGE_BY_TEMPLATE );
            preparedStatement.setInt( 1, templateId );
            ResultSet _rs = preparedStatement.executeQuery();

            if(_rs.next())
            _m = this.createMessage(_rs);

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new MessageDoesntExistsException
                    ("Error: No existe mensaje para esta plantilla con id:"
                            + templateId, e, templateId);
        } catch ( ParameterDoesntExistsException e ){
            throw new ParameterDoesntExistsException( "No hay mensajes para la plantilla " + templateId, e, templateId );
        } catch ( Exception e ){
            e.printStackTrace();
        }

        this.closeConnection();
        return _m;
    }

    /**
     * Get all the messages
     * @return
     */
    @Override
    public ArrayList<Template> getMessages(ArrayList<Template> templateArrayList){
        try {
            Connection _conn = this.getBdConnect();
            PreparedStatement preparedStatement = _conn.prepareCall( GET_ALL_MESSAGES );
            for(int x = 0; x < templateArrayList.size(); x++){
                preparedStatement.setInt(1, templateArrayList.get(x).get_id());
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                Message message = new Message();
                message.set_id(resultSet.getInt("messageId"));
                message.setMessage(resultSet.getString("messageText"));
                message.setParameters(ParameterHandler.getParametersByMessage(message.get_id()));
                templateArrayList.get(x).setMessage(message);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            this.closeConnection();
            return templateArrayList;
        }
    }




    @Override
    public boolean postParametersOfMessage(int messageId, String[] parameters, int companyId) {

        Connection _conn = this.getBdConnect();
        PreparedStatement preparedStatement = null;
        boolean _res = false;

        try{
            preparedStatement = _conn.prepareCall(POST_PARAMETER_OF_MESSAGE);

            for (int i = 0; i < parameters.length; i++ ){
                preparedStatement.setInt( 1, messageId );
                preparedStatement.setInt( 2, companyId );
                preparedStatement.setString( 3, parameters[i]);

                preparedStatement.execute();

                _res = true;
            }

        }catch ( SQLException e1 ) {
            e1.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.closeConnection();
            return _res;
        }
    }

    @Override
    public void updateMessage( String message, int templateId, String[] parameters,int companyId ) {
        Message _me = null;
        Connection _conn = this.getBdConnect();
        int messageId = 0;

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = _conn.prepareCall( UPDATE_MESSAGE );
            preparedStatement.setString( 1, message );
            preparedStatement.setInt( 2, templateId );
            ResultSet _rs = preparedStatement.executeQuery();

            //Get the ID of the inserted message and insert Parameters
            if(_rs.next())
            messageId = _rs.getInt(1);

            this.updateParameterOfMessage(messageId, parameters,companyId);
        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }

        this.closeConnection();
    }

    @Override
    public void updateParameterOfMessage(int messageId, String[] parameters, int companyId) {

        Connection _conn = this.getBdConnect();
        PreparedStatement _ps = null;
        try{

            _ps = _conn.prepareCall( DELETE_MESSAGE );
            _ps.setInt(1, messageId);
            _ps.execute();

            this.postParametersOfMessage(messageId,parameters,companyId);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    /**
     * Delete a message from DB
     * @param _messageId
     */
    public void deleteMessage(int _messageId){
        try{
            Connection _conn = this.getBdConnect();
            PreparedStatement _ps = null;

            _ps = _conn.prepareCall(DELETE_MESSAGE_BY_ID);
            _ps.setInt(1,_messageId);
            _ps.execute();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.closeConnection();
        }
    }

    /**
     * Creates message entity
     * @param _rs
     * @return
     */
    private Entity createMessage( ResultSet _rs )throws ParameterDoesntExistsException{
        Entity _m = null;
        try{
            //Parameters
            ArrayList<Parameter> _parameters = DAOFactory.instaciateDaoParameter()
                    .getParametersByMessage( _rs.getInt("messageid"));

            //Create de Message
            _m = EntityFactory.CreateMessage(
                    _rs.getInt("messageid"),
                    _parameters,
                    _rs.getString("messagetext")
                    );

        }catch ( ParameterDoesntExistsException ex ) {
            throw new ParameterDoesntExistsException( ex );
        }catch ( SQLException e1 ) {
            e1.printStackTrace();
        }

        return _m;
    }
}
