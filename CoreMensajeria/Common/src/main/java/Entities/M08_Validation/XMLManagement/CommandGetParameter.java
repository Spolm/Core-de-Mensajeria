package Entities.M08_Validation.XMLManagement;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class CommandGetParameter extends Command {
    private Node _node;
    private Parameter _parameter;
    private CommandGetTagValue _commandGetTagValue;

    public CommandGetParameter(Node node) {
        this._node = node;
        _parameter = new Parameter();
    }

    @Override
    public void execute() {
        if (_node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) _node;
            _commandGetTagValue = CommandFactory.CreateCommandGetTagValue("name", element);
            _commandGetTagValue.execute();
            _parameter.set_name(_commandGetTagValue.getValue());
            _commandGetTagValue = CommandFactory.CreateCommandGetTagValue("value", element);
            _commandGetTagValue.execute();
            _parameter.set_value(_commandGetTagValue.getValue());
        }
    }

    public Parameter getValue(){
        return _parameter;
    }
}
