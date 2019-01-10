package Persistence.M06_DataOrigin;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M06_DataOrigin.AddApplicationData;
import Entities.M06_DataOrigin.Application;
import Entities.M06_DataOrigin.Encrypter;
import Entities.Sql;
import Exceptions.ApplicationNotFoundException;
import Exceptions.DatabaseConnectionProblemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOApplication implements IDAOApplication {

    final String SELECT_ALL_APPLICATIONS = "{CALL m06_select_all_application()}";
    final String ADD_APPLICATION = "{CALL m06_add_application(?,?,?,?,?)}";
    final String UPDATE_APPLICATION_STATUS = "{CALL m06_update_application_status(?,?)}";
    final String SELECT_BY_TOKEN_APPLICATIONS = "{CALL m06_select_by_token_application(?)}";
    final String SELECT_BY_ID_APPLICATIONS = "{CALL m06_select_by_id_application(?)}";
    final String SELECT_BY_COMPANY_ID_APPLICATIONS = "{CALL m06_select_by_company_id_application(?)}";

    private Connection _conn = Sql.getConInstance();
    public Encrypter _encrypter= EntityFactory.getEncrypt();
    private Application _app = EntityFactory.emptyApplication();

    /*
     * Devuelve una lista de aplicaciones
     **/
    @Override
    public ArrayList<Application> getApplication() throws DatabaseConnectionProblemException {
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

    /**
     * Devuelve todas las aplicaciones que sean de una compañia en especifico
     * @param companyId id de la compañia
     **/
    @Override
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

    /**
     * Devuelve una aplicación en especifico
     * @param id identificador de la aplicación que desea mostrar
     **/
    @Override
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

    /**
     *Devuelve una aplicación recibiendo el token
     *@param token codigo de aplicacion que desea que se muestre
     **/
    @Override
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

    /**
     * Actualiza el estado de una aplicación
     * @param id identificador de la aplicación
     * @param status estado de la aplicación
     * @throws DatabaseConnectionProblemException
     * @throws ApplicationNotFoundException
     */
    @Override
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

    /**
     * Crea una aplicacion
     * @param a un objeto con los datos para llenar la aplicacion
     * @return
     * @throws DatabaseConnectionProblemException
     */
    @Override
    public Application createApplication (Entity a) throws DatabaseConnectionProblemException {
        try {
            AddApplicationData app = (AddApplicationData) a;

            String token = _encrypter.encryptToken(app.get_userId() + app.get_companyId() +
                    app.get_nameApplication() + _encrypter.getCurrentTime());
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

    /*
     * Metodo Privado que devuelve una aplicación
     * */
    private Application extractApplication(ResultSet resultSet) throws SQLException {

        _app.set_idApplication(resultSet.getInt("app_id"));
        _app.set_nameApplication(resultSet.getString("app_name"));
        _app.set_descriptionApplication(resultSet.getString("app_description"));
        _app.set_tokenApplication(resultSet.getString("app_token"));
        _app.set_dateOfCreateApplication(resultSet.getDate("app_date"));
        _app.set_statusApplication(resultSet.getInt("app_status"));
        _app.set_userCreatorId(resultSet.getInt("app_user_creator"));
        _app.set_companyId(resultSet.getInt("app_company"));

        return _app;
    }

    @Override
    public void create(Entity e) {

    }

    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }

}
