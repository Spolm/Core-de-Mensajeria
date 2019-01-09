package Persistence.Postgres.M10_Profile;

import Entities.Entity;
import Entities.M01_Login.Privilege;
import Entities.M10_Profile.Responsability;
import Persistence.IDAOProfile;
import Persistence.Postgres.DAOPostgres;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * DAOProfilePostgres Class is responsible for making all the queries that have to do with user profile
 */
public class DAOProfilePostgres extends DAOPostgres implements IDAOProfile {

    final static Logger log = LogManager.getLogger("CoreMensajeria");

    public void create(Entity e) { }

    public Entity read(Entity e) {
        return null;
    }

    public Entity update(Entity e) {
        return null;
    }

    /**
     * Implemented method of IDAOProfile that is responsible for removing
     * the privileges of one user per company from the database
     * @param userId id of the user
     * @param companyId id of the company
     * @return ArrayList<Privilege> list privileges a user by company
     */
    public ArrayList<Privilege> getPrivilegesByUserAndCompany(int userId, int companyId) {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo getPrivilegesByUserAndCompany("+userId+","+companyId+") " +
                "en DAOProfilePosrgres" );
        //endregion
        ArrayList<Privilege> privileges = new ArrayList<>();
        Connection connection = DAOPostgres.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareCall("{call m10_select_privileges_by_user_company(?,?)}");
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,companyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Privilege privilege = new Privilege(
                        resultSet.getInt("pri_id"),
                        resultSet.getString("pri_code"),
                        resultSet.getString("pri_action")
                );
                privileges.add(privilege);
            }
            //region Instrumentation Info
            log.info("Se ejecuto el metodo getPrivilegesByUserAndCompany("+userId+","+companyId+") " +
                    "exitosamente en DAOProfilePosrgres");
            //endregion
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo getPrivilegesByUserAndCompany("+userId+","+companyId+") " +
                    "arrojo la excepcion:" + e.getMessage() + "en SQLException en DAOProfilePostgres");
            //endregion
        }catch (Exception e){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo getPrivilegesByUserAndCompany("+userId+","+companyId+") " +
                    "arrojo la excepcion:" + e.getMessage()+ "en SQLException en DAOProfilePostgres");
            //endregion
        }
        //region Instrumentation Debug
        log.debug("Saliendo a el metodo getPrivilegesByUserAndCompany("+userId+","+companyId+") " +
                "en DAOProfilePosrgres y retornar lista privilegios: \n" +  privileges.toString());
        //endregion
        return privileges;
    }


    public Responsability getResponsability(int companyId) {
        Responsability responsability;
        return null;
    }

    /**
     * Implemented method of IDAOProfile which is responsible for editing the profile of a user in the database
     * @param userId id of the user
     * @param name  new name of the user
     * @param lastname new lastname of the user
     * @param ci new identification number of the user
     * @param geographicalRegion new geographical region of the user
     * @param address new address of the user
     * @param birthdate new birthdate of the user
     * @param gender new gender of the user
     * @param email new email of the user
     * @param phone new phone of the user
     * @return String with response of the update in the database
     */
    public String editProfile(int userId, String name, String lastname, int ci, int geographicalRegion, String address,
                              String birthdate, String gender, String email, String phone) {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo editProfile() " +
                "en DAOProfilePosrgres" );
        //endregion
        Connection connection = DAOPostgres.getConnection();
        String r = "";
        try {
            PreparedStatement preparedStatement = connection.prepareCall("{call m10_edit_user_by_profile(?,?,?,?,?,?,?,?,?,?)}");
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastname);
            preparedStatement.setInt(4, ci);
            preparedStatement.setInt(5, geographicalRegion);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, birthdate);
            preparedStatement.setString(8, gender);
            preparedStatement.setString(9, birthdate);
            preparedStatement.setString(10, birthdate);

            preparedStatement.executeQuery();
            r =  "Perfil editado con éxito";
            connection.close();
            //region Instrumentation Info
            log.info("Se ejecuto el metodo editProfile() " +
                    "exitosamente en DAOProfilePosrgres");
            //endregion
        } catch (SQLException e) {
            System.err.println(e.getStackTrace());
            //region Instrumentation Error
            log.error("El metodo editProfile" +
                    "arrojo la excepcion:" + e.getMessage()+ "en SQLException en DAOProfilePostgres");
            //endregion
            r = "Error al editar el perfil";
        }catch (Exception e){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo editProfile" +
                    "arrojo la excepcion:" + e.getMessage()+ "en Exception en DAOProfilePostgres");
            //endregion
        }finally {
            //region Instrumentation Debug
            log.debug("Saliendo del metodo editProfile() " +
                    "en DAOProfilePosrgres" );
            //endregion
            return r;
        }
    }
}