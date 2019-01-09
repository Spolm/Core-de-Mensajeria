package Mappers.M10_Profile;

import DTO.M10_DTO.DTOEditUser;
import Entities.Entity;
import Entities.EntityFactory;
import Mappers.GenericMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * MapperEditUser class is a mapper that translates DTOEditUser to User and vice versa
 */
public class MapperEditUser extends GenericMapper {
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    @Override
    public Object CreateDto(Entity entity) {
        return null;
    }

    /**
     *
     * @param o object with information for generate a DTOEditUser
     * @return Entity with information a User
     */
    @Override
    public Entity CreateEntity(Object o) {
        //region Instrumentation Debug
        log.debug("Entrando al metodo CreateEntity(" + o.toString() +") de MapperEditUser");
        //endregion
        DTOEditUser dto = (DTOEditUser) o;
        Entity e = EntityFactory.CreateUser(dto.getId(), null, null, 0, dto.getEmail(),
                dto.getPhone(), null, null, dto.getAddress(), dto.getGender(), null,
                dto.getName(), dto.getLastname(), dto.getCi(), dto.getGr(), dto.getBirthdate());
        //region Instrumentation Info
        log.info("Se ejecuto al metodo CreateEntity(" + o.toString() +") de MapperEditUser exitosamente");
        //endregion

        //region Instrumentation Debug
        log.debug("Saliendo al metodo CreateEntity retornando:" + e.toString() +" de MapperEditUser");
        //endregion
        return e;
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
