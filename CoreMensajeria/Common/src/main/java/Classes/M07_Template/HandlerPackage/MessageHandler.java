package Classes.M07_Template.HandlerPackage;

import Classes.M07_Template.MessagePackage.Message;
import Classes.M07_Template.MessagePackage.Parameter;
import Classes.M07_Template.Template;
import Classes.Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessageHandler {
    public static Sql sql;

    public MessageHandler() {
        sql = new Sql();
    }

    /*
     *
      Algo ineficiente, refactorisable
     */
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
                /*
                ResultSet resultSetAux = sql.sqlConn(
                        "SELECT PAR_ID,PAR_NAME FROM PARAMETER P " +
                                "INNER JOIN MESSAGE_PARAMETER MP " +
                                "ON P.PAR_ID = MP.MP_PARAMETER " +
                                "WHERE MP.MP_MESSAGE = " + message.getMessageId());
                ArrayList<Parameter> parameterArrayList = new ArrayList<>();
                while(resultSetAux.next()) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterId(resultSetAux.getInt("par_id"));
                    parameter.setName(resultSetAux.getString("par_name"));
                    parameterArrayList.add(parameter);
                }
                message.setParameters(parameterArrayList);
                */
                templateArrayList.get(x).setMessage(message);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            return templateArrayList;
        }
    }

    public static Message getMessage(int templateId){
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
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            return message;
        }
    }
}
