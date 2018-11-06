package Classes.M07_Template.Test;

import Classes.M07_Template.HandlerPackage.TemplateHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class TemplateHandlerTest {
    TemplateHandler templateHandler;

    @BeforeEach
    void init(){
      templateHandler = new TemplateHandler();
    }

    @Test
    void getTemplatesCorrectly(){
        assertNotNull(templateHandler);
        assertNotNull(templateHandler.getTemplates());
        assertEquals(templateHandler.getTemplates().size(), 5);

        for (int i=0 ; i < 5 ; i++){
            assertNotNull(templateHandler.getTemplates().get(i));
            assertEquals(templateHandler.getTemplates().get(i).getTemplateId(), i+1);
        }
    }

    @Test
    void getTemplateCorrectly(){
        assertNotNull(templateHandler);

        for (int i=1 ; i < 6 ; i++){
            assertNotNull(templateHandler.getTemplate(i));
            assertEquals(templateHandler.getTemplate(i).getTemplateId(), i);
        }
    }

    @AfterEach
    void tearDown(){
        templateHandler = null;
    }
}
