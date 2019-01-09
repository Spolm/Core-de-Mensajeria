package M04_Integrator;

import Exceptions.ChannelNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.IntegratorNotFoundException;
import Logic.M04_Integrator.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommandsTest {

    @Test
    public void commandDisableIntegratorTest() {
        try {
            CommandDisableIntegrator command = new CommandDisableIntegrator(1);
            command.execute();
            assertNotNull(command.Return());
        }
        catch (IntegratorNotFoundException e) {
            e.printStackTrace();
        }
        catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void commandEnableIntegratorTest() {
        try {
            CommandEnableIntegrator command = new CommandEnableIntegrator(1);
            command.execute();
            assertNotNull(command.Return());
        }
        catch (IntegratorNotFoundException e) {
            e.printStackTrace();
        }
        catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commandGetAllIntegrator() {
        try {
            CommandGetAllIntegrator command = new CommandGetAllIntegrator();
            command.execute();
            assertNotNull(command.ReturnList());
        }
        catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commandGetConcreteIntegrator() {
        try {
            CommandGetConcreteIntegrator command = new CommandGetConcreteIntegrator(1);
            command.execute();
            assertNotNull(command.Return());
        }
        catch (IntegratorNotFoundException e) {
            e.printStackTrace();
        }
        catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commandGetIntegratorByChannel() {
        try {
            CommandGetIntegratorByChannel command = new CommandGetIntegratorByChannel(1);
            command.execute();
            assertNull(command.Return());
            assertNotNull(command.ReturnList());
        }
        catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
        catch (ChannelNotFoundException e) {
            e.printStackTrace();
        }
    }
}
