package Entities.M08_Validation.XMLManagement;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CommandGetTagValue extends Command{
    private String _tag;
    private Element _element;
    private Node _node;

    public CommandGetTagValue(String tag, Element element){
        this._tag = tag;
        this._element = element;
    }

    @Override
    public void execute() {
        NodeList nodeList = _element.getElementsByTagName(_tag).item(0).getChildNodes();
        _node = (Node) nodeList.item(0);
    }

    public String getValue(){
        return _node.getNodeValue();
    }
}
