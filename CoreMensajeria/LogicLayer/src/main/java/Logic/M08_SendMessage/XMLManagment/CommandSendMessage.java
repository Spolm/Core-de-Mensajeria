package Logic.M08_SendMessage.XMLManagment;

import Entities.M04_Integrator.Integrator;
import Entities.M05_Channel.Channel;
import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.Message;
import Entities.M08_Validation.XMLManagement.ParameterXML;
import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Logic.Command;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CommandSendMessage extends Command {

    private ArrayList<Message> _verifiedMessages;
    private Template _template;

    public CommandSendMessage(VerifiedParameter verifiedParameter){
        _verifiedMessages = verifiedParameter.get_verifiedMessages();
        _template = verifiedParameter.get_template();
    }
    @Override
    public void execute() throws Exception {
        ArrayList<Channel> _channels = _template.getChannels();

        for(Message message : _verifiedMessages) {
            String correo = message.get_correo();
            String telefono = message.get_telefono();
            String finalMessage = parseMessage(message);

            System.out.println(finalMessage);

            for(Channel channel : _channels){
                ArrayList<Integrator> integrators = channel.getIntegrators();

                for(Integrator integrator : integrators){
                    if(channel.getNameChannel().equalsIgnoreCase("SMS")){
                        integrator.sendMessage(finalMessage,telefono,"Valor a cambiar");
                    }else{
                        integrator.sendMessage(finalMessage,correo,"Valor a cambiar");
                    }
                }
            }
        }
        System.out.println("Mensajes enviados satisactoriamente");
    }

    @Override
    public Object Return() {
        return null;
    }

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
