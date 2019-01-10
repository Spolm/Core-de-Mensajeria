package Mappers.M04_Integrator;

import DTO.DTOFactory;
import DTO.M04_Integrator.DTOIntegrator;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M04_Integrator.Integrator;
import Mappers.GenericMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MapperIntegrator extends GenericMapper<DTOIntegrator> {

    final static Logger log = LogManager.getLogger("CoreMensajeria");

    @Override
    public DTOIntegrator CreateDto(Entity entity) {
        //region Instrumentation Debug
        log.debug("Entrando al metodo CreateDTO MapperIntegrator");
        //endregion
        Integrator integrator = (Integrator) entity;
        //region Instrumentation Debug
        log.debug("Saliendo del metodo CreateDTO MapperIntegrator");
        //endregion
        return DTOFactory.CreateDTOIntegrator(integrator.get_id(), integrator.getClass().getName(),
                integrator.getThreadCapacity(), integrator.getMessageCost(),
                integrator.getNameIntegrator(), integrator.getApiIntegrator(),
                integrator.isEnabled());
    }

    @Override
    public Entity CreateEntity(DTOIntegrator dtoIntegrator) {
        //region Instrumentation Debug
        log.debug("Entrando al metodo CreateEntity MapperIntegrator");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo del metodo CreateEntity MapperIntegrator");
        //endregion
        return EntityFactory.CreateIntegrator(
                dtoIntegrator.getIntegratorType(),dtoIntegrator.getId(),
                dtoIntegrator.getNameIntegrator(), dtoIntegrator.getMessageCost(),
                dtoIntegrator.getThreadCapacity(), dtoIntegrator.getApiIntegrator(),
                dtoIntegrator.isEnabled());
    }

    @Override
    public List<DTOIntegrator> CreateDtoList(List<Entity> entities) {
        //region Instrumentation Debug
        log.debug("Entrando al metodo CreateDtoList MapperIntegrator");
        //endregion
        ArrayList<DTOIntegrator> dtoList = new ArrayList<>();
        for (Entity _integrator : entities) {
            dtoList.add( CreateDto(_integrator));
        }
        //region Instrumentation Debug
        log.debug("Saliendo del metodo CreateDtoList MapperIntegrator");
        //endregion
        return dtoList;
    }

    @Override
    public List<Entity> CreateEntityList(List<DTOIntegrator> dtoIntegrators) {
        //region Instrumentation Debug
        log.debug("Entrando al metodo CreateEntityList MapperIntegrator");
        //endregion
        ArrayList<Entity> entityList = new ArrayList<>();
        for (DTOIntegrator _dtointegrator : dtoIntegrators) {
            entityList.add ( CreateEntity ( _dtointegrator ) );
        }
        //region Instrumentation Debug
        log.debug("Saliendo del metodo CreateEntityList MapperIntegrator");
        //endregion
        return entityList;
    }
}
