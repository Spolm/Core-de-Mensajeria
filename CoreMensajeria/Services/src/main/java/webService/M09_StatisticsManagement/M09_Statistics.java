package webService.M09_StatisticsManagement;

import Classes.M02_Company.Company;
import Classes.M03_Campaign.Campaign;
import Classes.M09_Statistics.PieChart;
import Classes.M09_Statistics.SqlEstrella;
import Classes.M09_Statistics.Statistics;
import Classes.Sql;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    //private Connection conn = Sql.getConInstance();
    private Connection conn = SqlEstrella.getConInstance();


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

                co.set_idCompany( result.getInt("com_id" ) );
                co.set_name( result.getString("com_name" ) );
                co.set_desc( result.getString("com_description" ) );
                co.set_status( result.getBoolean("com_status" ) );

                companyList.add(co);
            }
            return Response.ok( gson.toJson( companyList ) ).build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException( select );
        } finally {
            Sql.bdClose( conn );
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

                ca.set_idCampaign( result.getInt("cam_id" ) );
                ca.set_nameCampaign( result.getString("cam_name" ) );
                ca.set_descCampaign( result.getString("cam_description" ) );
                ca.set_statusCampaign( result.getBoolean("cam_status" ) );
                ca.set_startCampaign( result.getTimestamp("cam_start_date" ) );
                ca.set_endCampaign( result.getTimestamp("cam_end_date" ) );
                co.set_idCompany( result.getInt("com_id" ) );
                co.set_name( result.getString("com_name" ) );
                co.set_status( result.getBoolean("com_status" ) );
                co.set_desc( result.getString("com_descrption" ) );
                //companyList.add(co);
                campaignList.add( ca );
            }
            return Response.ok( gson.toJson( campaignList )).build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException( select );
        } finally {
            Sql.bdClose(conn);
        }
    }


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



    public ArrayList<Integer> CountOfMessage ( ArrayList<String> listCompany ){

        String aux2 = "" ;
        int n = 0  ;
        ArrayList<Integer> listNum = new ArrayList<>();

        try {
        for ( int i = 0 ; i < listCompany.size() ; i++ ) {

            aux2 = listCompany.get(i).toString();
            String select = "SELECT count(M.*) from fact_message as M , dim_company_campaign as C \n" +
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


}

