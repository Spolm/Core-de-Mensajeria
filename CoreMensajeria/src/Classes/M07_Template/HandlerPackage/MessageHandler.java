package Classes.M07_Template.HandlerPackage;

import Classes.M07_Template.MessagePackage.Message;
import Classes.M07_Template.MessagePackage.Parameter;
import Classes.Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessageHandler {
    public Sql sql;

    public MessageHandler() {
        sql = new Sql();
    }

    /*
     * Algo ineficiente, refactorisable
     */
    public ArrayList<Message> getMessages(){
        ArrayList<Message> messagesArrayList = new ArrayList<>();
        try {
            ResultSet resultSet = sql.sqlConn("SELECT * FROM PUBLIC.MESSAGE");
            while(resultSet.next()){
                Message message = new Message();
                message.setMessageId(resultSet.getInt("mes_id"));
                message.setMessage(resultSet.getString("mes_text"));
                ResultSet resultSetAux = sql.sqlConn(
                        "SELECT PAR_ID,PAR_NAME FROM PARAMETER P" +
                        "INNER JOIN MESSAGE_PARAMETER MP" +
                        "ON P.PAR_ID = MP.MP_PARAMETER" +
                        "WHERE MP.MP_MESSAGE = " + message.getMessageId());
                while(resultSetAux.next()) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterId(resultSetAux.getInt("par_id"));
                    parameter.setName(resultSetAux.getString("par_name"));
                    message.addParameter(parameter);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            return messagesArrayList;
        }
    }

}
