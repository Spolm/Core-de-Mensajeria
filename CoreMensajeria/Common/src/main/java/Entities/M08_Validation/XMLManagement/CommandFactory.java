package Entities.M08_Validation.XMLManagement;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class CommandFactory {
    public static CommandGetTagValue CreateCommandGetTagValue(String tag, Element element){
        return new CommandGetTagValue(tag,element);
    }

    public static CommandGetParameter CreateCommandGetParameter(Node node){
        return new CommandGetParameter(node);
    }

    public static CommandGetMessage CreateCommandGetMessage(Node node){
        return new CommandGetMessage(node);
    }

    public static CommandProcessXML CreateCommanProcessXML(String filePath){
        return new CommandProcessXML(filePath);
    }
}
