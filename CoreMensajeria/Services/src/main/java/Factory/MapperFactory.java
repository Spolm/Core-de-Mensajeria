package Factory;

import DTO.M09_DTO.DTOStatistic;
import Mappers.GenericMapper;
import Mappers.StatisticMapper.StatisticMapper;

public class MapperFactory {

    public static GenericMapper createStatisticMapper(){ return new StatisticMapper();}
}
