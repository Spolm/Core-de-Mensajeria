package webService.M07_Template;

import Classes.Sql;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.Connection;

@Path("/")
public class M07_Message {

    public Gson gson = new Gson();
    private Connection conn = Sql.getConInstance();

    @GET
    public Response getMessages(){

        return null;
    }
}
