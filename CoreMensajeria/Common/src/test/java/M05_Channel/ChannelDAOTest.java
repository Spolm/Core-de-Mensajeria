package M05_Channel;

import Classes.M04_Integrator.Integrator;
import Classes.M05_Channel.Channel;
import Classes.M05_Channel.ChannelDAO;
import Exceptions.ChannelNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChannelDAOTest {
    private static ChannelDAO _channelDAO;
    private static ArrayList<Integrator> _integratorList;
    private static ArrayList<Channel> _channelList;

    @BeforeEach
    public void init() {
        _channelDAO = new ChannelDAO();
        _integratorList = new ArrayList<>();
        _channelList = new ArrayList<>();
    }

    /**
     * Prueba que nos permite  saber si la lista de
     * los integradores por canales es diferente a Null.
     */
    @Test
    public void TestIntegradorsByChannel() {
        try {
            _integratorList = _channelDAO.listIntegratorByChannel(1);
            assertNotNull(_integratorList);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (ChannelNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Prueba que nos permite saber la lista de canales
     * en el sistema es diferente a Null.
     */

    @Test
    public void listChannel() {
        try {
            _channelList = _channelDAO.listChannel();
            assertNotNull(_channelList);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prueba de la excepción, ejecutando el método de buscar un integrador
     * cuando no se encuentra un canal.
     */

    @Test
    public void channelNotFoundExceptionTest() {
        assertThrows(ChannelNotFoundException.class, () -> {
            _integratorList = _channelDAO.listIntegratorByChannel(15);
        });
    }
}