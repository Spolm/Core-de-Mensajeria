package Persistence.M01_Login;

import Entities.Entity;
import Entities.M01_Login.Privilege;
import Entities.Sql;
import Persistence.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOPrivilege extends DAO implements IDAOPrivilege {
    final String CALL_FIND_PRIVILEGES_BY_USER = "{CALL m01_getprivileges(?)}";

    private Connection _conn;
    private Privilege _privilege;
    private ArrayList<Privilege> _privilegeList;
    private ResultSet _result;
    private ResultSet _generatedKeys;

    public DAOPrivilege(){
        _conn = Sql.getConInstance();
    }

    /**
     * This method returns an ArrayList of privileges from an User.
     * @param id
     * @return ArrayList<Privilege></Privilege>
     * @throws SQLException
     */
    public ArrayList<Privilege> findPrivilegesByUserId(int id) throws SQLException {
        _privilegeList = new ArrayList<>();
        PreparedStatement preparedStatement = _conn.prepareCall(CALL_FIND_PRIVILEGES_BY_USER);
        preparedStatement.setInt(1,id);
        _result = preparedStatement.executeQuery();
        _privilege = null;
        while (_result.next()) {
            _privilege= new Privilege();
            setPrivilegeParams(_result,_privilege);
            _privilegeList.add(_privilege);
        }
        return _privilegeList;
    }

    public void setPrivilegeParams(ResultSet result, Privilege privilege) throws SQLException {
        privilege.set_idPrivileges(result.getInt("pri_id"));
        privilege.set_codePrivileges(result.getString("pri_code"));
        privilege.set_actionPrivileges(result.getString("pri_action"));
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

    @Override
    public Entity privilege(Entity e) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Entity> getPrivileges() throws SQLException {
        return null;
    }
}
