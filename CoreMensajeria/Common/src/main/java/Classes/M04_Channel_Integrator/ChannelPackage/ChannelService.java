package Classes.M04_Channel_Integrator.ChannelPackage;
import Classes.Sql;

import Classes.M04_Channel_Integrator.IntegratorPackage.Integrator;
import Classes.M04_Channel_Integrator.IntegratorPackage.IntegratorService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ChannelService {
    private static ChannelService channelDAO = null;

    public static ChannelService getInstance() {
        if (channelDAO == null)
            channelDAO = new ChannelService();
        return channelDAO;

    }

    public ArrayList<Integrator> listIntegratorByChannel(int id) {

        Sql db = new Sql();
        ArrayList<Integrator> integrators = new ArrayList<>();

        try {
            ResultSet rs = db.sqlConn("SELECT int_id, int_name, int_messageCost, int_threadCapacity, int_tokenApi, int_enabled" +
                                        " FROM INTEGRATOR, CHANNEL_INTEGRATOR ," +
                                        " CHANNEL"+
                                        " WHERE INT_ID = CI_INTEGRATOR_ID AND" +
                                        " CI_CHANNEL_ID = CHA_ID AND CHA_ID = "+id);
            IntegratorService.getIntegratorsRs(integrators, rs);
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        }
        return integrators;
    }

    public ArrayList<Channel> listChannel(){

        Sql db = new Sql();
        ArrayList<Channel> channels = new ArrayList<>();

        try {
            ResultSet rs = db.sqlConn("SELECT cha_id, cha_name, cha_description FROM CHANNEL");
            while (rs.next()) {
                ChannelFactory f = new ChannelFactory();
                int id = rs.getInt("cha_id");
                Channel c = f.getChannel(rs.getInt("cha_id"),rs.getString("cha_name"),rs.getString("cha_description"), listIntegratorByChannel(id));
                channels.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        }
        return channels;
    }
}
