package Entities.M08_Validation.XMLManagement;

import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.Template;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class CommandFactory {
    public static CommandGetTagValue CreateCommandGetTagValue(String tag, Element element){
        return new CommandGetTagValue(tag,element);
    }

    public static CommandGetParameter CreateCommandGetParameter(Node node, ArrayList<Parameter> parameter){
        return new CommandGetParameter(node, parameter);
    }

    public static CommandGetMessage CreateCommandGetMessage(Node node, Template template){
        return new CommandGetMessage(node,template);
    }

    public static CommandProcessXML CreateCommanProcessXML(String filePath){
        return new CommandProcessXML(filePath);
    }
}
