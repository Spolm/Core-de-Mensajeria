package M09_StatisticsManagement;

import Classes.M09_Statistics.SqlEstrella;
import Classes.M09_Statistics.Statistics;
import Classes.Sql;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import  webService.M09_StatisticsManagement.M09_Statistics;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import static  org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.Arrays;


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


    @Test
    void testGetNumberOfCompanysChart()throws SQLException {
        M09_Statistics instance = new M09_Statistics();

        assertNotNull( instance.getNumberOfCompanysChart() );
    }

    @Test
    void testGetNumberOfCompanysLine()throws SQLException {
        M09_Statistics instance = new M09_Statistics();
        Response res = instance.getNumberOfCompanysLine();
        assertEquals(200, res.getStatus());
        assertNotNull( res.getEntity() );
    }

    @Test
    void testGetNumberOfCompanysPie()throws SQLException {
        M09_Statistics instance = new M09_Statistics();
        Response res = instance.getNumberOfCompanysPie();
        assertEquals(200,res.getStatus());
        assertNotNull( res.getEntity() );
    }





    @Test
    void testGetNumberOfCampaignLine()throws SQLException {
        M09_Statistics instance = new M09_Statistics();
        Response res = instance.getNumberOfCampaignLine();
        assertEquals( 200, res.getStatus() );
        assertNotNull( res.getEntity() );
    }

    @Test
    void testgetNumberOfCampaignChart()throws SQLException {
        M09_Statistics instance = new M09_Statistics();
        Response res = instance.getNumberOfCampaignChart();
        assertEquals( 200, res.getStatus() );
        assertNotNull( res.getEntity() );
    }

    @Test
    void testGetNumberOfCampaignPie()throws SQLException {
        M09_Statistics instance = new M09_Statistics();
        Response res = instance.getNumberOfCampaignPie();
        assertEquals( 200, res.getStatus() );
        assertNotNull( res.getEntity() );
    }

    @Test
    void testGetNumberOfChannelChart()throws SQLException {
        M09_Statistics instance = new M09_Statistics();
        Response res = instance.getNumberOfChannelChart();
        assertEquals( 200, res.getStatus() );
        assertNotNull( res.getEntity() );
    }

    @Test
    void testGetNumberOfChannelLine()throws SQLException {
        M09_Statistics instance = new M09_Statistics();
        Response res = instance.getNumberOfChannelLine();
        assertEquals( 200, res.getStatus() );
        assertNotNull( res.getEntity() );
    }

    @Test
    void testGetNumberOfChannelPie()throws SQLException {
        M09_Statistics instance = new M09_Statistics();
        Response res = instance.getNumberOfChannelPie();
        assertEquals( 200, res.getStatus() );
        assertNotNull( res.getEntity() );
    }





}
