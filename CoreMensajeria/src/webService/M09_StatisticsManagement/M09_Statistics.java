package webService.M09_StatisticsManagement;

import Modulo_9.Statistics;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import java.util.ArrayList;

@Path("/M09_Statistics")
public class M09_Statistics extends Application {

    Gson gson = new Gson();

    @GET
    @Path("/Grafica")
    @Produces("application/json")
    public String  Mandarnumeros(){
        Statistics gr = new Statistics();
        ArrayList<Integer> listNum = new ArrayList<Integer>();
        listNum.add(100);
        listNum.add(200);
        listNum.add(300);
        listNum.add(400);

        ArrayList<String> listlabels = new ArrayList<String>();
        listlabels.add("Telegram");
        listlabels.add("SMS");
        listlabels.add("MAIL");
        listlabels.add("SENALES DE HUMO");
        gr.type="bar";
        gr.x = listlabels;
        gr.y = listNum;
        return gson.toJson(gr);
    }


}
