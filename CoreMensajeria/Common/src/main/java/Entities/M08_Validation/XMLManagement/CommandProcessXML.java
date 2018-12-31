package Entities.M08_Validation.XMLManagement;

import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Exceptions.TemplateDoesntExistsException;
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

public class CommandProcessXML extends Command{

    private File _xmlFile;
    private DocumentBuilderFactory _dbFactory;
    private DocumentBuilder _dBuilder;
    private CommandGetMessage _commandGetMessage;
    private CommandGetTagValue _commandGetTagValue;
    private Template _template;  /////// Cambiar por comando de Template
    private TemplateHandler _templateHandler = new TemplateHandler();   /////// Cambiar por comando de Template
    private String _templateId;                  /////// Cambiar por comando de Template


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
            _commandGetTagValue = CommandFactory.CreateCommandGetTagValue("id",(Element) node.item(0));
            _commandGetTagValue.execute();
            _templateId = _commandGetTagValue.getValue();

            _template =_templateHandler.getTemplate(Integer.valueOf(_templateId ));   /////// Cambiar por comando de Template

            NodeList nodeList = doc.getElementsByTagName("message");
            List<Message> messageList = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                _commandGetMessage = CommandFactory.CreateCommandGetMessage(nodeList.item(i),_template);
                _commandGetMessage.execute();
                if(_commandGetMessage.getValue() != null)
                    messageList.add(_commandGetMessage.getValue());
            }

            for(Message message : messageList){
                System.out.println(message.toString());
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        } catch (TemplateDoesntExistsException e) {
            e.printStackTrace();
        }
    }
}
