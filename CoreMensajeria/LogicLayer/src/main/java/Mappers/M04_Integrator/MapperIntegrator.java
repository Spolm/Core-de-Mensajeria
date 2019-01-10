package Mappers.M04_Integrator;

import DTO.DTOFactory;
import DTO.M04_Integrator.DTOIntegrator;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M04_Integrator.Integrator;
import Mappers.GenericMapper;
import java.util.ArrayList;
import java.util.List;

public class MapperIntegrator extends GenericMapper<DTOIntegrator> {

    @Override
    public DTOIntegrator CreateDto(Entity entity) {
        Integrator integrator = (Integrator) entity;
        return DTOFactory.CreateDTOIntegrator(integrator.get_id(), integrator.getClass().getName(),
                integrator.getThreadCapacity(), integrator.getMessageCost(),
                integrator.getNameIntegrator(), integrator.getApiIntegrator(),
                integrator.isEnabled());
    }

    @Override
    public Entity CreateEntity(DTOIntegrator dtoIntegrator) {
        return EntityFactory.CreateIntegrator(
                dtoIntegrator.getIntegratorType(),dtoIntegrator.getId(),
                dtoIntegrator.getNameIntegrator(), dtoIntegrator.getMessageCost(),
                dtoIntegrator.getThreadCapacity(), dtoIntegrator.getApiIntegrator(),
                dtoIntegrator.isEnabled());
    }

    @Override
    public List<DTOIntegrator> CreateDtoList(List<Entity> entities) {
        ArrayList<DTOIntegrator> dtoList = new ArrayList<>();
        for (Entity _integrator : entities) {
            dtoList.add( CreateDto(_integrator));
        }
        return dtoList;
    }

    @Override
    public List<Entity> CreateEntityList(List<DTOIntegrator> dtoIntegrators) {
        ArrayList<Entity> entityList = new ArrayList<>();
        for (DTOIntegrator _dtointegrator : dtoIntegrators) {
            entityList.add ( CreateEntity ( _dtointegrator ) );
        }
        return entityList;
    }
}
