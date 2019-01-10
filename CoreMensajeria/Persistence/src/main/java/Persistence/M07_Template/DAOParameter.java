package Persistence.M07_Template;

import Entities.Entity;
import Entities.M07_Template.MessagePackage.Parameter;
import Exceptions.ParameterDoesntExistsException;
import Persistence.DAO;

import java.sql.*;
import java.util.ArrayList;

public class DAOParameter extends DAO implements IDAOParameter {
    private final String FIND_BY_COMID_NAME = "{CALL m07_findParameterByPar_Com_IDAndByParameterName(?,?)}";
    private final String FIND_BY_COMID_NAME_INSERT = "{CALL m07_findParameterByPar_Com_IDAndByParameterNameInsert(?,?)}";
    private final String GET_PARAMETERS = "{CALL m07_getParameters(?)}";
    private final String GET_PARAMETERS_BY_MESSAGESS = "{CALL m07_getParametersByMessage(?)}";
    private final String DELETE_PARAMETER = "{CALL m07_deleteParameter(?)}";

    PreparedStatement _pt;

    /**
     * Guarda todos los parametros
     * @param parameters
     * @param companyId
     * @return
     */
    @Override
    public ArrayList<Entity> postParameter(String[] parameters, int companyId) {
        ArrayList<Entity> _parameters = new ArrayList<>();

        for (int i = 0; i < parameters.length;i++){
            Entity _p = postParameter(parameters[i],companyId);
            _parameters.add(_p);
        }

        return _parameters;
    }

    /**
     * Guarda parametro especifico
     * @param name
     * @param companyId
     * @return
     */
    @Override
    public Entity postParameter(String name, int companyId) {
        Parameter _parameter = null;
        Connection connection=getBdConnect();
        try {
            _pt = connection.prepareCall(FIND_BY_COMID_NAME);
            _pt.setInt(1,companyId);
            _pt.setString(2,name);
            ResultSet resultSet = _pt.executeQuery();

            if (!resultSet.next()) {
                _pt = connection.prepareCall(FIND_BY_COMID_NAME_INSERT);
                _pt.setString(1,name);
                _pt.setInt(2,companyId);
                ResultSet _rs = _pt.executeQuery();

                if(_rs.next()){
                    _parameter = new Parameter();
                    _parameter.setName(name);
                    _parameter.setCompanyId(companyId);
                    _parameter.set_id(_rs.getInt(1));
                }

            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch ( NullPointerException e ){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return _parameter;
    }

    @Override
    public ArrayList<Parameter> getParameters(int companyId) throws ParameterDoesntExistsException {
        ArrayList<Parameter> parameterList = new ArrayList<>();
        Connection connection=getBdConnect();
        try {
            _pt = connection.prepareCall(GET_PARAMETERS);
            _pt.setInt(1,companyId);
            ResultSet resultSet = _pt.executeQuery();
            while(resultSet.next()){
                Parameter parameter = new Parameter();
                parameter.setParameterId(resultSet.getInt("par_id"));
                parameter.setName(resultSet.getString("par_name"));
                parameterList.add(parameter);
            }
        } catch (SQLException e) {
            throw new ParameterDoesntExistsException("No existen parametros para la compania con este id: "
                    + companyId, e, companyId);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
            return parameterList;
        }
    }

    @Override
    public ArrayList<Parameter> getParametersByMessage(int messageId) throws ParameterDoesntExistsException {
        ArrayList<Parameter> parameterList = new ArrayList<>();
        Connection connection=getBdConnect();
        try {
            _pt = connection.prepareCall(GET_PARAMETERS_BY_MESSAGESS);
            _pt.setInt(1,messageId);
            ResultSet resultSet = _pt.executeQuery();
            while(resultSet.next()){
                Parameter parameter = new Parameter();
                parameter.setParameterId(resultSet.getInt("par_id"));
                parameter.setName(resultSet.getString("par_name"));
                parameterList.add(parameter);
            }
        } catch (SQLException e) {
            throw new ParameterDoesntExistsException("No existen parametros para la compania con este id: "
                    + messageId, e, messageId);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
            return parameterList;
        }
    }

    /**
     * Borra un parametro
     * @param id
     */
    @Override
    public void deleteParameter(int id) {
        try{
            Connection _conn = this.getBdConnect();
            PreparedStatement _ps = _conn.prepareCall(DELETE_PARAMETER);
            _ps.setInt(1,id);
            _ps.execute();

        }catch ( Exception e ){
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