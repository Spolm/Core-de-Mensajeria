package webService.M08_MessageCoreManagement;

import Classes.M07_Template.HandlerPackage.MessageHandler;
import Classes.M08_Validation.ValidationReciveParameter;
import Classes.M08_Validation.ValidationTemplate;
import Classes.M07_Template.HandlerPackage.TemplateHandler;
import Classes.M07_Template.Template;
import Exceptions.MessageDoesntExistsException;
import Exceptions.TemplateDoesntExistsException;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Path("/M08_MessageCore")
public class M08_MessageValidation {

    @GET
    @Path("/TestModule")
    @Produces("text/plain")
    public String ValidateMessage() {
        // Return some cliched textual content
        return "El Modulo esta funcionando";
    }



    @POST
    @Path("/ValidateParameters")
    @Produces("application/json")

    public String ValidateParameters(String JsonParameters) {

        int[] posicion;
        int j =0;
        
        try { 
           
         Gson g = new Gson();
         String newMessage="";
         ValidationReciveParameter parameters = g.fromJson(JsonParameters, ValidationReciveParameter.class);
         List<ValidationReciveParameter.Data> parametersArray = parameters.getData();
         TemplateHandler template = new TemplateHandler();
         Template template2=template.getTemplate( parameters.getTemplate_id());


        char[] characters = String.valueOf(MessageHandler.getMessage(template2.getTemplateId()).getMessage()).toCharArray();
        posicion= new int[characters.length];


            for (int i = 0; i < characters.length; i++){

                if (('[' == characters[i])&&('.' == characters[i+1])&&('$' == characters[i+2])){
                    posicion[j]=i;
                    j++;
                }
                if ((']' == characters[i])&&('.' == characters[i-1])&&('$' == characters[i-2])){
                    posicion[j]=i;
                    j++;
                }
            }

            if((j /parametersArray.size())==2){
                newMessage = MessageHandler.getMessage(template2.getTemplateId()).getMessage();
                for (int i = 0; i <  parametersArray.size(); i++){
                    newMessage = newMessage.replace("[.$"+parametersArray.get(i).getParameter().getName()+"$.]", parametersArray.get(i).getParameter().getValue());
                }
            }

            return newMessage + "..........."+parameters.getTemplate_id();
        } catch (TemplateDoesntExistsException e) {
            e.printStackTrace();
            return "template no existe";
        }
        catch (MessageDoesntExistsException e) {
            e.printStackTrace();
            return "mensaje no existe";
        }

    }

    @POST
    @Path("/ValidateFileParameters")
    @Produces("application/json")
   public String ValidateFileParameters(String fileRoute){

        Gson g = new Gson();
        Validations validations = new Validations();

        try {
            BufferedReader JsonFile = new BufferedReader( new FileReader(fileRoute));

            //Invoca la ruta de para obtener plantilla por id y la asigno aun String
            String JsonTemplate="{\n" +
                    " \"_idTemplate\":123,\n" +
                    " \"_name\":\"luis\",\n" +
                    " \"_lastName\":\"meneses\",\n" +
                    " \"_personId\":\"213456\",\n" +
                    " \"_sendDate\":\"12-12-19\",\n" +
                    " \"_sendHour\":\"00:00:00\",\n" +
                    " \"_chanel\":\"sms\",\n" +
                    " \"_message\":\"Mensaje con parametros\"\n" +
                    "}";

            ValidationTemplate  template = g.fromJson(JsonTemplate,ValidationTemplate.class);
            ValidationReciveParameter parameters = g.fromJson(JsonFile, ValidationReciveParameter.class);

            //Validacion de parametros con respecto a la plantilla
            Boolean date = validations.Validate_sendDate(template.get_sendDate());
            Boolean hour = validations.Validate_sendHour(template.get_sendHour());
           // Boolean message = validations.Validate_messageParameter(parameters.get_message());
            //Boolean messageLength = validations.ValidateSMSlentgh(parameters.get_message(),parameters.get_chanel());

           // return validations.Validate_Condicion(date,hour,message,messageLength,template,parameters);
   return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "El archivo no se encontro o no existe:: "+fileRoute;
        }
    }

}
