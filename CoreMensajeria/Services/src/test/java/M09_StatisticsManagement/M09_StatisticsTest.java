package M09_StatisticsManagement;

import Classes.M09_Statistics.SqlEstrella;
import Classes.M09_Statistics.Statistics;
import Classes.Sql;
import Exceptions.CampaignDoesntExistsException;
import Exceptions.CompanyDoesntExistsException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import  webService.M09_StatisticsManagement.M09_Statistics;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import static  org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class M09_StatisticsTest {

    Gson gson = new Gson();
    private Connection conn = SqlEstrella.getConInstance();

    @Test
    void getAllCompaniesTest() throws CompanyDoesntExistsException {
        try {
            M09_Statistics intance = new M09_Statistics();
            Response salida = intance.getAllCompanies(1);
            assertEquals( 200, salida.getStatus() );
            assertNotNull( salida.getEntity());
        }
        catch( Exception e ) {
            e.printStackTrace();
            throw new CompanyDoesntExistsException ( e );
        }

    }

    @Test
    void getAllChannelsTest()  {
        try {
            M09_Statistics intance = new M09_Statistics();
            Response salida = intance.getAllChannels();
            assertEquals( 200, salida.getStatus() );
            assertNotNull(salida.getEntity());
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }


    @Test
    void getCompaniesCountTest()  {
        try {
            M09_Statistics intance = new M09_Statistics();

            Response salida = intance.getCompaniesCount();
            assertEquals( 200, salida.getStatus() );
            assertNotNull( salida.getEntity() );

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    void getCampaignCountTest()  {
        try {
            M09_Statistics intance = new M09_Statistics();

            Response salida = intance.getCampaignsCount();
            assertEquals( 200, salida.getStatus() );
            assertNotNull( salida.getEntity() );

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }


    @Test
    void getChannelsCountTest()  {
        try {
            M09_Statistics intance = new M09_Statistics();

            Response salida = intance.getChannelsCount();
            assertEquals( 200, salida.getStatus() );
            assertNotNull( salida.getEntity() );

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    void setParametersforQueryTest()  {
        try {
            M09_Statistics intance = new M09_Statistics();
            ArrayList<Integer> lista = new ArrayList<>();
            lista.add(1);
            String params = "and me.sen_com_id in";
            assertEquals( intance.setParametersforQuery(lista, params), "and me.sen_com_id in(1)" );

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

   @Test
   void getStatisticsTestOk200()  {
       try {
           M09_Statistics intance = new M09_Statistics();
           ArrayList<Integer> listaCompany  = new ArrayList<>();
           ArrayList<Integer> listaCampaign = new ArrayList<>();
           ArrayList<Integer> listaChannels = new ArrayList<>();
           ArrayList<Integer> listaIntegrators = new ArrayList<>();
           ArrayList<Integer> listaYear = new ArrayList<>();
           ArrayList<Integer> listaMonth = new ArrayList<>();
           ArrayList<Integer> listaDayOfWeek = new ArrayList<>();
           ArrayList<Integer> listaWeekOfYear = new ArrayList<>();
           ArrayList<Integer> listaDayOfMonth = new ArrayList<>();
           ArrayList<Integer> listaDayOfYear = new ArrayList<>();
           ArrayList<Integer> listaHour = new ArrayList<>();
           ArrayList<Integer> listaMinute = new ArrayList<>();
           ArrayList<Integer> listaSecond = new ArrayList<>();
           ArrayList<Integer> listaQuarter = new ArrayList<>();
           listaCompany.add(1);
           listaCampaign.add(1);
           listaChannels.add(1);
           Response salida = intance.getStatistics( listaCompany, listaCampaign, listaChannels, listaIntegrators, listaYear, listaMonth,
           listaDayOfWeek, listaDayOfMonth, listaDayOfYear, listaHour, listaMinute, listaSecond, listaQuarter);

           assertEquals( 200, salida.getStatus() );
           assertNotNull( salida.getEntity());

       } catch ( Exception e ) {
           e.printStackTrace();
       }
   }

   @Test
    void getStatisticsTestBadRequest(){
        M09_Statistics instance = new M09_Statistics();
        ArrayList<Integer> listaCompany  = new ArrayList<>();
        ArrayList<Integer> listaCampaign = new ArrayList<>();
        ArrayList<Integer> listaChannels = new ArrayList<>();
        ArrayList<Integer> listaIntegrators = new ArrayList<>();
        ArrayList<Integer> listaYear = new ArrayList<>();
        ArrayList<Integer> listaMonth = new ArrayList<>();
        ArrayList<Integer> listaDayOfWeek = new ArrayList<>();
        ArrayList<Integer> listaWeekOfYear = new ArrayList<>();
        ArrayList<Integer> listaDayOfMonth = new ArrayList<>();
        ArrayList<Integer> listaDayOfYear = new ArrayList<>();
        ArrayList<Integer> listaHour = new ArrayList<>();
        ArrayList<Integer> listaMinute = new ArrayList<>();
        ArrayList<Integer> listaSecond = new ArrayList<>();
        ArrayList<Integer> listaQuarter = new ArrayList<>();
        Response salida = intance.getStatistics( listaCompany, listaCampaign, listaChannels, listaIntegrators, listaYear, listaMonth,
           listaDayOfWeek, listaDayOfMonth, listaDayOfYear, listaHour, listaMinute, listaSecond, listaQuarter);
        assertEquals(400, salida.getStatus());
        assertEquals("{ \"Mensaje\": \"Debe enviar al menos un parametro\" }", salida.getEntity().toString());
    }





    @Disabled
    @Test
    void getOverallCountFor()  {
        try {
            M09_Statistics intance = new M09_Statistics();

             /*Response salida = intance.getOverallCountFor();

            assertEquals( salida.getEntity().toString(),
                    "" );
*/
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Disabled
    @Test
    void getCampaignsForCompanyTest()  {
        try {
            M09_Statistics intance = new M09_Statistics();
            ArrayList<Integer> lista = new ArrayList<>();
            lista.add(1);
            lista.add(2);

            Response salida = intance.getCampaignsForCompany( lista );

            assertEquals( salida.getEntity().toString(),
                    "" );

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }


}
