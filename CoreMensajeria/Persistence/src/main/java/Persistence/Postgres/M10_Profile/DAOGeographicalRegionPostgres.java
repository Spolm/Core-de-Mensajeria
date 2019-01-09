package Persistence.Postgres.M10_Profile;

import Entities.Entity;
import Entities.M10_Profile.GeographicalRegion;
import Persistence.IDAOGeographicalRegion;
import Persistence.Postgres.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOGeographicalRegionPostgres extends DAOPostgres implements IDAOGeographicalRegion {
    public ArrayList<GeographicalRegion> getGeographicalRegions(int id) {
        ArrayList<GeographicalRegion> geographicalRegions = new ArrayList<>();
        Connection connection = DAOPostgres.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareCall("{call m10_select_geographical_region(?)}");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                GeographicalRegion geographicalRegion = new GeographicalRegion(
                        resultSet.getInt("rg_id"),
                        resultSet.getString("rg_name"),
                        resultSet.getInt("rg_type"),
                        resultSet.getInt("rg_rg")
                );
                geographicalRegions.add(geographicalRegion);
            }
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return geographicalRegions;
    }

    public void create(Entity e) {

    }

    public Entity read(Entity e) {
        return null;
    }

    public Entity update(Entity e) {
        return null;
    }
}
