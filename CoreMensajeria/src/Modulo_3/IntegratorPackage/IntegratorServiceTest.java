package Modulo_3.IntegratorPackage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
}