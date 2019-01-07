package Mappers.LoginMapper;

import DTO.M01_DTO.DTOLogin;
import Entities.Entity;
import Mappers.GenericMapper;

import java.util.List;

public class LoginMapper extends GenericMapper <DTOLogin> {
    @Override
    public DTOLogin CreateDto(Entity entity) {
        return null;
    }

    @Override
    public Entity CreateEntity(DTOLogin dtoLogin) {
        return null;
    }

    @Override
    public List<DTOLogin> CreateDtoList(List<Entity> entities) {
        return null;
    }

    @Override
    public List<Entity> CreateEntityList(List<DTOLogin> dtoLogins) {
        return null;
    }
}
