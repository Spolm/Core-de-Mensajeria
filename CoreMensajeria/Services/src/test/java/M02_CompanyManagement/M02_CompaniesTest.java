package M02_CompanyManagement;

import Entities.M02_Company.Company;
import Entities.M02_Company.CompanyDAO;
import Exceptions.CompanyDoesntExistsException;
import com.google.gson.Gson;

import java.util.ArrayList;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class M02_CompaniesTest {

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

    }

}
