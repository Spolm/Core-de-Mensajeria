package Logic.M09_Statistics;

import Entities.Entity;
import Exceptions.StatisticParametersNotFoundException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatisticEstrella;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetStatisticCommand extends Command<Map<String, Entity>> {
    DAOStatisticEstrella dao;
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

    public GetStatisticCommand(List<Integer> companyIds, List<Integer> campaignIds, List<Integer> channelIds,
                               List<Integer> integratorIds, List<Integer> yearIds, List<Integer> monthIds,
                               List<Integer> dayofweekIds, List<Integer> weekofyearIds, List<Integer> dayofmonthIds,
                               List<Integer> dayofyearIds, List<Integer> hourofdayIds, List<Integer> minuteofhourIds,
                               List<Integer> secondofminuteIds, List<Integer> quarterIds){
        companyin = setParametersforQuery(companyIds,"and me.sen_com_id in ");
        campaignin = setParametersforQuery(campaignIds,"and me.sen_cam_id in ");
        channelin = setParametersforQuery(channelIds,"and me.sen_cha_id in ");
        integratorin = setParametersforQuery(integratorIds, "and me.sen_int_id in");
        yearin = setParametersforQuery(yearIds, "and da.dat_year in");
        monthin = setParametersforQuery(monthIds, "and da.dat_month in");
        dayofweekin = setParametersforQuery(dayofweekIds,"and da.dat_dayofweek in");
        weekofyearin = setParametersforQuery(weekofyearIds, "and da.dat_weekofyear in");
        dayofmonthin = setParametersforQuery(dayofmonthIds, "and da.dat_dayofmonth in");
        dayofyearin = setParametersforQuery(dayofyearIds, "and da.dat_dayofyear in");
        hourin = setParametersforQuery(hourofdayIds, "and da.dat_hourofday in");
        minutein = setParametersforQuery(minuteofhourIds, "and da.dat_minuteofhour in");
        secondin = setParametersforQuery(secondofminuteIds, "and da.dat_secondofminute in");
        quarterin = setParametersforQuery(quarterIds, "and da.dat_quarterofyear in");
        stats = new HashMap<String, Entity>();
        if (!companyIds.isEmpty()) { flagCompany = true; }
        if (!campaignIds.isEmpty()) { flagCampaign = true;}
        if (!channelIds.isEmpty()) { flagChannel = true; }
        if (!integratorIds.isEmpty()) { flagIntegrator = true; }
    }

    @Override
    public void execute() throws StatisticParametersNotFoundException {
        dao = DAOFactory.instanciateDaoStatisticsEstrella();
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
        dao.bdClose();
        if (!flagCampaign && !flagCompany && !flagChannel && !flagIntegrator){
            throw new StatisticParametersNotFoundException();
        }
    }

    @Override
    public Map<String, Entity> Return(){
        return stats;
    }

    private String setParametersforQuery(List<Integer> ids, String params){
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
