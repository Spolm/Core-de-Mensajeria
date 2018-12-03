package webService.M07_Template;

import Classes.M01_Login.Privilege;
import Classes.M07_Template.HandlerPackage.StatusHandler;
import Classes.M07_Template.HandlerPackage.TemplateHandler;
import Classes.M07_Template.Template;
import Exceptions.TemplateDoesntExistsException;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * M07_Parameter class is an API that is responsible for requesting information
 * about the templates
 */

@Path("/templates")
@Produces(MediaType.APPLICATION_JSON)
public class M07_Template {

    /**
     * serialization and deserialization between Java objects
     */
    public Gson gson = new Gson();

    /**
     * Method that returns all the templates filtered by a user and his company.
     * @param userId id of the user
     * @param companyId id of the company
     * @return ArrayList of templates
     */
    @GET
    public Response getTemplates(@QueryParam("userId") int userId,
                                 @QueryParam("companyId") int companyId){
        TemplateHandler templateHandler = new TemplateHandler();
         ArrayList templateArrayList = templateHandler.getTemplates(userId,companyId);
        return Response.ok(gson.toJson(templateArrayList)).build();
    }

    /**
     * this method returns a specific template
     * @param id id of the template requested.
     * @return template
     */
    @GET
    @Path("/{templateId}")//Subsequent Path
    public Response getTemplate(@PathParam("templateId") int id){
        TemplateHandler templateHandler = new TemplateHandler();
        Template template = new Template();
        try {
            template = templateHandler.getTemplate(id);
        }catch (TemplateDoesntExistsException e){
            e.printStackTrace();
        }
        finally {
            return Response.ok(gson.toJson(template)).build();
        }
    }

    /**
     *
     this method returns the privileges that a user has over the templates.
     * @param userId id of the user
     * @param companyId id of the company
     * @return
     */
    @GET
    @Path("/privileges")
    public Response getTemplatePrivilegesByUser(@QueryParam("userId") int userId,
                                                @QueryParam("companyId") int companyId){
        TemplateHandler templateHandler = new TemplateHandler();
        ArrayList<Privilege> privileges = templateHandler.getTemplatePrivilegesByUser(userId,companyId);
        return Response.ok(gson.toJson(privileges)).build();
    }

    @Path("/messages")
    public M07_Message getMessages(){
        return new M07_Message();
    }

    /**
     *
     This method is responsible for updating the status
     of a template in specific.
     * @param templateId id of the template
     * @param userId id of the user
     * @return If the template was saved successfully it returns true,
     * otherwise it returns false.
     */
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

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateTemplate(String json){
        TemplateHandler templateHandler = new TemplateHandler();
        return templateHandler.updateTemplateData(json);
    }
}
