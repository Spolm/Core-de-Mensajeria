package Mappers;

import DTO.DTO;
import Entities.Entity;
import java.util.List;

public abstract class GenericMapper <TDto>
{
    public abstract TDto CreateDto(Entity entity);

    public abstract Entity CreateEntity(TDto dto);

    public abstract List<TDto> CreateDtoList(List<Entity> entities);

    public abstract List<Entity> CreateEntityList(List<TDto> dtos);

}
