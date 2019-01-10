package Mappers.LoginMapper;

import DTO.DTO;
import DTO.M01_DTO.DTOUser;
import DTO.DTOFactory;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M01_Login.User;
import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;

public class UserMapper extends GenericMapper <DTOUser> {
    @Override
    public DTOUser CreateDto(Entity entity) {

        DTOUser dto = null;
        try {

            User _use = (User) entity;
            dto = DTOFactory.CreateDTOUser(_use.get_idUser(), _use.get_passwordUser(), _use.get_usernameUser(),
                    _use.get_typeUser(), _use.get_emailUser(), _use.get_phoneUser(), _use.get_dateOfBirthUser(),
                    _use.get_countryUser(), _use.get_addressUser(), _use.get_genderUser(), _use.get_cityUser(),
                    _use.get_blockedUser(), _use.get_remainingAttemptsUser());


        }
        catch (Exception e) {
            throw e;
        }
        return dto;
    }

    @Override
    public List<DTOUser> CreateDtoList(List<Entity> entities) {
        ArrayList<DTOUser> dtos = new ArrayList<>();
        try
        {


            for (Entity _us : entities) {
                dtos.add( CreateDto( _us ) );
            }

        }
        catch(Exception e) {
            throw e;
        }
        return dtos;

    }

    @Override
    public List<Entity> CreateEntityList(List<DTOUser> dtos) {
        try
        {
            ArrayList<Entity> user = new ArrayList<>();

            for (DTOUser _dtous : dtos) {
                user.add ( CreateEntity ( _dtous ) );
            }

            return user;

        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public Entity CreateEntity(DTOUser dto) {

        Entity _use;

        try {

            _use = EntityFactory.CreateUser(dto.get_idUser(), dto.get_password(), dto.get_username(),
                    dto.get_type(), dto.get_email(), dto.get_phone(), dto.get_dateOfBirth(), dto.get_country(),
                    dto.get_address(), dto.get_gender(), dto.get_city());

        }
        catch (Exception e) {
            throw e;
        }
        return _use;
    }
}
