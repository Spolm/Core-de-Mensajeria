package M08_Validation;

import Entities.M08_Validation.XMLManagement.Message;
import Entities.M08_Validation.XMLManagement.ParameterXML;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.M08_SendMessageManager.TemplateNotApprovedException;
import Logic.Command;
import Logic.CommandsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Exceptions.M08_SendMessageManager.SMSTooLongException;
import java.util.ArrayList;


import static  org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidationServiceTest {
    private static ArrayList<Message> _msgs = new ArrayList<>();

    @BeforeAll
    public static void setMesssage() {
        ArrayList<ParameterXML> params = new ArrayList<>();
        ParameterXML param = new ParameterXML();
        param.set_name("Fernando");
        param.set_value("Que est");
        params.add(param);
        Message msg = new Message();
        msg.set_correo("prueba@gmail.com");
        msg.set_telefono("+58 04166102982");
        msg.set_param(params);
        _msgs.add(msg);
    }

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

    @Test
    public void testMessageValid() {
        Command c = CommandsFactory.createCommandValidateMessage(1, _msgs);
        try{
            c.execute();
            assertEquals(true, c.Return());
        } catch (Exception e) {}
    }

    @Test
    public void testMessageNotValid() {

        Command c = CommandsFactory.createCommandValidateMessage(6, _msgs);
        assertThrows(SMSTooLongException.class, () -> c.execute());
    }



}

