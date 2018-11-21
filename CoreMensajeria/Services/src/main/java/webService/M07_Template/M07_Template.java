package webService.M07_Template;

import Classes.M07_Template.HandlerPackage.TemplateHandler;
import Classes.M07_Template.Template;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/templates")
@Produces(MediaType.APPLICATION_JSON)
public class M07_Template {

    public Gson gson = new Gson();

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

    /*
     * Delegando la responsabilidad al servicio M07_Message
     * Ruta: /templates/messages
     */
    @Path("/messages")
    public M07_Message getMessages(){
        return new M07_Message();
    }

    @POST
    @Path("/update/{templateId}")//Subsequent Path
    public Boolean postTemplateStatus(@PathParam("templateId") int id){
        Boolean flag = false;
        TemplateHandler templateHandler = new TemplateHandler();
        flag = templateHandler.postTemplateStatus(id);
        return flag;
    }

    @POST
    @Path("posttemplate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postTemplate(String json){
        try {
            JsonParser parser = new JsonParser();
            JsonArray gsonArr = parser.parse(json).getAsJsonArray();
            JsonObject gsonObj = gsonArr.get(0).getAsJsonObject();

            String name = gsonObj.get("name").getAsString();
            int parameterId = gsonObj.get("parameterId").getAsInt();
            String text = "name: " + name + " id: " + String.valueOf(parameterId);
            return Response.ok(gson.toJson(text)).build();
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

}
