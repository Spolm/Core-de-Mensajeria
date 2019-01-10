package webService.M10_Profile;

import Logic.Command;
import Logic.CommandsFactory;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/geographical_regions")
public class M10_GeographicalRegion {
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    /**
     *  method that is responsible for obtaining a geographical region by an id
     * @param id id of the geographical region
     * @return object
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRegionByType(@QueryParam("id") int id){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo getRegionByType("+id+")" );
        //endregion
        Response response = null;
        try{
            // 1. Map DTO to Entity ...
            Command command = CommandsFactory.createGetGeographicalRegionCommand(id);
            command.execute();
            // 4. Map Entity to DTO
            // 5. Return DTO
            //region Instrumentation Debug
            log.info("Ejecutado el metodo getRegionByType("+id+") con exito" );
            //endregion
            return Response.ok(new Gson().toJson(command.Return())).build();
        }catch (Exception e){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo getRegionByType("+id+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }

        return response;
    }

}
