package Classes.M07_Template.HandlerPackage;

import Classes.M07_Template.MessagePackage.Message;
import Classes.M07_Template.MessagePackage.Parameter;
import Classes.M07_Template.Template;
import Classes.Sql;
import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;
import com.google.gson.JsonArray;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessageHandler {
    public static Sql sql;

    public MessageHandler() {
        sql = new Sql();
    }

    public ArrayList<Template> getMessages(ArrayList<Template> templateArrayList){
        try {
            for(int x = 0; x < templateArrayList.size(); x++){
                ResultSet resultSet = sql.sqlConn("SELECT * FROM PUBLIC.MESSAGE WHERE MES_TEMPLATE = " +
                        templateArrayList.get(x).getTemplateId());
                resultSet.next();
                Message message = new Message();
                message.setMessageId(resultSet.getInt("mes_id"));
                message.setMessage(resultSet.getString("mes_text"));
                message.setParameters(ParameterHandler.getParametersByMessage(message.getMessageId()));
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

    public static Message getMessage(int templateId)
            throws MessageDoesntExistsException, ParameterDoesntExistsException{
        String query = "select mes_id,mes_text from message where mes_template =" + templateId;
        Message message = new Message();
        sql = new Sql();
        try {
            ResultSet resultSet = sql.sqlConn(query);
            if (resultSet.next()){
                message.setMessageId(resultSet.getInt("mes_id"));
                message.setMessage(resultSet.getString("mes_text"));
                message.setParameters(ParameterHandler.getParametersByMessage(message.getMessageId()));
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
            if (sql.getConn() != null) {
                Sql.bdClose(sql.getConn());
            }
            return message;
        }
    }

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
