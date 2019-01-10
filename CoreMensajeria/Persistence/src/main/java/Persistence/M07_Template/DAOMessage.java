package Persistence.M07_Template;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M07_Template.HandlerPackage.ParameterHandler;
import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.Template;
import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;
import Persistence.DAO;
import Persistence.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    @Override
    public void create(Entity e) {

    }

    /**
     * Crea un mensaje
     * @param message
     * @param companyId
     * @param parameters
     * @param templateId
     */
    @Override
    public Entity postMessage(String message, int companyId,String[] parameters, int templateId ) {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo postMessage("+message+","+companyId+","+parameters+","+templateId+")" );
        //endregion
        Message _me = null;
        Connection _conn = getBdConnect();
        int messageId = 0;

        PreparedStatement _ps = null;

        try {
            _ps = _conn.prepareCall( CREATE_MESSAGE );
            _ps.setString( 1, message );
            _ps.setInt( 2, templateId );
            ResultSet _rs = _ps.executeQuery();

            if(_rs.next())
            messageId = _rs.getInt(1);
            this.postParametersOfMessage(messageId, parameters,companyId);
            _me = ( Message )this.getMessage(templateId);
            //region Instrumentation Info
            log.info("Se ejecuto el metodo postMessage("+message+","+companyId+","+parameters+","+templateId+") exitosamente");
            //endregion
        } catch ( SQLException e1 ) {
            e1.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo postMessage("+message+","+companyId+","+parameters+","+templateId+") arrojo la excepcion:" + e1.getMessage());
            //endregion
        } catch ( ParameterDoesntExistsException e ){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo postMessage("+message+","+companyId+","+parameters+","+templateId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        } catch ( MessageDoesntExistsException e ){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo postMessage("+message+","+companyId+","+parameters+","+templateId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }finally{
            closeConnection();
            return _me;
        }
    }


    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }

    /**
     * Busca mensaje por plantilla
     * @param templateId
     * @return
     * @throws ParameterDoesntExistsException
     * @throws MessageDoesntExistsException
     */
    @Override
    public Entity getMessage(int templateId) throws ParameterDoesntExistsException, MessageDoesntExistsException{
        //region Instrumentation Debug
        log.debug("Entrando a el metodo getMessage("+templateId+")" );
        //endregion
        Entity _m  = null;
        Connection _conn = getBdConnect();
        DAOParameter _daoParameter = DAOFactory.instaciateDaoParameter();

        PreparedStatement _ps = null;

        try {
            _ps = _conn.prepareCall( GET_MESSAGE_BY_TEMPLATE );
            _ps.setInt( 1, templateId );
            ResultSet _rs = _ps.executeQuery();

            if(_rs.next())
            _m = this.createMessage(_rs);
            //region Instrumentation Info
            log.info("Se ejecuto el metodo getMessage("+templateId+") exitosamente");
            //endregion
        } catch ( SQLException e ) {
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo getMessage("+templateId+") arrojo la excepcion:" + e.getMessage());
            //endregion
            throw new MessageDoesntExistsException
                    ("Error: No existe mensaje para esta plantilla con id:"
                            + templateId, e, templateId);
        } catch ( ParameterDoesntExistsException e ){
            //region Instrumentation Error
            log.error("El metodo getMessage("+templateId+") arrojo la excepcion:" + e.getMessage());
            //endregion
            throw new ParameterDoesntExistsException( "No hay mensajes para la plantilla " + templateId, e, templateId );
        } catch ( Exception e ){
            //region Instrumentation Error
            log.error("El metodo getMessage("+templateId+") arrojo la excepcion:" + e.getMessage());
            //endregion
            e.printStackTrace();
        }

        closeConnection();
        return _m;
    }

    /**
     * Busca todos los mensajes
     * @return
     */
    @Override
    public ArrayList<Template> getMessages(ArrayList<Template> templateArrayList){
        try {
            //region Instrumentation Debug
            log.debug("Entrando a el metodo getMessages("+templateArrayList+")" );
            //endregion
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
            //region Instrumentation Info
            log.info("Se ejecuto el metodo getMessages("+templateArrayList+") exitosamente");
            //endregion
        }catch (SQLException e) {
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo getMessages("+templateArrayList+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }catch(Exception e){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo getMessages("+templateArrayList+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }finally {
            this.closeConnection();
            return templateArrayList;
        }
    }


    /**
     * Guarda relacion entre parametro y mensaje
     * @param messageId
     * @param parameters
     * @param companyId
     * @return
     */
    @Override
    public boolean postParametersOfMessage(int messageId, String[] parameters, int companyId) {

        Connection _conn = getBdConnect();
        PreparedStatement _ps = null;
        boolean _res = false;

        try{
            _ps = _conn.prepareCall(POST_PARAMETER_OF_MESSAGE);

            for (int i = 0; i < parameters.length; i++ ){
                _ps.setInt( 1, messageId );
                _ps.setInt( 2, companyId );
                _ps.setString( 3, parameters[i]);

                _ps.execute();

                _res = true;
            }
            //region Instrumentation Info
            log.info("Se ejecuto el metodo postParametersOfMessage("+messageId+","+parameters+","+companyId+")" );
            //endregion
        }catch ( SQLException e1 ) {
            e1.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo postParametersOfMessage("+messageId+","+parameters+","+companyId+") arrojo la excepcion:" + e1.getMessage());
            //endregion
        }catch (Exception e){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo postParametersOfMessage("+messageId+","+parameters+","+companyId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        } finally {
            closeConnection();
            return _res;
        }
    }

    /**
     * Actualiza mensaje
     * @param message
     * @param templateId
     * @param parameters
     * @param companyId
     */
    @Override
    public void updateMessage( String message, int templateId, String[] parameters,int companyId ) {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo updateMessage("+message+","+templateId+","+parameters+","+companyId+")" );
        //endregion
        Message _me = null;
        Connection _conn = getBdConnect();
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

            updateParameterOfMessage(messageId, parameters,companyId);
            //region Instrumentation Info
            log.info("Se ejecuto el metodo updateMessage("+message+","+templateId+","+parameters+","+companyId+")" );
            //endregion
        } catch ( SQLException e1 ) {
            e1.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo updateMessage("+message+","+templateId+","+parameters+","+companyId+") arrojo la excepcion:" + e1.getMessage());
            //endregion
        }

        closeConnection();
    }

    /**
     * Actualiza relacion entre parametro y mensaje
     * @param messageId
     * @param parameters
     * @param companyId
     */
    @Override
    public void updateParameterOfMessage(int messageId, String[] parameters, int companyId) {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo updateParameterOfMessage("+messageId+","+parameters+","+companyId+")" );
        //endregion
        Connection _conn = getBdConnect();
        PreparedStatement _ps = null;
        try{

            _ps = _conn.prepareCall( DELETE_MESSAGE );
            _ps.setInt(1, messageId);
            _ps.execute();

            this.postParametersOfMessage(messageId,parameters,companyId);
            //region Instrumentation Info
            log.info("Se ejecuto el metodo updateParameterOfMessage("+messageId+","+parameters+","+companyId+")" );
            //endregion
        } catch (Exception e){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo updateParameterOfMessage("+messageId+","+parameters+","+companyId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        } finally {
            closeConnection();
        }
    }

    /**
     * Delete a message from DB
     * @param _messageId
     */
    public void deleteMessage(int _messageId){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo deleteMessage("+_messageId+")" );
        //endregion
        try{
            Connection _conn = this.getBdConnect();
            PreparedStatement _ps = null;

            _ps = _conn.prepareCall(DELETE_MESSAGE_BY_ID);
            _ps.setInt(1,_messageId);
            _ps.execute();
            //region Instrumentation Info
            log.info("Se ejecuto el metodo deleteMessage("+_messageId+")" );
            //endregion
        }catch(Exception e){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo deleteMessage("+_messageId+") arrojo la excepcion:" + e.getMessage());
            //endregion
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
        //region Instrumentation Debug
        log.debug("Entrando a el metodo createMessage("+_rs+")" );
        //endregion
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
            //region Instrumentation Info
            log.info("Se ejecuto el metodo createMessage("+_rs+")" );
            //endregion
        }catch ( ParameterDoesntExistsException ex ) {
            //region Instrumentation Error
            log.error("El metodo createMessage("+_rs+") arrojo la excepcion:" + ex.getMessage());
            //endregion
            throw new ParameterDoesntExistsException( ex );
        }catch ( SQLException e1 ) {
            e1.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo createMessage("+_rs+") arrojo la excepcion:" + e1.getMessage());
            //endregion
        }

        return _m;
    }
}
