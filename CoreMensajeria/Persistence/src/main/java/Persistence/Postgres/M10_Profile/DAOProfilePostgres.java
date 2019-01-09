package Persistence.Postgres.M10_Profile;

import Entities.Entity;
import Entities.M01_Login.Privilege;
import Persistence.IDAOProfile;
import Persistence.Postgres.DAOPostgres;

import java.sql.*;
import java.util.ArrayList;

public class DAOProfilePostgres extends DAOPostgres implements IDAOProfile {

    public void create(Entity e) { }

    public Entity read(Entity e) {
        return null;
    }

    public Entity update(Entity e) {
        return null;
    }

    public ArrayList<Privilege> getPrivilegesByUserAndCompany(int user, int company) {
        ArrayList<Privilege> privileges = new ArrayList<>();
        Connection connection = DAOPostgres.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareCall("{call m10_select_privileges_by_user_company(?,?)}");
            preparedStatement.setInt(1,user);
            preparedStatement.setInt(2,company);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Privilege privilege = new Privilege(
                        resultSet.getInt("pri_id"),
                        resultSet.getString("pri_code"),
                        resultSet.getString("pri_action")
                );
                privileges.add(privilege);
            }
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return privileges;
    }

    public String editProfile(int userId, String name, String lastname, int ci, int geographicalRegion, String address,
                              String birthdate, String gender, String email, String phone) {
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

            //Se realiza query
            preparedStatement.executeQuery();
            r =  "Perfil editado con éxito";
        } catch (SQLException e) {
            System.err.println(e.getStackTrace());
            r = "Error al editar el perfil";
        } finally {
            try {
                connection.close();
                return r;
            }catch (SQLException e){
                e.printStackTrace();
                return "Error al cerrar BD";
            }
        }
    }
}