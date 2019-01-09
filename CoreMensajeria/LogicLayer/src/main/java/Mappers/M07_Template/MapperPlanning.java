package Mappers.M07_Template;

import DTO.DTOFactory;
import DTO.M07_Template.DTOPlanning;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M07_Template.PlanningPackage.Planning;
import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperPlanning extends GenericMapper {


    @Override
    public Object CreateDto(Entity entity) {
        try{
            Planning planning = (Planning) entity;
            return DTOFactory.CreateDTOPlanning(planning.getStartDate(),planning.getEndDate(),planning.getStartTime(),planning.getEndTime(),planning.getIdPlanning());
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
        DTOPlanning dtoPlanning = (DTOPlanning) o;
        return EntityFactory.CreatePlanning(dtoPlanning.get_pStartDate(),dtoPlanning.get_pEndDate(),dtoPlanning.get_pStartTime(),dtoPlanning.get_pEndTime(),dtoPlanning.get_pIdPlanning());
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
