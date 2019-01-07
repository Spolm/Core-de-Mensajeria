package Persistence.M02_Company;

import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M02_Company.Company;
import Entities.Sql;
import Exceptions.CompanyDoesntExistsException;
import Persistence.DAOFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DAOCompanyTest {

    private Connection _conn = Sql.getConInstance();
    private DAOCompany _dao;

    @BeforeEach
    void setUp(){

        _dao = DAOFactory.instanciateDaoCompany();

    }

    @AfterEach
    void tearDown() {

        _dao = null;
    }

    /**
     * Pruebas unitarias para el Metodo allCompanies en DAOCompany
     */
    @Test
    void allCompaniesTest(){
        try {
            ArrayList<Entity> _companies = _dao.allCompanies();
            assertNotNull(_companies);
            assertEquals(8, _companies.size());
            assertEquals( ( ( Company )_companies.get( 0 ) ).get_name(), ("Company 8"));
            assertEquals( ( ( Company )_companies.get( 1 ) ).get_name(), ("Company 7"));
            assertEquals( ( ( Company )_companies.get( 2 ) ).get_name(), ("Company 6"));
            assertEquals( ( ( Company )_companies.get( 3 ) ).get_name(), ("Company 5"));
            assertEquals( ( ( Company )_companies.get( 4 ) ).get_name(), ("Company 4"));
            assertEquals( ( ( Company )_companies.get( 5 ) ).get_name(), ("Company 3"));
            assertEquals( ( ( Company )_companies.get( 6 ) ).get_name(), ("Company 2"));
            assertEquals( ( ( Company )_companies.get( 7 ) ).get_name(), ("Company 1"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Pruebas unitarias para el Metodo companyByIdT en DAOCompany
     */
    @Test
    void companyByIdTest(){
        Entity _com = EntityFactory.CreateCompanyOnlyId(1);

        try {
            Entity _e = _dao.companyById(_com);
            assertNotNull(_e);

            assertEquals(((Company) _com).get_idCompany(), ((Company) _e).get_idCompany());
            assertEquals(((Company) _e).get_name(), "Company 1");
        }
        catch (Exception e ){
            e.printStackTrace();
        }

    }


    /**
     * Pruebas unitarias para el Metodo companyByUser en DAOCompany
     */
    @Test
    void companyByUserTest(){
        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        Entity _user = EntityFactory.CreateFullCompany(1 ,"Company 1", "jeje", true ,
                "dkdk", 1);
        try {

            ArrayList<Entity> _companiesList = _dao.companiesByUser( _user );
            assertNotNull(_companiesList);
            assertEquals(8, _companiesList.size());
            assertEquals( ( ( Company )_companiesList.get( 0 ) ).get_name(), ("Company 2"));
            assertEquals( ( ( Company )_companiesList.get( 1 ) ).get_name(), ("Company 7"));
            assertEquals( ( ( Company )_companiesList.get( 2 ) ).get_name(), ("Company 4"));
            assertEquals( ( ( Company )_companiesList.get( 3 ) ).get_name(), ("Company 6"));
            assertEquals( ( ( Company )_companiesList.get( 4 ) ).get_name(), ("Company 8"));
            assertEquals( ( ( Company )_companiesList.get( 5 ) ).get_name(), ("Company 5"));
            assertEquals( ( ( Company )_companiesList.get( 6 ) ).get_name(), ("Company 1"));
            assertEquals( ( ( Company )_companiesList.get( 7 ) ).get_name(), ("Company 3"));
            assertEquals( ( ( Company )_companiesList.get( 0 ) ).get_status(), (true));
            assertEquals( ( ( Company )_companiesList.get( 7 ) ).get_status(), (false));
        }
        catch (Exception e ){
            e.printStackTrace();
        }

    }

    //REVISAR ESTA PRUEBA. HAY ALGO CON ESTE METODO Y SU SP
    @Test
    @Disabled
    void companiesByResponsibleTest() {

        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        Entity _user = EntityFactory.CreateUser(1,"1234", "Ronnie" , 1,
                "dd" , "64", sDate, "JEJ","dd","s",
                "jj");

        ArrayList<Entity> _companiesList = _dao.companiesByResponsible( _user );
        assertNotNull(_companiesList);

        assertEquals(8, _companiesList.size());
    }


    /**
     * Pruebas unitarias para el Metodo changeStatus en DAOCompany
     */
    @Test
    void changeStatusTest(){

        Entity _comp = EntityFactory.CreateCompanyWithOutLink(1,"Company" , "JAJAJ", true) ;

        try {
            _dao.changeStatus( _comp );
        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }


    /**
     * Pruebas unitarias para el Metodo getCompany en DAOCompany
     */
    @Test
    void getCompanyTest() throws SQLException {

        Company _companyTest = null;
        Entity _com = EntityFactory.CreateCompanyOnlyId(1);
        Entity _eq = EntityFactory.CreateCompanyWithOutUserID(1, "Company 1", " ", true,
                "");
        Company _company = (Company) _com;
        try {
            PreparedStatement _preparedStatement = _conn.prepareCall("{CALL  m02_getcompanybyid(?)}");
            _preparedStatement.setInt(1, _company.get_idCompany());
            ResultSet _result = _preparedStatement.executeQuery();
            while (_result.next()) {

                _companyTest = _dao.getCompany(_result);
            }
            assertNotNull(_companyTest);
            assertEquals(_companyTest.get_idCompany(), ((Company) _eq).get_idCompany());
            assertEquals(_companyTest.get_name(), (((Company) _eq).get_name()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
