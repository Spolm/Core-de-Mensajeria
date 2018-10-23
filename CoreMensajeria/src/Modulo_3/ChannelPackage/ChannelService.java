package Modulo_3.ChannelPackage;

import Modulo_3.ConectionDB.Sql;
import Modulo_3.IntegratorPackage.Integrator;
import Modulo_3.IntegratorPackage.IntegratorService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChannelService {
    private static ChannelService channelDAO = null;

    public static ChannelService getInstance() {
        if (channelDAO == null)
            channelDAO = new ChannelService();
        return channelDAO;

    }

    public List<Integrator> listIntegratorByChannel(int id) {

        Sql db = new Sql();
        List<Integrator> integrators = new ArrayList<>();

        try {
            ResultSet rs = db.sqlConn("SELECT in_id, in_name, in_messageCost, in_threadCapacity, in_tokenApi" +
                                        " FROM INTEGRATOR, CHANNEL_INTEGRATOR ," +
                                        " CHANNEL"+
                                        " WHERE IN_ID = CI_INTEGRATOR_ID AND" +
                                        " CI_CHANNEL_ID = CH_ID AND CH_ID = "+id);
            IntegratorService.getIntegratorRs(integrators, rs);
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        }
        return integrators;
    }
}
