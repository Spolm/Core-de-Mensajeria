package Classes.M07_Template.Test;

import Classes.M07_Template.MessagePackage.Message;
import Classes.M07_Template.StatusPackage.ApprovedStatus;
import Classes.M07_Template.StatusPackage.NotApprovedStatus;
import Classes.M07_Template.Template;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class TemplateTest {
    Template template;

    @BeforeEach
    void init(){
        template = new Template(new Message(1), new NotApprovedStatus(), "01-01-18", 1);
        objectsNotNull();
    }

    @Test
    void correctAttributes(){
        assertEquals(template.getStatus(), "Not Approved Template");
        assertEquals(template.getTemplateId(), 1);
        assertEquals(template.getCreationDate(), "01-01-18");
        assertEquals(template.getMessage().getMessageId(), 1);
    }

    @Test
    void changeAttributes(){
        template.setMessage(new Message(2));
        template.setStatus(new ApprovedStatus());

        assertEquals(template.getStatus(), "Approved Template");
        assertEquals(template.getMessage().getMessageId(), 2);
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
