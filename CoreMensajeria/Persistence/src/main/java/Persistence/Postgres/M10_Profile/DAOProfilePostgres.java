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
}