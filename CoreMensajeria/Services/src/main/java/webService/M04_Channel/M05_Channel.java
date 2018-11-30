package webService.M04_Channel;

import Classes.M05_Channel.Channel;
import Classes.M05_Channel.ChannelDAO;
import Classes.M05_Channel.ChannelNotFoundException;
import Classes.M04_Integrator.Integrator;
import Exceptions.DatabaseConnectionProblemException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/channel")
public class M05_Channel {

    private ChannelDAO _channelDAO = new ChannelDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listChannel() {
        try {
            ArrayList<Channel> c = _channelDAO.listChannel();
            return Response.ok().entity(c).build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/i/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIntegrator(@PathParam("id") int id) {
        try {
            ArrayList<Integrator> i = _channelDAO.listIntegratorByChannel(id);
            return Response.ok().entity(i).build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(e.getMessage()).build();
        } catch (ChannelNotFoundException e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }
}
