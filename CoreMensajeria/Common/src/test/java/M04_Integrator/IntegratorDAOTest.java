package M04_Integrator;

import Classes.M04_Integrator.Integrator;
import Classes.M04_Integrator.IntegratorDAO;
import Classes.M04_Integrator.MessageIntegrator;
import Classes.M04_Integrator.Movistar;
import Classes.M07_Template.MessagePackage.Message;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.IntegratorNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegratorDAOTest {
    private static IntegratorDAO _integratorDAO;
    private static ArrayList<Integrator> _integratorList;
    private static Movistar _movistar;
    private static Integrator _integrator;
    private static MessageIntegrator _messageIntegrator;
    boolean status;

    @BeforeAll
    public static void before() {

        _integratorList = new ArrayList<>();
        _movistar = new Movistar(1, 25, 13.4f,
                "Movistar", "oqiwueyeiu", true);
        _integrator = null;
        _messageIntegrator = new MessageIntegrator("TestMsg","TestDir","TestId");
        _messageIntegrator.setSend(true);

    }

    @BeforeEach
    public void init() {
        _integratorDAO = new IntegratorDAO();
    }

    @Test
    public void ListIntegratorTest() {
        try {
            _integratorList = _integratorDAO.listIntegrator();
            assertNotNull(_integratorList);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ConcreteIntegratorTest() {
        try {
            _integrator = _integratorDAO.getConcreteIntegrator(1);
            assertNotNull(_integrator);
            assertEquals(_movistar.getNameIntegrator(), _integrator.getNameIntegrator());
            assertEquals(_movistar.getApiIntegrator(), _integrator.getApiIntegrator());
            assertEquals(_movistar.getIdIntegrator(), _integrator.getIdIntegrator());
            assertEquals(_movistar.getThreadCapacity(), _integrator.getThreadCapacity());
            assertEquals(_movistar.getMessageCost(), _integrator.getMessageCost());
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (IntegratorNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void IntegratorNotFoundExceptionTest() {
        assertThrows(IntegratorNotFoundException.class, () -> {
            _integrator = _integratorDAO.getConcreteIntegrator(15);
        });
    }

    @Test
    public void IntegratorDisabledTest() {
        try {
            _integrator = _integratorDAO.getConcreteIntegrator(1);
            status = _integrator.isEnabled();
            _integratorDAO = new IntegratorDAO();
            _integratorDAO.disableIntegrator(1);
            _integratorDAO = new IntegratorDAO();
            _integrator = _integratorDAO.getConcreteIntegrator(1);
            if (status) {
                _integratorDAO = new IntegratorDAO();
                _integratorDAO.enableIntegrator(1);
            }
            assertEquals(false, _integrator.isEnabled());
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (IntegratorNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void IntegratorEnableTest() {
        try {
            _integrator = _integratorDAO.getConcreteIntegrator(1);
            status = _integrator.isEnabled();
            _integratorDAO = new IntegratorDAO();
            _integratorDAO.enableIntegrator(1);
            _integratorDAO = new IntegratorDAO();
            _integrator = _integratorDAO.getConcreteIntegrator(1);
            if (!status) {
                _integratorDAO = new IntegratorDAO();
                _integratorDAO.disableIntegrator(1);
            }
            assertEquals(true, _integrator.isEnabled());
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (IntegratorNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendMessageTest(){
        MessageIntegrator testMessage = _movistar.sendMessage("TestMsg","TestDir","TestId");
        assertEquals(_messageIntegrator,testMessage);

    }
}