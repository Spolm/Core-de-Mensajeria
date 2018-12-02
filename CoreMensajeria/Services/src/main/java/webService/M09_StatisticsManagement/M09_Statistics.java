package webService.M09_StatisticsManagement;

import Classes.M02_Company.Company;
import Classes.M03_Campaign.Campaign;
import Classes.M05_Channel.Channel;
import Classes.M05_Channel.ChannelFactory;
import Classes.M09_Statistics.PieChart;
import Classes.M09_Statistics.SqlEstrella;
import Classes.M09_Statistics.Statistics;
import Classes.Sql;
import Exceptions.CampaignDoesntExistsException;
import Exceptions.CompanyDoesntExistsException;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.*;
import java.util.List;

enum FilterType {
    company {
        @Override
        public String value() {
            return "com_name";
        }
    },
    campaign {
        @Override
        public String value() {
            return "cam_name";
        }
    },
    channel {
        @Override
        public String value() {
            return "cha_name";
        }
    },
    integrator {
        @Override
        public String value() {
            return "com_name";
        }
    };

    public abstract String value();
}

@Path( "/M09_Statistics" )
public class M09_Statistics extends Application {

    Gson gson = new Gson();
    private Connection connStar = SqlEstrella.getConInstance();
    private Connection conn = Sql.getConInstance();

    /* ====================
            Endpoints
    ======================= */

    @GET
    @Path("/companies")
    @Produces("application/json")
    public Response getAllCompanies(@QueryParam("userId") Integer userId) {
        String query = "SELECT com_id, com_name from m02_getcompanies(" + userId + ") ORDER BY com_id;";
        try {
            return getCompanies(query);
        } catch(CompanyDoesntExistsException e) {
            return Response.serverError().build();
        } finally {
            Sql.bdClose(conn);
        }

    }

    @GET
    @Path("/campaigns")
    @Produces("application/json")
    public Response getCampaignsForCompany(@QueryParam("companyId") List<Integer> companyIds) {
        String query = "SELECT DISTINCT cam_id, cam_name FROM m09_getAllCampaigns(";
        for (int i = 0; i < companyIds.size() - 1;  i++) {
            query += companyIds.get(i) + ", ";
        }
        query += companyIds.get(companyIds.size() - 1) + ") ORDER BY cam_id;";
        try {
            return getCampaigns(query);
        } catch(CampaignDoesntExistsException e) {
            return Response.serverError().build();
        } finally {
            Sql.bdClose(connStar);
        }
    }

    @GET
    @Path("/channels")
    @Produces("application/json")
    public Response getAllChannels() {
        String query = "SELECT DISTINCT cha_id, cha_name FROM dim_channel ORDER BY cha_id;";
        ArrayList<Channel> channels = new ArrayList<>();
        try {
            Statement statement = connStar.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                ChannelFactory channelFactory = new ChannelFactory();
                Channel channel = channelFactory.getChannel(result.getInt("cha_id"), result.getString("cha_name"), null, null);
                channels.add(channel);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            Sql.bdClose(connStar);
        }
        return Response.ok(gson.toJson(channels)).build();
    }

    @GET
    @Path("/companiesCount")
    @Produces("application/json")
    public Response getCompaniesCount() {
        return getOverallCountFor(FilterType.company);
    }

    @GET
    @Path("/campaignsCount")
    @Produces("application/json")
    public Response getCampaignsCount() {
        return getOverallCountFor(FilterType.campaign);
    }

    @GET
    @Path("/channelsCount")
    @Produces("application/json")
    public Response getChannelsCount() {
        return getOverallCountFor(FilterType.channel);
    }

