package Persistence.M07_Template;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M03_Campaign.Campaign;
import Entities.M07_Template.MessagePackage.Message;
import Entities.Sql;
import Persistence.DAO;
import Persistence.M03_Campaign.DAOCampaign;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOMessage extends DAO implements IDAOMessage {

    final String CREATE_MESSAGE= "{CALL m07_insert_message(?,?)}";
    final String GET_MESSAGE= "{CALL m07_get_message(?)}";
    final String GET_MESSAGE_BY_TEMPLATE= "{CALL m07_get_message_by_template(?)}";
    final String GET_ALL_MESSAGES = "{CALL m07_get_all_messages()}";
    final String UPDATE_MESSAGE = "{CALL m07_update_message(?,?)}";
    final String POST_PARAMETER_OF_MESSAGE = "{CALL m07_postparameterofmessage(?,?,?)}";

    /**
     * Add message to Database
     * @param e
     */
    @Override
    public void create(Entity e) {
        Message _me = (Message) e;
        Connection _conn = this.getBdConnect();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = _conn.prepareCall( CREATE_MESSAGE );
            preparedStatement.setString( 1, _me.getMessage() );
            preparedStatement.setInt( 2, _me.getTemplateId() );
            preparedStatement.execute();
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
                /*_m.add( EntityFactory.message(
                        _rs.getInt("mes_id"),
                        _rs.getString("mes_text"),
                        _rs.getInt("mes_template")
                ) );*/
            }


        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }

        this.closeConnection();
        return _m;
    }

    @Override
    public Entity getMessageByTemplate(int id) {
        Entity _m  = null;
        Connection _conn = this.getBdConnect();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = _conn.prepareCall( GET_MESSAGE_BY_TEMPLATE );
            preparedStatement.setInt( 1, id );
            ResultSet _rs = preparedStatement.executeQuery();

            _m = null;
            /*_m = EntityFactory.message(
                    _rs.getInt("mes_id"),
                    _rs.getString("mes_text"),
                    _rs.getInt("mes_template")
            );*/

        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }

        this.closeConnection();
        return _m;
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

            _m = null;
            /*_m = EntityFactory.message(
                    _rs.getInt("mes_id"),
                    _rs.getString("mes_text"),
                    _rs.getInt("mes_template")
            );*/

        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }

        this.closeConnection();
        return _m;
    }


    @Override
    public void postParametersOfMessage(int messageId, String[] parameters, int companyId) {

        Connection _conn = this.getBdConnect();
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = _conn.prepareCall( GET_MESSAGE_BY_TEMPLATE );

            for (int i = 0; i < parameters.length; i++){
                preparedStatement.setInt( 1, messageId );
                preparedStatement.setInt( 2, companyId );
                preparedStatement.setString( 3, parameters[i]);

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
}
