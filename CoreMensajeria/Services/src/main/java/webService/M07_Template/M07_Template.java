package webService.M07_Template;

import Classes.M07_Template.HandlerPackage.StatusHandler;
import Classes.M07_Template.HandlerPackage.TemplateHandler;
import Classes.M07_Template.Template;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/templates")
@Produces(MediaType.APPLICATION_JSON)
public class M07_Template {

    public Gson gson = new Gson();

    /**
     *
     * @param userId
     * @param companyId
     * @return
     */
    @GET
    public Response getTemplates(@QueryParam("userId") int userId,
                                 @QueryParam("companyId") int companyId){
        ArrayList<Template> templateArrayList = null;
        TemplateHandler templateHandler = new TemplateHandler();
        templateArrayList = templateHandler.getTemplates(userId,companyId);

        return Response.ok(gson.toJson("userId: " + userId + " companyId: " + companyId)).build();
        //return Response.ok(gson.toJson(templateArrayList)).build();
    }

    @GET
    @Path("/{templateId}")//Subsequent Path
    public Response getTemplate(@PathParam("templateId") int id){
        TemplateHandler templateHandler = new TemplateHandler();
        Template template = templateHandler.getTemplate(id);
        return Response.ok(gson.toJson(template)).build();
    }

    @Path("/messages")
    public M07_Message getMessages(){
        return new M07_Message();
    }

    @POST
    @Path("/update/{templateId}")//Subsequent Path
    public Boolean postTemplateStatus(@PathParam("templateId") int templateId, String userId){
        Boolean flag = false;
        flag = StatusHandler.postTemplateStatusAprovado(templateId,Integer.valueOf(userId));
        return flag;
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean postTemplate(String json){
        TemplateHandler templateHandler = new TemplateHandler();
        return templateHandler.postTemplateData(json);
    }

}
