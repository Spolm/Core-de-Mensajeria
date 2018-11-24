package Classes.M06_DataOrigin;

import Classes.Sql;
import exceptions.ApplicationNotFoundException;
import exceptions.DatabaseConnectionProblemException;

import java.sql.*;
import java.util.ArrayList;

public class ApplicationDAO {

    final String QUERY_SELECT_ALL_APPLICATIONS = "SELECT * FROM public.application ORDER BY app_name";
    final String QUERY_SELECT_APPLICATIONS_BY_COMPANY = "SELECT * FROM public.application WHERE app_company=? ORDER BY app_name";
    final String QUERY_SELECT_APPLICATION_BY_ID = "SELECT * FROM public.application where app_id= ?";
    final String QUERY_SELECT_APPLICATION_BY_TOKEN = "SELECT * FROM public.application WHERE app_token=?";
    final String QUERY_INSERT_APPLICATION = "INSERT INTO public.application" +
                                    "(app_name,app_description,app_token,app_user_creator,app_company,app_status,app_date)" +
                                    "values(?, ?, ?, ?, ?, 1, now() );";
    final String QUERY_UPDATE_APPLICATION_STATUS = "UPDATE public.application SET app_status=? WHERE app_id=? ;";
    final String QUERY_DELETE_APPLICATION = "DELETE FROM public.application WHERE app_id= ?";

    private Connection _conn;
    private Encrypter _encrypter;

    public ApplicationDAO() {
        _conn = Sql.getConInstance();
        _encrypter = new Encrypter();
    }

    //          SELECTS
    //Get all applications on the Database
    public ArrayList<Application> getApplications() throws DatabaseConnectionProblemException {
        try {
            ArrayList<Application> applicationList = new ArrayList<>();
            Statement st = _conn.createStatement();
            ResultSet result = st.executeQuery(QUERY_SELECT_ALL_APPLICATIONS);

            while (result.next()) {
                applicationList.add(this.extractApplication(result));
            }
            return applicationList;
        } catch (SQLException e) {
            throw new DatabaseConnectionProblemException("Error de comunicacion con la base de datos.", e);
        } finally {
            Sql.bdClose(_conn);
        }
    }

    //Get all applications with a given company id
    public ArrayList<Application> getApplications(int companyId) throws DatabaseConnectionProblemException {
        try {
            ArrayList<Application> applicationList = new ArrayList<>();
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_SELECT_APPLICATIONS_BY_COMPANY);
            preparedStatement.setInt(1, companyId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                applicationList.add(this.extractApplication(result));
            }
            return applicationList;
        } catch (SQLException e) {
            throw new DatabaseConnectionProblemException("Error al obtener aplicaion.", e);
        } finally {
            Sql.bdClose(_conn);
        }
    }

    //Get an application with a given application id
    public Application getApplication(int id) throws ApplicationNotFoundException, DatabaseConnectionProblemException {
        try {
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_SELECT_APPLICATION_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next())
                return this.extractApplication(result);
            else
                throw new ApplicationNotFoundException("La aplicacion " + id + " no existe.");
        } catch (SQLException e) {
            throw new DatabaseConnectionProblemException("Error al obtener aplicacion.", e);
        } finally {
            Sql.bdClose(_conn);
        }
    }

    //Get an application with a given Token
    public Application getApplication(String token) throws ApplicationNotFoundException, DatabaseConnectionProblemException {
        try {
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_SELECT_APPLICATION_BY_TOKEN);
            preparedStatement.setString(1, token);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            if(result.next())
                return this.extractApplication(result);
            else
                throw new ApplicationNotFoundException("La aplicacion no existe.");
        } catch (SQLException e) {
            throw new DatabaseConnectionProblemException("Error al obtener aplicacion.", e);
        } finally {
            Sql.bdClose(_conn);
        }
    }

    //          DELETES
    //Delete the application with the given application id
    public void deleteApplication(int id) throws DatabaseConnectionProblemException, ApplicationNotFoundException {
        try {
            //Find if application exist
            this.getApplication(id);
            //Restart DB instance
            _conn = Sql.getConInstance();
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_DELETE_APPLICATION);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        }catch (SQLException e){
            throw new DatabaseConnectionProblemException("Error al eliminar aplicacion.",e);
        }
    }

    //          UPDATES
    //Update the status of the application with the given application id
    public void updateApplication(int id, int status) throws DatabaseConnectionProblemException, ApplicationNotFoundException {
        try {
            //Find if application exist
            this.getApplication(id);
            //Restart DB instance
            _conn = Sql.getConInstance();
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_UPDATE_APPLICATION_STATUS);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DatabaseConnectionProblemException("Error al actualizar aplicacion.", e);
        }
    }
    //          CREATES
    //Create a new application
    public void addApplication (AddApplicationData app) throws DatabaseConnectionProblemException {
        try {
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_INSERT_APPLICATION);

            preparedStatement.setString(1, app.get_nameApplication());
            preparedStatement.setString(2, app.get_descriptionApplication());
            preparedStatement.setString(3, this._encrypter.encrypt(
                    app.get_userId() + app.get_companyId() + app.get_nameApplication()
            ));
            preparedStatement.setInt(4, app.get_userId());
            preparedStatement.setInt(5, app.get_companyId());

            preparedStatement.execute();
        }catch (SQLException e){
            throw new DatabaseConnectionProblemException("Error al crear aplicacion.", e);
        }
    }

    //          UTILITIES
    //Get the application from the given resultSet
    private Application extractApplication(ResultSet resultSet) throws SQLException {
        Application app = new Application();

        app.set_idApplication(resultSet.getInt("app_id"));
        app.set_nameApplication(resultSet.getString("app_name"));
        app.set_descriptionApplication(resultSet.getString("app_description"));
        app.set_tokenApplication(resultSet.getString("app_token"));
        app.set_dateOfCreateApplication(resultSet.getDate("app_date"));
        app.set_statusApplication(resultSet.getInt("app_status"));
        app.set_userCreatorId(resultSet.getInt("app_user_creator"));
        app.set_companyId(resultSet.getInt("app_company"));

        return app;
    }
}
