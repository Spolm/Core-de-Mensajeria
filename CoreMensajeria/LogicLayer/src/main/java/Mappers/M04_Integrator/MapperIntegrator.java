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
        return DTOFactory.CreateDTOIntegrator( _integrator.getThreadCapacity(),
                _integrator.getMessageCost(),_integrator.getNameIntegrator(), _integrator.getApiIntegrator(),
                _integrator.isEnabled());
       /* try {
            DTOIntegrator dto = null;
            Integrator _integrator = (Integrator) entity;
            dto = DTOFactory.CreateDTOIntegrator( _integrator.getThreadCapacity(),
            _integrator.getMessageCost(),_integrator.getNameIntegrator(), _integrator.getApiIntegrator(),
            _integrator.isEnabled());
            return dto;
        }
        catch(Exception e){

        }
        return null;*/
    }


    @Override
    public Entity CreateEntity(DTOIntegrator dtoIntegrator) {
        try {
            Integrator _integrator = EntityFactory.CreateIntegrator(null,-1,
                    dtoIntegrator.getNameIntegrator(),dtoIntegrator.getMessageCost(), dtoIntegrator.getThreadCapacity(),
                    dtoIntegrator.getApiIntegrator(), dtoIntegrator.isEnabled());
            return _integrator ;

        }
        catch (Exception e){
            throw e;
        }
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
