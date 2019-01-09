package M04_Integrator;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import webService.M04_Integrator.M04_Integrator;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ServiceIntegratorTest {

    @Test
    public void getConcreteIntegratorTest(){
        M04_Integrator integratorService = new M04_Integrator();
        Response response = integratorService.getConcreteIntegrator(1);
        assertNotNull(response);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void listIntegratorTest(){
        M04_Integrator integratorService = new M04_Integrator();
        Response response = integratorService.listIntegrator();
        assertNotNull(response);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void listIntegratorByChannelTest(){
        M04_Integrator integratorService = new M04_Integrator();
        Response response = integratorService.listIntegratorByChannel(1);
        assertNotNull(response);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void enableIntegratorTest(){
        M04_Integrator integratorService = new M04_Integrator();
        Response response = integratorService.enableIntegrator(1);
        assertNotNull(response);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void disableIntegratorTest(){
        M04_Integrator integratorService = new M04_Integrator();
        Response response = integratorService.disableIntegrator(1);
        assertNotNull(response);
        assertEquals(200, response.getStatus());
    }
}
