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

    @Test
    public void listChannel() {
        try {
            _channelList = _channelDAO.listChannel();
            assertNotNull(_channelList);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void channelNotFoundExceptionTest() {
        assertThrows(ChannelNotFoundException.class, () -> {
            _integratorList = _channelDAO.listIntegratorByChannel(15);
        });
    }
}