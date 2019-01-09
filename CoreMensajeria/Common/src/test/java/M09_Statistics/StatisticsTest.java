package M09_Statistics;

import Entities.Entity;
import Entities.EntityFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Entities.M09_Statistics.Statistics;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    ArrayList x;
    ArrayList<Integer> y;
    Entity statistic;

    @BeforeEach
    void init(){
        x = new ArrayList();
        y = new ArrayList<Integer>();
        for (int i = 1; i<3;i++){
            x.add("Campaña " + i);
            y.add(i);
        }
        statistic = EntityFactory.createStatistic(x,y);
    }

    @AfterEach
    void after(){
        x=null;
        y=null;
        statistic=null;
    }

    @Test
    void getX() {
        assertEquals(x,((Statistics)statistic).getX());
    }

    @Test
    void setX() {
        for (int i = 4; i<7;i++){
            x.add("Campaña " + i);
        }
        ((Statistics)statistic).setX(x);
        assertEquals(x,((Statistics)statistic).getX());
    }

    @Test
    void getY() {
        assertEquals(y,((Statistics)statistic).getY());
    }

    @Test
    void setY() {
        for (int i = 4; i<7;i++){
            y.add(i);
        }
        ((Statistics)statistic).setY(y);
        assertEquals(y,((Statistics)statistic).getY());
    }

    @Test
    void addX() {
        ((Statistics)statistic).addX("Campaña 4");
        assertTrue(((Statistics)statistic).getX().contains("Campaña 4"));
    }

    @Test
    void addY() {
        ((Statistics)statistic).addY(4);
        assertTrue(((Statistics)statistic).getY().contains(4));
    }
}