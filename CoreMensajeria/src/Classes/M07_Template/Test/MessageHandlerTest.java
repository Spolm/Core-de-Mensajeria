package Classes.M07_Template.Test;

import Classes.M07_Template.HandlerPackage.MessageHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MessageHandlerTest {
    MessageHandler messageHandler;

    @BeforeEach
    void init(){
        messageHandler = new MessageHandler();
        objectsNotNull();
    }

    @Test
    void getMessagesCorrectly(){
        assertEquals(messageHandler.getMessages().size(), 5);

        for (int i=0 ; i < 5 ; i++){
            assertEquals(messageHandler.getMessages().get(i).getMessageId(), i+1);
        }

        sizeParameterArrayListCorrectly();
        nameParameterArrayListCorrectly();
    }

    @AfterEach
    void tearDown(){
        messageHandler = null;
    }

    void objectsNotNull(){
        assertNotNull(messageHandler);
        assertNotNull(messageHandler.getMessages());

        for (int i=0 ; i < 5 ; i++){
            assertNotNull(messageHandler.getMessages().get(i));
        }
    }

    void sizeParameterArrayListCorrectly(){
        assertEquals(messageHandler.getMessages().get(1).getParameterArrayList().size(), 1);
        assertEquals(messageHandler.getMessages().get(2).getParameterArrayList().size(), 4);
        assertEquals(messageHandler.getMessages().get(3).getParameterArrayList().size(), 4);
        assertEquals(messageHandler.getMessages().get(4).getParameterArrayList().size(), 4);
        assertEquals(messageHandler.getMessages().get(5).getParameterArrayList().size(), 2);
    }

    void nameParameterArrayListCorrectly(){
        assertEquals(messageHandler.getMessages().get(1).getParameterArrayList().get(0).getName()
                , "Parametro");
        assertEquals(messageHandler.getMessages().get(2).getParameterArrayList().get(0).getName()
                , "Numero tarjeta");
        assertEquals(messageHandler.getMessages().get(2).getParameterArrayList().get(0).getName()
                , "Monto");
        assertEquals(messageHandler.getMessages().get(2).getParameterArrayList().get(0).getName()
                , "Fecha");
        assertEquals(messageHandler.getMessages().get(2).getParameterArrayList().get(0).getName()
                , "REF");
        assertEquals(messageHandler.getMessages().get(3).getParameterArrayList().get(0).getName()
                , "Evento");
        assertEquals(messageHandler.getMessages().get(3).getParameterArrayList().get(1).getName()
                , "Fecha");
        assertEquals(messageHandler.getMessages().get(3).getParameterArrayList().get(2).getName()
                , "Hora");
        assertEquals(messageHandler.getMessages().get(3).getParameterArrayList().get(3).getName()
                , "Lugar");
        assertEquals(messageHandler.getMessages().get(4).getParameterArrayList().get(0).getName()
                , "Cancion");
        assertEquals(messageHandler.getMessages().get(4).getParameterArrayList().get(0).getName()
                , "Codigo");
        assertEquals(messageHandler.getMessages().get(4).getParameterArrayList().get(0).getName()
                , "Renta");
        assertEquals(messageHandler.getMessages().get(4).getParameterArrayList().get(0).getName()
                , "Monto");
        assertEquals(messageHandler.getMessages().get(5).getParameterArrayList().get(0).getName()
                , "Fecha inicial");
        assertEquals(messageHandler.getMessages().get(5).getParameterArrayList().get(0).getName()
                , "Fecha fin");
    }
}
