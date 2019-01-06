package Entities.M07_Template.HandlerPackage;

import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.Template;
import Entities.Sql;
import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * MessageHandler class used for the management of information
 * search in the database with respect to Message.
 */
public class MessageHandler {
    /**
     * connection to the database
     */
    public static Sql sql;

    /**
     * constructor without receiving parameters that instantiates
     * the sql attribute of the MessageHandler class.
     */
    public MessageHandler() {
        sql = new Sql();
    }

    /**
     * this method returns a list of templates where each contains
     * its message and associated parameters.
     * @param templateArrayList arrayList of template without message
     * @return arrayList of template with message
     */
    public ArrayList<Template> getMessages(ArrayList<Template> templateArrayList){
        try {
            Connection connection = Sql.getConInstance();
            PreparedStatement preparedStatement = connection.prepareCall("{call m07_select_messages(?)}");
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
            Sql.bdClose(sql.getConn());
            return templateArrayList;
        }
    }

    /**
     * this method returns the message that is associated
     * with the specified template.
     * @param templateId template id
     * @return message template message specified
     * @throws MessageDoesntExistsException the specified template has no associated message
     * @throws ParameterDoesntExistsException the specified message has no associated parameters
     */
    public static Message getMessage(int templateId)
            throws MessageDoesntExistsException, ParameterDoesntExistsException{
        Message message = new Message();
        Connection connection = Sql.getConInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareCall("{call m07_select_message(?)}");
            preparedStatement.setInt(1, templateId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                message.set_id(resultSet.getInt("messageId"));
                message.setMessage(resultSet.getString("messageText"));
                message.setParameters(ParameterHandler.getParametersByMessage(message.get_id()));
            }
        }catch (ParameterDoesntExistsException e) {
            throw new ParameterDoesntExistsException
                    ("No hay parametros para template con id:"
                            + templateId,e, templateId);
        }catch (SQLException e) {
            throw new MessageDoesntExistsException
                    ("Error: No existe mensaje para esta plantilla con id:"
                            + templateId, e, templateId);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            Sql.bdClose(connection);
            return message;
        }
    }

    /**
     * this method is responsible for storing the message of a template
     * and all its attributes.
     * @param message string message with the parameters included
     * @param templateId template id
     * @param parameters list of associated parameters
     * @param companyId company id
     */
    public static void postMessage(String message, int templateId, String[] parameters,int companyId) {
        String query = "INSERT INTO public.Message(mes_text,mes_template)" +
                "VALUES ('" + message + "'," + templateId + ") returning mes_id";
        sql = new Sql();
        int messageId=0;
        try{
            ResultSet resultSet = sql.sqlConn(query);
            if (resultSet.next()) {
                messageId = resultSet.getInt("mes_id");
                postParameterOfMessage(messageId,parameters,companyId);
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            Sql.bdClose(sql.getConn());
        }
    }

    /**
     * this method is responsible for storing the parameters associated
     * with a specific message.
     * @param messageId message id
     * @param parameters list of associated parameters
     * @param companyId company id
     */
    private static void postParameterOfMessage(int messageId, String[] parameters, int companyId) {
        sql = new Sql();
        String query = "";
        try{
            for (int i = 0; i < parameters.length; i++)
                query = query + "insert into public.message_parameter(mp_message,mp_parameter)\n" +
                        "values (" + messageId + ",(select par_id \n" +
                        "from public.parameter\n" +
                        "where par_company_id = " + companyId + "  and par_name = '" + parameters[i] +"'));";
            sql.sqlNoReturn(query);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            Sql.bdClose(sql.getConn());
        }
    }

    /**
     * this method is responsible for modify the message of a template
     * and all its attributes.
     * @param message string message with the parameters included
     * @param templateId template id
     * @param parameters list of associated parameters
     * @param companyId company id
     */
    public static void updateMessage(String message, int templateId, String[] parameters,int companyId) {
        String query = "UPDATE public.message\n" +
                "SET mes_text = '" + message + "'\n" +
                "WHERE mes_template = " + templateId + "\n" +
                "returning mes_id";
        sql = new Sql();
        ResultSet resultSet;
        int messageId;
        try{
            resultSet = sql.sqlConn(query);
            if (resultSet.next()){
                messageId = resultSet.getInt("mes_id");
                updateParameterOfMessage(messageId,parameters,companyId);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            Sql.bdClose(sql.getConn());
        }
    }

    /**
     * his method is responsible for modifying the parameters associated
     * with a specific message.
     * @param messageId message id
     * @param parameters list of associated parameters
     * @param companyId company id
     */
    private static void updateParameterOfMessage(int messageId, String[] parameters, int companyId) {
        String query = "delete from public.message_parameter\n" +
                "WHERE mp_message = " + messageId;
        Sql sql = new Sql();
        try{
            sql.sqlNoReturn(query);
            postParameterOfMessage(messageId,parameters,companyId);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            Sql.bdClose(sql.getConn());
        }
    }
}
