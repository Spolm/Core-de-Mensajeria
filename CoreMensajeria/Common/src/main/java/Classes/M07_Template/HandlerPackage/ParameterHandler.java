package Classes.M07_Template.HandlerPackage;

import Classes.M07_Template.MessagePackage.Parameter;
import Classes.Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParameterHandler {
    public static Sql sql;

    public ParameterHandler() {
        sql = new Sql();
    }

    public void postParameter(String name) {
        String query = "INSERT INTO public.Parameter (par_name) \n" +
                "VALUES ('"+name+"')";
        try {
            sql.sqlNoReturn(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<Parameter> getParameters() {
        String query = "select par_id,par_name " +
                "from public.parameter";
        return executeParameterQuery(query);
    }

    public static ArrayList<Parameter> getParametersByMessage(int messageId) {
        String query = "select par_id,par_name " +
                "from parameter,message_parameter " +
                "where par_id = mp_parameter and mp_message = " + messageId;
        return executeParameterQuery(query);
    }

    private static ArrayList<Parameter> executeParameterQuery(String query) {
        ArrayList<Parameter> parameterList = new ArrayList<>();
        sql = new Sql();
        try {
            ResultSet resultSet = sql.sqlConn(query);
            while(resultSet.next()){
                Parameter parameter = new Parameter();
                parameter.setParameterId(resultSet.getInt("par_id"));
                parameter.setName(resultSet.getString("par_name"));
                parameterList.add(parameter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            Sql.bdClose(sql.getConn());
            return parameterList;
        }
    }

}
