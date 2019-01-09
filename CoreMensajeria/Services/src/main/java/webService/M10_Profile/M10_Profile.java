package webService.M10_Profile;

import DTO.DTO;
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

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Path("/profiles")
public class M10_Profile {

    @GET
    @Path("/privileges")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrivileges(@QueryParam("userId") int userId,
                                  @QueryParam("companyId") int companyId){
        Response response = null;
        try {
            // 1. Map DTO to Entity ...
            Command command = CommandsFactory.createGetPrivilegesByUserCompanyCommand(userId, companyId);
            command.execute();
            // 4. Map Entity to DTO
            // 5. Return DTO
            return Response.ok(new Gson().toJson(command.Return())).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        // 5. Return DTO
        return response;
    }

    @Path("/edit")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editProfile(DTOEditUser dto) throws URISyntaxException {

        try {
            MapperEditUser map = MapperFactory.createMapperEditUser();
            User user = (User) map.CreateEntity(dto);
            Command c = CommandsFactory.createEditUserProfileCommand(user);
            c.execute();
            return Response.ok(new Gson().toJson(c.Return())).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
}