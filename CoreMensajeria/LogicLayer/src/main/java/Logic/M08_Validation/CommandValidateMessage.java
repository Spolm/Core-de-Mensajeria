package Logic.M08_Validation;


import Entities.Entity;
import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.Template;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.SMSTooLongException;
import Logic.Command;
import Logic.CommandsFactory;

import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/**
 * Comando para validar mensajes
 */
public class CommandValidateMessage extends CommandValidateParameter{
    private int _template;
    private String _channel;

    /**
     * @param _template recibe el id de una plantilla
     * @param _channel recibe el tipo de canal
     */
    public CommandValidateMessage(int _template, String _channel) {
        this._template = _template;
        this._channel = _channel;
    }

    /**
     * @throws SMSTooLongException
     * @throws TemplateDoesntExistsException
     *
     */
    public void execute () throws Exception {
        Logger logger = Logger.getLogger(CommandValidateParameter.class.getName());
        try {
            Command<Template> c =CommandsFactory.createCommandGetTemplate(_template);
            c.execute();
            Template template = c.Return();
            Message message = (template).getMessage();
            String msg = (message).getMessage();
            if ((this._channel.equals("SMS"))&& (msg.length() > 160) ){
                logger.warning("SMS supera 160 caracteres");
                this.set_valid(false);
                throw new SMSTooLongException();
            }
            else
                this.set_valid(true);
        } catch (TemplateDoesntExistsException e) {
            logger.warning("Plantilla no Existe");
            this.set_valid(false);
            throw e;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Boolean Return() {
        return this.get_valid();
    }
}
