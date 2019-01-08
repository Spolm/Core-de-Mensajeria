package Entities.M07_Template.Test;

import Entities.M07_Template.HandlerPackage.TemplateHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Entities.M07_Template.Template;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class TemplateHandlerTest {
    TemplateHandler templateHandler;
    Template template;

    @BeforeEach
    void init(){
        templateHandler = new TemplateHandler();
        template = new Template();
        //objectsNotNull();
    }

    @Test
    void getTemplatesCorrectly(){
        assertEquals(templateHandler.getTemplates().size(), 5);

        for (int i=0 ; i < 5 ; i++){
            assertEquals(templateHandler.getTemplates().get(i).get_id(), i+1);
        }

        //statusTemplateCorrectly();
    }

    @Test
    void getTemplateCorrectly(){
        try{

            for (int i=0 ; i < 5 ; i++){
                template = templateHandler.getTemplate(i+1);
                assertEquals(template.get_id(), i+1);
            }
            //statusTemplateCorrectly();
        }catch (TemplateDoesntExistsException e){
            //logg
        }
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
            //assertNotNull(templateHandler.getTemplates().get(i+1));
        }

        try {
            for (int i = 1; i < 6; i++) {
                assertNotNull(templateHandler.getTemplate(i));
            }
        }catch (TemplateDoesntExistsException e){
            e.printStackTrace();
        }
    }

    void statusTemplateCorrectly(){
        try {
            assertEquals(templateHandler.getTemplate(1).getStatus(), "Aprobado");
            assertEquals(templateHandler.getTemplate(2).getStatus(), "No aprobado");
            assertEquals(templateHandler.getTemplate(3).getStatus(), "Aprobado");
            assertEquals(templateHandler.getTemplate(4).getStatus(), "No aprobado");
            assertEquals(templateHandler.getTemplate(5).getStatus(), "No aprobado");
        }catch (TemplateDoesntExistsException e){
            //log
        }catch (Exception e){
            //logg
        }
    }
}