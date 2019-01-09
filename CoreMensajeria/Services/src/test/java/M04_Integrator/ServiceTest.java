package M04_Integrator;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import webService.M04_Integrator.M04_Integrator;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ServiceTest {
    private static M04_Integrator _integratorService;

    @BeforeEach
    public void init() {
        _integratorService = new M04_Integrator();
    }

    /*@Test
    public void listChannelTest(){
        Response response = _integratorService.listIntegrator();
        assertNotNull(response);
    }*/

}
