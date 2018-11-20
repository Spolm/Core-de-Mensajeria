package Classes.M06_DataOrigin;
//import Classes.M01_Login.*;
import Classes.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ApplicationDAO {
    final String QUERY_SELECT_APPLICATION = "SELECT * FROM public.application";
    final String QUERY_DELETE_APPLICATION = "DELETE FROM public.application where app_id=?";
    final String QUERY_INSERT_APPLICATION = "INSERT INTO public.application " +
            "(app_name,app_description,app_token,app_status,app_user_creator,app_date) values" +
            "(?, ?, ?, ?, ?, timestamp );";
   /* final String QUERY_UPDATE_APPLICATION = "UPDATE FROM public.application SET" +
            "app_name=? , app_description=? , app_token , app_date=? , app_status=? , app_user_creator=? " +
            "WHERE app_id=? ;" */
    // User _user;

    private Connection _conn;
    //  private Application _application;
    //  private Application _newApplication;
    //  private ArrayList<Application> _applicationList;
    //  private ResultSet _result;
    private ResultSet _generatedKeys;

    public ApplicationDAO() {
        _conn = Sql.getConInstance();
    }

    /*public void addApplication (String nameApplication, String descriptionApplication) throws SQLException {
        try {
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_INSERT_APPLICATION);
            preparedStatement.setString(1, nameApplication);
            preparedStatement.setString(2, descriptionApplication);
            preparedStatement.setString(3, "1234" + nameApplication + "##");
            preparedStatement.setInt(4, 1);
            preparedStatement.setInt(5, _user.get_idUser());
            preparedStatement.execute();
        }catch (SQLDataException e){
            System.out.println("Error Nombre o Descripcion no valido");
        }
    }*/

    public void deleteApplication(int applicationID) throws SQLException{
        try {
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_DELETE_APPLICATION);
            preparedStatement.setInt(1, applicationID);
            preparedStatement.execute();
        }catch (SQLDataException e){
            System.out.println("Error id incorrecto");
        }
    }
}
