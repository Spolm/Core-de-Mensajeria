package M08_SendMessage;

import Entities.EntityFactory;
import Entities.M08_Validation.SentMessage;
import Persistence.DAOFactory;
import Persistence.M08_SendMessage.DAOSentMessage;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SentMessageDAOTest {

    @Test
    public void testInsertOk() {
        SentMessage sentMessage = EntityFactory.createSendMessage();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date fecha = sdf.parse("21/12/2018");
            sentMessage.set_sentTime(new Timestamp(fecha.getTime()));
            sentMessage.set_integratorId(1);
            sentMessage.set_channel(1);
            sentMessage.set_campaignId(1);
            sentMessage.set_applicationId(1);
            sentMessage.set_message(1);
            DAOSentMessage dao = DAOFactory.instanciateDaoSentMessage();
            dao.create(sentMessage);
            assertNotNull(sentMessage.get_id());
        } catch (Exception e){}

    }

    @Test
    public  void testInsertBad() {
        DAOSentMessage dao = DAOFactory.instanciateDaoSentMessage();
        assertThrows(SQLException.class, () -> dao.create(new SentMessage()));
    }
}
