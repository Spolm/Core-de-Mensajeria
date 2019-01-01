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
        Statistics companiesStatistic = new Statistics();
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
        Statistics CampaignStatistic = new Statistics();
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
        return null;
    }

    @Override
    public Entity getIntegratorStatistic() {
        return null;
    }


}
