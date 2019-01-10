package M05_Channel;

import Entities.Entity;
import Exceptions.DatabaseConnectionProblemException;
import Persistence.M05_Channel.DAOChannel;
import Persistence.M05_Channel.IDAOChannel;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class DAOChannelTest {

    /**
     * Prueba que nos permite saber la lista de canales
     * en el sistema es diferente a Null.
     */
    @Test
    public void listChannelTest() {
        try {
            IDAOChannel daoChannel = new DAOChannel();
            ArrayList<Entity> channelList = daoChannel.listChannel();
            assertNotNull(channelList);
            assertTrue(channelList.size() > 1);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }
}