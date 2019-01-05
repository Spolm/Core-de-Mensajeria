package Mappers.LoginMapper;

import DTO.DTO;
import Entities.Entity;
import Mappers.GenericMapper;

import java.util.List;

public class LoginMapper extends GenericMapper <DTO> {
    @Override
    public DTO CreateDto(Entity entity) {
        return null;
    }

    @Override
    public List<DTO> CreateDtoList(List<Entity> entities) {
        return null;
    }

    @Override
    public List<Entity> CreateEntityList(List<DTO> dtos) {
        return null;
    }

    @Override
    public Entity CreateEntity(DTO dto) {
        return null;
    }
}
