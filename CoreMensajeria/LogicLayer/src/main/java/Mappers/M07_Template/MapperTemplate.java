package Mappers.M07_Template;

import DTO.DTOFactory;
import DTO.M07_Template.DTOTemplate;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M07_Template.Template;
import Mappers.GenericMapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MapperTemplate extends GenericMapper {

    @Override
    public Object CreateDto(Entity entity) {
        try{
            Template template = (Template) entity;
            return DTOFactory.CreateDTOTemplate(template.getMessage(),template.getCreationDate(),template.get_id(),template.getStatus(),template.getChannels(),template.getCampaign(),template.getApplication(),template.getUser(),template.getPlanning());
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
        return EntityFactory.CreateTemplate(dtoTemplate.get_tTemplateid(),dtoTemplate.get_tMessage(), dtoTemplate.get_tCreationDate(),dtoTemplate.get_tChannels(),dtoTemplate.get_tCampaign(),dtoTemplate.get_tApplication(),dtoTemplate.get_tUser(),dtoTemplate.get_tPlanning());

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
