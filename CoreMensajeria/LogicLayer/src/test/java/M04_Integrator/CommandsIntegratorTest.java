package M04_Integrator;

import Exceptions.M04_Integrator.IntegratorNotFoundException;
import Exceptions.PersonalizedException;
import Logic.M04_Integrator.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandsIntegratorTest {

    @Test
    public void commandGetConcreteIntegrator() {
        try {
            CommandGetConcreteIntegrator command = new CommandGetConcreteIntegrator(1);
            command.execute();
            assertNotNull(command.Return());
        }
        catch (PersonalizedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commandGetConcreteIntegratorNotFoundTest() {
        assertThrows(IntegratorNotFoundException.class, () -> {
            CommandGetConcreteIntegrator command = new CommandGetConcreteIntegrator(25);
            command.execute();
        });
    }

    @Test
    public void commandEnableIntegratorTest() {
        try {
            CommandEnableIntegrator command = new CommandEnableIntegrator(1);
            command.execute();
            assertNotNull(command.Return());
        }
        catch (PersonalizedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commandEnableIntegratorNotFoundTest() {
        assertThrows(IntegratorNotFoundException.class, () -> {
            CommandEnableIntegrator command = new CommandEnableIntegrator(25);
            command.execute();
        });
    }

    @Test
    public void commandDisableIntegratorTest() {
        try {
            CommandDisableIntegrator command = new CommandDisableIntegrator(1);
            command.execute();
            assertNotNull(command.Return());
        }
        catch (PersonalizedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void commandDisableIntegratorNotFoundTest() {
        assertThrows(IntegratorNotFoundException.class, () -> {
            CommandDisableIntegrator command = new CommandDisableIntegrator(25);
            command.execute();
        });
    }

    @Test
    public void commandGetAllIntegrator() {
        try {
            CommandGetAllIntegrator command = new CommandGetAllIntegrator();
            command.execute();
            assertNull(command.Return());
            assertNotNull(command.returnList());
            assertTrue(command.returnList().size() > 1);
        }
        catch (PersonalizedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commandGetIntegratorByChannel() {
        try {
            CommandGetIntegratorByChannel command = new CommandGetIntegratorByChannel(1);
            command.execute();
            assertNull(command.Return());
            assertNotNull(command.returnList());
            assertTrue(command.returnList().size() > 1);
        }
        catch (PersonalizedException e) {
            e.printStackTrace();
        }
    }
}
