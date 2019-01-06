package Persistence.M01_Login;

import Entities.Entity;
import Entities.M01_Login.User;
import Entities.Sql;
import Persistence.DAO;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DAOUser extends DAO implements  IDAOUser {

    private Connection _conn = Sql.getConInstance();

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
    final String CALL_FIND_USER = "{CALL m01_findByUsernameId(?)}";
    final String CALL_DELETE = "{CALL m01_deleteUser(?)}";
    final String CALL_INSERT = "{CALL m01_insertUser(MD5(?),?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    final String CALL_CHANGE_PASSWORD = " {CALL m01_changePassword(?,?)}";
    final String QUERY_INSERT = "INSERT INTO public.USER " +
            "(use_password, use_username, use_type, use_email, use_phone, use_country," +
            "use_city, use_address, use_date_of_birth, use_gender) values" +
            "(MD5(?), ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    final String QUERY_UPDATE_REMAINING_ATTEMPTS = "UPDATE public.USER SET" +
            " use_remaining_attempts=? " +
            " WHERE use_id=?; ";
    final String QUERY_UPDATE_PASSWORD = "UPDATE public.USER SET"+
            " use_password=? " + " WHERE use_id=?; ";
    final String CALL_IS_BLOCKED = "{CALL m01_isBlocked(?)}";

    private User _user;
    private ArrayList<User> _userList;
    private ResultSet _result;
    private ResultSet _generatedKeys;

    @Override
    public void create(Entity e) {
        User _us = (User) e;
        try {

            PreparedStatement preparedStatement = _conn.prepareCall(CALL_INSERT);
            preparedStatement.setString(1, _us.get_passwordUser());
            preparedStatement.setString(2, _us.get_usernameUser());
            preparedStatement.setInt(3, _us.get_typeUser());
            preparedStatement.setString(4, _us.get_emailUser());
            preparedStatement.setString(5, _us.get_phoneUser());
            preparedStatement.setString(6, _us.get_countryUser());
            preparedStatement.setString(7, _us.get_cityUser());
            preparedStatement.setString(8, _us.get_addressUser());
            preparedStatement.setDate(9, _us.get_dateOfBirthUser());
            preparedStatement.setString(10, _us.get_genderUser());
            preparedStatement.execute();

        }catch (Exception ex){
            ex.printStackTrace();
        }
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
    public Entity user(Entity e) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<User> getUsers(Entity u) throws SQLException {
        User us = (User) u;
        ArrayList<User> userList = new ArrayList<>();
        PreparedStatement preStatement;
        ResultSet result;
        try {
            Connection conexion = DAO.getBdConnect();

            //Invocando el SP
            preStatement = conexion.prepareCall("");
            //Metiendo los parametros al SP
//            preStatement.setInt(1, 1);
            //Ejecucion del query
            result = preStatement.executeQuery();
            while(result.next()){
//                int _idUser = result.getInt("use_id");
//                User use = EntityFactory.user(_idUser, use
//
// );
//                userList.add(use);
            }
            result.close();
        } catch ( SQLException e) {
//            throw new PLConnectException1(e);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return userList;
    }




//    public DAOUser() {
//        _conn = Sql.getConInstance();
//    }

    /**
     * This method returns an User when typing the username in the method.
     * @param username
     * @return
     * @throws SQLException
     */
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

    /**
     * This method returns a List of Users. The objective is to get all Users.
     * @return
     * @throws SQLException
     */
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

    /**
     * This method returns the User who has the typed user id in the method.
     * @param id
     * @return
     * @throws SQLException
     */
    //Stored Procedure
    public User findByUsernameId(int id) throws SQLException {
        PreparedStatement st = _conn.prepareCall(CALL_FIND_USER);
        st.setInt(1, id);
        _result = st.executeQuery();
        _user = null;
        while (_result.next()) {
            _user = new User();
            setUserParams(_result, _user);
            _user.set_passwordUser("");
        }
        return _user;
    }

    /**
     * This method searchs the typed user and deletes him from DB
     * @param user
     * @throws SQLException
     */
    //Stored Procedure
    public void deleteUser(User user) throws SQLException {
        PreparedStatement st = _conn.prepareCall(CALL_DELETE);
        st.setInt(1, user.get_idUser());
        st.execute();
    }

    //Stored Procedure
    public void insertUser(String password, String use_username, int use_type, String use_email, String use_phone, String use_country, String use_city, String use_address, Timestamp use_date_of_birth,
                           char use_gender) throws SQLException {
        PreparedStatement st = _conn.prepareCall(CALL_INSERT);
        st.setString(1, password);
        st.setString(2, use_username);
        st.setInt(3, use_type);
        st.setString(4, use_email);
        st.setString(5, use_phone);
        st.setString(6, use_country);
        st.setString(7, use_city);
        st.setString(8, use_address);
        st.setTimestamp(9, use_date_of_birth);
        _result = st.executeQuery();
    }

    /**
     * This methods inserts the given User in the DB
     * @param user
     * @throws SQLException
     */
    public void saveUser(User user) throws SQLException {
        PreparedStatement st = _conn.prepareStatement(QUERY_INSERT,Statement.RETURN_GENERATED_KEYS);
        Timestamp ts= new Timestamp(user.get_dateOfBirthUser().getTime());
        st.setString(1, user.get_passwordUser());
        st.setString(2, user.get_usernameUser());
        st.setInt(3, user.get_typeUser());
        st.setString(4, user.get_emailUser());
        st.setString(5, user.get_phoneUser());
        st.setString(6, user.get_countryUser());
        st.setString(7, user.get_cityUser());
        st.setString(8, user.get_addressUser());
        st.setDate(9, user.get_dateOfBirthUser());
        st.setString(10,user.get_genderUser());
        st.execute();
        _generatedKeys= st.getGeneratedKeys();
        if (_generatedKeys.next()) {
            user.set_idUser(_generatedKeys.getInt(1));
        }
    }

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

    /*
    public void deleteUser(User user) throws SQLException{
        PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_DELETE);
        preparedStatement.setInt(1, user.get_idUser());
        preparedStatement.execute();
    }*/

    /**
     * Takes a ResultSet and transforms it in an User
     * @param result
     * @param user
     * @throws SQLException
     */
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

    /**
     * This method returns a boolean
     * @param username
     * @return true or false
     * @throws SQLException
     */
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

    /**
     * This method will try to log the user with the given parameters.
     * @param username
     * @param password
     * @return User
     * @throws SQLException
     */

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

    /**
     * This method creates a token and transforms it with MD5, for encryptation of the passwords.
     * @param email
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public String tokenGenerator(String email) throws SQLException, NoSuchAlgorithmException {
        _user = findByUsernameOrEmail(email);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new Date();
        String token = String.valueOf(_user.get_idUser());
        token += _user.get_emailUser();
        token += format.format(date);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(token.getBytes());
        byte[] digest = md.digest();
        String myhash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return  myhash;
    }

    /**
     * This method allows a user to change his password.
     * @param username
     * @param password
     * @throws SQLException
     */
    public void changePassword(String username, String password) throws SQLException {
        PreparedStatement st = _conn.prepareCall(CALL_CHANGE_PASSWORD);
        st.setString(1, username);
        st.setString(2, password);
        _result = st.executeQuery();
    }
}
