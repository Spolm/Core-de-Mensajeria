package Classes.M06_DataOrigin;

import Classes.Sql;
import exceptions.AddApplicationProblemException;
import exceptions.ApplicationNotFoundException;
import exceptions.DatabaseConnectionProblemException;

import java.sql.*;
import java.util.ArrayList;

public class ApplicationDAO {

    final String QUERY_SELECT_ALL_APPLICATIONS = "SELECT * FROM public.application ORDER BY app_name";
    final String QUERY_SELECT_APPLICATION_BY_ID = "SELECT * FROM public.application where app_id= ?";
    final String QUERY_SELECT_APPLICATION_BY_TOKEN = "SELECT * FROM public.application WHERE app_token=?";
    final String QUERY_INSERT_APPLICATION = "INSERT INTO public.application" +
                                    "(app_name,app_description,app_token,app_user_creator,app_status,app_date) values" +
                                    "(?, ?, ?, ?, 1, now() );";
    final String QUERY_UPDATE_APPLICATION = "UPDATE FROM public.application SET " +
                                    "app_name=? , app_description=? WHERE app_id=? ;";
    final String QUERY_UPDATE_APPLICATION_STATUS = "UPDATE public.application SET app_status=? WHERE app_id=? ;";
    final String QUERY_DELETE_APPLICATION = "DELETE FROM public.application where app_id=?";

    private Connection _conn;
    private Encrypter _encrypter;

    public ApplicationDAO() {
        _conn = Sql.getConInstance();
        _encrypter = new Encrypter();
    }

    public ArrayList<Application> getApplications() throws ApplicationNotFoundException, DatabaseConnectionProblemException {
        try {
            ArrayList<Application> applicationList = new ArrayList<>();
            Statement st = _conn.createStatement();
            ResultSet result = st.executeQuery(QUERY_SELECT_ALL_APPLICATIONS);

            while (result.next()) {
                applicationList.add(this.extractApplication(result));
            }
            return applicationList;
        } catch (SQLException e) {
            throw new DatabaseConnectionProblemException("Error de conexion.", e);
        } finally {
            Sql.bdClose(_conn);
        }
    }

    public Application getApplication(int id) throws ApplicationNotFoundException, DatabaseConnectionProblemException {
        try {
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_SELECT_APPLICATION_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            return this.extractApplication(result);

        } catch (SQLException e) {
            throw new DatabaseConnectionProblemException("Error al extraer data.", e);
        } finally {
            Sql.bdClose(_conn);
        }
    }

    public Application getApplication(String token) throws ApplicationNotFoundException, DatabaseConnectionProblemException {
        try {
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_SELECT_APPLICATION_BY_TOKEN);
            preparedStatement.setString(1, token);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            return this.extractApplication(result);

        } catch (SQLException e) {
            throw new DatabaseConnectionProblemException("Error de conexion.", e);
        } finally {
            Sql.bdClose(_conn);
        }
    }

    public void deleteApplication(int applicationID) throws SQLException{
        try {
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_DELETE_APPLICATION);
            preparedStatement.setInt(1, applicationID);
            preparedStatement.execute();
        }catch (SQLDataException e){
            System.out.println("Error id incorrecto");
        }
    }

    public void updateApplication(int id, String name, String description) throws DatabaseConnectionProblemException {
        try {
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_UPDATE_APPLICATION);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DatabaseConnectionProblemException("Error de conexion.", e);
        }
    }

    public void updateApplication(int id, int status) throws DatabaseConnectionProblemException {
        try {
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_UPDATE_APPLICATION_STATUS);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DatabaseConnectionProblemException("Error de conexion.", e);
        }
    }

    public void addApplication (AddApplicationData app) throws AddApplicationProblemException {

        try {
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_INSERT_APPLICATION);
            preparedStatement.setString(1, app.get_nameApplication());
            preparedStatement.setString(2, app.get_descriptionApplication());
            preparedStatement.setString(3, this._encrypter.encrypt(
                    app.get_userId() + app.get_nameApplication()
            ));
            preparedStatement.setInt(4, app.get_userId());
            preparedStatement.execute();
        }catch (SQLException e){
            throw new AddApplicationProblemException("Error al insertar en la Base de Datos", e);

        }
    }

    private Application extractApplication(ResultSet resultSet) throws ApplicationNotFoundException {
        try {
            Application app = new Application();

            app.set_idApplication(resultSet.getInt("app_id"));
            app.set_nameApplication(resultSet.getString("app_name"));
            app.set_descriptionApplication(resultSet.getString("app_description"));
            app.set_tokenApplication(resultSet.getString("app_token"));
            app.set_dateOfCreateApplication(resultSet.getDate("app_date"));
            app.set_statusApplication(resultSet.getInt("app_status"));
            app.set_userCreatorApplication(resultSet.getInt("app_user_creator"));

            return app;
        } catch (SQLException e) {
            throw new ApplicationNotFoundException("Aplicacion no encontrada.", e);
        }
    }
}
