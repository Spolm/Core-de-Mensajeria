package webService.M07_Template;

import Classes.M07_Template.HandlerPackage.TemplateHandler;
import Classes.M07_Template.Template;
import Classes.Sql;
import com.google.gson.Gson;
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

    //@GET
    @Path("/messages")
    public M07_Message getMessages(){

        return new M07_Message();
    }

    @POST
    @Path("/update/{templateId}")//Subsequent Path
    public void postTemplateStatus(@PathParam("templateId") int id){
        TemplateHandler templateHandler = new TemplateHandler();
        templateHandler.postTemplateStatus(id);
    }

}
