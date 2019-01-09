package webService.M10_Profile;

import Logic.Command;
import Logic.CommandsFactory;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/geographical_regions")
public class M10_GeographicalRegion {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRegionByType(@QueryParam("id") int id){
        Response response = null;
        try{
            // 1. Map DTO to Entity ...
            Command command = CommandsFactory.createGetGeographicalRegionCommand(id);
            command.execute();
            // 4. Map Entity to DTO
            // 5. Return DTO
            return Response.ok(new Gson().toJson(command.Return())).build();
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

}
