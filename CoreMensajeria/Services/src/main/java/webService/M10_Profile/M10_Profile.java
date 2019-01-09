package webService.M10_Profile;

import DTO.M10_DTO.DTOEditUser;
import Entities.M01_Login.Privilege;
import Entities.M01_Login.User;
import Logic.Command;
import Logic.CommandsFactory;
import Mappers.M10_Profile.MapperEditUser;
import Mappers.MapperFactory;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAOProfile;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


/**
 * M10_Profile class is an API that is responsible for requesting and edit
 * information about the profile a user
 */
@Path("/profiles")
public class M10_Profile {
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    @GET
    @Path("/privileges")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrivileges(@QueryParam("userId") int userId,
                                  @QueryParam("companyId") int companyId){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo getPrivileges("+userId+","+companyId+")" );
        //endregion
        Response response = null;
        try {
            // 1. Map DTO to Entity ...
            Command command = CommandsFactory.createGetPrivilegesByUserCompanyCommand(userId, companyId);
            command.execute();
            // 4. Map Entity to DTO
            // 5. Return DTO
            //region Instrumentation Info
            log.info("Se ejecuto el metodo getPrivileges("+userId+","+companyId+") exitosamente");
            log.info("Privilegios obtenidos : \n" + command.Return().toString());
            //endregion
            return Response.ok(new Gson().toJson(command.Return())).build();
        }catch (Exception e){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo getPrivileges("+userId+","+companyId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }
        // 5. Return DTO
        //region Instrumentation Debug
        log.debug("Saliendo del metodo getPrivileges("+userId+","+companyId+") con retorno: "+ response.getEntity().toString());
        //endregion
        return response;
    }

    /**
     * this method is responsible for editing a user profile (non-administrator)
     * @param dto DTO with the information to edit
     * @return state of the edition in bd
     */
    @Path("/edit")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editProfile(DTOEditUser dto){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo editProfile(" + dto.toString() +")");
        //endregion
        Response response = null;
        try {
            MapperEditUser map = MapperFactory.createMapperEditUser();
            User user = (User) map.CreateEntity(dto);
            Command c = CommandsFactory.createEditUserProfileCommand(user);
            c.execute();
            log.info("Se ejecuto el metodo editProfile(" + dto.toString() +") exitosamente");
            response = Response.ok(new Gson().toJson(c.Return())).build();
        } catch (Exception e) {
            response =  Response.status(500).entity(e).build();
            //region Instrumentation Error
            log.error("El metodo editProfile(" + dto.toString() +") arrojo la excepcion:" + e.getMessage());
            //endregion
        }finally {

            //region Instrumentation Debug
            log.debug("Saliendo del metodo editProfile(" + dto.toString() +") con retorno: "+ response.getEntity().toString());
            //endregion

            return response;
        }
    }
}