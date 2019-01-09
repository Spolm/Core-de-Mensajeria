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

    PreparedStatement _pt;
    Statement  _st;
    boolean _salida;

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

    public Timestamp transform(String str_date){
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("dd/MM/yyyy");
            // you can change format of date
            Date date = formatter.parse(str_date);
            java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate;
        } catch (ParseException e) {
            return null;
        }
    }

    public void postPlanning(String[] planning, int templateId) {
        try{
            Connection connection = getBdConnect();
            _pt = connection.prepareCall("{callm07_postPlanning(?)}");
            _pt.setInt(1, templateId);
            _pt.setTimestamp(2, transform(planning[0]));
            _pt.setTimestamp(3, transform(planning[2]));
            _pt.setTimestamp(4, transform(planning[1]));
            _pt.setTimestamp(5, transform(planning[3]));
            _pt.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }catch ( NullPointerException e ){
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }


    public void updatePlanning(String[] planning, int templateId) {
        try{
            Connection connection = getBdConnect();
            _pt = connection.prepareCall("{m07_updatePlanning(?)}");
            _pt.setInt(1, templateId);
            _pt.setTimestamp(2, transform(planning[0]));
            _pt.setTimestamp(3, transform(planning[2]));
            _pt.setTimestamp(4, transform(planning[1]));
            _pt.setTimestamp(5, transform(planning[3]));
            _pt.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            closeConnection();
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