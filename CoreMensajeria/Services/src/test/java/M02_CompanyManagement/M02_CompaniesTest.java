package M02_CompanyManagement;

import Classes.M02_Company.Company;
import Classes.M02_Company.CompanyDAO;
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
/*
    Gson gson = new Gson();
    private ArrayList<Company> _coList;
    private Company _co;
    private CompanyDAO _coMng = new CompanyDAO();

    @Test
    public void GetTest() {
        try {
            _co = _coMng.getDetails(1);
            assertNotNull(_co);
        }
        catch (CompanyDoesntExistsException e) {
            e.printStackTrace();
        }

    }*/
}
