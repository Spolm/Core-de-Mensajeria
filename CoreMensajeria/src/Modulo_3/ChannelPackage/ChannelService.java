package Modulo_3.ChannelPackage;
import Classes.Sql;

import Modulo_3.IntegratorPackage.Integrator;
import Modulo_3.IntegratorPackage.IntegratorService;

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
            ResultSet rs = db.sqlConn("SELECT in_id, in_name, in_messageCost, in_threadCapacity, in_tokenApi" +
                                        " FROM INTEGRATOR, CHANNEL_INTEGRATOR ," +
                                        " CHANNEL"+
                                        " WHERE IN_ID = CI_INTEGRATOR_ID AND" +
                                        " CI_CHANNEL_ID = CH_ID AND CH_ID = "+id);
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
            ResultSet rs = db.sqlConn("SELECT ch_id, ch_name, ch_description FROM CHANNEL");
            while (rs.next()) {
                ChannelFactory f = new ChannelFactory();
                int id = rs.getInt("ch_id");
                Channel c = f.getChannel(rs.getInt("ch_id"),rs.getString("ch_name"),rs.getString("ch_description"), listIntegratorByChannel(id));
                channels.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        }
        return channels;
    }
}
