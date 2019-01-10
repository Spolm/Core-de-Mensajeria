package Logic.M08_SendMessage.XMLManagment;

import Entities.Entity;
import Entities.M04_Integrator.Integrator;
import Entities.M05_Channel.Channel;
import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.Message;
import Entities.M08_Validation.XMLManagement.ParameterXML;
import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Logic.CommandsFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Clase patrón comando que se encarga de enviar el mensaje a los
 * destinatarios a través del integrador.
 */
public class CommandSendMessage extends Command {

    private ArrayList<Message> _verifiedMessages;
    private Template _template;
    private Date _dateToBeSent;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    /**
     * Constructor de la clase CommandSendMessage.
     * @param verifiedParameter parametros verificados.
     */
    public CommandSendMessage(VerifiedParameter verifiedParameter, Date dateToBeSent){
        _verifiedMessages = verifiedParameter.get_verifiedMessages();
        _template = verifiedParameter.get_template();
        _dateToBeSent = dateToBeSent;
    }

    /**
     * Método para enviar el mensaje a través de los dos canales disponbiles
     * SMS y/o Integrador.
     */
    @Override
    public void execute() throws Exception {
        ArrayList<Channel> _channels = _template.getChannels();

        for(Message message : _verifiedMessages) {
            String correo = message.get_correo();
            String telefono = message.get_telefono();
            Command<String> c = CommandsFactory.createCommandParseMessage(message,this._template);
            try {
                c.execute();
            } catch (Exception e){
                throw new UnexpectedErrorException();
            }
            String finalMessage = c.Return();
            for(Channel channel : _channels){
                ArrayList<Entity> integrators = channel.get_integrators();

                for(Entity entity : integrators){
                    Integrator integrator = (Integrator)entity;
                    try {
                        if (integrator.isEnabled()) {
                            if (channel.get_nameChannel().equalsIgnoreCase("SMS")) {
                                //integrator.sendMessage(finalMessage,telefono,"Valor a cambiar");
                                log.debug( finalMessage + " destino " + telefono );
                            } else {
                                //integrator.sendMessage(finalMessage,correo,"Valor a cambiar");
                                log.debug( finalMessage + " destino " + correo );
                            }
                        }
                       Command<Entity> sc = CommandsFactory.createCommandInsertMessage(_template, integrator.get_id(),
                                channel.get_id(), this._dateToBeSent);
                        sc.execute();
                    }catch (Exception e){
                        log.error("Error al insertar en base de datos");
                        throw e;
                    }
                }
            }
        }
        log.info("Mensajes enviados satisactoriamente");
    }

    @Override
    public Object Return() {
        return null;
    }
}
