package Logic.M08_SendMessage;

import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Logic.Command;
import Logic.CommandsFactory;

import java.util.logging.Logger;

public class ScheduleMessageTask implements Runnable {

    private VerifiedParameter _verifiedParameters;

    public ScheduleMessageTask(VerifiedParameter verifiedParameter) {
        _verifiedParameters = verifiedParameter;
    }

    public void run() {
        Command sendMessageCommand = CommandsFactory.createSendMessage(_verifiedParameters);
        Logger logger = Logger.getLogger(ScheduleMessageTask.class.getName());
        try {
            logger.info("Enviando mensaje");
            sendMessageCommand.execute();
        } catch(Exception e) {
            // TODO: Manejar el error
            logger.warning("Error enviando el mensaje: " + e);
            System.out.println("Could not send message");
        }
    }
}
