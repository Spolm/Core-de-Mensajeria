package webService.M09_StatisticsManagement;

import Classes.M02_Company.Company;
import Classes.M03_Campaign.Campaign;
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

        if (paramType.equals("Compañias")){
            try {

                Response responseGraphCompany = getNumberOfCompanysLine();
                return responseGraphCompany ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (paramType.equals("Campañas")){

        }

        else {

        }

        return Response.ok( gson.toJson(" mi fecha es " + paramDate+ " mi seleccion es "+ paramType) ).build();



        // String squery ="select * from producto where id="@id;
        //squery=squery.replace("@id","1");
    }

    @GET
    @Path("/PruebaParam2")
    @Produces("application/json")

    public Response test1(@QueryParam( "paramDate" ) String paramDate,
                          @QueryParam( "paramType" ) String paramType) {

        if (paramType.equals("Compañias")){
            try {

                Response responseGraphCompany = getNumberOfCompanysPie();
                return responseGraphCompany ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (paramType.equals("Campañas")){

        }

        else {

        }

        return Response.ok( gson.toJson(" mi fecha es " + paramDate+ " mi seleccion es "+ paramType) ).build();



        // String squery ="select * from producto where id="@id;
        //squery=squery.replace("@id","1");
    }

    @GET
    @Path("/PruebaParam3")
    @Produces("application/json")

    public Response test3(@QueryParam( "paramDate" ) String paramDate,
                          @QueryParam( "paramType" ) String paramType) {

        if (paramType.equals("Compañias")){
            try {

                Response responseGraphCompany = getNumberOfCompanysChart();
                return responseGraphCompany ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (paramType.equals("Campañas")){

        }

        else {

        }

        return Response.ok( gson.toJson(" mi fecha es " + paramDate+ " mi seleccion es "+ paramType) ).build();



        // String squery ="select * from producto where id="@id;
        //squery=squery.replace("@id","1");
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

