package webService.M09_StatisticsManagement;

import DTO.DTO;
import DTO.M09_DTO.DTOStatistic;
import Entities.Entity;
import Entities.M05_Channel.Channel;
import Exceptions.CampaignDoesntExistsException;
import Exceptions.ChannelNotFoundException;
import Exceptions.CompanyDoesntExistsException;
import Exceptions.M09_Statistic.StatisticParametersNotFoundException;
import Logic.Command;
import Logic.CommandsFactory;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import webService.M01_Login.Error;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Path( "/M09_Statistics" )
public class M09_Statistics extends Application {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private GenericMapper mapper;
    private DTO dto;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    /* ====================
            Endpoints
    ======================= */

    @GET
    @Path("/companies")
    @Produces("application/json")
    public Response getAllCompanies(@QueryParam("userId") Integer userId) {
        log.debug("Entrado al metodo getAllCompanies(" + userId + ")");
        Response response;
        Error error;
        Command<ArrayList<Entity>> command = CommandsFactory.getAllCompaniesByUserCommand(userId);
        try {
            List<DTO> dto;
            mapper = MapperFactory.CreateMapperCompanyWithOut_Link();
            command.execute();
            dto = mapper.CreateDtoList(command.Return());
            log.info("Se ejecuto el metodo getAllCompanies(" + userId + ") exitosamente");
            response = Response.ok(gson.toJson(dto)).build();
        } catch(CompanyDoesntExistsException e) {
            e.printStackTrace();
            error = new Error(e.ERROR_MSG);
            error.addError("Excepcion", e.ERROR_MSG);
            log.error("El metodo getAllCompanies(" + userId + ") arrojo la excepcion: " + e.ERROR_MSG);
            response = Response.status(204).entity(error).build();
        }catch (Exception e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getAllCompanies(" + userId + ") arrojo la excepcion: " + e.getMessage());
            response = Response.status(500).entity(error).build();
        }
        log.debug("Saliendo del metodo getAllCompanies(" + userId + ") con retorno " + response.getEntity().toString());
        return response;
    }

