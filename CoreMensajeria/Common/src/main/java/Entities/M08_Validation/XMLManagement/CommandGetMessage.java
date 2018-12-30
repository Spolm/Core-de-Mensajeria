package Entities.M08_Validation.XMLManagement;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class CommandGetMessage extends Command {

    private Node _node;
    private Message _message;
    private CommandGetTagValue _commandGetTagValue;
    private CommandGetParameter _commandGetParameter;

    public CommandGetMessage(Node node){
        this._node = node;
        _message = new Message();
    }

    @Override
    public void execute() {
        if (_node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) _node;
            _commandGetTagValue = CommandFactory.CreateCommandGetTagValue("destiny", element);
            _commandGetTagValue.execute();
            _message.set_destiny(_commandGetTagValue.getValue());
            NodeList nodeList = element.getElementsByTagName("parameter");
            ArrayList<Parameter> parameterList = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                _commandGetParameter = CommandFactory.CreateCommandGetParameter(nodeList.item(i));
                _commandGetParameter.execute();
                parameterList.add(_commandGetParameter.getValue());
            }
            _message.set_param(parameterList);
        }
    }

    public Message getValue(){
        return _message;
    }
}
