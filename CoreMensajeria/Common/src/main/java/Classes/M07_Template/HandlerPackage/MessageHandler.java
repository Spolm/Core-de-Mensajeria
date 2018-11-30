package Classes.M07_Template.HandlerPackage;

import Classes.M07_Template.MessagePackage.Message;
import Classes.M07_Template.MessagePackage.Parameter;
import Classes.M07_Template.Template;
import Classes.Sql;
import Exceptions.MessageDoesntExistsException;

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

    public static Message getMessage(int templateId) throws MessageDoesntExistsException{
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
        }catch (SQLException e) {
            e.printStackTrace();
            throw new MessageDoesntExistsException
                ("Error: No existe mensaje para esta plantilla.", e, templateId);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (sql.getConn() != null) {
                Sql.bdClose(sql.getConn());
            }
            return message;
        }
    }

    public static void postMessage(String message, int templateId) {
        String query = "INSERT INTO public.Message(mes_text,mes_template)" +
                "VALUES ('" + message + "'," + templateId + ") returning mes_id";
        sql = new Sql();
        int messageId;
        try{
            ResultSet resultSet = sql.sqlConn(query);
            if (resultSet.next())
                messageId=resultSet.getInt("mes_id");

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            Sql.bdClose(sql.getConn());
        }
    }
}
