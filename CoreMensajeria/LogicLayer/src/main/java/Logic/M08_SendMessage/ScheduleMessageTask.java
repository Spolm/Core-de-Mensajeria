package Logic.M08_SendMessage;

import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Logic.Command;
import Logic.CommandsFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Clase que se inserta en la cola de mensajes a enviar para ser ejecutado al momento
 * de tener que enviar un mensaje. Esta clase, con su método run es la que ejecuta el
 * comando para enviar el mensaje
 */
public class ScheduleMessageTask implements Runnable {

    private VerifiedParameter _verifiedParameters;
    private Date _dateToBeSent;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    /**
     * @param verifiedParameter
     */
    public ScheduleMessageTask(VerifiedParameter verifiedParameter, Date dateToBeSent) {
        _verifiedParameters = verifiedParameter;
        _dateToBeSent = dateToBeSent;
    }

    /**
     * Se ejecuta el commando para enviar el mensaje
     */
    public void run() {
        Command sendMessageCommand = CommandsFactory.createSendMessage(_verifiedParameters, _dateToBeSent);
        try {
            log.info("Enviando mensaje");
            System.out.println("Enviando mensaje");
            sendMessageCommand.execute();
        } catch(Exception e) {
            // TODO: Manejar el error
            log.error("Error enviando el mensaje: " + e);
            System.out.println("Could not send message");
        }
    }
}
