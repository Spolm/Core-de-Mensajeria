package Classes.M03_Channel_Integrator.IntegratorPackage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class IntegratorServiceTest {
    static IntegratorService i;

    @BeforeAll
    public static void before() {
        i = new IntegratorService();
    }

    @Test
    public void testIntegratorsList() {
        List<Integrator> integrators = i.getInstance().listIntegrator();
        assertNotNull(integrators);
    }

    @Test
    public void testIntegratorConcrete() {
        Movistar m = new Movistar(1, 25, 13.4f, "Movistar", "oqiwueyeiu");
        Integrator integrator = i.getConcreteIntegrator(9);
        assertEquals(m,integrator);
    }
}