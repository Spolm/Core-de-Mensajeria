package M05_Channel;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import webService.M05_Channel.M05_Channel;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ServiceTest {
    private static M05_Channel _channelService;

    @BeforeEach
    public void init() {
        _channelService = new M05_Channel();
    }

    @Test
    public void listChannelTest(){
        Response response = _channelService.listChannel();
        assertNotNull(response);
    }
}
