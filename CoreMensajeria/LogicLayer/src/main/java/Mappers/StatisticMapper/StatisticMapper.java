package Mappers.StatisticMapper;

import DTO.M09_DTO.DTOStatistic;
import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M09_Statistics.Statistics;
import DTO.DTOFactory;
import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;

public class StatisticMapper extends GenericMapper<DTOStatistic> {

    /**
     * Metodo con el cual se transforma una entidad en un DTOStatistic
     * @param entity Entidad que recibe para hacer el mapeo
     * @return un objeto del tipo DTOStatistic
     */
    @Override
    public DTOStatistic CreateDto(Entity entity) {
        Statistics statistics = (Statistics) entity;
        DTOStatistic dto = DTOFactory.CreateDTOStatistic(statistics.getX(),statistics.getY());
        return dto;
    }

    /**
     * Metodo con el cual se transforma un DTOStatistic en una entidad Statistic
     * @param dto DTO que recibe para llevarlo a entidad
     * @return un objeto del tipo Statistic, extiende de Entity
     */

    @Override
    public Entity CreateEntity(DTOStatistic dto) {
        Entity statistics = EntityFactory.createStatistic(dto.getX(),dto.getY());
        return statistics ;
    }

    /**
     * Metodo con el cual se transforma de una lista de DTOStadistic a una lista de entidades Stadistic
     * @param list , lista de DTOs que se recibe para hacer para hacer el mapeo
     * @return una lista de Entidades del tipo Statistic
     */

    @Override
    public List<Entity> CreateEntityList(List<DTOStatistic> list) {
        ArrayList<Entity> Stadistics = new ArrayList<>();
        for (DTOStatistic statistic : list) {
            Stadistics.add ( CreateEntity ( statistic ) );
        }
        return Stadistics ;
    }

    /**
     * Metodo con el cual se transforma de una lista de entidades a una lista de dto
     * @param list , Entidad que se recibe para hacer para hacer el mapeo
     * @return una lista de objetos del tipo DTOStatistic
     */

    @Override
    public List CreateDtoList(List<Entity> list) {
        try
        {
            ArrayList<DTOStatistic> dtos = new ArrayList<>();

            for (Entity statistic : list) {
                dtos.add( CreateDto( statistic ) );
            }

            return dtos;

        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
