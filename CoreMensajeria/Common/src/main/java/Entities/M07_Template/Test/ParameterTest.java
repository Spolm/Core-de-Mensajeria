package Entities.M07_Template.Test;

import Entities.M07_Template.MessagePackage.Parameter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParameterTest {
    private Parameter parameter;
    private int parameterId;
    private String name;

    @BeforeEach
    void init() {
        name = "Parametro 1";
        parameterId = 1;
        parameter = new Parameter(parameterId, name);
    }

    @Test
    public void isNotNull(){
        assertNotNull(parameter);
    }

    @Test
    public void correctAttributes(){
        assertEquals(parameter.getName(),name);
        assertEquals(parameter.getParameterId(),parameterId);
    }

    @Test
    public void changeAttributes(){
        name = "Parametro 2";
        parameterId = 2;
        parameter.setName(name);
        parameter.setParameterId(parameterId);

        assertEquals(parameter.getName(),name);
        assertEquals(parameter.getParameterId(),parameterId);
    }

    @AfterEach
    void tearDown(){
        name = null;
        parameter = null;
    }
}
