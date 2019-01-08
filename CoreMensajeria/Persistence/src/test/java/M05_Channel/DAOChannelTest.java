package M05_Channel;

import Entities.Entity;
import Exceptions.DatabaseConnectionProblemException;
import Persistence.M05_Channel.DAOChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class DAOChannelTest {
    private static DAOChannel _DAOChannel;
    private static ArrayList<Entity> _channelList;

    @BeforeEach
    public void init() {
        _DAOChannel = new DAOChannel();
        _channelList = new ArrayList<>();
    }

    /**
     * Prueba que nos permite saber la lista de canales
     * en el sistema es diferente a Null.
     */
    @Test
    public void listChannelTest() {
        try {
            _channelList = _DAOChannel.listChannel();
            assertNotNull(_channelList);
        }
        catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }
}