package Modulo_3.ChannelPackage;

import Modulo_3.ConectionDB.DbConnection;
import Modulo_3.ConectionDB.DbResultSet;
import Modulo_3.IntegratorPackage.Integrator;
import Modulo_3.IntegratorPackage.IntegratorFactory;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChannelService {
    private static ChannelService channelDao = null;
    private DbConnection conn;

    ChannelService() {
        this.conn = conn;
    }

    public static ChannelService getInstance() {
        if (channelDao == null)
            channelDao = new ChannelService();
        return channelDao;
    }

    public List<Integrator> listIntegratorByChannel() {
        conn = new DbConnection();
        conn.connect();
        List<Integrator> integrators = new ArrayList<>();
        DbResultSet result = new DbResultSet();
        try {
            ResultSet rs = result.getResultSet(conn, "SELECT in_id, in_name, in_messageCost, in_threadCapacity, in_tokenApi " +
                    "FROM INTEGRATOR, CHANNEL_INTEGRATOR , " +
                    "CHANNEL WHERE IN_ID = CI_INTEGRATOR_ID AND CI_CHANNEL_ID = CH_ID AND CH_ID = 1");
            while (rs.next()) {
                IntegratorFactory factory = new IntegratorFactory();
                String integratorType = rs.getString("in_name");
                Integrator i = factory.getIntegrator(integratorType, rs.getInt("in_id"), rs.getString("in_name"), rs.getFloat("in_messageCost"),
                        rs.getInt("in_threadCapacity"), rs.getString("in_tokenApi"));
                integrators.add(i);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        }
        return integrators;
    }
}
