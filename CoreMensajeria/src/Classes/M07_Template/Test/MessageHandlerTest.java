package Classes.M07_Template.Test;

import Classes.M07_Template.HandlerPackage.MessageHandler;
import Classes.M07_Template.Template;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MessageHandlerTest {
    MessageHandler messageHandler;
    ArrayList<Template> templateArrayList = new ArrayList<Template>();

    @BeforeEach
    void init(){
        messageHandler = new MessageHandler();

        Template template;

        for (byte i = 1 ; i < 6; i++){
            template = new Template(i);
            templateArrayList.add(template);
        }

        objectsNotNull();
    }

    @Test
    void getMessagesCorrectly(){
        assertEquals(messageHandler.getMessages(templateArrayList).size(), 5);

        for (int i=0 ; i < 5 ; i++){
            assertEquals(messageHandler.getMessages(templateArrayList).get(i).getTemplateId(), i+1);
            assertEquals(messageHandler.getMessages(templateArrayList).get(i).getMessage().getMessageId(), i+1);
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
        assertNotNull(messageHandler.getMessages(templateArrayList));

        for (int i=0 ; i < 5 ; i++){
            assertNotNull(messageHandler.getMessages(templateArrayList).get(i));
            assertNotNull(messageHandler.getMessages(templateArrayList).get(i).getMessage());
        }
    }

    void sizeParameterArrayListCorrectly(){
        assertEquals(messageHandler.getMessages(templateArrayList).get(1).getMessage().getParameterArrayList().size()
                , 1);
        assertEquals(messageHandler.getMessages(templateArrayList).get(2).getMessage().getParameterArrayList().size()
                , 4);
        assertEquals(messageHandler.getMessages(templateArrayList).get(3).getMessage().getParameterArrayList().size()
                , 4);
        assertEquals(messageHandler.getMessages(templateArrayList).get(4).getMessage().getParameterArrayList().size()
                , 4);
        assertEquals(messageHandler.getMessages(templateArrayList).get(5).getMessage().getParameterArrayList().size()
                , 2);
    }

    void nameParameterArrayListCorrectly(){
        assertEquals(messageHandler.getMessages(templateArrayList).get(1).getMessage().getParameterArrayList().get(0).getName()
                , "Parametro");
        assertEquals(messageHandler.getMessages(templateArrayList).get(2).getMessage().getParameterArrayList().get(0).getName()
                , "Numero tarjeta");
        assertEquals(messageHandler.getMessages(templateArrayList).get(2).getMessage().getParameterArrayList().get(0).getName()
                , "Monto");
        assertEquals(messageHandler.getMessages(templateArrayList).get(2).getMessage().getParameterArrayList().get(0).getName()
                , "Fecha");
        assertEquals(messageHandler.getMessages(templateArrayList).get(2).getMessage().getParameterArrayList().get(0).getName()
                , "REF");
        assertEquals(messageHandler.getMessages(templateArrayList).get(3).getMessage().getParameterArrayList().get(0).getName()
                , "Evento");
        assertEquals(messageHandler.getMessages(templateArrayList).get(3).getMessage().getParameterArrayList().get(1).getName()
                , "Fecha");
        assertEquals(messageHandler.getMessages(templateArrayList).get(3).getMessage().getParameterArrayList().get(2).getName()
                , "Hora");
        assertEquals(messageHandler.getMessages(templateArrayList).get(3).getMessage().getParameterArrayList().get(3).getName()
                , "Lugar");
        assertEquals(messageHandler.getMessages(templateArrayList).get(4).getMessage().getParameterArrayList().get(0).getName()
                , "Cancion");
        assertEquals(messageHandler.getMessages(templateArrayList).get(4).getMessage().getParameterArrayList().get(0).getName()
                , "Codigo");
        assertEquals(messageHandler.getMessages(templateArrayList).get(4).getMessage().getParameterArrayList().get(0).getName()
                , "Renta");
        assertEquals(messageHandler.getMessages(templateArrayList).get(4).getMessage().getParameterArrayList().get(0).getName()
                , "Monto");
        assertEquals(messageHandler.getMessages(templateArrayList).get(5).getMessage().getParameterArrayList().get(0).getName()
                , "Fecha inicial");
        assertEquals(messageHandler.getMessages(templateArrayList).get(5).getMessage().getParameterArrayList().get(0).getName()
                , "Fecha fin");
    }
}
