package Classes.M04_Channel_Integrator.IntegratorPackage;

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
        Integrator integrator = i.getConcreteIntegrator(1);
        assertNotNull(integrator);
        assertEquals(m.getNameIntegrator(), integrator.getNameIntegrator());
        assertEquals(m.getApiIntegrator(), integrator.getApiIntegrator());
        assertEquals(m.getIdIntegrator(), integrator.getIdIntegrator());
        assertEquals(m.getThreadCapacity(), integrator.getThreadCapacity());
        assertEquals(m.getMessageCost(), integrator.getMessageCost());
    }
}