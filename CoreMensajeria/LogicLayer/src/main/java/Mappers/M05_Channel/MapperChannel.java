package Mappers.M05_Channel;

import DTO.DTOFactory;
import DTO.M05_Channel.DTOChannel;
import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M05_Channel.Channel;
import Mappers.GenericMapper;
import java.util.ArrayList;
import java.util.List;

public class MapperChannel extends GenericMapper<DTOChannel> {

    @Override
    public DTOChannel CreateDto(Entity entity) {
        Channel channel = (Channel) entity;
        return DTOFactory.createDTOChannel(
                channel.get_nameChannel(),
                channel.get_descriptionChannel(),
                channel.get_integrators()
        );
    }

    @Override
    public Entity CreateEntity(DTOChannel dtoChannel) {
        return EntityFactory.CreateChannel(
                -1,
                dtoChannel.get_nameChannel(),
                dtoChannel.get_descriptionChannel(),
                dtoChannel.get_integrators()
        );
    }

    @Override
    public List<DTOChannel> CreateDtoList(List<Entity> entities) {
        ArrayList<DTOChannel> dtoChannelList = new ArrayList<>();

        for (Entity entity : entities) {
            dtoChannelList.add(CreateDto(entity));
        }

        return dtoChannelList;
    }

    @Override
    public List<Entity> CreateEntityList(List<DTOChannel> dtoChannels) {
        ArrayList<Entity> entityList = new ArrayList<>();

        for (DTOChannel dtoChannel : dtoChannels) {
            entityList.add(CreateEntity(dtoChannel));
        }

        return entityList;
    }
}
