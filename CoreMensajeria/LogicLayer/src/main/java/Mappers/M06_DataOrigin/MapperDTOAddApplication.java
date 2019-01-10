package Mappers.M06_DataOrigin;

import DTO.DTOFactory;
import DTO.M06_DataOrigin.DTOAddApplication;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M06_DataOrigin.AddApplicationData;
import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperDTOAddApplication extends GenericMapper <DTOAddApplication> {


    @Override
    public DTOAddApplication CreateDto(Entity entity) {
        try {
            DTOAddApplication dto = null;
            AddApplicationData _cam = (AddApplicationData) entity;
            dto = DTOFactory.addApplication(dto.get_nameApplication(),
                    dto.get_descriptionApplication(),dto.get_userId()
                    ,dto.get_companyId());

            return dto;
        }
        catch (Exception e) {

            throw e;
        }
    }

    @Override
    public Entity CreateEntity(DTOAddApplication dto) {
        try {
            AddApplicationData _addApp = EntityFactory.createApplicationData(dto.get_nameApplication(),
                    dto.get_descriptionApplication(), dto.get_userId(), dto.get_companyId());

            return _addApp;

        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<DTOAddApplication> CreateDtoList(List<Entity> entities) {
        try
        {
            ArrayList<DTOAddApplication> dtos = new ArrayList<>();

            for (Entity _cam : entities) {
                dtos.add( CreateDto( _cam ) );
            }

            return dtos;

        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public List<Entity> CreateEntityList(List<DTOAddApplication> dtoAddApplications) {
        try
        {
            ArrayList<Entity> AddApplication = new ArrayList<>();

            for (DTOAddApplication _dtoAddApp : dtoAddApplications) {
                AddApplication.add ( CreateEntity ( _dtoAddApp ) );
            }

            return AddApplication ;

        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
