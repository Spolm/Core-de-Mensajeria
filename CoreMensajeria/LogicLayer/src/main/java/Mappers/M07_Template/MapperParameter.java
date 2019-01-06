package Mappers.M07_Template;

import DTO.DTOFactory;
import DTO.M07_Template.DTOParameter;
import Entities.Entity;
import Entities.M07_Template.MessagePackage.Parameter;
import Mappers.GenericMapper;

import java.util.List;

public class MapperParameter extends GenericMapper {

    @Override
    public Object CreateDto(Entity entity) {
        try{
            Parameter parameter = (Parameter) entity;
            DTOParameter dto_parameter = DTOFactory.CreateDTOParameter(parameter.getParameterId(),parameter.getName(),parameter.getDescription());
            return dto_parameter;
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
        
        return null;
    }

    @Override
    public List<Entity> CreateEntityList(List list) {
        return null;
    }

    @Override
    public List CreateDtoList(List list) {
        return null;
    }
}
