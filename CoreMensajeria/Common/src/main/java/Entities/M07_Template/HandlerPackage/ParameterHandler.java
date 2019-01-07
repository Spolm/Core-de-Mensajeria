package Entities.M07_Template.HandlerPackage;

import Entities.M07_Template.MessagePackage.Parameter;
import Entities.Sql;
import Exceptions.ParameterDoesntExistsException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ParameterHandler class used for the management of information
 * search in the database with respect to ParameterXML.
 */
public class ParameterHandler {
    /**
     * connection to the database
     */
    public static Sql sql;

    /**
     * constructor without receiving parameters that instantiates
     * the sql attribute of the ParameterHandler class.
     */
    public ParameterHandler() {
        sql = new Sql();
    }

    /**
     * this method is responsible for storing the parameters
     * associated with a specific company.
     * @param parameters list of parameters
     * @param companyId company id
     */
    public static void postParameter(String[] parameters, int companyId){
        for (int i = 0; i < parameters.length;i++)
            postParameter(parameters[i],companyId);
    }

    /**
     * this method is responsible for modifying a parameter
     * associated with a specific company.
     * @param name list of parameters
     * @param companyId company id
     */
    public static void postParameter(String name, int companyId) {
        Sql sql = new Sql();
        String query = "select par_id \n" +
                "from public.parameter \n" +
                "where par_company_id = " + companyId + "  and par_name = '" + name +"'";


        try {
            ResultSet resultSet = sql.sqlConn(query);
            if (!resultSet.next()) {
                query = "INSERT INTO public.ParameterXML (par_name,par_company_id) \n" +
                        "VALUES ('" + name + "'," + companyId + ")";
                sql.sqlNoReturn(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * this method returns a list of parameters filtering by company
     * @param companyId company id
     * @return ArrayList of parameters
     * @throws ParameterDoesntExistsException
     */
    public ArrayList<Parameter> getParameters(int companyId)
            throws ParameterDoesntExistsException{
        String query = "select par_id,par_name \n" +
                "from public.parameter\n" +
                "where par_company_id =" + companyId +"\n" +
                "order by par_name";
        ArrayList<Parameter> parameterList = new ArrayList<>();
        try{
            parameterList = executeParameterQuery(query);
        }catch (ParameterDoesntExistsException e){
            throw new ParameterDoesntExistsException
                    ("No existen parametros para la compania con este id: "
                            + companyId, e, companyId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return parameterList;
        }
    }

    /**
     * this method returns a list of parameters filtering by message
     * @param messageId company id
     * @return ArrayList of parameters
     * @throws ParameterDoesntExistsException
     */
    public static ArrayList<Parameter> getParametersByMessage(int messageId)
            throws ParameterDoesntExistsException{
        String query = "select par_id,par_name " +
                "from parameter,message_parameter " +
                "where par_id = mp_parameter and mp_message = " + messageId;
        ArrayList<Parameter> parameterList = new ArrayList<>();
        try{
            parameterList = executeParameterQuery(query);
        }catch (ParameterDoesntExistsException e){
            throw new ParameterDoesntExistsException
                    ("No existen parametros para el mensaje con este id: "
                            + messageId, e, messageId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return parameterList;
        }
    }

    /**
     * This method is responsible for making queries to obtain
     * the list of parameters, either by filtering by company
     * or filtering by message.
     * @param query string with the query
     * @return ArrayList of parameters
     * @throws ParameterDoesntExistsException
     */
    private static ArrayList<Parameter> executeParameterQuery(String query)
            throws ParameterDoesntExistsException {
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
            throw new ParameterDoesntExistsException();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            Sql.bdClose(sql.getConn());
            return parameterList;
        }
    }

}
