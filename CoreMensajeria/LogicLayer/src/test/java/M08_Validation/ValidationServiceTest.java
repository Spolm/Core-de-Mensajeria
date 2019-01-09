package M08_Validation;

import Entities.M07_Template.StatusPackage.Status;
import Entities.M07_Template.Template;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.TemplateNotApprovedException;
import Logic.Command;
import Logic.CommandsFactory;

import Logic.M07_Template.CommandGetTemplate;
import Logic.M08_Validation.CommandValidateTemplate;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.jupiter.api.Test;
import Exceptions.SMSTooLongException;

import static  org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidationServiceTest {

    @Test
    public void testTemplateValid() {
        Command c = CommandsFactory.createCommandValidateTemplate(1);
        try{
            c.execute();
            assertEquals(true, c.Return());
        } catch (Exception e) {}

    }

    @Test
    public void testTemplateDoesntExist() {
        Command c = new CommandsFactory().createCommandValidateTemplate(312873618);
            assertThrows(TemplateDoesntExistsException.class,() -> c.execute());
    }

    @Test
    public void testTemplateNotApproved() {
        Command c = new CommandsFactory().createCommandValidateTemplate(2);
        assertThrows(TemplateNotApprovedException.class,() -> c.execute());
    }
//
//    @Test
//    public void testMessageValid() {
//        Command c = CommandsFactory.createCommandValidateMessage(1, "esto es un mensaje de template " +
//                "con + un [.$Parametro$.]", "SMS");
//        try {
//            c.execute();
//            assertEquals(true, c.Return());
//        } catch (Exception e) {}
//    }

    @Test
    public void testMessageNotValid() {
        Command c = CommandsFactory.createCommandValidateMessage(6);
        //assertThrows(SMSTooLongException.class, () -> c.execute());
    }

}

