package Entities.M08_Validation.XMLManagement;

import Entities.M07_Template.HandlerPackage.MessageHandler;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;
import Exceptions.SMSTooLongException;
import Exceptions.TemplateDoesntExistsException;

import java.util.logging.Logger;

public class CommandValidateMessage extends CommandValidateParameter{
    private int _template;
    private String _message;
    private String _channel;

    public CommandValidateMessage(int _template, String _message, String _channel) {
        this._template = _template;
        this._message = _message;
        this._channel = _channel;
    }

    public void execute () throws Exception{
        Logger logger = Logger.getLogger(CommandValidateParameter.class.getName());
        TemplateHandler template = new TemplateHandler();
        try {
            Template t = template.getTemplate(this._template);
            MessageHandler.getMessage(t.getTemplateId());
            if ((this._channel.equals("SMS"))&& (this._message.length()>160)){
                logger.warning("SMS supera 160 caracteres");
                this.set_valid(false);
                this.set_response("SMS supera 160 caracteres");
                throw new SMSTooLongException();
            }
            else
                this.set_valid(true);
        } catch (TemplateDoesntExistsException e) {
            logger.warning("Plantilla no Existe");
            this.set_valid(false);
            this.set_response("Plantilla no Existe");
            throw e;
        } catch (MessageDoesntExistsException e) {
            logger.warning("Mensaje no Existe");
            this.set_valid(false);
            this.set_response("Mensaje no Existe");
            throw e;
        } catch (ParameterDoesntExistsException e) {
            logger.warning("Parámetro no Existe");
            this.set_valid(false);
            this.set_response("Parámetro no Existe");
        }

    }
}
