package Logic.M08_SendMessage.XMLManagment;

import Entities.M04_Integrator.Integrator;
import Entities.M05_Channel.Channel;
import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.Message;
import Entities.M08_Validation.XMLManagement.ParameterXML;
import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Logic.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Clase patrón comando que se encarga de enviar el mensaje a los
 * destinatarios a través del integrador.
 */
public class CommandSendMessage extends Command {

    private ArrayList<Message> _verifiedMessages;
    private Template _template;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    /**
     * Constructor de la clase CommandSendMessage.
     * @param verifiedParameter parametros verificados.
     */
    public CommandSendMessage(VerifiedParameter verifiedParameter){
        _verifiedMessages = verifiedParameter.get_verifiedMessages();
        _template = verifiedParameter.get_template();
    }

    /**
     * Método para enviar el mensaje a través de los dos canales disponbiles
     * SMS y/o Integrador.
     */
    @Override
    public void execute() {
        ArrayList<Channel> _channels = _template.getChannels();

        for(Message message : _verifiedMessages) {
            String correo = message.get_correo();
            String telefono = message.get_telefono();
            String finalMessage = parseMessage(message);

            for(Channel channel : _channels){
                ArrayList<Integrator> integrators = channel.getIntegrators();

                for(Integrator integrator : integrators){
                    if (integrator.isEnabled()) {
                        if(channel.getNameChannel().equalsIgnoreCase("SMS")){ ///*** MOSCA CON ESTO
                            //integrator.sendMessage(finalMessage,telefono,"Valor a cambiar");
                            System.out.println(finalMessage + " destino " +  telefono);
                        }else{
                            //integrator.sendMessage(finalMessage,correo,"Valor a cambiar"); ///*** MOSCA CON ESTO
                            System.out.println(finalMessage + " destino " +  correo);
                        }
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

    /**
     * Método para parsear el mensaje final a ser enviado.
     * @param message Mensaje que contiene los valores a ser enviado.
     * @return el String a ser enviado.
     */
    private String parseMessage(Message message) {
        String text = _template.getMessage().getMessage();
        ArrayList<ParameterXML> params = message.get_param();

        for (ParameterXML param : params) {
            String parameterToBeReplaced = Pattern.quote("[.$" + param.get_name() + "$.]");
            text = text.replaceAll("(?i)" + parameterToBeReplaced, param.get_value());
        }
        return text;
    }
}
