package Persistence.M07_Template;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M03_Campaign.Campaign;
import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.Sql;
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
    final String GET_MESSAGE= "{CALL m07_get_message(?)}";
    final String GET_MESSAGE_BY_TEMPLATE= "{CALL m07_select_message(?)}";
    final String GET_ALL_MESSAGES = "{CALL m07_get_all_messages()}";
    final String UPDATE_MESSAGE = "{CALL m07_update_message(?,?)}";
    final String POST_PARAMETER_OF_MESSAGE = "{CALL m07_postparameterofmessage(?,?,?)}";

    /**
     * Add message to Database
     * @param e
     */
    @Override
    public void create(Entity e) {

    }

    /**
     * Adds a message to database
     * @param e
     * @param companyId ID de la compania
     */
    @Override
    public void postMessage(Entity e, int companyId) {
        Message _me = (Message) e;
        Connection _conn = this.getBdConnect();
        int messageId = 0;

        PreparedStatement preparedStatement = null;

        try {
            //Insert the new Message on DB
            preparedStatement = _conn.prepareCall( CREATE_MESSAGE );
            preparedStatement.setString( 1, _me.getMessage() );
            preparedStatement.setInt( 2, _me.getTemplateId() );
            ResultSet _rs = preparedStatement.executeQuery();

            //Get the ID of the inserted message and insert Parameters
            messageId = _rs.getInt(1);
            this.postParametersOfMessage(messageId, _me.getParameterArrayList(),companyId);

        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }

        this.closeConnection();
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

        Message _me = (Message) e;
        Connection _conn = this.getBdConnect();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = _conn.prepareCall( UPDATE_MESSAGE );
            preparedStatement.setInt( 1, _me.get_id() );
            preparedStatement.setInt( 2, _me.getTemplateId() );
            preparedStatement.execute();
        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }

        this.closeConnection();
        return null;
    }

    @Override
    public Entity get(int id) {
        Entity _m  = null;
        Connection _conn = this.getBdConnect();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = _conn.prepareCall( GET_MESSAGE );
            preparedStatement.setInt( 1, id );
            ResultSet _rs = preparedStatement.executeQuery();


            _m = this.createMessage(_rs);


        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }

        this.closeConnection();
        return _m;
    }

    /**
     * Get all the messages
     * @return
     */
    public ArrayList<Entity> getAll(){
        ArrayList<Entity> _m  = null;
        Connection _conn = this.getBdConnect();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = _conn.prepareCall( GET_ALL_MESSAGES );
            ResultSet _rs = preparedStatement.executeQuery();


            while( _rs.next() ){
                Entity _message = this.createMessage(_rs);
                _m.add( _message );
            }


        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }

        this.closeConnection();
        return _m;
    }

    @Override
    public Message getMessageByTemplate(int id) {
        Message _m  = null;
        Connection _conn = this.getBdConnect();
        DAOParameter _daoParameter = DAOFactory.instaciateDaoParameter();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = _conn.prepareCall( GET_MESSAGE_BY_TEMPLATE );
            preparedStatement.setInt( 1, id );
            ResultSet _rs = preparedStatement.executeQuery();

            _m = EntityFactory.CreateMessage(
                    _rs.getInt("messageid"),
                    _daoParameter.getParametersByMessage(id),
                    _rs.getString("messagetext")
                    );

        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( ParameterDoesntExistsException e ){
            e.printStackTrace();
        } catch ( Exception e ){
            e.printStackTrace();
        }

        this.closeConnection();
        return _m;
    }



    @Override
    public void postParametersOfMessage(int messageId, ArrayList<Parameter> parameters, int companyId) {

        Connection _conn = this.getBdConnect();
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = _conn.prepareCall(POST_PARAMETER_OF_MESSAGE);

            for (Parameter parameter : parameters){
                preparedStatement.setInt( 1, messageId );
                preparedStatement.setInt( 2, companyId );
                preparedStatement.setString( 3, parameter.getName());

                preparedStatement.execute();
            }

        }catch ( SQLException e1 ) {
            e1.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    private Entity createMessage( ResultSet _rs ){
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

        }catch ( ParameterDoesntExistsException e1 ) {
            e1.printStackTrace();
        }catch ( SQLException e1 ) {
            e1.printStackTrace();
        }

        return _m;
    }
}
