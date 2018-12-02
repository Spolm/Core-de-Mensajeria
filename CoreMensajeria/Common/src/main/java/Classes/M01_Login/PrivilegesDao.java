package Classes.M01_Login;

import Classes.Sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PrivilegesDao {

    private Connection _conn;
    private Privileges privileges;
    private ArrayList<Privileges> _privilegesList;
    private ResultSet _result;
    private ResultSet _generatedKeys;

    public PrivilegesDao(){
        _conn = Sql.getConInstance();
    }

}
