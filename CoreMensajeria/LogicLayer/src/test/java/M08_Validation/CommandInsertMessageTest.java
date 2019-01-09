package M08_Validation;

import Entities.M07_Template.Template;
import Logic.Command;
import Logic.CommandsFactory;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandInsertMessageTest {
    private Template _t;
    private Date _fecha;
    private Command<Boolean> insertMessage;

   @BeforeEach
   public void setTemplateAndTime(){
        Command<Template> c = CommandsFactory.createCommandGetTemplate(1);
        try {
            c.execute();
            _t = c.Return();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            _fecha = sdf.parse("21/12/2012");
        } catch(Exception e){}
    }

//    @Test
//    public void testInsertMessageOk(){
//        try {
//            insertMessage = CommandsFactory.createCommandInsertMessage(_t, 1, 1, _fecha);
//            insertMessage.execute();
//        } catch (Exception e){
//            System.out.println(e);
//        }
//        assertTrue(insertMessage.Return());
//    }

    @Test
    public void testInsertMessageException(){
        Command<Template> c = CommandsFactory.createCommandGetTemplate(1);
        try {
            c.execute();
            _t = c.Return();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            _fecha = sdf.parse("21/12/2012");
        } catch(Exception e){}
        insertMessage = CommandsFactory.createCommandInsertMessage(_t, 1, 0, _fecha);
        assertThrows(SQLException.class, () -> insertMessage.execute());
    }
}
