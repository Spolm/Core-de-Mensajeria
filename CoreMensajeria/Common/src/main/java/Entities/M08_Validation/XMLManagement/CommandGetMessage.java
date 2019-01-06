package Entities.M08_Validation.XMLManagement;

import Entities.M07_Template.HandlerPackage.ParameterHandler;
import Entities.M08_Validation.XMLManagement.Message;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.ParameterXML;
import Entities.M08_Validation.XMLManagement.Command;
import Entities.M08_Validation.XMLManagement.CommandsFactory;
import Exceptions.ParameterDoesntExistsException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class CommandGetMessage extends Command<Message> {

    private Node _node;
    private Message _message;
    private Command<String> _commandGetTagValue;
    private Command<ParameterXML> _commandGetParameter;
    private Template _template;
    private int idMessage; ////// cambiar por comando plantilla

    public CommandGetMessage(Node node, Template template){
        this._node = node;
        _message = new Message();
        this._template = template;
    }

    @Override
    public void execute() {
        try {
            if (_node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) _node;
                _commandGetTagValue = CommandsFactory.createCommandGetTagValue("destiny", element);
                _commandGetTagValue.execute();

                _message.set_destiny( _commandGetTagValue.Return());
                NodeList nodeList = element.getElementsByTagName("parameter");
                ArrayList<ParameterXML> parameterXMLList = new ArrayList<>();

                idMessage = _template.getMessage().getMessageId();   ////// cambiar por comando plantilla
                ArrayList<Parameter> parameterList = ParameterHandler.getParametersByMessage(idMessage); /////// cambiar por comando plantilla

                for (int i = 0; i < nodeList.getLength(); i++) {
                    _commandGetParameter = CommandsFactory.createCommandGetParameter(nodeList.item(i),parameterList);
                    _commandGetParameter.execute();

                    if(_commandGetParameter.Return() != null) {
                        parameterXMLList.add( _commandGetParameter.Return());
                    } else {
                        _message = null;
                    }
                }
                if(_message != null)
                    _message.set_param(parameterXMLList);
            }
        }
        catch (ParameterDoesntExistsException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }
    }

    @Override
    public Message Return() {
        return _message;
    }

}
