package M09_StatisticsManagement;

import Classes.M09_Statistics.SqlEstrella;
import Classes.M09_Statistics.Statistics;
import Classes.Sql;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import  webService.M09_StatisticsManagement.M09_Statistics;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import static  org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class M09_StatisticsTests {

    Gson gson = new Gson();
    private Connection conn = SqlEstrella.getConInstance();

    @Test
    public void testGetNumberOfCompaniesChartOk() {
        M09_Statistics instance = new M09_Statistics();
        try {
            Response res = instance.getNumberOfCompanysChart();
            assertEquals(200, res.getStatus());
            assertNotNull(res.getEntity());
        }
        catch (SQLException e){}

    }


}
