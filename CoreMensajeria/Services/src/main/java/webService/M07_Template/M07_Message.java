package webService.M07_Template;

import Classes.M07_Template.HandlerPackage.MessageHandler;
import Classes.M07_Template.HandlerPackage.TemplateHandler;
import Classes.M07_Template.Template;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/")
public class M07_Message {

    public Gson gson = new Gson();

    @GET
    public Response getMessages(){
        TemplateHandler templateHandler = new TemplateHandler();
        ArrayList<Template> templateArrayList = templateHandler.getTemplates();
        MessageHandler messageHandler = new MessageHandler();
        ArrayList<Template> templateList = messageHandler.getMessages(templateArrayList);
        return Response.ok(gson.toJson(templateList)).build();
    }

}
