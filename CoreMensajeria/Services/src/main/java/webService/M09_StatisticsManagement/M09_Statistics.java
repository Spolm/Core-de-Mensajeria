package webService.M09_StatisticsManagement;

import DTO.DTO;
import DTO.M03_DTO.DTOCampaignWithOut_Company;
import DTO.M09_DTO.DTOStatistic;
import Entities.Entity;
import Entities.M05_Channel.Channel;
import Exceptions.CampaignDoesntExistsException;
import Exceptions.ChannelNotFoundException;
import Exceptions.CompanyDoesntExistsException;
import Exceptions.StatisticParametersNotFoundException;
import Logic.Command;
import Logic.CommandsFactory;
import Logic.M09_Statistics.*;
import Mappers.CampaignMapper.MapperCampaignWithOut_Company;
import Mappers.CompanyMapper.MapperCompanyWithOut_Link;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Path( "/M09_Statistics" )
public class M09_Statistics extends Application {

    Gson gson = new Gson();
    private GenericMapper mapper;
    private DTO dto;

    /* ====================
            Endpoints
    ======================= */

    @GET
    @Path("/companies")
    @Produces("application/json")
    public Response getAllCompanies(@QueryParam("userId") Integer userId) {
        Command<ArrayList<Entity>> command = CommandsFactory.getAllCompaniesByUserCommand(userId);
        try {
            List<DTO> dto;
            mapper = MapperFactory.CreateMapperCompanyWithOut_Link();
            command.execute();
            dto = mapper.CreateDtoList(command.Return());
            return Response.ok(gson.toJson(dto)).build();
        } catch(CompanyDoesntExistsException e) {
            return Response.serverError().build();
        }catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/campaigns")
    @Produces("application/json")
    public Response getCampaignsForCompany(@QueryParam("companyId") List<Integer> companyIds) {
        Command<ArrayList<Entity>> command = CommandsFactory.getCampaignsForCompanyCommand(companyIds);
        try {
            List<DTO> dto;
            mapper = MapperFactory.CreateMapperCampaignWithOut_Company();
            command.execute();
            dto = mapper.CreateDtoList(command.Return());
            return Response.ok(gson.toJson(dto)).build();
        } catch(CampaignDoesntExistsException e) {
            return Response.serverError().build();
        }catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/channels")
    @Produces("application/json")
    public Response getAllChannels() {
        Command<ArrayList<Channel>> command = CommandsFactory.getAllChannelsCommand();
        try {
            command.execute();
            return Response.ok(gson.toJson(command.Return())).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/integrators")
    @Produces("application/json")
    public Response getIntegratorsForChannel(@QueryParam("channelId") List<Integer> channelIds) {
        Command command = CommandsFactory.getIntegratorsForChannelCommand(channelIds);
        try {
            command.execute();
            return Response.ok(gson.toJson(
                    command.Return()
            )).build();
        } catch (ChannelNotFoundException e) {
            return Response.serverError().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/companiesCount")
    @Produces("application/json")
    public Response getCompaniesCount() {
        Command command = CommandsFactory.getCompanyStatisticCommand();
        return getStadisticCount(command);
    }

    @GET
    @Path("/campaignsCount")
    @Produces("application/json")
    public Response getCampaignsCount() {
        Command command = CommandsFactory.getCampaignStatisticCommand();
        return getStadisticCount(command);
    }

    @GET
    @Path("/channelsCount")
    @Produces("application/json")
    public Response getChannelsCount() {
        Command command = CommandsFactory.getChannelStatisticCommand();
        return getStadisticCount(command);
    }

    @GET
    @Path("/integratorsCount")
    @Produces("application/json")
    public Response getIntegratosCount() {
        Command command = CommandsFactory.getIntegratorStatisticCommand();
        return getStadisticCount(command);
    }

    private Response getStadisticCount(Command<Entity> command) {
        try {
            GenericMapper<DTOStatistic> mapper;
            command.execute();
            mapper = MapperFactory.createStatisticMapper();
            dto = mapper.CreateDto(command.Return());
            return Response.ok(gson.toJson(dto)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/update")
    @Produces("application/json")
    public Response updateStarSchema() {
        Command command = CommandsFactory.updateStarSchema();
        try {
            command.execute();
            return Response.ok().build();
        } catch(SQLException e) {
            return Response.serverError().build();
        } catch(Exception e) {
            return Response.serverError().build();
        }
    }

    //Endpoint que Devuelve Todos los Años donde ha habido envío de mensajes
    @GET
    @Path("/yearsCount")
    @Produces("application/json")
    public Response getYears(){
        Command command = CommandsFactory.getYears();
        try{
            command.execute();
            return Response.ok(gson.toJson(command.Return())).build();
        } catch(Exception e) {
            return Response.serverError().build();
        }
    }

    //Endpoint que Devuelve Todos los Meses donde ha habido envío de mensajes
    @GET
    @Path("/monthsCount")
    @Produces("application/json")
    public Response getMonths(){
        Command command = CommandsFactory.getMonths();
        try{
            command.execute();
            return Response.ok(gson.toJson(command.Return())).build();
        } catch(Exception e) {
            return Response.serverError().build();
        }
    }

    //Endpoint que Devuelve Todos los días de la semana donde ha habido envío de mensajes
    @GET
    @Path("/daysofweekCount")
    @Produces("application/json")
    public Response getDaysofWeek(){
        Command command = CommandsFactory.getDaysofWeek();
        try {
            command.execute();
            return Response.ok(gson.toJson(command.Return())).build();
        }  catch (Exception e){
            return Response.serverError().build();
        }
    }

    //Endpoint que Devuelve Todos los días del mes donde ha habido envío de mensajes
    @GET
    @Path("/daysofmonthCount")
    @Produces("application/json")
    public Response getDaysofMonth(){
        Command command = CommandsFactory.getDaysofMonth();
        try {
            command.execute();
            return Response.ok(gson.toJson(command.Return())).build();
        }  catch (Exception e){
            return Response.serverError().build();
        }
    }

    //Endpoint que Devuelve Todos los días del año donde ha habido envío de mensajes
    @GET
    @Path("/daysofyearCount")
    @Produces("application/json")
    public Response getDaysofYear(){
        Command command = CommandsFactory.getDaysofYear();
        try {
            command.execute();
            return Response.ok(gson.toJson(command.Return())).build();
        }  catch (Exception e){
            return Response.serverError().build();
        }
    }

    //Endpoint que Devuelve Todas las semanas del año donde ha habido envío de mensajes
    @GET
    @Path("/weeksofyearCount")
    @Produces("application/json")
    public Response getWeeksofYear(){
        Command command = CommandsFactory.getWeeksofYear();
        try {
            command.execute();
            return Response.ok(gson.toJson(command.Return())).build();
        }  catch (Exception e){
            return Response.serverError().build();
        }
    }

    //Endpoint que devuelve los cuartos del año
    @GET
    @Path("/quartersofyearCount")
    @Produces("application/json")
    public Response getQuartersofYear(){
        Command command = CommandsFactory.getQuartersofYear();
        try {
            command.execute();
            return Response.ok(gson.toJson(command.Return())).build();
        }  catch (Exception e){
            return Response.serverError().build();
        }
    }

    //Endpoint que devuelve las horas del día donde se han enviado mensaje
    @GET
    @Path("/hoursCount")
    @Produces("application/json")
    public Response getHours(){
        Command command = CommandsFactory.getHours();
        try {
            command.execute();
            return Response.ok(gson.toJson(command.Return())).build();
        }  catch (Exception e){
            return Response.serverError().build();
        }
    }

    //Endpoint que devuelve los minutos donde se han enviado mensaje
    @GET
    @Path("/minutesCount")
    @Produces("application/json")
    public Response getMinutes(){
        Command command = CommandsFactory.getMinutes();
        try {
            command.execute();
            return Response.ok(gson.toJson(command.Return())).build();
        }  catch (Exception e){
            return Response.serverError().build();
        }
    }

    //Endpoint que devuelve los segundos donde se han enviado mensaje
    @GET
    @Path("/secondsCount")
    @Produces("application/json")
    public Response getSeconds(){
        Command command = CommandsFactory.getSeconds();
        try {
            command.execute();
            return Response.ok(gson.toJson(command.Return())).build();
        }  catch (Exception e){
            return Response.serverError().build();
        }
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
        Command command = CommandsFactory.getStatisticCommand(companyIds, campaignIds, channelIds,
                                                                            integratorIds, yearIds, monthIds,
                                                                            dayofweekIds, weekofyearIds, dayofmonthIds,
                                                                            dayofyearIds, hourofdayIds, minuteofhourIds,
                                                                            secondofminuteIds, quarterIds);
        try {
            command.execute();
            return Response.ok(gson.toJson(
                    command.Return()
            )).build();
        } catch (StatisticParametersNotFoundException e) {
            return Response.status(400).entity("{ \"Mensaje\": \"Debe enviar al menos un parametro\" }").build();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

