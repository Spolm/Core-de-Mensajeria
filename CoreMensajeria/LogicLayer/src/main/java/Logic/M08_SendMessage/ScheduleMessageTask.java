package Logic.M08_SendMessage;

import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Logic.Command;
import Logic.CommandsFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase que se inserta en la cola de mensajes a enviar para ser ejecutado al momento
 * de tener que enviar un mensaje. Esta clase, con su método run es la que ejecuta el
 * comando para enviar el mensaje
 */
public class ScheduleMessageTask implements Runnable {

    private VerifiedParameter _verifiedParameters;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    /**
     * @param verifiedParameter
     */
    public ScheduleMessageTask(VerifiedParameter verifiedParameter) {
        _verifiedParameters = verifiedParameter;
    }

    /**
     * Se ejecuta el commando para enviar el mensaje
     */
    public void run() {
        Command sendMessageCommand = CommandsFactory.createSendMessage(_verifiedParameters);
        try {
            log.info("Enviando mensaje");
            sendMessageCommand.execute();
        } catch(Exception e) {
            // TODO: Manejar el error
            log.error("Error enviando el mensaje: " + e);
            System.out.println("Could not send message");
        }
    }
}
