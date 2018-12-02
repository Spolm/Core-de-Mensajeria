package M02_CompanyManagement;

import Classes.M02_Company.Company;
import Exceptions.CompanyDoesntExistsException;
import com.google.gson.Gson;
import java.sql.*;
import java.util.ArrayList;

import Classes.Sql;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webService.M02_CompanyManagement.M02_Companies;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class M02_CompaniesTest {

    Gson gson = new Gson();
    private ArrayList<Company> _coList;
    private Company _co;
    private M02_Companies _coMng = new M02_Companies();
    /*public M02_CompaniesTest() {

    }*/

    @BeforeEach
    public  void setUpClass() throws SQLException {
        Connection conn = Sql.getConInstance();

        String query = "INSERT INTO public.user(use_id, use_password, " +
                "use_username) VALUES (9999,'123456','junit');\n"
                + "INSERT INTO public.company(com_id, com_name, com_description, " +
                "com_status, com_route_link, com_user_id) " +
                "VALUES (99,'Prueba', 'prueba prueba', TRUE, '', 9999);";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

    }


    @Test
    public void getCompaniesTest() throws Exception {
        System.out.println("getCompanies");
        _coList = _coMng.companyList(99);
        assertNotNull(_coList);
    }

    @Test
    public void getCompanyDetails() throws CompanyDoesntExistsException {
        System.out.println("getDetails");
        _co = _coMng.getDetails(99);
        assertNotNull(_co);
    }

    @AfterEach
    public void tearDownClass() throws SQLException {
        Connection conn;
        conn = Sql.getConInstance();
        String query = "DELETE FROM public.user WHERE use_id = 9999;\n"
                + "DELETE FROM public.company WHERE com_id = 99;";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }


}
