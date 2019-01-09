package Mappers.M07_Template;

import DTO.DTOFactory;
import DTO.M07_Template.DTOMessage;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M07_Template.MessagePackage.Message;
import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperMessage extends GenericMapper {

    @Override
    public Object CreateDto(Entity entity) {
        try{
            Message message = (Message) entity;
            return DTOFactory.CreateDTOMessage(message.get_id(),message.getParameterArrayList(),message.getMessage());
        }
        catch (NullPointerException e) {
            throw e;
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Entity CreateEntity(Object o) {
        DTOMessage dtoMessage = (DTOMessage) o;
        return EntityFactory.CreateMessage(dtoMessage.get_mMessageId(),dtoMessage.get_mParameterArrayList(),dtoMessage.get_mMessage());
    }

    @Override
    public List<Entity> CreateEntityList(List list) {
        ArrayList<Entity> entityList = new ArrayList<Entity>();
        for (Object e:list){
            entityList.add(CreateEntity(e));
        }
        return entityList;
    }

    @Override
    public List CreateDtoList(List list) {
        ArrayList<Object> dtoList = new ArrayList<Object>();
        for(Object e: list){
            dtoList.add(CreateDto((Entity) e));
        }
        return dtoList;
    }
}
