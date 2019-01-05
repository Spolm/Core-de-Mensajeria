package webService.M08_MessageCoreManagement;


import DTO.ValidationDTO.ParametersDTO;
import Entities.Entity;
import Entities.M07_Template.HandlerPackage.MessageHandler;
import Entities.M08_Validation.SentMessage;
import Entities.M08_Validation.ValidationReciveParameter;
import Entities.M08_Validation.ValidationTemplate;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Entities.M04_Integrator.*;
import Entities.M08_Validation.XMLManagement.Command;
import Entities.M08_Validation.XMLManagement.CommandValidate;
import Exceptions.*;
import Logic.M08_SendMessage.SendMessage;
import Mappers.SendMessageMapper.SendMessageMapper;
import com.google.gson.Gson;

import java.util.Random;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Path("/M08_MessageCore")
public class M08_MessageValidation {

    Gson gson = new Gson();

    @POST
    @Path("/SendMessage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendMessage(@Valid ParametersDTO dto) {
        System.out.println(dto.get_name());
        Exception error = null;
        Entity sentMessage = new SentMessage();
        try {
            sentMessage = SendMessageMapper.CreateEntity(dto);
            System.out.println(((SentMessage) sentMessage).get_message());
            //Codigo cuando es exitoso
            return  Response.ok(gson.toJson(sentMessage)).build();
        } catch (TemplateDoesntExistsException e) {
            error = e;
        } catch (SMSTooLongException e) {
            error = e;
        } catch (ParameterDoesntExistsException e) {
            error = e;
        } catch (MessageDoesntExistsException e) {
            error = e;
        }
        if (error != null)
         return Response.status(400).entity(gson.toJson(error.toString())).build();
        return Response.status(400).entity("{\"Mensaje\": \"Error inesperado\"}").build();
        //return Response.ok(gson.toJson(sentMessage)).build();

    }

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
        String correo="";
        String telefono="";
        Random rnd = new Random();
        try { 
           
         Gson g = new Gson();
         String newMessage="";
         ValidationReciveParameter parameters = g.fromJson(JsonParameters, ValidationReciveParameter.class);
         List<ValidationReciveParameter.Data> parametersArray = parameters.getData();
         TemplateHandler template = new TemplateHandler();
         Template template2=template.getTemplate( parameters.getTemplate_id());
         IntegratorDAO _integratorDAO =new IntegratorDAO();
         MessageIntegrator respuestaIntegrador = null;

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
                    if(parametersArray.get(i).getParameter().getName().equals("Correo")){
                        correo = parametersArray.get(i).getParameter().getValue();
                    }
                    if(parametersArray.get(i).getParameter().getName().equals("Telefono")){
                        telefono = parametersArray.get(i).getParameter().getValue();
                    }
                    newMessage = newMessage.replace("[.$"+parametersArray.get(i).getParameter().getName()+"$.]", parametersArray.get(i).getParameter().getValue());
                }
            }

            for (int i = 0; i < template2.getChannels().size(); i++){
                if ((template2.getChannels().get(i).getNameChannel().equals("SMS"))&&(newMessage.length()>160)){
                    return "El sms supera los 160 caracteres";
                }
                ///envio mensaje
                for (int k = 0; i <  template2.getChannels().get(i).getIntegrators().size(); i++){

                    if(template2.getChannels().get(i).getIntegrators().get(i).getNameIntegrator().equals("Movistar")){
                        respuestaIntegrador = _integratorDAO.getConcreteIntegrator( template2.getChannels().get(i).getIntegrators().get(k).getIdIntegrator()).sendMessage(newMessage,telefono,Integer.toString(rnd.nextInt()));
                       if(respuestaIntegrador.isSend()){
                           return "Mensaje enviado:: "+respuestaIntegrador.getAcknowledge();
                       }else{
                           return "Mensaje no enviado:: "+respuestaIntegrador.getMsg();
                       }
                    }
                    if(template2.getChannels().get(i).getIntegrators().get(k).getNameIntegrator().equals("Digitel")){
                        respuestaIntegrador = _integratorDAO.getConcreteIntegrator( template2.getChannels().get(i).getIntegrators().get(k).getIdIntegrator()).sendMessage(newMessage,telefono,Integer.toString(rnd.nextInt()));
                        if(respuestaIntegrador.isSend()){
                            return "Mensaje enviado:: "+respuestaIntegrador.getAcknowledge();
                        }else{
                            return "Mensaje no enviado:: "+respuestaIntegrador.getMsg();
                        }
                    }
                    if(template2.getChannels().get(i).getIntegrators().get(k).getNameIntegrator().equals("Movilnet")){
                        respuestaIntegrador = _integratorDAO.getConcreteIntegrator( template2.getChannels().get(i).getIntegrators().get(k).getIdIntegrator()).sendMessage(newMessage,telefono,Integer.toString(rnd.nextInt()));
                        if(respuestaIntegrador.isSend()){
                            return "Mensaje enviado:: "+respuestaIntegrador.getAcknowledge();
                        }else{
                            return "Mensaje no enviado:: "+respuestaIntegrador.getMsg();
                        }
                    }
                    if(template2.getChannels().get(i).getIntegrators().get(k).getNameIntegrator().equals("MailChimp")){
                        respuestaIntegrador = _integratorDAO.getConcreteIntegrator( template2.getChannels().get(i).getIntegrators().get(k).getIdIntegrator()).sendMessage(newMessage,correo,Integer.toString(rnd.nextInt()));
                        if(respuestaIntegrador.isSend()){
                            return "Mensaje enviado:: "+respuestaIntegrador.getAcknowledge();
                        }else{
                            return "Mensaje no enviado:: "+respuestaIntegrador.getMsg();
                        }
                    }
                    if(template2.getChannels().get(i).getIntegrators().get(k).getNameIntegrator().equals("Aweber")){
                        respuestaIntegrador = _integratorDAO.getConcreteIntegrator( template2.getChannels().get(i).getIntegrators().get(k).getIdIntegrator()).sendMessage(newMessage,correo,Integer.toString(rnd.nextInt()));
                        if(respuestaIntegrador.isSend()){
                            return "Mensaje enviado:: "+respuestaIntegrador.getAcknowledge();
                        }else{
                            return "Mensaje no enviado:: "+respuestaIntegrador.getMsg();
                        }
                    }
                    if(template2.getChannels().get(i).getIntegrators().get(k).getNameIntegrator().equals("Infusionsoft")){
                        respuestaIntegrador = _integratorDAO.getConcreteIntegrator( template2.getChannels().get(i).getIntegrators().get(k).getIdIntegrator()).sendMessage(newMessage,correo,Integer.toString(rnd.nextInt()));
                        if(respuestaIntegrador.isSend()){
                            return "Mensaje enviado:: "+respuestaIntegrador.getAcknowledge();
                        }else{
                            return "Mensaje no enviado:: "+respuestaIntegrador.getMsg();
                        }
                    }
                }

            }

            return "Fin del metodo" ;

        } catch (TemplateDoesntExistsException e) {
            e.printStackTrace();
            return "template no existe";
        }
        catch (MessageDoesntExistsException e) {
            e.printStackTrace();
            return "mensaje no existe";
        } catch (ParameterDoesntExistsException e) {
            e.printStackTrace();
            return "parametro no existe";
        } catch (IntegratorNotFoundException e) {
            e.printStackTrace();
            return "integrador no existe";
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
            return "Error al conectarse a la base de datos";
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
