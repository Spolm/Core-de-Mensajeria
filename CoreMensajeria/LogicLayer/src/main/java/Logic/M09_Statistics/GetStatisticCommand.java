package Logic.M09_Statistics;

import Entities.Entity;
import Exceptions.M09_Statistic.StatisticParametersNotFoundException;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAO_StatisticEstrella;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetStatisticCommand extends Command<Map<String, Entity>> {
    IDAO_StatisticEstrella dao;
    String companyin;
    String campaignin;
    String channelin;
    String integratorin;
    String yearin;
    String monthin;
    String dayofweekin;
    String weekofyearin;
    String dayofmonthin;
    String dayofyearin;
    String hourin;
    String minutein;
    String secondin;
    String quarterin;
    Map<String, Entity> stats;
    Boolean flagCompany = false;
    Boolean flagCampaign = false;
    Boolean flagChannel = false;
    Boolean flagIntegrator = false;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    public GetStatisticCommand(List<Integer> companyIds, List<Integer> campaignIds, List<Integer> channelIds,
                               List<Integer> integratorIds, List<Integer> yearIds, List<Integer> monthIds,
                               List<Integer> dayofweekIds, List<Integer> weekofyearIds, List<Integer> dayofmonthIds,
                               List<Integer> dayofyearIds, List<Integer> hourofdayIds, List<Integer> minuteofhourIds,
                               List<Integer> secondofminuteIds, List<Integer> quarterIds){
        companyin = setParametersforQueryCompany(companyIds);
        campaignin = setParametersforQueryCampaign(campaignIds);
        channelin = setParametersforQueryChannel(channelIds);
        integratorin = setParametersforQueryIntegrator(integratorIds);
        yearin = setParametersforQueryYear(yearIds);
        monthin = setParametersforQueryMonth(monthIds);
        dayofweekin = setParametersforQueryDayWeek(dayofweekIds);
        weekofyearin = setParametersforQueryWeekYear(weekofyearIds);
        dayofmonthin = setParametersforQueryDayMonth(dayofmonthIds);
        dayofyearin = setParametersforQueryDayYear(dayofyearIds);
        hourin = setParametersforQueryHour(hourofdayIds);
        minutein = setParametersforQueryMinute(minuteofhourIds);
        secondin = setParametersforQuerySeconds(secondofminuteIds);
        quarterin = setParametersforQueryQuarter(quarterIds);
        stats = new HashMap<String, Entity>();
        if (!companyIds.isEmpty()) { flagCompany = true; }
        if (!campaignIds.isEmpty()) { flagCampaign = true;}
        if (!channelIds.isEmpty()) { flagChannel = true; }
        if (!integratorIds.isEmpty()) { flagIntegrator = true; }
    }

    @Override
    public void execute() throws StatisticParametersNotFoundException {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de GetStatisticCommand" );
        //endregion
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        dao = factory.instanciateDaoStatisticsEstrella();
        if (flagCompany) {
            stats.put("companies", dao.getMessagesParamCompany(companyin, campaignin, channelin, integratorin, yearin, monthin, dayofweekin,
                    weekofyearin, dayofmonthin, dayofyearin, hourin, minutein, secondin, quarterin));
            //stats.add();
        }
        if (flagCampaign) {
            stats.put("campaigns", dao.getMessagesParamCampaign(companyin, campaignin, channelin, integratorin, yearin, monthin, dayofweekin,
                    weekofyearin, dayofmonthin, dayofyearin, hourin, minutein, secondin, quarterin));
            //stats.add();
        }
        if (flagChannel) {
            stats.put("channels", dao.getMessagesParamChannel(companyin, campaignin, channelin, integratorin, yearin, monthin, dayofweekin,
                    weekofyearin, dayofmonthin, dayofyearin, hourin, minutein, secondin, quarterin));
            //stats.add();
        }
        if (flagIntegrator) {
            stats.put("integrators", dao.getMessagesParamIntegrator(companyin, campaignin, channelin, integratorin, yearin, monthin, dayofweekin,
                    weekofyearin, dayofmonthin, dayofyearin, hourin, minutein, secondin, quarterin));
            //stats.add();
        }
        dao.close();
        if (!flagCampaign && !flagCompany && !flagChannel && !flagIntegrator){
            throw new StatisticParametersNotFoundException();
        }
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de GetStatisticCommand" );
        //endregion
    }

    @Override
    public Map<String, Entity> Return(){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de GetStatisticCommand" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de GetStatisticCommand" );
        //endregion
        return stats;
    }

    private String setParametersforQueryCompany(List<Integer> ids){
        String params = "and me.sen_com_id in ";
        return setParametersforQuery(ids,params);
    }

    private String setParametersforQueryCampaign(List<Integer> ids){
        String params = "and me.sen_cam_id in ";
        return setParametersforQuery(ids,params);
    }

    private String setParametersforQueryChannel(List<Integer> ids){
        String params = "and me.sen_cha_id in ";
        return setParametersforQuery(ids,params);
    }

    private String setParametersforQueryIntegrator(List<Integer> ids){
        String params = "and me.sen_int_id in ";
        return setParametersforQuery(ids,params);
    }

    private String setParametersforQueryYear(List<Integer> ids){
        String params = "and da.dat_year in ";
        return setParametersforQuery(ids,params);
    }

    private String setParametersforQueryMonth(List<Integer> ids){
        String params = "and da.dat_month in ";
        return setParametersforQuery(ids,params);
    }

    private String setParametersforQueryDayWeek(List<Integer> ids){
        String params = "and da.dat_dayofweek in ";
        return setParametersforQuery(ids,params);
    }

    private String setParametersforQueryWeekYear(List<Integer> ids){
        String params = "and da.dat_weekofyear in ";
        return setParametersforQuery(ids,params);
    }

    private String setParametersforQueryDayMonth(List<Integer> ids){
        String params = "and da.dat_dayofmonth in ";
        return setParametersforQuery(ids,params);
    }

    private String setParametersforQueryDayYear(List<Integer> ids){
        String params = "and da.dat_dayofyear in ";
        return setParametersforQuery(ids,params);
    }

    private String setParametersforQueryHour(List<Integer> ids){
        String params = "and da.dat_hourofday in ";
        return setParametersforQuery(ids,params);
    }

    private String setParametersforQueryMinute(List<Integer> ids){
        String params = "and da.dat_minuteofhour in ";
        return setParametersforQuery(ids,params);
    }

    private String setParametersforQuerySeconds(List<Integer> ids){
        String params = "and da.dat_secondofminute in ";
        return setParametersforQuery(ids,params);
    }

    private String setParametersforQueryQuarter(List<Integer> ids){
        String params = "and da.dat_quarterofyear in ";
        return setParametersforQuery(ids,params);
    }

    private String setParametersforQuery(List<Integer> ids,String params){
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
