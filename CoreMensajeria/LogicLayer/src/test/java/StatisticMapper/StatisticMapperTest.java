package StatisticMapper;

import DTO.DTO;
import DTO.M09_DTO.DTOStatistic;
import DTO.DTOFactory;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M09_Statistics.Statistics;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatisticMapperTest {

    GenericMapper<DTOStatistic> mapper;

    @BeforeEach
    void setUp() {
        mapper = MapperFactory.createStatisticMapper();
    }

    @AfterEach
    void tearDown() {
        mapper = null;
    }

    @Test
    void createDto() {
        ArrayList x = new ArrayList();;
        ArrayList<Integer> y = new ArrayList<Integer>();
        for (int i = 1; i<3;i++){
            x.add("Campaña " + i);
            y.add(i);
        }
        Entity statistic = EntityFactory.createStatistic(x,y);
        DTO dto = mapper.CreateDto(statistic);
        assertNotNull(dto);
        assertEquals(((Statistics) statistic).getY(),((DTOStatistic) dto).getY());
        assertEquals(((Statistics) statistic).getX(),((DTOStatistic) dto).getX());
    }

    @Test
    void createEntity() {
        ArrayList x = new ArrayList();;
        ArrayList<Integer> y = new ArrayList<Integer>();
        for (int i = 1; i<4;i++){
            x.add("Campaña " + i);
            y.add(i);
        }
        DTO dto = DTOFactory.CreateDTOStatistic(x,y);
        Entity statistic = mapper.CreateEntity((DTOStatistic) dto);
        assertNotNull(statistic);
        assertEquals(((DTOStatistic) dto).getY(),((Statistics) statistic).getY());
        assertEquals(((DTOStatistic) dto).getX(),((Statistics) statistic).getX());
    }

    @Test
    void createEntityList() {
        List<DTOStatistic> dtos = new ArrayList<>();
        DTOStatistic dto;
        List<Entity> statistics;

        ArrayList x;
        ArrayList<Integer> y;
        for (int i = 0; i< 3;i++) {
            x = new ArrayList();
            y = new ArrayList<Integer>();
            for (int j = 1; j < 4; j++) {
                x.add("Campaña " + j);
                y.add(j);
            }
            dto = DTOFactory.CreateDTOStatistic(x,y);
            dtos.add(dto);
        }
        statistics = mapper.CreateEntityList(dtos);
        assertNotNull(statistics);
        for (int i = 0; i< 3;i++) {
            assertEquals(dtos.get(i).getX(),((Statistics) statistics.get(i)).getX());
            assertEquals(dtos.get(i).getY(),((Statistics) statistics.get(i)).getY());
        }
    }

    @Test
    void createDtoList() {
        List<DTOStatistic> dtos;
        Statistics statistic;
        List<Entity> statistics = new ArrayList<>();

        ArrayList x;
        ArrayList<Integer> y;
        for (int i = 0; i< 3;i++) {
            x = new ArrayList();
            y = new ArrayList<Integer>();
            for (int j = 1; j < 4; j++) {
                x.add("Campaña " + j);
                y.add(j);
            }
            statistic = EntityFactory.createStatistic(x,y);
            statistics.add(statistic);
        }
        dtos = mapper.CreateDtoList(statistics);
        assertNotNull(dtos);
        for (int i = 0; i< 3;i++) {
            assertEquals(((Statistics) statistics.get(i)).getX(),dtos.get(i).getX());
            assertEquals(((Statistics) statistics.get(i)).getY(),dtos.get(i).getY());
        }
    }

}