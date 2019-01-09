package M09_Statistics;

import Entities.Entity;
import Entities.M02_Company.Company;
import Entities.M04_Integrator.Integrator;
import Exceptions.ChannelNotFoundException;
import Exceptions.CompanyDoesntExistsException;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatistic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DAOStatisticTest {

    private DAOStatistic dao;

    @BeforeEach
    void setUp() {
        dao = DAOFactory.instanciateDAOStatistic();
    }

    @AfterEach
    void tearDown() {
        dao.close();
    }

    @Test
    void getAllCompanies() {
        try{
            ArrayList<Entity> companies = dao.getAllCompanies(3);
            assertNotNull(companies);
            assertEquals(3, companies.size());
            assertTrue(((Company)companies.get(0)).get_name().contains("Company 1"));
            assertTrue(((Company)companies.get(1)).get_name().contains("Company 2"));
            assertTrue(((Company)companies.get(2)).get_name().contains("Company 3"));
        }catch (CompanyDoesntExistsException e){
            e.printStackTrace();
        }

    }

    @Test
    void getIntegratorsForChannel() {
        List<Integer> channelIds = Arrays.asList(1,2);
        try {
            ArrayList<Entity> integrators = dao.getIntegratorsForChannel(channelIds);
            assertNotNull(integrators);
            assertEquals(6, integrators.size());
            assertTrue(((Integrator)integrators.get(0)).getNameIntegrator().contains("Movistar"));
            assertTrue(((Integrator)integrators.get(1)).getNameIntegrator().contains("Digitel"));
            assertTrue(((Integrator)integrators.get(2)).getNameIntegrator().contains("Movilnet"));
            assertTrue(((Integrator)integrators.get(3)).getNameIntegrator().contains("MailChimp"));
            assertTrue(((Integrator)integrators.get(4)).getNameIntegrator().contains("Aweber"));
            assertTrue(((Integrator)integrators.get(5)).getNameIntegrator().contains("Infusionsoft"));
        }catch (ChannelNotFoundException e){
            e.printStackTrace();
        }
    }
}