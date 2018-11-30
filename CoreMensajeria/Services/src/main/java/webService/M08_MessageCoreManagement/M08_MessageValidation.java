package webService.M08_MessageCoreManagement;

import Classes.M08_Validation.ValidationReciveParameter;
import Classes.M08_Validation.ValidationTemplate;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

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


        Gson g = new Gson();
        Validation validations = new Validation();

        //Invoco la ruta de para obtener plantilla por id y la asigno aun String
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


        //Convert the json to  Java object (ValidationReciveParameter && ValidationTemplate.class)

        ValidationTemplate  template = g.fromJson(JsonTemplate,ValidationTemplate.class);
        ValidationReciveParameter parameters = g.fromJson(JsonParameters, ValidationReciveParameter.class);

        //Validacion de parametros con respecto a la plantilla
        Boolean date = validations.Validate_sendDate(template.get_sendDate());
        Boolean hour = validations.Validate_sendHour(template.get_sendHour());
      //  Boolean message = validations.Validate_messageParameter(parameters.get_message());
       // Boolean messageLength = validations.ValidateSMSlentgh(parameters.get_message(),parameters.get_chanel());

        //return validations.Validate_Condicion(date,hour,message,messageLength,template,parameters);
    return "null";
    }

    @POST
    @Path("/ValidateFileParameters")
    @Produces("application/json")
   public String ValidateFileParameters(String fileRoute){

        Gson g = new Gson();
        Validation validations = new Validation();

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
