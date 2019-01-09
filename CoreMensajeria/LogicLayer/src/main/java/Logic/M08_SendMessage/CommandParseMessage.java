package Logic.M08_SendMessage;

import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.Message;
import Entities.M08_Validation.XMLManagement.ParameterXML;
import Logic.Command;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Clase patrón comando que se encarga de parsear el mensaje
 */
public class CommandParseMessage extends Command {

    private Message _message;
    private Template _template;
    private String _text;

    /**
     * Constructor de la clase CommandSendMessage.
     * @param _message,_template parametros verificados.
     */
    public CommandParseMessage(Message _message, Template _template) {
        this._message = _message;
        this._template = _template;
    }

    /**
     * Método para parsear el mensaje final a ser enviado.
     */
    public void execute(){
        _text = _template.getMessage().getMessage();
        ArrayList<ParameterXML> params = _message.get_param();

        for (ParameterXML param : params) {
            String parameterToBeReplaced = Pattern.quote("[.$" + param.get_name() + "$.]");
            _text = _text.replaceAll("(?i)" + parameterToBeReplaced, param.get_value());
        }
    }

    /**
     * Método para retornar el mensaje parseado
     * @return el String a ser enviado.
     */
    public String Return(){
        return _text;
    }
}
