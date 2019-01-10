package Logic.M08_Validation;


import Entities.M05_Channel.Channel;
import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.Message;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.M08_SendMessageManager.SMSTooLongException;
import Logic.Command;
import Logic.CommandsFactory;

import java.util.ArrayList;

/**
 * Comando para validar mensajes
 */
public class CommandValidateMessage extends CommandValidateParameter{
    private int _template;
    private ArrayList<Message> _messages;

    /**
     * @param _template recibe el id de una plantilla
     * @param _messages recibe los parámetros
     */
    public CommandValidateMessage(int _template, ArrayList<Message> _messages)
    {
        this._template = _template;
        this._messages = _messages;
    }

    /**
     *
     * @throws SMSTooLongException
     * @throws TemplateDoesntExistsException
     */
    public void execute () throws Exception {
        try {
            Command<Template> c =CommandsFactory.createCommandGetTemplate(_template);
            c.execute();
            Template template = c.Return();
            ArrayList<Channel> channels = template.getChannels();
            for (Channel channel: channels) {
                String channelName = channel.get_nameChannel();
                for (Message message: _messages){
                    Command<String> commandParse = CommandsFactory.createCommandParseMessage(message,template);
                    commandParse.execute();
                    String msg = commandParse.Return();
                    if ((channelName.equals("SMS"))&& (msg.length() > 160) ) {
                        log.error("SMS supera 160 caracteres");
                        this.set_valid(false);
                        throw new SMSTooLongException();
                    }
                }
            }
            log.info("Validación del Mensaje Correcta");
            this.set_valid(true);
        } catch (TemplateDoesntExistsException e) {
            log.error("Plantilla no Existe");
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
