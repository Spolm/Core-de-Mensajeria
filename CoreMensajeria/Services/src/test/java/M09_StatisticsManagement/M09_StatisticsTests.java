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
    public void testCountOfMessageCompany(){
        M09_Statistics instance = new M09_Statistics();
        ArrayList<Integer> listnum = new ArrayList<>();
        ArrayList<String> listCompany =  new ArrayList<>();
        try {
            listnum.add(2);
            listnum.add(0);
            listnum.add(2);
            listnum.add(7);
            listnum.add(18);
            listCompany.add("Company 3");
            listCompany.add("Company 5");
            listCompany.add("Company 4");
            listCompany.add("Company 2");
            listCompany.add("Company 1");
            assertEquals( listnum , instance.CountOfMessage( listCompany ) );

        }
        catch ( Exception e ){
            e.printStackTrace();
        }
    }


    @Test
    public void testCountOfMessageChannels(){
        M09_Statistics instance = new M09_Statistics();
        ArrayList<Integer> listnum = new ArrayList<>();
        ArrayList<String> listChannel =  new ArrayList<>();
        try {
            listnum.add(14);
            listnum.add(15);
            listChannel.add("Email");
            listChannel.add("SMS");

            assertEquals( listnum , instance.CountOfMessageCha( listChannel ) );

        }
        catch ( Exception e ){
            e.printStackTrace();
        }
    }

    @Test
    public void testCountOfMessageCamp(){
        M09_Statistics instance = new M09_Statistics();
        ArrayList<Integer> listnum =
                new ArrayList<>( Arrays.asList( 3,6,2,4,3,3,1,1,1,1,1,1,1,1,0 ) );
        ArrayList<String> listCampaign =
                new ArrayList<>();
        try {

            listCampaign.add("Campaign 2");
            listCampaign.add("Campaign 1");
            listCampaign.add("Campaign 4");
            listCampaign.add("Campaign 3");
            listCampaign.add("Campaign 6");
            listCampaign.add("Campaign 5");
            listCampaign.add("Campaign 8");
            listCampaign.add("Campaign 7");
            listCampaign.add("Campaign 9");
            listCampaign.add("Campaign 10");
            listCampaign.add("Campaign 11");
            listCampaign.add("Campaign 12");
            listCampaign.add("Campaign 14");
            listCampaign.add("Campaign 13");
            listCampaign.add("Campaign 15");
           // assertNotNull( instance.CountOfMessage( listCampaign ) );
             assertEquals( listnum , instance.CountOfMessageCamp( listCampaign ) );

        }
        catch ( Exception e ){
            e.printStackTrace();
        }
    }

    @Test
    void testGetNumberOfCompanysChart()throws SQLException {
        M09_Statistics instance = new M09_Statistics();

        assertNotNull( instance.getNumberOfCompanysChart() );
    }

    @Test
    void testGetNumberOfCompanysLine()throws SQLException {
        M09_Statistics instance = new M09_Statistics();

        assertNotNull( instance.getNumberOfCompanysLine() );
    }

    @Test
    void testGetNumberOfCompanysPie()throws SQLException {
        M09_Statistics instance = new M09_Statistics();

        assertNotNull( instance.getNumberOfCompanysPie() );
    }





    @Test
    void testGetNumberOfCampaignLine()throws SQLException {
        M09_Statistics instance = new M09_Statistics();

        assertNotNull( instance.getNumberOfCampaignLine() );
    }

    @Test
    void testgetNumberOfCampaignChart()throws SQLException {
        M09_Statistics instance = new M09_Statistics();

        assertNotNull( instance.getNumberOfCampaignChart() );
    }

    @Test
    void testGetNumberOfCampaignPie()throws SQLException {
        M09_Statistics instance = new M09_Statistics();

        assertNotNull( instance.getNumberOfCampaignPie() );
    }

    @Test
    void testGetNumberOfChannelChart()throws SQLException {
        M09_Statistics instance = new M09_Statistics();

        assertNotNull( instance.getNumberOfChannelChart() );
    }

    @Test
    void testGetNumberOfChannelLine()throws SQLException {
        M09_Statistics instance = new M09_Statistics();

        assertNotNull( instance.getNumberOfChannelLine() );
    }

    @Test
    void testGetNumberOfChannelPie()throws SQLException {
        M09_Statistics instance = new M09_Statistics();

        assertNotNull( instance.getNumberOfChannelPie() );
    }





}
