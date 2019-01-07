package Persistence.M07_Template;

import Entities.Entity;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.Sql;
import Exceptions.ParameterDoesntExistsException;
import Persistence.DAO;

import java.sql.*;
import java.util.ArrayList;

public class DAOParameter extends DAO implements IDAOParameter {

    PreparedStatement _pt;
    Statement _st;
    boolean _salida;

    @Override
    public void postParameter(String[] parameters, int companyId) {
        for (int i = 0; i < parameters.length;i++)
            postParameter(parameters[i],companyId);
    }

    @Override
    public void postParameter(String name, int companyId) {
        Connection connection=getBdConnect();

        try {
            _pt = connection.prepareCall("{m07_findParameterByPar_Com_IDAndByParameterName}");
            _pt.setInt(1,companyId);
            _pt.setString(2,name);
            ResultSet resultSet = _pt.executeQuery();
            if (!resultSet.next()) {
                _pt = connection.prepareCall("{m07_findParameterByPar_Com_IDAndByParameterNameInsert}");
                _pt.setString(1,name);
                _pt.setInt(2,companyId);
                _pt.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch ( NullPointerException e ){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Parameter> getParameters(int companyId) throws ParameterDoesntExistsException {
        ArrayList<Parameter> parameterList = new ArrayList<>();
        try{

            parameterList = executeParameterQuery(companyId,"m07_getParameters");
        }catch (ParameterDoesntExistsException e){
            throw new ParameterDoesntExistsException
                    ("No existen parametros para la compania con este id: "
                            + companyId, e, companyId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return parameterList;
        }
    }

    @Override
    public ArrayList<Parameter> getParametersByMessage(int messageId) throws ParameterDoesntExistsException {
        ArrayList<Parameter> parameterList = new ArrayList<>();
        try{
            parameterList = executeParameterQuery(messageId,"m07_getParametersByMessage");
        }catch (ParameterDoesntExistsException e){
            throw new ParameterDoesntExistsException
                    ("No existen parametros para el mensaje con este id: "
                            + messageId, e, messageId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return parameterList;
        }
    }

    public ArrayList<Parameter> executeParameterQuery(int id,String procedure) throws ParameterDoesntExistsException {
        ArrayList<Parameter> parameterList = new ArrayList<>();
        Connection connection = getBdConnect();
        try {
            _pt = connection.prepareCall("{procedure}");
            _pt.setInt(1,id);
            ResultSet resultSet = _pt.executeQuery();
            while(resultSet.next()){
                Parameter parameter = new Parameter();
                parameter.setParameterId(resultSet.getInt("par_id"));
                parameter.setName(resultSet.getString("par_name"));
                parameterList.add(parameter);
            }
        } catch (SQLException e) {
            throw new ParameterDoesntExistsException();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
            return parameterList;
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