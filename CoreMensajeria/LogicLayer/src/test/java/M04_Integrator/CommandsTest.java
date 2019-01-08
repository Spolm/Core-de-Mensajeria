package M04_Integrator;

import Entities.Entity;
import Exceptions.ChannelNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.IntegratorNotFoundException;
import Logic.M04_Integrator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommandsTest {

    private static CommandDisableIntegrator _commandd;
    private static CommandEnableIntegrator _commande;
    private static CommandGetAllIntegrator _commandga;
    private static CommandGetConcreteIntegrator _commandgci;
    private static CommandGetIntegratorByChannel _commandgic;

    @BeforeEach
    public void init() {
        _commandd = new CommandDisableIntegrator(1);
        _commande = new CommandEnableIntegrator(1);
        _commandga = new CommandGetAllIntegrator();
        _commandgci = new CommandGetConcreteIntegrator(1);
    }

    @Test
    public void commandDisableIntegratorTest() {
        try {
            _commandd.execute();
            assertNotNull(_commandd.Return());
        } catch (IntegratorNotFoundException e) {
            e.printStackTrace();
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void commandEnableIntegratorTest() {
        try {
            _commande.execute();
            assertNotNull(_commande.Return());
        } catch (IntegratorNotFoundException e) {
            e.printStackTrace();
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commandGetAllIntegrator() {
        try {
            _commandga.execute();
            assertNotNull(_commandga.ReturnList());
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commandGetConcreteIntegrator() {
        try {
            _commandgci.execute();
            assertNull(_commandgic.ReturnList());
            assertNotNull(_commandgci.Return());
        } catch (IntegratorNotFoundException e) {
            e.printStackTrace();
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commandGetIntegratorByChannel() {

        try {
            _commandgic.execute();
            assertNull(_commandgic.Return());
            assertNotNull(_commandgic.ReturnList());
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (ChannelNotFoundException e) {
            e.printStackTrace();
        }


    }
}
