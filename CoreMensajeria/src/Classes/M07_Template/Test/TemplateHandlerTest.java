package Classes.M07_Template.Test;

import Classes.M07_Template.HandlerPackage.TemplateHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class TemplateHandlerTest {
    TemplateHandler templateHandler;

    @BeforeEach
    void init(){
        templateHandler = new TemplateHandler();
        objectsNotNull();
    }

    @Test
    void getTemplatesCorrectly(){
        assertEquals(templateHandler.getTemplates().size(), 5);

        for (int i=0 ; i < 5 ; i++){
            assertEquals(templateHandler.getTemplates().get(i).getTemplateId(), i+1);
        }

        //statusTemplateCorrectly();
    }

    @Test
    void getTemplateCorrectly(){

        for (int i=0 ; i < 5 ; i++){
            assertEquals(templateHandler.getTemplate(i).getTemplateId(), i+1);
        }

        //statusTemplateCorrectly();
    }

    @AfterEach
    void tearDown(){
        templateHandler = null;
    }

    void objectsNotNull(){
        assertNotNull(templateHandler);
        assertNotNull(templateHandler.getSql());
        assertNotNull(templateHandler.getTemplates());

        for (int i=0 ; i < 5 ; i++){
            assertNotNull(templateHandler.getTemplates().get(i));
        }

        for (int i=1 ; i < 6 ; i++){
            assertNotNull(templateHandler.getTemplate(i));
        }
    }

    void statusTemplateCorrectly(){
        assertEquals(templateHandler.getTemplates().get(0).getStatus(), "Aprobado");
        assertEquals(templateHandler.getTemplates().get(1).getStatus(), "No aprobado");
        assertEquals(templateHandler.getTemplates().get(2).getStatus(), "Aprobado");
        assertEquals(templateHandler.getTemplates().get(3).getStatus(), "No aprobado");
        assertEquals(templateHandler.getTemplates().get(4).getStatus(), "No aprobado");
    }
}