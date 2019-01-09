package Mappers.M10_Profile;

import DTO.M10_DTO.DTOEditUser;
import Entities.Entity;
import Entities.EntityFactory;
import Mappers.GenericMapper;

import java.util.List;

public class MapperEditUser extends GenericMapper {
    @Override
    public Object CreateDto(Entity entity) {
        return null;
    }

    @Override
    public Entity CreateEntity(Object o) {
        DTOEditUser dto = (DTOEditUser) o;
        return EntityFactory.CreateUser(dto.getId(), null, null, 0, dto.getEmail(),
                dto.getPhone(), null, null, dto.getAddress(), dto.getGender(), null,
                dto.getName(), dto.getLastname(), dto.getCi(), dto.getGr(), dto.getBirthdate());
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
