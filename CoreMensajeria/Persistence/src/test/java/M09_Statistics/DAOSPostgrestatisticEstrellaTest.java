package M09_Statistics;

import Entities.Entity;
import Entities.M09_Statistics.Statistics;
import Exceptions.CampaignDoesntExistsException;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DAOSPostgrestatisticEstrellaTest {
    IDAO_StatisticEstrella dao;
    Entity entity;

    @BeforeEach
    void setUp() {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
    }

    @AfterEach
    void tearDown() {
        dao.close();
        entity = null;
    }

    @Test
    void campaignsForCompany() {
        List<Entity> entitylist;
        List<Integer> companyIds = new ArrayList();
        try {
            //assertThrows(CampaignDoesntExistsException.class, (Executable) dao.CampaignsForCompany(companyIds));
            for (int i = 0; i < 3;i++){
                companyIds.add(i);
            }
            entitylist = dao.CampaignsForCompany(companyIds);
            assertNotNull(entitylist);
        } catch (CampaignDoesntExistsException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllChannels() {
        ArrayList<Entity> channels;
        channels = dao.getAllChannels();
        assertNotNull(channels);
        /*
        Entity canal = channels.get(0);
        assertEquals(((Channel) canal).get_nameChannel(),"SMS");
        canal = channels.get(1);
        assertEquals(((Channel) canal).get_nameChannel(),"Email");
        */
    }

    @Test
    void getCompanyStatistic() {
        entity = dao.getCompanyStatistic();
        assertNotNull(entity);
        assertNotNull(entity.get_id());
        assertNotNull(((Statistics)entity).getX());
        assertNotNull(((Statistics)entity).getY());
    }

    @Test
    void getCampaignStatistic() {
        entity = dao.getCampaignStatistic();
        assertNotNull(entity);
        assertNotNull(entity.get_id());
        assertNotNull(((Statistics)entity).getX());
        assertNotNull(((Statistics)entity).getY());
    }

    @Test
    void getChannelStatistic() {
        entity = dao.getChannelStatistic();
        assertNotNull(entity);
        assertNotNull(entity.get_id());
        assertNotNull(((Statistics)entity).getX());
        assertNotNull(((Statistics)entity).getY());
    }

    @Test
    void getIntegratorStatistic() {
        entity = dao.getIntegratorStatistic();
        assertNotNull(entity);
        assertNotNull(entity.get_id());
        assertNotNull(((Statistics)entity).getX());
        assertNotNull(((Statistics)entity).getY());
    }

    @Test
    void updateStarSchema() {
    }

    @Test
    void getYears() {
        ArrayList<Integer> years;
        years = dao.getYears();
        assertNotNull(years);
        assertFalse(years.isEmpty());
        assertTrue(years.contains(2018));
    }

    @Test
    void getMonths() {
        ArrayList<Integer> expectedMonths = new ArrayList<>();
        ArrayList<Integer> months = dao.getMonths();
        assertNotNull(months);
        for ( int i=1; i<13; i++ ){
            expectedMonths.add(i);
        }
        for (int i = 0; i < expectedMonths.size(); i++){
            assertTrue(months.contains(expectedMonths.get(i)));
        }
    }

    @Test
    void getDaysofWeek() {
        ArrayList<Integer> expectedDays = new ArrayList<>();
        ArrayList<Integer> days = dao.getDaysofWeek();
        assertNotNull(days);
        /*for ( int i=1; i<8; i++ ){
            expectedDays.add(i);
        }
        assertEquals(expectedDays, days);
        */
    }

    @Test
    void getDaysofMonth() {
        //ArrayList<Integer> expectedDays = new ArrayList<>(Arrays.asList(1, 5, 7, 9, 10, 11, 12, 13, 15, 16, 17, 19, 23,
          //      24, 25, 28, 29, 30, 31));
        ArrayList<Integer> days = dao.getDaysofMonth();
        assertNotNull(days);
        /*for (int  i = 0; i < expectedDays.size(); i++){
            assertTrue(days.contains(expectedDays.get(i)));
        }*/
        //assertEquals(expectedDays, days);
    }

    @Test
    void getDaysofYear() {
        //ArrayList<Integer> expectedDays = new ArrayList<>(Arrays.asList(1, 36, 41, 67, 74, 76, 102, 103, 106, 109, 132,
          //      175, 181, 204, 240, 243, 253, 254, 285, 296, 314, 330, 333, 359, 365));
        ArrayList<Integer> days = dao.getDaysofYear();
        assertNotNull(days);
        /*for (int  i = 0; i < expectedDays.size(); i++){
            assertTrue(days.contains(expectedDays.get(i)));
        }*/
        //assertEquals(expectedDays, days);
    }

    @Test
    void getWeeksofYear() {
        /*ArrayList<Integer> expectedWeeks = new ArrayList<>(Arrays.asList(6, 10, 11, 15, 16, 19, 25, 26, 29, 35, 36,
                37, 41, 43, 45, 47, 48, 51, 52, 53));*/
        ArrayList<Integer> weeks = dao.getWeeksofYear();
        assertNotNull(weeks);
        /*for (int  i = 0; i < expectedWeeks.size(); i++){
            assertTrue(weeks.contains(expectedWeeks.get(i)));
        }*/
        //assertEquals(expectedWeeks, weeks);
    }

    @Test
    void getQuartersofYear() {
        ArrayList<Integer> expectedQuarters = new ArrayList<>();
        ArrayList<Integer> quarters = dao.getQuartersofYear();
        assertNotNull(quarters);
        for ( int i=1; i<5; i++ ){
            expectedQuarters.add(i);
        }
        for (int  i = 0; i < expectedQuarters.size(); i++){
            assertTrue(quarters.contains(expectedQuarters.get(i)));
        }
        //assertEquals(expectedQuarters, quarters);
    }

    @Test
    void getHours() {
        ArrayList<Integer> expectedHours = new ArrayList<>();
        ArrayList<Integer> hours = dao.getHours();
        assertNotNull(hours);
        //expectedHours.add(0);
        //assertEquals(expectedHours, hours);
    }

    @Test
    void getMinutes() {
        ArrayList<Integer> expectedMinutes = new ArrayList<>();
        ArrayList<Integer> minutes = dao.getMinutes();
        assertNotNull(minutes);
        //expectedMinutes.add(0);
        //assertEquals(expectedMinutes, minutes);
    }

    @Test
    void getSeconds() {
        ArrayList<Integer> expectedSeconds = new ArrayList<>();
        ArrayList<Integer> seconds = dao.getSeconds();
        assertNotNull(seconds);
        //expectedSeconds.add(0);
        //assertEquals(expectedSeconds, seconds);
    }

    @Test
    void getMessagesParamCompany() {
        ArrayList<Integer> lista  = new ArrayList<>();
        String campaignin = setParametersforQuery(lista,"and me.sen_cam_id in ");
        String channelin = setParametersforQuery(lista,"and me.sen_cha_id in ");
        String integratorin = setParametersforQuery(lista, "and me.sen_int_id in");
        String yearin = setParametersforQuery(lista, "and da.dat_year in");
        String monthin = setParametersforQuery(lista, "and da.dat_month in");
        String weekofyearin = setParametersforQuery(lista, "and da.dat_weekofyear in");
        String dayofweekin = setParametersforQuery(lista,"and da.dat_dayofweek in");
        String dayofmonthin = setParametersforQuery(lista, "and da.dat_dayofmonth in");
        String dayofyearin = setParametersforQuery(lista, "and da.dat_dayofyear in");
        String hourin = setParametersforQuery(lista, "and da.dat_hourofday in");
        String minutein = setParametersforQuery(lista, "and da.dat_minuteofhour in");
        String secondin = setParametersforQuery(lista, "and da.dat_secondofminute in");
        String quarterin = setParametersforQuery(lista, "and da.dat_quarterofyear in");
        for (int i=1;i < 5; i++)
            lista.add(i);
        String companyin = setParametersforQuery(lista,"and me.sen_com_id in ");
        entity = dao.getMessagesParamCompany(companyin, campaignin, channelin, integratorin, yearin, monthin,
                weekofyearin, dayofweekin, dayofmonthin, dayofyearin, hourin, minutein, secondin, quarterin);
        assertNotNull(entity);
        for (int i=1;i < 5; i++)
            assertTrue(((Statistics) entity).getX().contains("Company " + i));
    }

    @Test
    void getMessagesParamCampaign() {
        ArrayList<Integer> lista  = new ArrayList<>();
        String companyin = setParametersforQuery(lista,"and me.sen_com_id in ");
        String channelin = setParametersforQuery(lista,"and me.sen_cha_id in ");
        String integratorin = setParametersforQuery(lista, "and me.sen_int_id in");
        String yearin = setParametersforQuery(lista, "and da.dat_year in");
        String monthin = setParametersforQuery(lista, "and da.dat_month in");
        String weekofyearin = setParametersforQuery(lista, "and da.dat_weekofyear in");
        String dayofweekin = setParametersforQuery(lista,"and da.dat_dayofweek in");
        String dayofmonthin = setParametersforQuery(lista, "and da.dat_dayofmonth in");
        String dayofyearin = setParametersforQuery(lista, "and da.dat_dayofyear in");
        String hourin = setParametersforQuery(lista, "and da.dat_hourofday in");
        String minutein = setParametersforQuery(lista, "and da.dat_minuteofhour in");
        String secondin = setParametersforQuery(lista, "and da.dat_secondofminute in");
        String quarterin = setParametersforQuery(lista, "and da.dat_quarterofyear in");
        for (int i=1;i < 5; i++)
            lista.add(i);
        String campaignin = setParametersforQuery(lista,"and me.sen_cam_id in ");
        entity = dao.getMessagesParamCampaign(companyin, campaignin, channelin, integratorin, yearin, monthin,
                weekofyearin, dayofweekin, dayofmonthin, dayofyearin, hourin, minutein, secondin, quarterin);
        assertNotNull(entity);
        for (int i=1;i < 5; i++)
            assertTrue(((Statistics) entity).getX().contains("Campaign " + i));
    }

    @Test
    void getMessagesParamChannel() {
        ArrayList<Integer> lista  = new ArrayList<>();
        String campaignin = setParametersforQuery(lista,"and me.sen_cam_id in ");
        String companyin = setParametersforQuery(lista,"and me.sen_com_id in ");
        String integratorin = setParametersforQuery(lista, "and me.sen_int_id in");
        String yearin = setParametersforQuery(lista, "and da.dat_year in");
        String monthin = setParametersforQuery(lista, "and da.dat_month in");
        String weekofyearin = setParametersforQuery(lista, "and da.dat_weekofyear in");
        String dayofweekin = setParametersforQuery(lista,"and da.dat_dayofweek in");
        String dayofmonthin = setParametersforQuery(lista, "and da.dat_dayofmonth in");
        String dayofyearin = setParametersforQuery(lista, "and da.dat_dayofyear in");
        String hourin = setParametersforQuery(lista, "and da.dat_hourofday in");
        String minutein = setParametersforQuery(lista, "and da.dat_minuteofhour in");
        String secondin = setParametersforQuery(lista, "and da.dat_secondofminute in");
        String quarterin = setParametersforQuery(lista, "and da.dat_quarterofyear in");
        lista.add(1);
        lista.add(2);
        String channelin = setParametersforQuery(lista,"and me.sen_cha_id in ");
        entity = dao.getMessagesParamChannel(companyin, campaignin, channelin, integratorin, yearin, monthin,
                weekofyearin, dayofweekin, dayofmonthin, dayofyearin, hourin, minutein, secondin, quarterin);
        assertNotNull(entity);
        assertTrue(((Statistics) entity).getX().contains("Email"));
        assertTrue(((Statistics) entity).getX().contains("SMS"));

    }

    @Test
    void getMessagesParamIntegrator() {
        ArrayList<Integer> lista  = new ArrayList<>();
        String campaignin = setParametersforQuery(lista,"and me.sen_cam_id in ");
        String companyin = setParametersforQuery(lista,"and me.sen_com_id in ");
        String channelin = setParametersforQuery(lista,"and me.sen_cha_id in ");
        String yearin = setParametersforQuery(lista, "and da.dat_year in");
        String monthin = setParametersforQuery(lista, "and da.dat_month in");
        String weekofyearin = setParametersforQuery(lista, "and da.dat_weekofyear in");
        String dayofweekin = setParametersforQuery(lista,"and da.dat_dayofweek in");
        String dayofmonthin = setParametersforQuery(lista, "and da.dat_dayofmonth in");
        String dayofyearin = setParametersforQuery(lista, "and da.dat_dayofyear in");
        String hourin = setParametersforQuery(lista, "and da.dat_hourofday in");
        String minutein = setParametersforQuery(lista, "and da.dat_minuteofhour in");
        String secondin = setParametersforQuery(lista, "and da.dat_secondofminute in");
        String quarterin = setParametersforQuery(lista, "and da.dat_quarterofyear in");
        lista.add(1);
        lista.add(2);
        String integratorin = setParametersforQuery(lista, "and me.sen_int_id in");
        entity = dao.getMessagesParamIntegrator(companyin, campaignin, channelin, integratorin, yearin, monthin,
                weekofyearin, dayofweekin, dayofmonthin, dayofyearin, hourin, minutein, secondin, quarterin);
        assertNotNull(entity);
        assertTrue(((Statistics) entity).getX().contains("Movistar"));
        assertTrue(((Statistics) entity).getX().contains("Digitel"));
    }

    private String setParametersforQuery(List<Integer> ids, String params) {
        if (ids.isEmpty()) {
            return "";
        }
        params = params.concat("(");
        for (int i = 0; i < ids.size(); i++) {
            params = params.concat(ids.get(i).toString());
            if (i == ids.size() - 1) {
                params = params.concat(")");
            } else {
                params = params.concat(",");
            }
        }
        return params;
    }
}