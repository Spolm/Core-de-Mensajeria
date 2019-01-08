package webService.M10_Profile;

import Entities.M01_Login.Privilege;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAOProfile;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/profiles")
public class M10_Profile {
    @GET
    @Path("/privileges")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrivileges(@QueryParam("userId") int user,
                                  @QueryParam("companyId") int company){
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        IDAOProfile dao = factory.createDAOProfile();
        ArrayList<Privilege> privileges = dao.getPrivilegesByUserAndCompany(user, company);
        return Response.ok(new Gson().toJson(privileges)).build();
    }
}
