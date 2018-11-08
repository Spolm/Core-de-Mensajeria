package Classes.M09_Statistics;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Classes.Campaign;
import com.google.gson.Gson;
import Classes.Company;
import org.junit.jupiter.api.Test;
import webService.M09_StatisticsManagement.M09_Statistics;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class StatisticsTest {
    Gson gson = new Gson();


    ///Test v1
    @Test
    void testGetCompany()throws SQLException {
        M09_Statistics statistics = new M09_Statistics();
        ArrayList<Company> companyList = new ArrayList<>();
        companyList.add( new Company
                ("Company1"
                        ,"Company1 descripcion"
                        ,true));
        assertEquals(Response.ok(gson.toJson(companyList)).build(), statistics.getCompany());
    }

    @Test
    void testGetCampaign()throws SQLException {
        M09_Statistics statistics = new M09_Statistics();
        Company comp = new Company
                ("Company1"
                        ,"Company1 descripcion"
                        ,true);

        ArrayList<Campaign> campaignList = new ArrayList<>();
        campaignList.add( new Campaign
                (1
                        ,"Nombre1"
                        ,"Nombre2"
                        ,true, Date.from(Instant.now())
                        ,Date.from(Instant.now())
                        ,comp));
        assertEquals(Response.ok(gson.toJson(campaignList)).build(), statistics.getCampaign());
    }

    @Test
    void testGetNumberOfCompanysChart()throws SQLException {
        M09_Statistics statistics = new M09_Statistics();
        Statistics gr = new Statistics();
        ArrayList<Integer> listNum = new ArrayList<Integer>();
        listNum.add(100);
        listNum.add(200);
        listNum.add(300);
        listNum.add(400);

        ArrayList<String> listlabels = new ArrayList<String>();
        listlabels.add("Telegram");
        listlabels.add("SMS");
        listlabels.add("MAIL");
        listlabels.add("SENALES DE HUMO");
        gr.type = "bar";
        gr.x = listlabels;
        gr.y = listNum;
        assertEquals(Response.ok(gson.toJson(gr)).build(), statistics.getNumberOfCompanysChart());
    }

    @Test
    void testCountOfMessage() {
        M09_Statistics statistics = new M09_Statistics();
        ArrayList<Integer> listNum = new ArrayList<>();
        listNum.add(1);
        listNum.add(2);
        listNum.add(3);
        ArrayList<String> companyList = new ArrayList<String>();
        companyList.add("Company1");
        companyList.add("Company2");
        companyList.add("Company3");
        assertEquals(listNum,statistics.CountOfMessage(companyList));
    }



    //Test de ejemplo para comprobar disponibilidad del jUnit
    @Test
    void myFirstTest() {
        assertEquals(2, 1 + 1);
    }


}
