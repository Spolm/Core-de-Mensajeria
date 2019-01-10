package Mappers.M05_Channel;

import DTO.DTOFactory;
import DTO.M05_Channel.DTOChannel;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M05_Channel.Channel;
import Mappers.GenericMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MapperChannel extends GenericMapper<DTOChannel> {

    final static Logger log = LogManager.getLogger("CoreMensajeria");

    @Override
    public DTOChannel CreateDto(Entity entity) {
        //region Instrumentation Debug
        log.debug("Entrando al metodo CreateDto MapperChannel");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo del metodo CreateDto MapperChannel");
        //endregion
        Channel channel = (Channel) entity;
        return DTOFactory.createDTOChannel(
                channel.get_id(),
                channel.get_nameChannel(),
                channel.get_descriptionChannel(),
                channel.get_integrators()
        );
    }

    @Override
    public Entity CreateEntity(DTOChannel dtoChannel) {
        //region Instrumentation Debug
        log.debug("Entrando al metodo CreateEntity MapperChannel");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo del metodo CreateEntity MapperChannel");
        //endregion
        return EntityFactory.createChannel(
                dtoChannel.get_id(),
                dtoChannel.get_nameChannel(),
                dtoChannel.get_descriptionChannel(),
                dtoChannel.get_integrators()
        );
    }

    @Override
    public List<DTOChannel> CreateDtoList(List<Entity> entities) {
        //region Instrumentation Debug
        log.debug("Entrando al metodo CreateDtoList MapperChannel");
        //endregion
        ArrayList<DTOChannel> dtoList = new ArrayList<>();
        for (Entity entity : entities) {
            dtoList.add(CreateDto(entity));
        }
        //region Instrumentation Debug
        log.debug("Saliendo del metodo CreateDtoList MapperChannel");
        //endregion
        return dtoList;
    }

    @Override
    public List<Entity> CreateEntityList(List<DTOChannel> dtoChannels) {
        //region Instrumentation Debug
        log.debug("Entrando al metodo CreateEntityList MapperChannel");
        //endregion
        ArrayList<Entity> entityList = new ArrayList<>();
        for (DTOChannel dtoChannel : dtoChannels) {
            entityList.add(CreateEntity(dtoChannel));
        }
        //region Instrumentation Debug
        log.debug("Saliendo del metodo CreateEntityList MapperChannel");
        //endregion
        return entityList;
    }
}
