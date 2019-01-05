package Entities.M08_Validation.XMLManagement;

import Entities.M07_Template.MessagePackage.Parameter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class CommandGetParameter extends Command {
    private Node _node;
    private ParameterXML _parameterXML;
    private CommandGetTagValue _commandGetTagValue;
    private ArrayList<Parameter> _parameters;

    public CommandGetParameter(Node node, ArrayList<Parameter> parameter) {
        this._node = node;
        _parameterXML = new ParameterXML();
        this._parameters = parameter;
    }

    @Override
    public void execute() { //////////////// Rodear de try catch y hacer excepcion personalizada
        if (_node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) _node;
            _commandGetTagValue = CommandFactory.CreateCommandGetTagValue("name", element);
            _commandGetTagValue.execute();
            if(findParameter(_commandGetTagValue.getValue())) {
                _parameterXML.set_name(_commandGetTagValue.getValue());

                _commandGetTagValue = CommandFactory.CreateCommandGetTagValue("value", element);
                _commandGetTagValue.execute();
                if(_commandGetTagValue.getValue() != ""){
                    _parameterXML.set_value(_commandGetTagValue.getValue());
                } else {
                    _parameterXML = null;
                }
            } else{
                _parameterXML = null;
            }

        }
    }

    public boolean findParameter(String parameter){
        for (Parameter param : _parameters){
            String singleParam = param.getName();
            if(singleParam.equalsIgnoreCase(parameter)){
                return true;
            }
        }
        return false;
    }

    public ParameterXML getValue(){
        return _parameterXML;
    }
}
