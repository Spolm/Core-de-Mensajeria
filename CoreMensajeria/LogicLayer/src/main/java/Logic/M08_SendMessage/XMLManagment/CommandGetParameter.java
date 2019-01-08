package Logic.M08_SendMessage.XMLManagment;

import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M08_Validation.XMLManagement.ParameterXML;
import Exceptions.M08_SendMessageManager.NullValueXMLException;
import Logic.Command;
import Logic.CommandsFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;

/**
 *
 */
public class CommandGetParameter extends Command<ParameterXML> {

    private Node _node;
    private ParameterXML _parameterXML;
    private Command<String> _commandGetTagValue;
    private ArrayList<Parameter> _parameters;

    public CommandGetParameter(Node node, ArrayList<Parameter> parameter) {
        this._node = node;
        _parameterXML = new ParameterXML();
        this._parameters = parameter;
    }

    /**
     *
     */
    @Override
    public void execute() {
        try {
                if (_node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) _node;
                _commandGetTagValue = CommandsFactory.createCommandGetTagValue("name", element);
                _commandGetTagValue.execute();

                if(findParameter( _commandGetTagValue.Return())) {

                    _parameterXML.set_name(_commandGetTagValue.Return());
                    _commandGetTagValue = CommandsFactory.createCommandGetTagValue("value", element);
                    _commandGetTagValue.execute();
                    _parameterXML.set_value(_commandGetTagValue.Return());

                } else {
                    _parameterXML = null;

                }
            }
        } catch (NullValueXMLException e) {
            e.printStackTrace();
            _parameterXML = null;
        } catch (Exception e){

        }
    }

    /**
     * @param parameter
     * @return
     */
    public boolean findParameter(String parameter){
        for (Parameter param : _parameters){
            String singleParam = param.getName();
            if(singleParam.equalsIgnoreCase(parameter)){
                _parameters.remove(param);
                return true;
            }
        }
        return false;
    }

    /**
     * @return
     */
    @Override
    public ParameterXML Return() {
        return _parameterXML;
    }

}
