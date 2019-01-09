package Mappers.M04_Integrator;

import DTO.DTO;
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
        Integrator _integrator = (Integrator) entity;
        return DTOFactory.CreateDTOIntegrator(null, _integrator.getThreadCapacity(),
                _integrator.getMessageCost(),_integrator.getNameIntegrator(), _integrator.getApiIntegrator(),
                _integrator.isEnabled());
    }


    @Override
    public Entity CreateEntity(DTOIntegrator dtoIntegrator) {
        return EntityFactory.CreateIntegrator(
                dtoIntegrator.getIntegratorType(),-1,
                dtoIntegrator.getNameIntegrator(), dtoIntegrator.getMessageCost(),
                dtoIntegrator.getThreadCapacity(), dtoIntegrator.getApiIntegrator(),
                dtoIntegrator.isEnabled());
    }




    @Override
    public List<DTOIntegrator> CreateDtoList(List<Entity> entities) {

        try
        {
            ArrayList<DTOIntegrator> dtoIntegrators = new ArrayList<>();

            for (Entity _integrator : entities) {
                dtoIntegrators.add( CreateDto(_integrator));
            }

            return dtoIntegrators;

        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public List<Entity> CreateEntityList(List<DTOIntegrator> dtoIntegrators) {

        {
            ArrayList<Entity> entityIntegrator = new ArrayList<>();

            for (DTOIntegrator _dtointegrator : dtoIntegrators) {
                entityIntegrator.add ( CreateEntity ( _dtointegrator ) );
            }

            return entityIntegrator ;

        }
    }
}
