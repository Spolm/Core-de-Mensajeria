package Entities.M08_Validation.XMLManagement;

import org.w3c.dom.Document;
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

            NodeList nodeList = doc.getElementsByTagName("message");

            List<Message> messageList = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                _commandGetMessage = CommandFactory.CreateCommandGetMessage(nodeList.item(i));
                _commandGetMessage.execute();
                messageList.add(_commandGetMessage.getValue());
            }

            for(Message message : messageList){
                System.out.println(message.toString());
                parseMessage(message);
            }

        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }

    private void parseMessage(Message message) {
        String text = "Hola [.$Nombre$.] tu edad es [.$Edad$.]";
        ArrayList<Parameter> params = message.get_param();

        for (Parameter param : params) {
            System.out.println(param.get_name());
            text = text.replace("[.$" + param.get_name() + "$.]", param.get_value());
        }
        System.out.println(text);
    }
}
