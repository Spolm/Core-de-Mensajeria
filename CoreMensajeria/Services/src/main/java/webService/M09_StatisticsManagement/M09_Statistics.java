package webService.M09_StatisticsManagement;

import Classes.M02_Company.Company;
import Classes.M03_Campaign.Campaign;
import Classes.M04_Channel_Integrator.ChannelPackage.Channel;
import Classes.M04_Channel_Integrator.ChannelPackage.ChannelFactory;
import Classes.M09_Statistics.PieChart;
import Classes.M09_Statistics.SqlEstrella;
import Classes.M09_Statistics.Statistics;
import Classes.Sql;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


@Path("/M09_Statistics")
public class M09_Statistics extends Application {

    Gson gson = new Gson();
    private Connection conn = SqlEstrella.getConInstance();

    @GET
    @Path("/MessageCompanyBar")
    @Produces("application/json")
    public Response getNumberOfCompanysChart() throws SQLException {
        String aux = "";
        String select2 = "SELECT com_name  from dim_company_campaign group by com_name";

        try {
            Statistics gr = new Statistics();
            ArrayList<Integer> listNum = new ArrayList<Integer>();
            ArrayList<String> listCompany = new ArrayList<String>();
            int n = 0 ;
            Statement st2 = conn.createStatement();
            ResultSet result2 = st2.executeQuery(select2);
            while ( result2.next() ) {
                Company co = new Company();
                co.set_name( result2.getString("com_name" ) );
                aux = co.get_name();
                listCompany.add( aux ) ;
            }
            listNum = CountOfMessage(listCompany);
            gr.type = "bar";
            gr.x = listCompany;
            gr.y = listNum;
            return Response.ok( gson.toJson( gr ) ).build();
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new SQLException( select2 );
        } finally {
            Sql.bdClose( conn );
        }
    }

    @GET
    @Path("/MessageCompanyLine")
    @Produces("application/json")
    public Response getNumberOfCompanysLine() throws SQLException {
        String aux = "";
        String select2 = "SELECT com_name  from dim_company_campaign group by com_name ";
        try {
            Statistics gr = new Statistics();
            ArrayList<Integer> listNum = new ArrayList<Integer>();
            ArrayList<String> listCompany = new ArrayList<String>();
            int n = 0 ;
            Statement st2 = conn.createStatement();
            ResultSet result2 = st2.executeQuery( select2 );
            while ( result2.next() ) {
                Company co = new Company();
                co.set_name( result2.getString("com_name" ) );
                aux = co.get_name();
                listCompany.add( aux ) ;
            }
            listNum = CountOfMessage( listCompany );
            gr.type = "line";
            gr.x = listCompany;
            gr.y = listNum;
            return Response.ok( gson.toJson( gr ) ).build();
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new SQLException( select2 );
        } finally {
            Sql.bdClose( conn );
        }
    }

    @GET
    @Path("/MessageCompanyPie")
    @Produces("application/json")
    public Response getNumberOfCompanysPie() throws SQLException {
        String aux = "";
        String select2 = "SELECT com_name  from dim_company_campaign group by com_name";
        try {
            PieChart PieC = new PieChart();
            ArrayList<Integer> listNum = new ArrayList<Integer>();
            ArrayList<String> listlabels = new ArrayList<String>();
            int n = 0 ;
            Statement st2 = conn.createStatement();
            ResultSet result2 = st2.executeQuery(select2);
            while ( result2.next() ) {
                Company co = new Company();
                co.set_name( result2.getString("com_name" ) );
                aux = co.get_name();
                listlabels.add( aux ) ;
            }
            listNum = CountOfMessage( listlabels );
            PieC.type = "pie";
            PieC.labels = listlabels;
            PieC.values = listNum;
            return Response.ok( gson.toJson( PieC ) ).build();
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new SQLException( select2 );
        } finally {
            Sql.bdClose( conn );
        }
    }



    @GET
    @Path("/MessageCampaignPie")
    @Produces("application/json")
    public Response getNumberOfCampaignPie() throws SQLException {
        String aux = "";
        String select2 = "SELECT cam_name  from dim_company_campaign ";
        try {
            PieChart PieC = new PieChart();
            ArrayList<Integer> listNum = new ArrayList<Integer>();
            ArrayList<String> listlabels = new ArrayList<String>();
            int n = 0 ;
            Statement st2 = conn.createStatement();
            ResultSet result2 = st2.executeQuery(select2);
            while ( result2.next() ) {
                Campaign ca = new Campaign();
                ca.set_nameCampaign( result2.getString("cam_name" ) );
                aux = ca.get_nameCampaign();
                listlabels.add( aux ) ;
            }
            listNum = CountOfMessageCamp( listlabels );
            PieC.type = "pie";
            PieC.labels = listlabels;
            PieC.values = listNum;
            return Response.ok( gson.toJson( PieC ) ).build();
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new SQLException( select2 );
        } finally {
            Sql.bdClose( conn );
        }
    }

