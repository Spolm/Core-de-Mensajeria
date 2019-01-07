package Entities.M08_Validation.XMLManagement;

import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.Template;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;

/**
 *
 */
public class CommandsFactory {
    /**
     * @param tag
     * @param element
     * @return
     */
    public static CommandGetTagValue createCommandGetTagValue(String tag, Element element){
        return new CommandGetTagValue(tag,element);
    }

    /**
     * @param node
     * @param parameter
     * @return
     */
    public static CommandGetParameter createCommandGetParameter(Node node, ArrayList<Parameter> parameter){
        return new CommandGetParameter(node, parameter);
    }

    /**
     * @param node
     * @param template
     * @return
     */
    public static CommandGetMessage createCommandGetMessage(Node node, Template template){
        return new CommandGetMessage(node,template);
    }

    /**
     * @param filePath
     * @return
     */
    public static CommandProcessXML createCommandProcessXML(String filePath){
        return new CommandProcessXML(filePath);
    }

    public static Command createSendMessage(VerifiedParameter verifiedParameters) {
        return new CommandSendMessage(verifiedParameters);
    }

}
