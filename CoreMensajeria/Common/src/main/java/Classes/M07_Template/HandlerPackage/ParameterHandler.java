package Classes.M07_Template.HandlerPackage;

import Classes.M07_Template.MessagePackage.Parameter;
import Classes.Sql;
import com.google.gson.JsonArray;
import com.sun.xml.internal.ws.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParameterHandler {
    public static Sql sql;

    public ParameterHandler() {
        sql = new Sql();
    }

    public static void postParameter(String[] parameters, int companyId){
        for (int i = 0; i < parameters.length;i++)
            postParameter(parameters[i],companyId);
    }
    public static void postParameter(String name, int companyId) {
        Sql sql = new Sql();
        String query = "select par_id \n" +
                "from public.parameter \n" +
                "where par_company_id = " + companyId + "  and par_name = '" + name +"'";


        try {
            ResultSet resultSet = sql.sqlConn(query);
            if (!resultSet.next()) {
                query = "INSERT INTO public.Parameter (par_name,par_company_id) \n" +
                        "VALUES ('" + name + "'," + companyId + ")";
                sql.sqlNoReturn(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<Parameter> getParameters(int companyId) {
        String query = "select par_id,par_name \n" +
                "from public.parameter\n" +
                "where par_company_id =" + companyId +"\n" +
                "order by par_name";
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
