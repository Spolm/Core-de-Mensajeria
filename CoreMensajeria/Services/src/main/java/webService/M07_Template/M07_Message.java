package webService.M07_Template;

import Entities.M07_Template.HandlerPackage.MessageHandler;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * M07_Message class is an API that is responsible for requesting information
 * about template messages
 */
@Path("/")
public class M07_Message {

    /**
     * serialization and deserialization between Java objects
     */
    public Gson gson = new Gson();

    /**
     * this method returns all the templates and each one with
     * its message and associated parameters.
     * @return ArrayList of template with messages and parameters
     */
    @GET
    public Response getMessages(){
        TemplateHandler templateHandler = new TemplateHandler();
        ArrayList<Template> templateArrayList = templateHandler.getTemplates();
        MessageHandler messageHandler = new MessageHandler();
        ArrayList<Template> templateList = messageHandler.getMessages(templateArrayList);
        return Response.ok(gson.toJson(templateList)).build();
    }

}
