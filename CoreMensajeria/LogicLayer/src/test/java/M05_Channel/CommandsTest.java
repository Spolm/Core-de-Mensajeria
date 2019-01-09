package M05_Channel;

import Exceptions.DatabaseConnectionProblemException;
import Logic.M05_Channel.CommandGetAllChannels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommandsTest {
    private static CommandGetAllChannels _command;

    @BeforeEach
    public void init() {
        _command = new CommandGetAllChannels();
    }

    /**
     * Prueba que nos permite saber la lista de canales
     * en el sistema mediante el comando .
     */
    @Test
    public void listChannelTest() {
        try {
            _command.execute();
            assertNull(_command.Return());
            assertNotNull(_command.returnList());
        }
        catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }
}
