package Entities.M08_Validation.XMLManagement;

import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Entities.M08_Validation.ValidationReciveParameter;
import Entities.M08_Validation.XMLManagement.Message;
import Exceptions.TemplateDoesntExistsException;
import Entities.M08_Validation.XMLManagement.ParameterXML;
import Entities.M08_Validation.XMLManagement.CommandsFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandProcessXML extends Command {

    private File _xmlFile;
    private DocumentBuilderFactory _dbFactory;
    private DocumentBuilder _dBuilder;
    private Command<Message> _commandGetMessage;
    private Command<String> _commandGetTagValue;
    private Template _template;  /////// Cambiar por comando de Template
    private TemplateHandler _templateHandler = new TemplateHandler();   /////// Cambiar por comando de Template
    private String _templateId;                  /////// Cambiar por comando de Template
    private List<Message> _messageList = new ArrayList<>();

    public CommandProcessXML(String filePath){
        _xmlFile = new File(filePath);
        _dbFactory = DocumentBuilderFactory.newInstance();
    }

    @Override
    public void execute() {
        try {
            _dBuilder = _dbFactory.newDocumentBuilder();
            Document doc = _dBuilder.parse(_xmlFile);
            doc.getDocumentElement().normalize();

            NodeList node = doc.getElementsByTagName("template");
            _commandGetTagValue = CommandsFactory.createCommandGetTagValue("id",(Element) node.item(0));
            _commandGetTagValue.execute();
            _templateId = _commandGetTagValue.Return();
            _template =_templateHandler.getTemplate(Integer.valueOf(_templateId ));   /////// Cambiar por comando de Template
            NodeList nodeList = doc.getElementsByTagName("message");

            for (int i = 0; i < nodeList.getLength(); i++) {
                _commandGetMessage = CommandsFactory.createCommandGetMessage(nodeList.item(i),_template);
                _commandGetMessage.execute();
                if(_commandGetMessage.Return() != null)
                    _messageList.add(_commandGetMessage.Return());
            }

            for(Message message : _messageList){
                System.out.println(message.toString());
                parseMessage(message);
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        } catch (TemplateDoesntExistsException e) {
            e.printStackTrace();
        } catch (Exception e){}
    }

    @Override
    public Object Return() {
        return null;
    }

    private void parseMessage(Message message) {
        String text = "Hola [.$Nombre$.] tu edad es [.$Edad$.]";
        ArrayList<ParameterXML> params = message.get_param();

        for (ParameterXML param : params) {
            System.out.println(param.get_name());
            text = text.replace("[.$" + param.get_name() + "$.]", param.get_value());
        }
        System.out.println(text);
    }
}
