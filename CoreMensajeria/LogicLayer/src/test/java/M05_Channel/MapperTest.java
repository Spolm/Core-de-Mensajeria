package M05_Channel;

import DTO.M05_Channel.DTOChannel;
import Entities.Entity;
import Entities.M05_Channel.Channel;
import Entities.M05_Channel.ChannelSms;
import Mappers.M05_Channel.MapperChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MapperTest {
    private static MapperChannel _mapper;
    private static Channel _channel;
    private static DTOChannel _dtoChannel;

    @BeforeEach
    public void init() {
        _mapper = new MapperChannel();
        _channel = new ChannelSms(1, "Yahoo", "Yahoo sms service");
        _dtoChannel = new DTOChannel("Claro", "Claro sms service");
    }

    @Test
    public void CreateDtoTest(){
        DTOChannel dtoChannel = _mapper.CreateDto(_channel);
        assertNotNull(dtoChannel);
        assertEquals("Yahoo", dtoChannel.get_nameChannel());
        assertEquals("Yahoo sms service", dtoChannel.get_descriptionChannel());
    }

    @Test
    public void CreateEntityTest(){
        ChannelSms channel = (ChannelSms) _mapper.CreateEntity(_dtoChannel);
        assertNotNull(channel);
        assertEquals(-1, channel.get_id());
        assertEquals("Claro", channel.get_nameChannel());
        assertEquals("Claro sms service", channel.get_descriptionChannel());
    }

    @Test
    public void CreateEntityListTest(){
        ArrayList<DTOChannel> dtoChannelList = new ArrayList<>();
        dtoChannelList.add(_dtoChannel);

        assertNotNull(_mapper.CreateEntityList(dtoChannelList));
    }

    @Test
    public void CreateDTOListTest(){
        ArrayList<Entity> channelList = new ArrayList<>();
        channelList.add(_channel);

        assertNotNull(_mapper.CreateDtoList(channelList));
    }
}
