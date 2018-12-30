package Persistence.M09_Statistics;

import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M03_Campaign.Campaign;
import Entities.M05_Channel.Channel;
import Entities.M05_Channel.ChannelFactory;
import Entities.Sql;
import Exceptions.CampaignDoesntExistsException;
import Exceptions.CompanyDoesntExistsException;
import Persistence.DAOEstrella;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOStatisticEstrella extends DAOEstrella implements IDAO_StatisticEstrella {

    private Connection conn = Sql.getConInstance();


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


}
