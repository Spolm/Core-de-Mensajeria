package M05_Channel;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import webService.M05_Channel.M05_Channel;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ServiceTest {

    @Test
    public void listChannelTest(){
        M05_Channel  channelService = new M05_Channel();
        Response response = channelService.listChannel();
        assertEquals(200, response.getStatus());
    }
}
