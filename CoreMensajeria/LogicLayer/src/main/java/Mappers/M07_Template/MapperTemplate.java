package Mappers.M07_Template;

import DTO.DTOFactory;
import DTO.M07_Template.DTOTemplate;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M07_Template.Template;
import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperTemplate extends GenericMapper {

    @Override
    public DTOTemplate CreateDto(Entity entity) {
        try{
            Template template = (Template) entity;
            return DTOFactory.CreateDTOTemplate(template.getMessage(),template.getCreationDate(),template.get_id(),template.getStatus(),template.getChannels(),template.getCampaign(),template.getApplication(),template.getUser(),template.getPlanning(),template.getCompanyId());
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
        DTOTemplate dtoTemplate = (DTOTemplate) o;
        return EntityFactory.CreateTemplate(dtoTemplate.get_id(),dtoTemplate.getMessage(), dtoTemplate.getCreationDate(),dtoTemplate.getStatus(),dtoTemplate.getChannels(),dtoTemplate.getCampaign(),dtoTemplate.getApplication(),dtoTemplate.getUser(),dtoTemplate.getPlanning());

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
