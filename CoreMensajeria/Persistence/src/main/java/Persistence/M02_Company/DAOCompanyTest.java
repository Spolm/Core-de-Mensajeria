package Persistence.M02_Company;

import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M02_Company.Company;
import Exceptions.CompanyDoesntExistsException;
import Persistence.DAOFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DAOCompanyTest {

    private DAOCompany _dao;

    @BeforeEach
    void setUp(){

        _dao = DAOFactory.instanciateDaoCompany();

    }

    @AfterEach
    void tearDown() {

        _dao = null;
    }

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

    @Test
    void companyByUserTest(){
        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        Entity _user = EntityFactory.CreateUser(1,"1234", "Ronnie" , 1,
                "dd" , "64", sDate, "JEJ","dd","s",
                "jj");
        try {

            ArrayList<Entity> _companiesList = _dao.companiesByUser(_user);
            assertNotNull(_companiesList);
            assertEquals(8, _companiesList.size());
            assertEquals( ( ( Company )_companiesList.get( 0 ) ).get_name(), ("Company 1"));
            assertEquals( ( ( Company )_companiesList.get( 1 ) ).get_name(), ("Company 6"));
            assertEquals( ( ( Company )_companiesList.get( 2 ) ).get_name(), ("Company 2"));
            assertEquals( ( ( Company )_companiesList.get( 3 ) ).get_name(), ("Company 7"));
            assertEquals( ( ( Company )_companiesList.get( 4 ) ).get_name(), ("Company 4"));
            assertEquals( ( ( Company )_companiesList.get( 5 ) ).get_name(), ("Company 8"));
            assertEquals( ( ( Company )_companiesList.get( 6 ) ).get_name(), ("Company 5"));
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
    void companiesByResponsibleTest() {

        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        Entity _user = EntityFactory.CreateUser(1,"1234", "Ronnie" , 1,
                "dd" , "64", sDate, "JEJ","dd","s",
                "jj");

        ArrayList<Entity> _companiesList = _dao.companiesByResponsible(_user);
        assertNotNull(_companiesList);

        assertEquals(8, _companiesList.size());
    }


    //Revisar este metodo y su Sp
    @Test
    void changeStatusTest(){

        Entity _comp = EntityFactory.CreateCompanyWithOutLink(1,"Company" , "JAJAJ", true) ;

        try {
            Boolean _companyStatus = _dao.changeStatus( _comp );
            assertNotNull( _companyStatus );
            assertEquals( _companyStatus.booleanValue() , true);

        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }

}