    public Response getOverallCountFor(FilterType filterType) {
        String query = queryForOverallCount(filterType);
        Statistics companies = new Statistics();
        try {
            Statement statement = connStar.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                companies.addX(result.getString(filterType.value()));
                companies.addY(result.getInt("messages"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            Sql.bdClose(connStar);
        }
        return Response.ok(gson.toJson(companies)).build();
    }

    public String queryForOverallCount(FilterType filterType) {
        switch (filterType) {
            case company:
                return "SELECT DISTINCT c.com_id, c.com_name, messages from dim_company_campaign c, " +
                        "(select sen_com_id, count(*) as messages from fact_sent_message " +
                        "group by sen_com_id) as m where c.com_id = m.sen_com_id ORDER BY c.com_id ASC;";
            case campaign:
                return "SELECT DISTINCT c.cam_id, c.cam_name, messages from dim_company_campaign c, " +
                        "(select sen_cam_id, count(*) as messages from fact_sent_message " +
                        "group by sen_cam_id) as m where c.cam_id = m.sen_cam_id ORDER BY c.cam_id ASC;";
            case channel:
                return "SELECT DISTINCT c.cha_id, c.cha_name, messages from dim_channel c, " +
                        "(select sen_cha_id, count(*) as messages from fact_sent_message " +
                        "group by sen_cha_id) as m where c.cha_id = m.sen_cha_id ORDER BY c.cha_id ASC;";
            default: return "";
        }
    }

    private Response getCompanies(String query) throws CompanyDoesntExistsException {
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
            throw new CompanyDoesntExistsException(e);
        } finally {
            Sql.bdClose(conn);
        }
        return Response.ok(gson.toJson(companies)).build();
    }

    public Response getCampaigns(String query) throws CampaignDoesntExistsException {
        ArrayList<Campaign> campaigns = new ArrayList<>();
        try {
            Statement statement = connStar.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                Campaign campaign = new Campaign();
                campaign.set_idCampaign(result.getInt("cam_id"));
                campaign.set_nameCampaign(result.getString("cam_name"));
                campaigns.add(campaign);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new CampaignDoesntExistsException(e);
        } finally {
            Sql.bdClose(connStar);
        }
        return Response.ok(gson.toJson(campaigns)).build();
    }

//    @GET
//    @Path("/channelLine")
//    @Produces("application/json")
//    public Response getNumberOfChannelLine() throws SQLException {
//        String aux = "";
//        String select2 = "SELECT icount, channelName as cha_name  from public.Get_ChannelName() ";
//        try {
//            Statistics gr = new Statistics();
//            ArrayList<Integer> listNum = new ArrayList<Integer>();
//            ArrayList<String> listChannel = new ArrayList<String>();
//            Statement st2 = connStar.createStatement();
//            ResultSet result2 = st2.executeQuery( select2 );
//            int num;
//            while ( result2.next() ) {
//                ChannelFactory channelFactory = new ChannelFactory();
//                Channel channel = channelFactory.getChannel(0 , result2.getString("cha_name"), null, null);
//                aux = channel.getNameChannel();
//                listChannel.add( aux ) ;
//                String rs =  result2.getString("icount" );
//                num = Integer.parseInt( rs ) ;
//                listNum.add( num ) ;
//            }
//            gr.type = "line";
//            gr.x = listChannel;
//            gr.y = listNum;
//            return Response.ok( gson.toJson( gr ) ).build();
//        } catch ( SQLException e ) {
//            e.printStackTrace();
//            throw new SQLException( select2 );
//        } finally {
//            Sql.bdClose(connStar);
//        }
//    }
//
//    public Response getNumberOfChannelChart() throws SQLException {
//        String aux = "";
//        String select2 = "SELECT icount, channelName as cha_name  from public.Get_ChannelName() ";
//        try {
//            Statistics gr = new Statistics();
//            ArrayList<Integer> listNum = new ArrayList<Integer>();
//            ArrayList<String> listChannel = new ArrayList<String>();
//            Statement st2 = connStar.createStatement();
//            ResultSet result2 = st2.executeQuery( select2 );
//            int num;
//            while ( result2.next() ) {
//                ChannelFactory channelFactory = new ChannelFactory();
//                Channel channel = channelFactory.getChannel(0 , result2.getString("cha_name"), null, null);
//                aux = channel.getNameChannel();
//                listChannel.add( aux ) ;
//                String rs =  result2.getString("icount" );
//                num = Integer.parseInt( rs ) ;
//                listNum.add( num ) ;
//            }
//            gr.type = "bar";
//            gr.x = listChannel;
//            gr.y = listNum;
//            return Response.ok( gson.toJson( gr ) ).build();
//        } catch ( SQLException e ) {
//            e.printStackTrace();
//            throw new SQLException( select2 );
//        } finally {
//            Sql.bdClose(connStar);
//        }
//    }
//
//    public Response getNumberOfChannelPie() throws SQLException {
//        String aux = "";
//        String select2 = "SELECT icount, channelName as cha_name  from public.Get_ChannelName() ";
//        try {
//            PieChart PieC = new PieChart();
//            ArrayList<Integer> listNum = new ArrayList<Integer>();
//            ArrayList<String> listlabels = new ArrayList<String>();
//            int num ;
//            Statement st2 = connStar.createStatement();
//            ResultSet result2 = st2.executeQuery(select2);
//            while ( result2.next() ) {
//                ChannelFactory channelFactory = new ChannelFactory();
//                Channel channel = channelFactory.getChannel(0 , result2.getString("cha_name"), null, null);
//                aux = channel.getNameChannel();
//                listlabels.add( aux ) ;
//                String rs =  result2.getString("icount" );
//                num = Integer.parseInt( rs ) ;
//                listNum.add( num ) ;
//            }
//            PieC.type = "pie";
//            PieC.labels = listlabels;
//            PieC.values = listNum;
//            return Response.ok( gson.toJson( PieC ) ).build();
//        } catch ( SQLException e ) {
//            e.printStackTrace();
//            throw new SQLException( select2 );
//        } finally {
//            Sql.bdClose(connStar);
//        }
//    }

    @GET
    @Path("/filters")
    @Produces("application/json")
    public Response getStatistics(@QueryParam("companyId") List<Integer> companyIds,
                                  @QueryParam("campaignId") List<Integer> campaignIds,
                                  @QueryParam("channelId") List<Integer> channelIds) {
        String companyin = setParametersforQuery(companyIds,"and me.sen_com_id in ");
        String campaignin = setParametersforQuery(campaignIds,"and me.sen_cam_id in ");
        String channelin = setParametersforQuery(channelIds,"and me.sen_cha_id in ");
        Map<String, Statistics> stats = new HashMap<String, Statistics>();
        try {
            Statement st = connStar.createStatement();
            if (!companyIds.isEmpty()) {
                stats.put("companies", getMessagesParam(companyin, campaignin, channelin, "me.sen_com_id", "co.com_name",
                        "public.dim_company_campaign co", "co.com_id", st));
                //stats.add();
            }
            if (!campaignIds.isEmpty()) {
                stats.put("campaigns", getMessagesParam(companyin, campaignin, channelin, "me.sen_cam_id", "ca.cam_name",
                        "public.dim_company_campaign ca", "ca.cam_id", st));
                //stats.add();
            }
            if (!channelIds.isEmpty()) {
                stats.put("channels", getMessagesParam(companyin, campaignin, channelin, "me.sen_cha_id", "ch.cha_name",
                        "public.dim_channel ch", "ch.cha_id", st));
                //stats.add();
            }
            if (channelIds.isEmpty() && campaignIds.isEmpty() && companyIds.isEmpty()){
                return Response.status(400).entity("{ \"Mensaje\": \"Debe enviar al menos un parametro\" }").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         finally {
            Sql.bdClose(connStar);
        }
        return Response.ok(gson.toJson(stats)).build();
    }

    public Statistics getMessagesParam(String companyIds, String campaignIds, String channelIds, String param1, String param2, String param3,
                                       String param4, Statement st){
        int num;
        String name;
        ArrayList<String> listName = new ArrayList<>();
        ArrayList<Integer> listNum = new ArrayList<>();
        Statistics gr = new Statistics();
        try {
            String select = "SELECT icount, paramName FROM m09_get_MessageParameter('"+ companyIds + "','" + campaignIds + "','" +
                    channelIds + "','" + param1 + "','" + param2 + "','" + param3 + "','" + param4 + "')";
            ResultSet result = st.executeQuery( select );
            while ( result.next() ) {
                num = result.getInt("icount");
                name = result.getString("paramName");
                listNum.add( num );
                listName.add( name );
                gr.x = listName;
                gr.y = listNum;
            }
        }
        catch ( SQLException e ) {
            e.printStackTrace();
            // throw new SQLException();
        }
        return gr;
    }

    public String setParametersforQuery(List<Integer> ids, String params){
        if (ids.isEmpty()) {
            return "";
        }
        params = params.concat("(");
        for(int i=0;i<ids.size();i++){
            params = params.concat(ids.get(i).toString());
            if (i == ids.size()-1){
                params = params.concat(")");
            }
            else{
                params = params.concat(",");
            }
        }
        return params;
    }

}

