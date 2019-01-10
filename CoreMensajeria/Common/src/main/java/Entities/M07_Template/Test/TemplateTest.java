package Entities.M07_Template.Test;

import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.StatusPackage.ApprovedStatus;
import Entities.M07_Template.StatusPackage.NotApprovedStatus;
import Entities.M07_Template.Template;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class TemplateTest {
    Template template;

    @BeforeEach
    void init(){
        template = new Template(new Message(1), new NotApprovedStatus(1, "Not Approved Template"), "01-01-18", 1);
        objectsNotNull();
    }

    @Test
    void correctAttributes(){
        assertEquals(template.getStatus().getStatusName(), "Not Approved Template");
        assertEquals(template.getStatus().getStatusId(),1);
        assertEquals(template.get_id(), 1);
        assertEquals(template.getCreationDate(), "01-01-18");
        assertEquals(template.getMessage().get_id(), 1);
    }

    @Test
    void changeAttributes(){
        template.setMessage(new Message(2));
        template.setStatus(new ApprovedStatus(2, "Approved Template"));

        assertEquals(template.getStatus().getStatusName(), "Approved Template");
        assertEquals(template.getStatus().getStatusId(),2);
        assertEquals(template.getMessage().get_id(), 2);
    }

    @AfterEach
    void tearDown(){
        template = null;
    }

    void objectsNotNull() {
        assertNotNull(template);
        assertNotNull(template.getMessage());
        assertNotNull(template.getStatus());
        assertNotNull(template.getCreationDate());
    }
}
