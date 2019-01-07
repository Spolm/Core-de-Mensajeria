package Entities.M08_Validation.XMLManagement;

import Entities.M04_Integrator.Integrator;
import Entities.M05_Channel.Channel;
import Entities.M07_Template.Template;

import java.util.ArrayList;

public class CommandSendMessage extends Command {

    private ArrayList<Message> _verifiedMessages;
    private Template _template;

    public CommandSendMessage(VerifiedParameter verifiedParameter){
        _verifiedMessages = verifiedParameter.get_verifiedMessages();
        _template = verifiedParameter.get_template();
    }
    @Override
    public void execute() throws Exception {
        System.out.println("Enviando mensaje");
        ArrayList<Channel> _channels = _template.getChannels();

        for(Message message : _verifiedMessages){
            String correo = message.get_correo();
            String telefono = message.get_telefono();
            String finalMessage = parseMessage(message);

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
    }

    @Override
    public Object Return() {
        return null;
    }

    private String parseMessage(Message message) {
        String text = _template.getMessage().getMessage();
        ArrayList<ParameterXML> params = message.get_param();

        for (ParameterXML param : params) {
            text = text.replace("[.$" + param.get_name() + "$.]", param.get_value());
        }
        return text;
    }
}