    @GET
    @Path("/campaigns")
    @Produces("application/json")
    public Response getCampaignsForCompany(@QueryParam("companyId") List<Integer> companyIds) {
        log.debug("Entrado al metodo getCampaignsForCompany(" + companyIds + ")");
        Response response;
        Error error;
        Command<ArrayList<Entity>> command = CommandsFactory.getCampaignsForCompanyCommand(companyIds);
        try {
            List<DTO> dto;
            mapper = MapperFactory.CreateMapperCampaignWithOut_Company();
            command.execute();
            dto = mapper.CreateDtoList(command.Return());
            response =  Response.ok(gson.toJson(dto)).build();
            log.info("Se ejecuto el metodo getCampaignsForCompany(" + companyIds + ") exitosamente");
        } catch(CampaignDoesntExistsException e) {
            e.printStackTrace();
            error = new Error(e.ERROR_MSG);
            error.addError("Excepcion", e.ERROR_MSG);
            log.error("El metodo getCampaignsForCompany(" + companyIds + ") arrojo la excepcion: " + e.ERROR_MSG);
            response =  Response.serverError().build();
        }catch (Exception e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getCampaignsForCompany(" + companyIds + ") arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Saliendo del metodo getCampaignsForCompany(" + companyIds + ") con retorno " + response.getEntity().toString());
        return response;
    }

    @GET
    @Path("/channels")
    @Produces("application/json")
    public Response getAllChannels() {
        log.debug("Entrado al metodo getAllChannels()");
        Response response;
        Error error;
        Command<ArrayList<Channel>> command = CommandsFactory.getAllChannelsCommand();
        try {
            command.execute();
            response = Response.ok(gson.toJson(command.Return())).build();
            log.info("Se ejecuto el metodo getAllChannels() exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getAllChannels() arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Saliendo del metodo getAllChannels() con retorno " + response.getEntity().toString());
        return response;
    }

    @GET
    @Path("/integrators")
    @Produces("application/json")
    public Response getIntegratorsForChannel(@QueryParam("channelId") List<Integer> channelIds) {
        log.debug("Entrado al metodo getIntegratorsForChannel(" + channelIds + ")");
        Response response;
        Error error;
        Command command = CommandsFactory.getIntegratorsForChannelCommand(channelIds);
        try {
            command.execute();
            response = Response.ok(gson.toJson(
                    command.Return()
            )).build();
            log.info("Se ejecuto el metodo getIntegratorsForChannel(" + channelIds + ") exitosamente");
        } catch (ChannelNotFoundException e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getIntegratorsForChannel(" + channelIds + ") arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getIntegratorsForChannel(" + channelIds + ") arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Saliendo del metodo getIntegratorsForChannel(" + channelIds + ") con retorno " + response.getEntity().toString());
        return response;
    }

    @GET
    @Path("/companiesCount")
    @Produces("application/json")
    public Response getCompaniesCount() {
        log.debug("Entrado al metodo getCompaniesCount()");
        Command command = CommandsFactory.getCompanyStatisticCommand();
        return getStadisticCount(command, "getCompaniesCount()");
    }

    @GET
    @Path("/campaignsCount")
    @Produces("application/json")
    public Response getCampaignsCount() {
        log.debug("Entrado al metodo getCampaignsCount()");
        Command command = CommandsFactory.getCampaignStatisticCommand();
        return getStadisticCount(command, "getCampaignsCount()");
    }

    @GET
    @Path("/channelsCount")
    @Produces("application/json")
    public Response getChannelsCount() {
        log.debug("Entrado al metodo getChannelsCount()");
        Command command = CommandsFactory.getChannelStatisticCommand();
        return getStadisticCount(command, "getChannelsCount()");
    }

    @GET
    @Path("/integratorsCount")
    @Produces("application/json")
    public Response getIntegratosCount() {
        log.debug("Entrado al metodo getIntegratosCount()");
        Command command = CommandsFactory.getIntegratorStatisticCommand();
        return getStadisticCount(command, "getIntegratosCount()");
    }

    private Response getStadisticCount(Command<Entity> command, String method) {
        Response response;
        Error error;
        try {
            GenericMapper<DTOStatistic> mapper;
            command.execute();
            mapper = MapperFactory.createStatisticMapper();
            dto = mapper.CreateDto(command.Return());
            response = Response.ok(gson.toJson(dto)).build();
            log.info("Se ejecuto el metodo " + method + " exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            response = Response.serverError().build();
            log.error("El metodo " + method + " arrojo la excepcion: " + e.getMessage());
        }
            log.debug("Saliendo del metodo " + method + " con retorno " + response.getEntity().toString());
            return response;
    }

    @GET
    @Path("/update")
    @Produces("application/json")
    public Response updateStarSchema() {
        log.debug("Entrando al metodo updateStarSchema()");
        Response response;
        Error error;
        Command command = CommandsFactory.updateStarSchemaCommand();
        try {
            command.execute();
            response = Response.ok().build();
            log.info("Se ejecuto el metodo updateStarSchema() exitosamente");
        } catch(Exception e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo updateStarSchema() arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Saliendo del metodo updateStarSchema()");
        return response;
    }

    //Endpoint que Devuelve Todos los Años donde ha habido envío de mensajes
    @GET
    @Path("/yearsCount")
    @Produces("application/json")
    public Response getYears(){
        log.debug("Entrando al metodo getYears()");
        Response response;
        Error error;
        Command command = CommandsFactory.getYearsCommand();
        try{
            command.execute();
            response = Response.ok(gson.toJson(command.Return())).build();
            log.info("Se ejecuto el metodo getYears() exitosamente");
        } catch(Exception e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getYears() arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Saliendo del metodo getYears() con retorno " + response.getEntity().toString());
        return response;
    }

    //Endpoint que Devuelve Todos los Meses donde ha habido envío de mensajes
    @GET
    @Path("/monthsCount")
    @Produces("application/json")
    public Response getMonths(){
        log.debug("Entrando al metodo getMonths()");
        Response response;
        Error error;
        Command command = CommandsFactory.getMonthsCommand();
        try{
            command.execute();
            response = Response.ok(gson.toJson(command.Return())).build();
            log.info("Se ejecuto el metodo getMonths() exitosamente");
        } catch(Exception e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getMonths() arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Saliendo del metodo getMonths() con retorno " + response.getEntity().toString());
        return response;
    }

    //Endpoint que Devuelve Todos los días de la semana donde ha habido envío de mensajes
    @GET
    @Path("/daysofweekCount")
    @Produces("application/json")
    public Response getDaysofWeek(){
        log.debug("Entrando al metodo getDaysofWeek()");
        Response response;
        Error error;
        Command command = CommandsFactory.getDaysofWeekCommand();
        try {
            command.execute();
            response = Response.ok(gson.toJson(command.Return())).build();
            log.info("Se ejecuto el metodo getDaysofWeek() exitosamente");
        }  catch (Exception e){
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getDaysofWeek() arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Saliendo del metodo getDaysofWeek() con retorno " + response.getEntity().toString());
        return response;
    }

    //Endpoint que Devuelve Todos los días del mes donde ha habido envío de mensajes
    @GET
    @Path("/daysofmonthCount")
    @Produces("application/json")
    public Response getDaysofMonth(){
        log.debug("Entrando al metodo getDaysofMonth()");
        Response response;
        Error error;
        Command command = CommandsFactory.getDaysofMonthCommand();
        try {
            command.execute();
            response = Response.ok(gson.toJson(command.Return())).build();
            log.info("Se ejecuto el metodo getDaysofMonth() exitosamente");
        }  catch (Exception e){
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getDaysofMonth() arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Saliendo del metodo getDaysofMonth() con retorno " + response.getEntity().toString());
        return response;
    }

    //Endpoint que Devuelve Todos los días del año donde ha habido envío de mensajes
    @GET
    @Path("/daysofyearCount")
    @Produces("application/json")
    public Response getDaysofYear(){
        log.debug("Entrando al metodo getDaysofYear()");
        Response response;
        Error error;
        Command command = CommandsFactory.getDaysofYearCommand();
        try {
            command.execute();
            response = Response.ok(gson.toJson(command.Return())).build();
            log.info("Se ejecuto el metodo getDaysofYear() exitosamente");
        }  catch (Exception e){
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getDaysofYear() arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Saliendo del metodo getDaysofYear() con retorno " + response.getEntity().toString());
        return response;
    }

    //Endpoint que Devuelve Todas las semanas del año donde ha habido envío de mensajes
    @GET
    @Path("/weeksofyearCount")
    @Produces("application/json")
    public Response getWeeksofYear(){
        log.debug("Entrando al metodo getWeeksofYear()");
        Response response;
        Error error;
        Command command = CommandsFactory.getWeeksofYearCommand();
        try {
            command.execute();
            response = Response.ok(gson.toJson(command.Return())).build();
            log.info("Se ejecuto el metodo getWeeksofYear() exitosamente");
        }  catch (Exception e){
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getWeeksofYear() arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Saliendo del metodo getWeeksofYear() con retorno " + response.getEntity().toString());
        return response;
    }

    //Endpoint que devuelve los cuartos del año
    @GET
    @Path("/quartersofyearCount")
    @Produces("application/json")
    public Response getQuartersofYear(){
        log.debug("Entrando al metodo getQuartersofYear()");
        Response response;
        Error error;
        Command command = CommandsFactory.getQuartersofYearCommand();
        try {
            command.execute();
            response = Response.ok(gson.toJson(command.Return())).build();
            log.info("Se ejecuto el metodo getQuartersofYear() exitosamente");
        }  catch (Exception e){
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getQuartersofYear() arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Saliendo del metodo getQuartersofYear() con retorno " + response.getEntity().toString());
        return response;
    }

    //Endpoint que devuelve las horas del día donde se han enviado mensaje
    @GET
    @Path("/hoursCount")
    @Produces("application/json")
    public Response getHours(){
        log.debug("Entrando al metodo getHours()");
        Response response;
        Error error;
        Command command = CommandsFactory.getHoursCommand();
        try {
            command.execute();
            response = Response.ok(gson.toJson(command.Return())).build();
            log.info("Se ejecuto el metodo getHours() exitosamente");
        }  catch (Exception e){
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getHours() arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Saliendo del metodo getHours() con retorno " + response.getEntity().toString());
        return response;
    }

    //Endpoint que devuelve los minutos donde se han enviado mensaje
    @GET
    @Path("/minutesCount")
    @Produces("application/json")
    public Response getMinutes(){
        log.debug("Entrando al metodo getMinutes()");
        Response response;
        Error error;
        Command command = CommandsFactory.getMinutesCommand();
        try {
            command.execute();
            response = Response.ok(gson.toJson(command.Return())).build();
            log.info("Se ejecuto el metodo getMinutes() exitosamente");
        }  catch (Exception e){
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getMinutes() arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Saliendo del metodo getMinutes() con retorno " + response.getEntity().toString());
        return response;
    }

    //Endpoint que devuelve los segundos donde se han enviado mensaje
    @GET
    @Path("/secondsCount")
    @Produces("application/json")
    public Response getSeconds(){
        log.debug("Entrando al metodo getSeconds()");
        Response response;
        Error error;
        Command command = CommandsFactory.getSecondsCommand();
        try {
            command.execute();
            response = Response.ok(gson.toJson(command.Return())).build();
            log.info("Se ejecuto el metodo getSeconds() exitosamente");
        }  catch (Exception e){
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getSeconds() arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Saliendo del metodo getSeconds() con retorno " + response.getEntity().toString());
        return response;
    }

    //Endpoint que devuelve la cantidad de Mensajes según los filtros enviados(compañia, campaña, canal, integrador, tiempo)
    @GET
    @Path("/filters")
    @Produces("application/json")
    public Response getStatistics(@QueryParam("companyId") List<Integer> companyIds,
                                  @QueryParam("campaignId") List<Integer> campaignIds,
                                  @QueryParam("channelId") List<Integer> channelIds,
                                  @QueryParam("integratorId") List<Integer> integratorIds,
                                  @QueryParam("yearId") List<Integer> yearIds,
                                  @QueryParam("monthId") List<Integer> monthIds,
                                  @QueryParam("dayofweekId") List<Integer> dayofweekIds,
                                  @QueryParam("weekofyearId") List<Integer> weekofyearIds,
                                  @QueryParam("dayofmonthId") List<Integer> dayofmonthIds,
                                  @QueryParam("dayofyearId") List<Integer> dayofyearIds,
                                  @QueryParam("hourId") List<Integer> hourofdayIds,
                                  @QueryParam("minuteId") List<Integer> minuteofhourIds,
                                  @QueryParam("secondId") List<Integer> secondofminuteIds,
                                  @QueryParam("quarterId") List<Integer> quarterIds)
    {
        log.debug("Entrando al metodo getStatistics(" + companyIds + "," + campaignIds + "," + channelIds + ","
                + integratorIds + "," + yearIds + "," + monthIds + "," + dayofweekIds + "," + weekofyearIds + ","
                + dayofmonthIds + "," + dayofyearIds + "," + hourofdayIds + "," + minuteofhourIds + ","
                + secondofminuteIds + "," + quarterIds + ")");
        Response response;
        Error error;
        Command command = CommandsFactory.getStatisticCommand(companyIds, campaignIds, channelIds,
                                                                            integratorIds, yearIds, monthIds,
                                                                            dayofweekIds, weekofyearIds, dayofmonthIds,
                                                                            dayofyearIds, hourofdayIds, minuteofhourIds,
                                                                            secondofminuteIds, quarterIds);
        try {
            command.execute();
            response = Response.ok(gson.toJson(
                    command.Return()
            )).build();
            log.error("Se ejecuto el metodo getStatistics(" + companyIds + "," + campaignIds + "," + channelIds + ","
                    + integratorIds + "," + yearIds + "," + monthIds + "," + dayofweekIds + "," + weekofyearIds + ","
                    + dayofmonthIds + "," + dayofyearIds + "," + hourofdayIds + "," + minuteofhourIds + ","
                    + secondofminuteIds + "," + quarterIds + ") exitosamente");
        } catch (StatisticParametersNotFoundException e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getStatistics(" + companyIds + "," + campaignIds + "," + channelIds + ","
                    + integratorIds + "," + yearIds + "," + monthIds + "," + dayofweekIds + "," + weekofyearIds + ","
                    + dayofmonthIds + "," + dayofyearIds + "," + hourofdayIds + "," + minuteofhourIds + ","
                    + secondofminuteIds + "," + quarterIds + ") arrojo la excepcion: " + e.getMessage());
            response = Response.status(400).entity("{ \"Mensaje\": \"Debe enviar al menos un parametro\" }").build();
        } catch (Exception e){
            e.printStackTrace();
            error = new Error(e.getMessage());
            error.addError("Excepcion", e.getMessage());
            log.error("El metodo getStatistics(" + companyIds + "," + campaignIds + "," + channelIds + ","
                    + integratorIds + "," + yearIds + "," + monthIds + "," + dayofweekIds + "," + weekofyearIds + ","
                    + dayofmonthIds + "," + dayofyearIds + "," + hourofdayIds + "," + minuteofhourIds + ","
                    + secondofminuteIds + "," + quarterIds + ") arrojo la excepcion: " + e.getMessage());
            response = Response.serverError().build();
        }
        log.debug("Entrando al metodo getStatistics(" + companyIds + "," + campaignIds + "," + channelIds + ","
                + integratorIds + "," + yearIds + "," + monthIds + "," + dayofweekIds + "," + weekofyearIds + ","
                + dayofmonthIds + "," + dayofyearIds + "," + hourofdayIds + "," + minuteofhourIds + ","
                + secondofminuteIds + "," + quarterIds + ") con retorno: " + response.getEntity().toString());
        return response;
    }
}

