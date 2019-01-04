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





}