    @GET
    @Path("/MessageCampaignBar")
    @Produces("application/json")
    public Response getNumberOfCampaignChart() throws SQLException {
        String aux = "";
        String select2 ="SELECT cam_name  from dim_company_campaign";

        try {
            Statistics gr = new Statistics();
            ArrayList<Integer> listNum = new ArrayList<Integer>();
            ArrayList<String> listCampaign = new ArrayList<String>();
            int n = 0 ;
            Statement st2 = conn.createStatement();
            ResultSet result2 = st2.executeQuery(select2);
            while ( result2.next() ) {
                Campaign co = new Campaign();
                co.set_nameCampaign( result2.getString("cam_name" ) );
                aux = co.get_nameCampaign();
                listCampaign.add( aux ) ;
            }
            listNum = CountOfMessageCamp(listCampaign);
            gr.type = "bar";
            gr.x = listCampaign;
            gr.y = listNum;
            return Response.ok( gson.toJson( gr ) ).build();
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new SQLException( select2 );
        } finally {
            Sql.bdClose( conn );
        }
    }

    @GET
    @Path("/MessageCampaignLine")
    @Produces("application/json")
    public Response getNumberOfCampaignLine() throws SQLException {
        String aux = "";
        String select2 = " SELECT cam_name  from dim_company_campaign";
        try {
            Statistics gr = new Statistics();
            ArrayList<Integer> listNum = new ArrayList<Integer>();
            ArrayList<String> listCamp = new ArrayList<String>();
            int n = 0 ;
            Statement st2 = conn.createStatement();
            ResultSet result2 = st2.executeQuery( select2 );
            while ( result2.next() ) {
                Campaign co = new Campaign();
                co.set_nameCampaign( result2.getString("cam_name" ) );
                aux = co.get_nameCampaign();
                listCamp.add( aux ) ;
            }
            listNum = CountOfMessageCamp( listCamp );
            gr.type = "line";
            gr.x = listCamp;
            gr.y = listNum;
            return Response.ok( gson.toJson( gr ) ).build();
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new SQLException( select2 );
        } finally {
            Sql.bdClose( conn );
        }
    }


    @GET
    @Path("/{NombreConsulta}/{FechaDeConsulta}")
    @Produces("application/json")

    public Response test(@PathParam( "NombreConsulta" ) String Consulta,
                         @PathParam( "FechaDeConsulta" ) String Fecha) {

        return Response.ok( gson.toJson( Consulta + " " + Fecha ) ).build();

    }


    @GET
    @Path("/PruebaParam")
    @Produces("application/json")

    public Response test2(@QueryParam( "paramDate" ) String paramDate,
                          @QueryParam( "paramType" ) String paramType) {


        Response responseAnswerLine = filterOfTypeStatisticsLine(paramDate,paramType);

        return responseAnswerLine ;



        // String squery ="select * from producto where id="@id;
        //squery=squery.replace("@id","1");
    }

    @GET
    @Path("/PruebaParam2")
    @Produces("application/json")

    public Response test1(@QueryParam( "paramDate" ) String paramDate,
                          @QueryParam( "paramType" ) String paramType) {

        Response responseAnswerPie = filterOfTypeStatisticsPie(paramDate,paramType);

        return responseAnswerPie ;

        // String squery ="select * from producto where id="@id;
        //squery=squery.replace("@id","1");
    }

    @GET
    @Path("/PruebaParam3")
    @Produces("application/json")

    public Response test3(@QueryParam( "paramDate" ) String paramDate,
                          @QueryParam( "paramType" ) String paramType) {

        Response responseAnswerBar = filterOfTypeStatisticsBar(paramDate,paramType);

        return responseAnswerBar ;

        // String squery ="select * from producto where id="@id;
        //squery=squery.replace("@id","1");
    }


