package webService.M09_StatisticsManagement;

import Classes.Campaign;
import Classes.Company;
import Classes.Sql;
import Classes.User;
import Modulo_9.PieChart;
import Modulo_9.Statistics;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.io.Console;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Path("/M09_Statistics")
public class M09_Statistics extends Application {

    Gson gson = new Gson();
    private Connection conn = Sql.getConInstance();

    @GET
    @Path("/Grafica")
    @Produces("application/json")
    public String Mandarnumeros() {
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
        gr.type = "bar";
        gr.x = listlabels;
        gr.y = listNum;
        return gson.toJson(gr);
    }

    @GET
    @Path("/Grafica2")
    @Produces("application/json")
    public String Mandarnumeros2() {
        Statistics gr = new Statistics();
        ArrayList<Integer> listNum = new ArrayList<Integer>();
        listNum.add(50);
        listNum.add(70);
        listNum.add(300);
        listNum.add(147);

        ArrayList<String> listlabels = new ArrayList<String>();
        listlabels.add("Telegram");
        listlabels.add("SMS");
        listlabels.add("MAIL");
        listlabels.add("SENALES DE HUMO");
        gr.type = "line";
        gr.x = listlabels;
        gr.y = listNum;
        return gson.toJson(gr);
    }


    @GET
    @Path("/Grafica3")
    @Produces("application/json")
    public String Mandarnumeros3() {
        PieChart PieC = new PieChart();
        ArrayList<Integer> listNum = new ArrayList<Integer>();
        listNum.add(50);
        listNum.add(70);
        listNum.add(300);
        listNum.add(147);

        ArrayList<String> listlabels = new ArrayList<String>();
        listlabels.add("Telegram");
        listlabels.add("SMS");
        listlabels.add("MAIL");
        listlabels.add("SENALES DE HUMO");
        PieC.type = "pie";
        PieC.labels = listlabels;
        PieC.values = listNum;
        return gson.toJson(PieC);
    }

    @GET
    @Path("/ConsultaCompany")
    @Produces("application/json")
    public Response getCompany() throws SQLException {
        String select = "SELECT * FROM public.Company";

        try {
            ArrayList<Company> companyList = new ArrayList<>();
            Statement st = conn.createStatement();

            ResultSet result = st.executeQuery(select);

            while (result.next()) {
                Company co = new Company();

                co.set_idCompany(result.getInt("com_id"));
                co.set_name(result.getString("com_name"));
                co.set_desc(result.getString("com_description"));
                co.set_status(result.getBoolean("com_status"));

                companyList.add(co);
            }
            return Response.ok(gson.toJson(companyList)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(select);
        } finally {
            Sql.bdClose(conn);
        }
    }


    @GET
    @Path("/ConsultaCampaign")
    @Produces("application/json")
    public Response getCampaign() throws SQLException {

        String select = "SELECT * FROM public.Campaign";
        try {
            //ArrayList<Company> companyList = new ArrayList<>();
            ArrayList<Campaign> campaignList = new ArrayList<>();
            Statement st = conn.createStatement();

            ResultSet result = st.executeQuery(select);

            while (result.next()) {

                Company co = new Company();
                Campaign ca = new Campaign();

                ca.set_idCampaign(result.getInt("cam_id"));
                ca.set_nameCampaign(result.getString("cam_name"));
                ca.set_descCampaign(result.getString("cam_description"));
                ca.set_statusCampaign(result.getBoolean("cam_status"));
                ca.set_startCampaign(result.getTimestamp("cam_start_date"));
                ca.set_endCampaign(result.getTimestamp("cam_end_date"));
                co.set_idCompany(result.getInt("com_id"));
                co.set_name(result.getString("com_name"));
                co.set_status(result.getBoolean("com_status"));
                co.set_desc(result.getString("com_descrption"));
                //companyList.add(co);
                campaignList.add(ca);
            }
            return Response.ok(gson.toJson(campaignList)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(select);
        } finally {
            Sql.bdClose(conn);
        }
    }


        //Falta algunas modificaciones a este metodo
    @GET
    @Path("/NumberCompany")
    @Produces("application/json")
    public Response getNumeroDeCompanys() throws SQLException {

        String select = "SELECT count(*) FROM public.Company";
        String select2 = "SELECT com_name FROM public.Company";
        try {
            Statistics gr = new Statistics();
            ArrayList<Integer> listNum = new ArrayList<Integer>();
            ArrayList<Company> listCompany = new ArrayList<Company>();
            int n = 0 ;

            Statement st = conn.createStatement();
            Statement st2 = conn.createStatement();
            ResultSet result2 = st2.executeQuery(select2);
            ResultSet result = st.executeQuery(select);

            while (result2.next()) {

                Company co = new Company();
                co.set_name(result2.getString("com_name"));
                listCompany.add(co);
            }


            if(result.next()) {
                //Si hay resultados obtengo el valor.
                n = result.getInt(1);
                listNum.add(n);
                listNum.add(n);
                listNum.add(n);
                listNum.add(n);
            }

            gr.type = "bar";
            gr.x = listCompany;
            gr.y = listNum;

            return Response.ok(gson.toJson(gr)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(select);
        } finally {
            Sql.bdClose(conn);
        }
    }

}

