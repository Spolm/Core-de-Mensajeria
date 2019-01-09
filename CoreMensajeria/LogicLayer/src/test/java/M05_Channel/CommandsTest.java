package M05_Channel;

import Exceptions.PersonalizedException;
import Logic.M05_Channel.CommandGetAllChannels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommandsTest {

    /**
     * Prueba que nos permite saber la lista de canales
     * en el sistema mediante el comando .
     */
    @Test
    public void listChannelTest() {
        try {
            CommandGetAllChannels command = new CommandGetAllChannels();
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
