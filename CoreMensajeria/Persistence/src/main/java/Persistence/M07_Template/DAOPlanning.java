package Persistence.M07_Template;

import Entities.Entity;
import Entities.M07_Template.PlanningPackage.Planning;
import Entities.Sql;
import Persistence.DAO;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.*;

public class DAOPlanning  extends DAO implements IDAOPlanning {

    final String POST_PLANNING = "{call m07_postPlanning(?,?,?,?,?)}";
    final String UPDATE_PLANNING = "{ call m07_updatePlanning(?,?,?,?,?) }";
    final String DELETE_PLANNING = "{ call m07_deletePlanning(?) }";
    PreparedStatement _pt;
    Statement  _st;
    boolean _salida;

    /**
     * Obtener Planificacion
     * @param templateId
     * @return
     */
    @Override
    public Entity getPlanning(int templateId) {
        Planning planning = new Planning();
        try{
            Connection connection = getBdConnect();
            _pt = connection.prepareCall("{call m07_select_Planning(?)}");
            _pt.setInt(1, templateId);
            ResultSet resultSet = _pt.executeQuery();
            resultSet.next();
            planning.setStartDate(resultSet.getDate("panningStartDate"));
            planning.setStartTime(resultSet.getString("planningStartTime"));
            planning.setEndDate(resultSet.getDate("planningEndDate"));
            planning.setEndTime(resultSet.getString("planningEndTime"));
            planning.setIdPlanning(resultSet.getInt("(planningId"));
        }catch (SQLException e){
            e.fillInStackTrace();
        }catch (Exception e){
            e.fillInStackTrace();
        }finally {
            closeConnection();
            return planning;
        }
    }

    /**
     * Crear Planificacion
     * @param planning
     * @param templateId
     */
    @Override
    public Entity postPlanning(String[] planning, int templateId) {
        Planning _p = null;
        try{
            Connection connection = getBdConnect();
            _pt = connection.prepareCall(POST_PLANNING);
            _pt.setInt(1, templateId);
            _pt.setString(2, planning[0]);
            _pt.setString(3, planning[2]);
            _pt.setString(4, planning[1]);
            _pt.setString(5, planning[3]);
            _pt.executeQuery();
            _p = (Planning) this.getPlanning(templateId);
        }catch (SQLException e) {
            e.printStackTrace();
        }catch ( NullPointerException e ){
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return _p;
    }


    /**
     * Actualizar planificacion
     * @param planning
     * @param templateId
     */
    @Override
    public void updatePlanning(String[] planning, int templateId) {
        try{
            Connection connection = getBdConnect();
            _pt = connection.prepareCall(UPDATE_PLANNING);
            _pt.setInt(1, templateId);
            _pt.setString(2, planning[0]);
            _pt.setString(3, planning[2]);
            _pt.setString(4, planning[1]);
            _pt.setString(5, planning[3]);
            _pt.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Borrar planificacion
     * @param templateId
     */
    @Override
    public void deletePlanning(int templateId) {
        Connection _conn = this.getBdConnect();
        PreparedStatement _ps = null;
        try{
            _ps = _conn.prepareCall(DELETE_PLANNING);
            _ps.setInt(1,templateId);
            _ps.execute();

        }catch( Exception e ){
            e.printStackTrace();
        }
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