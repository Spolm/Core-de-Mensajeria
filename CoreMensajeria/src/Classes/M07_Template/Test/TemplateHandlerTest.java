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
        ObjectsNotNull();
    }

    void ObjectsNotNull(){
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

    @Test
    void getTemplatesCorrectly(){
        assertEquals(templateHandler.getTemplates().size(), 5);

        for (int i=0 ; i < 5 ; i++){
            assertEquals(templateHandler.getTemplates().get(i).getTemplateId(), i+1);
        }
    }

    @Test
    void getTemplateCorrectly(){

        for (int i=1 ; i < 6 ; i++){
            assertEquals(templateHandler.getTemplate(i).getTemplateId(), i);
        }
    }

    @Test
    void postTemplateStatusCorrectly(){
        assertTrue(templateHandler.postTemplateStatus(1));
        assertFalse(templateHandler.postTemplateStatus(2));
        assertTrue(templateHandler.postTemplateStatus(3));
        assertFalse(templateHandler.postTemplateStatus(4));
        assertFalse(templateHandler.postTemplateStatus(5));
    }

    @AfterEach
    void tearDown(){
        templateHandler = null;
    }
}