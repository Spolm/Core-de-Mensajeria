package webService.M07_Template;

import Classes.M07_Template.HandlerPackage.MessageHandler;
import Classes.M07_Template.HandlerPackage.TemplateHandler;
import Classes.M07_Template.MessagePackage.Message;
import Classes.M07_Template.Template;
import Classes.Sql;
import com.google.gson.Gson;
import com.oracle.wls.shaded.org.apache.xpath.operations.Bool;

import java.sql.Connection;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/templates")
@Produces(MediaType.APPLICATION_JSON)
public class M07_Template {

    public Gson gson = new Gson();
    private Connection conn = Sql.getConInstance();

    @GET
    public Response getTemplates(){
        TemplateHandler templateHandler = new TemplateHandler();
        ArrayList<Template> templateArrayList = templateHandler.getTemplates();
        return Response.ok(gson.toJson(templateArrayList)).build();
    }

    @GET
    @Path("/{templateId}")//Subsequent Path
    public Response getTemplate(@PathParam("templateId") int id){
        TemplateHandler templateHandler = new TemplateHandler();
        Template template = templateHandler.getTemplate(id);
        return Response.ok(gson.toJson(template)).build();
    }

    @GET
    @Path("/messages")
    public Response  getMessages(){
        MessageHandler messageHandler = new MessageHandler();
        ArrayList<Message> messageArrayList = messageHandler.getMessages();
        return Response.ok(gson.toJson(messageArrayList)).build();
        //return new M07_Message();
    }

    @POST
    @Path("/update/{templateId}")//Subsequent Path
    public Boolean postTemplateStatus(@PathParam("templateId") int id){
        Boolean flag = false;
        TemplateHandler templateHandler = new TemplateHandler();
        flag = templateHandler.postTemplateStatus(id);
        return flag;
    }

}
