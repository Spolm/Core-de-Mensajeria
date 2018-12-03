package Classes.M06_DataOrigin;

import Classes.Sql;
import Exceptions.ApplicationNotFoundException;
import Exceptions.DatabaseConnectionProblemException;

import java.sql.*;
import java.util.ArrayList;

public class ApplicationDAO {


    final String SELECT_ALL_APPLICATIONS = "{CALL m06_select_all_application()}";
    final String ADD_APPLICATION = "{CALL m06_add_application(?,?,?,?,?)}";
    final String UPDATE_APPLICATION_STATUS = "{CALL m06_update_application_status(?,?)}";
    final String SELECT_BY_TOKEN_APPLICATIONS = "{CALL m06_select_by_token_application(?)}";
    final String SELECT_BY_ID_APPLICATIONS = "{CALL m06_select_by_id_application(?)}";
    final String SELECT_BY_COMPANY_ID_APPLICATIONS = "{CALL m06_select_by_company_id_application(?)}";
/*
    final String QUERY_SELECT_ALL_APPLICATIONS = "SELECT * FROM public.application ORDER BY app_name";
    final String QUERY_SELECT_APPLICATIONS_BY_COMPANY = "SELECT * FROM public.application WHERE app_company=? ORDER BY app_name";
    final String QUERY_SELECT_APPLICATION_BY_ID = "SELECT * FROM public.application where app_id= ?";
    final String QUERY_SELECT_APPLICATION_BY_TOKEN = "SELECT * FROM public.application WHERE app_token=?";
    final String QUERY_INSERT_APPLICATION = "INSERT INTO public.application" +
                                    "(app_name,app_description,app_token,app_user_creator,app_company,app_status,app_date)" +
                                    "values(?, ?, ?, ?, ?, 1, now() );";
    final String QUERY_UPDATE_APPLICATION_STATUS = "UPDATE public.application SET app_status=? WHERE app_id=? ;";
    //final String QUERY_DELETE_APPLICATION = "DELETE FROM public.application WHERE app_id= ?";
    */
    private Connection _conn;
    private Encrypter _encrypter;

    /**
     * Constructor para ApplicationDAO
     */
    public ApplicationDAO() {
        _conn = Sql.getConInstance();
        _encrypter = new Encrypter();
    }

    //          SELECTS
    //Get all applications on the Database
    /**
     * SELECTS
     * @return Lista (array) de Applications (todas)
     * @throws DatabaseConnectionProblemException si hay problemas en la comunicacion con la Base
     *         de Datos
     */
    public ArrayList<Application> getApplications() throws DatabaseConnectionProblemException {
        try {
            ArrayList<Application> applicationList = new ArrayList<>();

            PreparedStatement preparedStatement = _conn.prepareCall(SELECT_ALL_APPLICATIONS);

            ResultSet result = preparedStatement.executeQuery();

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
    /**
     * SELECTS
     * @param companyId id de la compania
     * @return Lista (array) de Application (que esten asociada a la compania senalada)
     * @throws DatabaseConnectionProblemException si hay un error al obetener la lista de Application deseada
     */
    public ArrayList<Application> getApplications(int companyId) throws DatabaseConnectionProblemException {
        try {
            ArrayList<Application> applicationList = new ArrayList<>();
            PreparedStatement preparedStatement = _conn.prepareCall(SELECT_BY_COMPANY_ID_APPLICATIONS);
            preparedStatement.setInt(1, companyId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                applicationList.add(this.extractApplication(result));
            }
            return applicationList;
        } catch (SQLException e) {
            throw new DatabaseConnectionProblemException("Error al obtener aplicacion.", e);
        } finally {
            Sql.bdClose(_conn);
        }
    }

    //Get an application with a given application id
    /**
     * SELECTS
     * @param id id de la Application
     * @return una Application (que posea el id senalado)
     * @throws ApplicationNotFoundException si la Application con el id senalado no se encuentra/no existe
     * @throws DatabaseConnectionProblemException si hay problema de comunicacion con la Base de Datos
     */
    public Application getApplication(int id) throws ApplicationNotFoundException, DatabaseConnectionProblemException {
        try {
            PreparedStatement preparedStatement = _conn.prepareCall(SELECT_BY_ID_APPLICATIONS);
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
    /**
     * SELECTS
     * @param token token de la Application
     * @return una Application (que posea el token senalado)
     * @throws ApplicationNotFoundException si la Application con el token senalado no se encuentra/no existe
     * @throws DatabaseConnectionProblemException si hay problema de comunicacion con la Base de Datos
     */
    public Application getApplication(String token) throws ApplicationNotFoundException, DatabaseConnectionProblemException {
        try {

            PreparedStatement  preparedStatement = _conn.prepareCall(SELECT_BY_TOKEN_APPLICATIONS);
            preparedStatement.setString(1, token);
            ResultSet result = preparedStatement.executeQuery();
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

    //          UPDATES
    //Update the status of the application with the given application id
    /**
     * UPDATES - Actualiza el status de una Application con el id senalado al status senalado
     * @param id id de la Application
     * @param status status de la Application
     * @throws DatabaseConnectionProblemException si hay problema de comunicacion con la Base de Datos
     * @throws ApplicationNotFoundException si la Application con el id senalado no se encuentra/no existe
     */
    public Application updateApplication(int id, int status) throws DatabaseConnectionProblemException, ApplicationNotFoundException {
        try {
            //Find if application exist
            this.getApplication(id);
            //Restart DB instance
            _conn = Sql.getConInstance();
            PreparedStatement preparedStatement = _conn.prepareCall(UPDATE_APPLICATION_STATUS);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

            return this.getApplication(id);
        }catch (SQLException e){
            throw new DatabaseConnectionProblemException(/*"Error al actualizar aplicacion."*/ e.getMessage(), e);
        }
    }

    //          CREATES
    //Create a new application
    /**
     * CREATES
     * @param app objeto AddApplicationData
     * @return una Application a partir del los datos del AddApplicationData y los datos asociados
     * @throws DatabaseConnectionProblemException si hay un error con la Base de Datos (no se logra crear
     *         la Application)
     */
    public Application createApplication (AddApplicationData app) throws DatabaseConnectionProblemException {
        try {

            String token = this._encrypter.encryptToken(app.get_userId() + app.get_companyId() +
                    app.get_nameApplication() + Encrypter.getCurrentTime());
            PreparedStatement preparedStatement = _conn.prepareCall(ADD_APPLICATION);
            preparedStatement.setString(1, app.get_nameApplication());
            preparedStatement.setString(2, app.get_descriptionApplication());
            preparedStatement.setString(3, token);
            preparedStatement.setInt(4, app.get_userId());
            preparedStatement.setInt(5, app.get_companyId());

            preparedStatement.execute();
            return this.getApplication(token);
        }catch (SQLException e){
            throw new DatabaseConnectionProblemException("Error al crear aplicacion.", e);
        }catch (ApplicationNotFoundException e){
            throw new DatabaseConnectionProblemException("Error al obtener aplicacion.", e);
        }
    }

    //          DELETES
    //Delete the application with the given application id
    /*public void deleteApplication(int id) throws DatabaseConnectionProblemException, ApplicationNotFoundException {
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
    }*/

    //          UTILITIES
    //Get the application from the given resultSet
    /**
     * UTILITES
     * @param resultSet fila resultante de un query anetrior
     * @return una Application con los datos del resultSet
     * @throws SQLException si ...
     */
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
