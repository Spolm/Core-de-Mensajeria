package Classes.M01_Login;

import Classes.Sql;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserDAO {


    final String QUERY_SELECT_BY_USERNAME_OR_EMAIL = "SELECT * FROM public.user where use_username=? or use_email=?";
    final String QUERY_SELECT_BY_ID = "SELECT * FROM public.user where use_id=?";
    final String QUERY_DELETE = "DELETE FROM public.user where use_id=?";
    final String QUERY_SELECT = "SELECT * FROM public.user";
    final String QUERY_LOGIN = "SELECT * FROM m01_loguser(?,?);";
    final String QUERY_UPDATE = "UPDATE public.USER SET" +
            " use_password=? , use_username = ?, use_type=? , use_email=? , use_phone=? , use_country=? , " +
            " use_city=? , use_address=? , use_date_of_birth=? , use_gender=?  " +
            " use_remaining_attempts=? " +
            " WHERE use_id=?; ";
    final String CALL_SELECT = "{CALL m01_getusers()}";
    final String CALL_LOGIN = "{CALL m01_loguser(?,?)}";
    final String CALL_CHANGE_PASSWORD = " {CALL m01_changePassword(?,?)}";
    final String QUERY_INSERT = "INSERT INTO public.USER " +
            "(use_password, use_username, use_type, use_email, use_phone, use_country," +
            "use_city, use_address, use_date_of_birth, use_gender) values" +
            "(MD5(?), ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    final String QUERY_UPDATE_REMAINING_ATTEMPTS = "UPDATE public.USER SET" +
            " use_remaining_attempts=? " +
            " WHERE use_id=?; ";
    final String QUERY_UPDATE_BLOCKED = "UPDATE public.USER SET"+
            " use_blocked=? "+
            " WHERE use_id=?";
    final String QUERY_UPDATE_PASSWORD = "UPDATE public.USER SET"+
            " use_password=? " + " WHERE use_id=?; ";
    final String CALL_IS_BLOCKED = "{CALL m01_isBlocked(?)}";

    private Connection _conn;
    private User _user;
    private ArrayList<User> _userList;
    private ResultSet _result;
    private ResultSet _generatedKeys;



    public UserDAO() {
        _conn = Sql.getConInstance();
    }

    public User findByUsernameOrEmail(String username) throws SQLException {
        PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_SELECT_BY_USERNAME_OR_EMAIL);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, username);
        _result = preparedStatement.executeQuery();
        _user = new User();
        while (_result.next()) {
            setUserParams(_result, _user);
        }
        return _user;
    }

    public User findByUsernameId(int id) throws SQLException {
        PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_SELECT_BY_ID);
        preparedStatement.setInt(1, id);
        _result = preparedStatement.executeQuery();
        _user = null;
        while (_result.next()) {
            _user = new User();
            setUserParams(_result, _user);
            _user.set_passwordUser("");
        }

        return _user;
    }

    public ArrayList<User> findAll() throws SQLException {
        _user = new User();
        _userList = new ArrayList<>();
        PreparedStatement st = _conn.prepareCall(CALL_SELECT);
        _result = st.executeQuery();

        while (_result.next()) {
            _user = new User();
            setUserParams(_result, _user);
            _userList.add(_user);
        }

        return _userList;
    }

    public void saveUser(User user) throws SQLException {
        PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,user.get_passwordUser());
        preparedStatement.setString(2,user.get_usernameUser());
        preparedStatement.setInt(3,user.get_typeUser());
        preparedStatement.setString(4,user.get_emailUser());
        preparedStatement.setString(5,user.get_phoneUser());
        preparedStatement.setString(6,user.get_countryUser());
        preparedStatement.setString(7,user.get_cityUser());
        preparedStatement.setString(8,user.get_addressUser());
        preparedStatement.setDate(9,user.get_dateOfBirthUser());
        preparedStatement.setString(10, user.get_genderUser());
        preparedStatement.execute();
        _generatedKeys= preparedStatement.getGeneratedKeys();
        if (_generatedKeys.next()) {
            user.set_idUser(_generatedKeys.getInt(1));
        }
    };

    public void updateUser(User user) throws SQLException {
        PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_UPDATE);
        preparedStatement.setString(1,user.get_passwordUser());
        preparedStatement.setString(2,user.get_usernameUser());
        preparedStatement.setInt(3,user.get_typeUser());
        preparedStatement.setString(4,user.get_emailUser());
        preparedStatement.setString(5,user.get_phoneUser());
        preparedStatement.setString(6,user.get_countryUser());
        preparedStatement.setString(7,user.get_cityUser());
        preparedStatement.setString(8,user.get_addressUser());
        preparedStatement.setDate(9,user.get_dateOfBirthUser());
        preparedStatement.setString(10, user.get_genderUser());
        preparedStatement.setInt(11,user.get_idUser());
        preparedStatement.executeUpdate();
    };

    public void deleteUser(User user) throws SQLException{
        PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_DELETE);
        preparedStatement.setInt(1, user.get_idUser());
        preparedStatement.execute();
    }

    private void setUserParams(ResultSet result, User user) throws SQLException {
        user.set_idUser(result.getInt("use_Id"));
        //user.set_passwordUser(result.getString("use_password"));
        user.set_usernameUser(result.getString("use_username"));
        user.set_typeUser(result.getInt("use_type"));
        user.set_emailUser(result.getString("use_email"));
        user.set_phoneUser(result.getString("use_phone"));
        user.set_countryUser(result.getString("use_country"));
        user.set_cityUser(result.getString("use_city"));
        user.set_addressUser(result.getString("use_address"));
        user.set_dateOfBirthUser(result.getDate("use_date_of_birth"));
        user.set_genderUser(result.getString("use_gender"));
        user.set_blockedUser(result.getInt("use_blocked"));
        user.set_remainingAttemptsUser(result.getInt("use_remaining_attempts"));
    }

    public void updateUserRemainingAttempts(User user) throws SQLException {
        PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_UPDATE_REMAINING_ATTEMPTS);
        preparedStatement.setInt(1,user.get_remainingAttemptsUser());
        preparedStatement.setInt(2,user.get_idUser());
        preparedStatement.executeUpdate();
    }

    public void updateUserPassword(User user) throws  SQLException {
        PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_UPDATE_PASSWORD);

    }

    public boolean isBlockedUser(String username) throws SQLException {
        int blocked = 0;
        PreparedStatement preparedStatement = _conn.prepareCall(CALL_IS_BLOCKED);
        preparedStatement.setString(1, username);
        _result = preparedStatement.executeQuery();
        while (_result.next()) {
            blocked = _result.getInt(1);
        }
        return blocked==1;
    }

    public User logUser(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = _conn.prepareCall(CALL_LOGIN);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        _result = preparedStatement.executeQuery();
        _user = null;
        while (_result.next()) {
            _user = new User();
            setUserParams(_result, _user);
        }
        return _user;
    }

    public String tokenGenerator(String email) throws SQLException, NoSuchAlgorithmException {
        _user = findByUsernameOrEmail(email);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String token = String.valueOf(_user.get_idUser());
        token += _user.get_emailUser();
        token += format.format(date);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(token.getBytes());
        byte[] digest = md.digest();
        String myhash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return  myhash;
    }

    public void changePassword(String username, String password) throws SQLException {
        PreparedStatement st = _conn.prepareCall(CALL_CHANGE_PASSWORD);
        st.setString(1, username);
        st.setString(2, password);
        _result = st.executeQuery();
    }

}
