package M02_CompanyManagement;

import com.google.gson.Gson;
import java.sql.*;
import Classes.Sql;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webService.M02_CompanyManagement.M02_Companies;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class M02_CompaniesTests {

    Gson gson = new Gson();
    /*public M02_CompaniesTest() {

    }*/

    @BeforeEach
    public  void setUpClass() throws SQLException {
        Connection conn = Sql.getConInstance();

        String query = "INSERT INTO public.user(use_id, use_password, use_username) VALUES (99,'123456','junit');\n"
                + "INSERT INTO public.company(com_id, com_name, com_description, com_status, com_user_id) VALUES (99,'Prueba', 'prueba prueba', TRUE, 99);";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

    }


  @Test
    public void testGetCompanies() throws Exception {
        System.out.println("getCompanies");
        M02_Companies instance = new M02_Companies();
        Response result = instance.getCompanies(99);
        assertNotNull(result);
    }

    @AfterEach
    public void tearDownClass() throws SQLException {
        Connection conn;
        conn = Sql.getConInstance();
        String query = "DELETE FROM public.user WHERE use_id = 99;\n"
                + "DELETE FROM public.company WHERE com_id = 99;";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }


}
