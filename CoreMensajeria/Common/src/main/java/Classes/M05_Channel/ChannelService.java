package Classes.M05_Channel;

import Classes.M04_Integrator.Integrator;
import Classes.M04_Integrator.IntegratorService;
import Classes.Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ChannelService {
    private static ChannelService channelDAO = null;


    /**
     * Retorna un ChannelService, clase que se encarga de realizar la
     * conexion a la base de datos.
     * En caso de que esta exista, la retoma.
     *
     * @return channelService con los metodos para realizar operaciones sobre los canales.
     * @see ChannelService
     */
    public static ChannelService getInstance() {
        if (channelDAO == null)
            channelDAO = new ChannelService();
        return channelDAO;

    }

    /**
     * Este metodo retorna una lista de integradores por canal.
     *
     * @param id el id del canal a ser ubicado en la base de datos.
     * @return lista de Integradores que pertenecen a un canal
     * @see Integrator
     */
    public ArrayList<Integrator> listIntegratorByChannel(int id) {

        Sql db = new Sql();
        ArrayList<Integrator> integrators = new ArrayList<>();

        try {
            ResultSet rs = db.sqlConn("SELECT int_id, int_name, int_messageCost, int_threadCapacity, int_tokenApi, int_enabled" +
                    " FROM INTEGRATOR, CHANNEL_INTEGRATOR ," +
                    " CHANNEL" +
                    " WHERE INT_ID = CI_INTEGRATOR_ID AND" +
                    " CI_CHANNEL_ID = CHA_ID AND CHA_ID = " + id);
            IntegratorService.getIntegratorsRs(integrators, rs);
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        } finally {
            Sql.bdClose(db.getConn());
        }
        return integrators;
    }

    /**
     * Metodo que se encarga de mostrar todos los canales existentes
     *
     * @return Lista de canales
     * @see Channel
     */
    public ArrayList<Channel> listChannel() {

        Sql db = new Sql();
        ArrayList<Channel> channels = new ArrayList<>();

        try {
            ResultSet rs = db.sqlConn("SELECT cha_id, cha_name, cha_description FROM CHANNEL");
            while (rs.next()) {
                ChannelFactory f = new ChannelFactory();
                int id = rs.getInt("cha_id");
                Channel c = f.getChannel(rs.getInt("cha_id"), rs.getString("cha_name"), rs.getString("cha_description"), listIntegratorByChannel(id));
                channels.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        } finally {
            Sql.bdClose(db.getConn());
        }
        return channels;
    }
}
