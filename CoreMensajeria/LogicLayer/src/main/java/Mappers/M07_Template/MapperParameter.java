package Mappers.M07_Template;

import DTO.DTOFactory;
import DTO.M07_Template.DTOParameter;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M07_Template.MessagePackage.Parameter;
import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapperParameter extends GenericMapper {

    @Override
    public Object CreateDto(Entity entity) {
        try{
            Parameter parameter = (Parameter) entity;
            return DTOFactory.CreateDTOParameter(parameter.getParameterId(),parameter.getName(),parameter.getDescription());
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
        DTOParameter dtoParameter = (DTOParameter) o;
        return EntityFactory.CreateParameter(dtoParameter.getPid(),dtoParameter.get_pName(),dtoParameter.get_pDescription());
    }

    @Override //Convierte DTO en E y las anade en la lista de entidades
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
