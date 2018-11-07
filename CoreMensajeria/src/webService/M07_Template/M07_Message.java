package webService.M07_Template;

import Classes.M07_Template.HandlerPackage.MessageHandler;
import Classes.M07_Template.MessagePackage.Message;
import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/")
public class M07_Message {

    public Gson gson = new Gson();

    @GET
    public Response getMessages(){
        MessageHandler messageHandler = new MessageHandler();
        ArrayList<Message> messageArrayList = messageHandler.getMessages();
        return Response.ok(gson.toJson(messageArrayList)).build();
    }
}
