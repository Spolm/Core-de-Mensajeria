package M01_Login;

import Classes.M01_Login.LoginIntent;
import Classes.M01_Login.User;
import Classes.M01_Login.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private LoginIntent _loginIntent;
    private User _user;
    private User _newUser;
    private ArrayList<User> _userList;
    private UserDAO _userDAO = new UserDAO();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date date = new java.util.Date();

    /**
     * This method initializes an Intent, which is a representation of a user that wants to log in.
     */
    private void createLoginIntent(){
        _loginIntent = new LoginIntent();
        _loginIntent.set_username("prueba");
        _loginIntent.set_password("Al28*/12");
    }

    /**
     * This method initializes an user to make the tests.
     */
    private void createUser(){
        _user = new User();
        _user.set_usernameUser("prueba");
        _user.set_emailUser("prueba@gmail.com");
        _user.set_passwordUser("Al28*/12");
        _user.set_dateOfBirthUser(Date.valueOf("2000-11-01"));
        _user.set_addressUser("kjsaksjd");
        _user.set_cityUser("ksajdl");
        _user.set_genderUser("M");
        _user.set_phoneUser("123123");
        _user.set_countryUser("kjsadk");
        _user.set_typeUser(2);
        try {
            _userDAO.saveUser(_user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This calls the initialization methods.
     */
    @BeforeEach
    public void createTestData(){
        createLoginIntent();
        createUser();
    }

    /**
     * This deletes the created data during the tests.
     */
    @AfterEach
    public void deleteTestData(){
        try {
            _userDAO.deleteUser(_user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method tests findByUsernameOrEmail method.
     */
    @Test
    public void findByUsernameOrEmailTest(){
        try {
            _newUser = _userDAO.findByUsernameOrEmail(_user.get_usernameUser());
            assertEquals(_user,_newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method tests findAll() method.
     */
    @Test
    public void findAllTest(){
        try{
            ArrayList list = _userDAO.findAll();
            assertTrue(list.size()>=1);
        }
        catch (SQLException e){

        }
    }

    /**
     * This method tests findByUsernameId() method.
     */
    @Test
    public void findByUsernameIdTest(){
        try{
            _newUser = _userDAO.findByUsernameId(_user.get_idUser());
            assertEquals(_user,_newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method tests deleteUser() method.
     */
    @Test
    public void deleteUserTest(){
        try {
            _userDAO.deleteUser(_user);
            assertNull(_userDAO.findByUsernameId(_user.get_idUser()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method tests saveUser() method.
     */
    @Test
    public void saveUserTest(){
        try {
            User user = _userDAO.findByUsernameId(_user.get_idUser());
            assertEquals(user,_user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method tests isBlockedUser() method.
     */
    @Test
    public void isBlockedUserTest(){
        try {
            assertFalse(_userDAO.isBlockedUser(_user.get_usernameUser()));
            _userDAO.logUser(_user.get_usernameUser(),"123242342343");
            _userDAO.logUser(_user.get_usernameUser(),"123242342343");
            _userDAO.logUser(_user.get_usernameUser(),"123242342343");
            _userDAO.logUser(_user.get_usernameUser(),"123242342343");
            assertTrue(_userDAO.isBlockedUser(_user.get_usernameUser()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method tests logUser() method.
     */
    @Test
    public void logUserTest(){
        try{
            assertEquals(_user, _userDAO.logUser(_user.get_usernameUser(),_user.get_passwordUser()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method tests tokenGenerator() method.
     */
    @Test
    public void tokenGeneratorTest(){
        String token = String.valueOf(_user.get_idUser())+_user.get_emailUser()+format.format(date);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(token.getBytes());
            byte[] digest = md.digest();
            String myhash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            assertEquals(myhash,_userDAO.tokenGenerator(_user.get_emailUser()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }





}
