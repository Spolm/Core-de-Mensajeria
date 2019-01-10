package Mappers.LoginMapper;

import DTO.DTOFactory;
import DTO.M01_DTO.DTOLogin;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M01_Login.LoginIntent;
import Mappers.GenericMapper;

import java.util.List;

public class LoginMapper extends GenericMapper <DTOLogin> {
    @Override
    public DTOLogin CreateDto(Entity entity) {
        DTOLogin dto = null;
        try {

            LoginIntent _log = (LoginIntent) entity;
            dto = DTOFactory.createDTOLogin(_log.get_username(), _log.get_password());

        }
        catch (Exception e) {
            throw e;
        }
        return dto;
    }

    @Override
    public Entity CreateEntity(DTOLogin dtoLogin) {
        Entity _log;
        try {

            _log = EntityFactory.createLoginIntent(dtoLogin.get_username(), dtoLogin.get_password());


        } catch (Exception e) {
            throw e;
        }
        return _log;
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