    //Metodo para contar la cantidad me menajes enviados por Campana
    public ArrayList<Integer> CountOfMessageCamp ( ArrayList<String> listCampaign ){

        String aux2 = "" ;
        int n = 0  ;
        ArrayList<Integer> listNum = new ArrayList<>();

        try {
            for ( int i = 0 ; i < listCampaign.size() ; i++ ) {

                aux2 = listCampaign.get(i).toString();
                String select = "SELECT count(M.*) from fact_sent_message as M , dim_company_campaign as C \n" +
                        "where C.cam_id = M.mes_cam_id and C.cam_name = '" + aux2 + "' ";

                Statement st = conn.createStatement();
                ResultSet result = st.executeQuery( select );
                while (result.next()) {
                    n = result.getInt(1);
                    listNum.add(n);
                }
            }
        }
        catch ( SQLException e ) {
            e.printStackTrace();
            // throw new SQLException();
        } finally {
            Sql.bdClose( conn );
        }
        return listNum;
    }

// metodo para contar la cantidad de mensajes enviados por compania
    public ArrayList<Integer> CountOfMessage ( ArrayList<String> listCompany ){

        String aux2 = "" ;
        int n = 0  ;
        ArrayList<Integer> listNum = new ArrayList<>();

        try {
        for ( int i = 0 ; i < listCompany.size() ; i++ ) {

            aux2 = listCompany.get(i).toString();
            String select = "SELECT count(M.*) from fact_sent_message as M , dim_company_campaign as C \n" +
                    "where C.cam_id = M.mes_cam_id and C.com_name = '" + aux2 + "' ";

            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery( select );
            while (result.next()) {
                n = result.getInt(1);
                listNum.add(n);
            }
        }
        }
        catch ( SQLException e ) {
            e.printStackTrace();
           // throw new SQLException();
        } finally {
            Sql.bdClose( conn );
        }
        return listNum;
    }


    //Metodos con los if y los Filtros

    public Response filterOfTypeStatisticsBar(String paramDate, String paramType){

        if (paramType.equals("Compañias")){   //&& paramDate.equals(null)
            try {

                Response responseGraphCompany = getNumberOfCompanysChart();
                return responseGraphCompany ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (paramType.equals("Campañas")){
            try {
                Response responseGreaphCampaign= getNumberOfCampaignChart();
                return responseGreaphCampaign;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else {

        }

        return Response.ok( gson.toJson("Pase por filterOfTypeStatisticsBar") ).build() ;
    }

    public Response filterOfTypeStatisticsLine(String paramDate, String paramType){


        if (paramType.equals("Compañias")){
            try {

                Response responseGraphCompany = getNumberOfCompanysLine();
                return responseGraphCompany ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (paramType.equals("Campañas")){
            try {
                Response responseGreaphCampaign= getNumberOfCampaignLine();
                return responseGreaphCampaign;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else {

        }

        return Response.ok( gson.toJson("Pase por filterOfTypeStatisticsLine") ).build();
    }

    public Response filterOfTypeStatisticsPie(String paramDate, String paramType){

        if (paramType.equals("Compañias")){
            try {

                Response responseGraphCompany = getNumberOfCompanysPie();
                return responseGraphCompany ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (paramType.equals("Campañas")){
            try {
                Response responseGreaphCampaign= getNumberOfCampaignPie();
                return responseGreaphCampaign;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else {

        }

        return Response.ok( gson.toJson("Pase por filterOfTypeStatisticsPie") ).build();
    }

    @GET
    @Path("/companies")
    @Produces("application/json")
    public Response getAllCompanies() {
        String query = "SELECT DISTINCT com_id, com_name FROM dim_company_campaign ORDER BY com_id;";
        ArrayList<Company> companies = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                Company company = new Company(result.getInt("com_id"), result.getString("com_name"), "", true);
                companies.add(company);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            Sql.bdClose(conn);
        }
        return Response.ok(gson.toJson(companies)).build();
    }

    @GET
    @Path("/campaigns")
    @Produces("application/json")
    public Response getAllCampaigns() {
        String query = "SELECT DISTINCT cam_id, cam_name FROM dim_company_campaign ORDER BY cam_id;";
        ArrayList<Campaign> campaigns = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                Campaign campaign = new Campaign();
                campaign.set_idCampaign(result.getInt("cam_id"));
                campaign.set_nameCampaign(result.getString("cam_name"));
                campaigns.add(campaign);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            Sql.bdClose(conn);
        }
        return Response.ok(gson.toJson(campaigns)).build();
    }

    @GET
    @Path("/channels")
    @Produces("application/json")
    public Response getAllChannels() {
        String query = "SELECT DISTINCT cha_id, cha_name FROM dim_channel ORDER BY cha_id;";
        ArrayList<Channel> channels = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                ChannelFactory channelFactory = new ChannelFactory();
                Channel channel = channelFactory.getChannel(result.getInt("cha_id"), result.getString("cha_name"), null, null);
                channels.add(channel);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            Sql.bdClose(conn);
        }
        return Response.ok(gson.toJson(channels)).build();
    }


/////////////////////////////////////


}

