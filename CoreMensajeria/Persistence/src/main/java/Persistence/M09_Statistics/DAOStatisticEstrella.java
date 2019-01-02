package Persistence.M09_Statistics;

import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M03_Campaign.Campaign;
import Entities.M05_Channel.Channel;
import Entities.M05_Channel.ChannelFactory;
import Entities.M09_Statistics.FilterType;
import Entities.M09_Statistics.SqlEstrella;
import Entities.M09_Statistics.Statistics;
import Entities.Sql;
import Exceptions.CampaignDoesntExistsException;
import Exceptions.CompanyDoesntExistsException;
import Persistence.DAOEstrella;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOStatisticEstrella extends DAOEstrella implements IDAO_StatisticEstrella {

    private Connection connDB = Sql.getConInstance();


    @Override
    public void create(Entity e) {
    }

    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }

    @Override
    public ArrayList<Entity> CampaignsForCompany(List<Integer> companyIds) throws CampaignDoesntExistsException{
        String query = "SELECT DISTINCT cam_id, cam_name FROM m09_getAllCampaigns(";
        for (int i = 0; i < companyIds.size() - 1;  i++) {
            query += companyIds.get(i) + ", ";
        }
        query += companyIds.get(companyIds.size() - 1) + ") ORDER BY cam_id;";
        ArrayList<Entity> campaigns = new ArrayList<>();
        try {
            Statement statement = _conn.createStatement();
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
            bdClose();
            return  campaigns;
        }
    }

    @Override
    public ArrayList<Channel> getAllChannels() {
        String query = "SELECT DISTINCT cha_id, cha_name FROM dim_channel ORDER BY cha_id;";
        ArrayList<Channel> channels = new ArrayList<>();
        try {
            Statement statement = _conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                ChannelFactory channelFactory = new ChannelFactory();
                Channel channel = channelFactory.getChannel(result.getInt("cha_id"), result.getString("cha_name"), null, null);
                channels.add(channel);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            bdClose();
        }
        return channels;
    }

    @Override
    public Entity getCompanyStatistic() {
        String query = "SELECT DISTINCT c.com_id, c.com_name, messages from dim_company_campaign c, " +
                "(select sen_com_id, count(*) as messages from fact_sent_message " +
                "group by sen_com_id) as m where c.com_id = m.sen_com_id ORDER BY c.com_id ASC;";
        Statistics companiesStatistic =EntityFactory.createStatistic();
        try {
            Statement statement = _conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                companiesStatistic.addX(result.getString(FilterType.company.value()));
                companiesStatistic.addY(result.getInt("messages"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
        return companiesStatistic;
    }

    @Override
    public Entity getCampaignStatistic() {
        String query = "SELECT DISTINCT c.cam_id, c.cam_name, messages from dim_company_campaign c, " +
                "(select sen_cam_id, count(*) as messages from fact_sent_message " +
                "group by sen_cam_id) as m where c.cam_id = m.sen_cam_id ORDER BY c.cam_id ASC;";
        Statistics CampaignStatistic =EntityFactory.createStatistic();
        try {
            Statement statement = _conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                CampaignStatistic.addX(result.getString(FilterType.campaign.value()));
                CampaignStatistic.addY(result.getInt("messages"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
        return CampaignStatistic;
    }

    @Override
    public Entity getChannelStatistic() {
        String query = "SELECT DISTINCT c.cha_id, c.cha_name, messages from dim_channel c, " +
                "(select sen_cha_id, count(*) as messages from fact_sent_message " +
                "group by sen_cha_id) as m where c.cha_id = m.sen_cha_id ORDER BY c.cha_id ASC;";
        Statistics ChannelStatistic =EntityFactory.createStatistic();
        try {
            Statement statement = _conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                ChannelStatistic.addX(result.getString(FilterType.channel.value()));
                ChannelStatistic.addY(result.getInt("messages"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
        return ChannelStatistic;
    }

    @Override
    public Entity getIntegratorStatistic() {
        String query = "SELECT DISTINCT i.int_id, i.int_name, messages from dim_integrator i, " +
                "(select sen_int_id, count(*) as messages from fact_sent_message " +
                "group by sen_int_id) as m where i.int_id = m.sen_int_id ORDER BY i.int_id ASC;";
        Statistics integratorStatistic = EntityFactory.createStatistic();
        try {
            Statement statement = _conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                integratorStatistic.addX(result.getString(FilterType.integrator.value()));
                integratorStatistic.addY(result.getInt("messages"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
        return integratorStatistic;
    }

    @Override
    public void updateStarSchema() {
        String query = "SELECT m09_update_starschema();";
        try {
            Statement st = _conn.createStatement();
            ResultSet result = st.executeQuery(query);
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
    }

    @Override
    public ArrayList<Integer> getYears() {
        ArrayList<Integer> years = new ArrayList<>();
        try{
            Statement statement = _conn.createStatement();
            String query = "SELECT dat_year FROM m09_getYears()";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                years.add(result.getInt("dat_year"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
        return years;
    }

    @Override
    public ArrayList<Integer> getMonths() {
        ArrayList<Integer> months = new ArrayList<>();
        try{
            Statement statement = _conn.createStatement();
            String query = "SELECT dat_month FROM m09_getMonths()";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                months.add(result.getInt("dat_month"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
        return months;
    }

    @Override
    public ArrayList<Integer> getDaysofWeek() {
        ArrayList<Integer> daysofweek = new ArrayList<>();
        try{
            Statement statement = _conn.createStatement();
            String query = "SELECT dat_dayofweek FROM m09_getDaysofWeek()";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                daysofweek.add(result.getInt("dat_dayofweek"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
        return daysofweek;
    }

    @Override
    public ArrayList<Integer> getDaysofMonth() {
        ArrayList<Integer> daysofmonth = new ArrayList<>();
        try{
            Statement statement = _conn.createStatement();
            String query = "SELECT dat_dayofmonth FROM m09_getDaysofMonth()";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                daysofmonth.add(result.getInt("dat_dayofmonth"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
        return daysofmonth;
    }

    @Override
    public ArrayList<Integer> getDaysofYear() {
        ArrayList<Integer> daysofyear = new ArrayList<>();
        try{
            Statement statement = _conn.createStatement();
            String query = "SELECT dat_dayofyear FROM m09_getDaysofYear()";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                daysofyear.add(result.getInt("dat_dayofyear"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
        return daysofyear;
    }

    @Override
    public ArrayList<Integer> getWeeksofYear() {
        ArrayList<Integer> weeksofyear = new ArrayList<>();
        try{
            Statement statement = _conn.createStatement();
            String query = "SELECT dat_weekofyear FROM m09_getWeeksofYear()";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                weeksofyear.add(result.getInt("dat_weekofyear"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
        return weeksofyear;
    }

    @Override
    public ArrayList<Integer> getQuartersofYear() {
        ArrayList<Integer> quartersofyear = new ArrayList<>();
        try{
            Statement statement = _conn.createStatement();
            String query = "SELECT dat_quarterofyear FROM m09_getQuartersofYear()";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                quartersofyear.add(result.getInt("dat_quarterofyear"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
        return quartersofyear;
    }

    @Override
    public ArrayList<Integer> getHours() {
        ArrayList<Integer> hours = new ArrayList<>();
        try{
            Statement statement = _conn.createStatement();
            String query = "SELECT dat_hourofday FROM m09_getHours()";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                hours.add(result.getInt("dat_hourofday"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
        return hours;
    }

    @Override
    public ArrayList<Integer> getMinutes() {
        ArrayList<Integer> minutes = new ArrayList<>();
        try{
            Statement statement = _conn.createStatement();
            String query = "SELECT dat_minuteofhour FROM m09_getMinutes()";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                minutes.add(result.getInt("dat_minuteofhour"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
        return minutes;
    }

    @Override
    public ArrayList<Integer> getSeconds() {
        ArrayList<Integer> seconds = new ArrayList<>();
        try{
            Statement statement = _conn.createStatement();
            String query = "SELECT dat_secondofminute FROM m09_getSeconds()";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                seconds.add(result.getInt("dat_secondofminute"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            SqlEstrella.bdClose(_conn);
        }
        return seconds;
    }


}
