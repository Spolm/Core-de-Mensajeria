package webService.M03_Channel_Integrator;
import Classes.M03_Channel_Integrator.ChannelPackage.Channel;
import Classes.M03_Channel_Integrator.ChannelPackage.ChannelService;
import Classes.M03_Channel_Integrator.IntegratorPackage.Integrator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/channel")
public class ChannelServiceAPI {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listChannel() {
        ArrayList<Channel> p = ChannelService.getInstance().listChannel();
        return Response
                .ok()
                .entity(p)
                .build();
    }

    @Path("/i/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIntegrator(@PathParam( "id" ) int id) {
        List<Integrator> i = ChannelService.getInstance().listIntegratorByChannel(id);
        return Response
                .ok()
                .entity(i)
                .build();
    }
}
