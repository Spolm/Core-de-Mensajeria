package Modulo_3.ChannelPackage;
import Modulo_3.IntegratorPackage.Integrator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/channel")
public class ChannelServiceAPI {

    @Path("/i/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIntegrator(@PathParam( "id" ) int id) {
        System.out.println("aqui!"+id);
        List<Integrator> i = ChannelService.getInstance().listIntegratorByChannel(id);
        return Response
                .ok()
                .entity(i)
                .build();
    }

}
