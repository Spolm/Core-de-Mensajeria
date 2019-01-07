package Entities.M08_Validation.XMLManagement;

import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Exceptions.M07_Template.TemplateDoesntExistsException;
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

/**
 *
 */
public class CommandProcessXML extends Command<VerifiedParameter> {

    private File _xmlFile;
    private DocumentBuilderFactory _dbFactory;
    private DocumentBuilder _dBuilder;
    private Command<Message> _commandGetMessage;
    private Command<String> _commandGetTagValue;
    private Command _commandGetTemplate;
    private Template _template;  /////// Cambiar por comando de Template
    private TemplateHandler _templateHandler = new TemplateHandler();   /////// Cambiar por comando de Template
    private String _templateId;                  /////// Cambiar por comando de Template
    private ArrayList<Message> _messageList = new ArrayList<>();
    private VerifiedParameter _verifiedParameters;

    public CommandProcessXML(String filePath){
        _xmlFile = new File(filePath);
        _dbFactory = DocumentBuilderFactory.newInstance();
    }

    /**
     *
     */
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

            if(_templateId != "") {
                _template = _templateHandler.getTemplate(Integer.valueOf(_templateId));   /////// Cambiar por comando de Template
                NodeList nodeList = doc.getElementsByTagName("message");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    _commandGetMessage = CommandsFactory.createCommandGetMessage(nodeList.item(i), _template);
                    _commandGetMessage.execute();
                    if (_commandGetMessage.Return() != null)
                        _messageList.add(_commandGetMessage.Return());
                }

                for (Message message : _messageList) {
                    System.out.println(message.toString());
                }

                _verifiedParameters.set_verifiedMessages(_messageList);
                _verifiedParameters.set_template(_template);
            } else{
                // Excepcion personalizada
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        } catch (TemplateDoesntExistsException e) {
            e.printStackTrace();
        } catch (NullPointerException e){}
          catch (Exception e){}
    }

    /**
     * @return
     */
    @Override
    public VerifiedParameter Return() {
        return _verifiedParameters;
    }
}
