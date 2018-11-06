package Classes.M07_Template.Test;

import Classes.M07_Template.MessagePackage.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class MessageTest {

    Message message;
    ArrayList<Parameter> parameters;

    @BeforeEach
    void init(){
        parameters = new ArrayList<Parameter>();

        for (int i=1 ; i < 4 ; i++){
            parameters.add(new Parameter("parameter" + i));
        }

        String text = "hola parameter1 este es un mensaje parameter2 de prueba parameter3";
        message = new Message(parameters, text);
    }

    @Test
    void correctAttributes(){
        String text;

        assertNotNull(message);
        assertNotNull(parameters);

        for (int i=0 ; i < 3 ; i++){
            assertNotNull(parameters.get(i));
        }

        text ="hola " + parameters.get(0).getName()
                + " este es un mensaje " + parameters.get(1).getName()
                + " de prueba " + parameters.get(2).getName();
        assertEquals(message.getMessage(), text);
        assertEquals(message.getParameters(), parameters);
    }

    @AfterEach
    void tearDown(){
        message = null;
        parameters = null;
    }
}
