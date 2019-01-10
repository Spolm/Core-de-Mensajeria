package Persistence.Postgres.M10_Profile;

import Entities.Entity;
import Entities.M10_Profile.GeographicalRegion;
import Persistence.IDAOGeographicalRegion;
import Persistence.Postgres.DAOPostgres;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DAOGeographicalRegionPostgres Class is responsible for doing all the queries that have to do with geographic region
 */
public class DAOGeographicalRegionPostgres extends DAOPostgres implements IDAOGeographicalRegion {
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    /**
     * Implemented method of IDAOProfile which is in charge of searching the database
     * for a geographical region according to an id
     * @param id id of the geographical region
     * @return  ArrayList<GeographicalRegion>
     */
    public ArrayList<GeographicalRegion> getGeographicalRegions(int id) {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo getGeographicalRegions("+id+") " +
                "en DAOGeographicalRegionPostgres" );
        //endregion
        ArrayList<GeographicalRegion> geographicalRegions = new ArrayList<>();
        Connection connection = DAOPostgres.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareCall("{call m10_select_geographical_region(?)}");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                GeographicalRegion geographicalRegion = new GeographicalRegion(
                        resultSet.getInt("rg_id_"),
                        resultSet.getString("rg_name_"),
                        resultSet.getInt("rg_type_"),
                        resultSet.getInt("rg_rg_")
                );
                geographicalRegions.add(geographicalRegion);
            }
            connection.close();
            //region Instrumentation Info
            log.info("Se ejecuto el metodo getGeographicalRegions("+id+") " +
                    "exitosamente en DAOGeographicalRegionPostgres");
            //endregion
        }catch (SQLException e){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo getGeographicalRegions" +
                    "arrojo la excepcion:" + e.getMessage()+ "en SQLException en DAOGeographicalRegionPostgres");
            //endregion
        }catch (Exception e){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo getGeographicalRegions" +
                    "arrojo la excepcion:" + e.getMessage()+ "en Exception en DAOGeographicalRegionPostgres");
            //endregion
        }

        //region Instrumentation Debug
        log.debug("Saliendo del metodo getGeographicalRegions("+id+") " +
                "en DAOGeographicalRegionPostgres" );
        //endregion
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
