package Logic.M08_SendMessage;

import Logic.Command;
import Logic.CommandsFactory;
import Entities.M08_Validation.XMLManagement.VerifiedParameter;

import java.util.logging.Logger;

public class CommandScheduleMessageTask implements Runnable {

    private VerifiedParameter _verifiedParameters;

    public CommandScheduleMessageTask(VerifiedParameter verifiedParameter) {
        _verifiedParameters = verifiedParameter;
    }

    public void run() {
        Command sendMessageCommand = CommandsFactory.createSendMessage(_verifiedParameters);
        Logger logger = Logger.getLogger(CommandScheduleMessageTask.class.getName());
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
