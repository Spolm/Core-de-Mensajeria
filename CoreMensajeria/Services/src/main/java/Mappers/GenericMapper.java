package Mappers;

import DTO.DTO;
import Entities.Entity;
import java.util.List;

public abstract class GenericMapper {
    public abstract DTO CreateDto(Entity entity);

    public abstract Entity CreateEntity(DTO dto);

    public abstract List<DTO> CreateDtoList(List<Entity> entities);

    public abstract List<Entity> CreateEntityList(List<DTO> dtos);

}
