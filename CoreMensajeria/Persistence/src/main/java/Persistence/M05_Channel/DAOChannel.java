package Persistence.M05_Channel;

import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M04_Integrator.Integrator;
import Entities.M04_Integrator.IntegratorDAO;
import Entities.M05_Channel.Channel;
import Entities.Sql;
import Exceptions.ChannelNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que nos permite realizar la conexión a la base de datos
 * con los métodos relacionados a la clase Channel
 *
 * @author Kevin Martinez
 * @author Braulio Picon
 * @author Alexander Fernandez
 * @see Channel
 */

public class DAOChannel {

    final String SELECT_ALL_CHANNELS = "{call m04_getchannels()}";
    final String SELECT_ALL_INTEGRATORS_BY_CHANNEL = "{call m04_getIntegratorsByChannel(?)}";

    private Connection _conn;

    /**
     * Constructor que se encarga de realizar la conexión
     * a la base de datos.
     *
     * @see Connection
     */

    public DAOChannel() {
        _conn = Sql.getConInstance();
    }

    /**
     * Retorna una lista de integradores por canal.
     * Este método retorna una lista de integradores, en caso de no tener
     * el archivo se encontrara en blanco.
     *
     * @return Lista de canales
     * @see Channel
     * @see Integrator
     */

    public ArrayList<Entity> listIntegratorByChannel(Entity e) throws DatabaseConnectionProblemException, ChannelNotFoundException {
        try {
            Channel channel = (Channel) e;
            Sql.bdClose(_conn);
            _conn = Sql.getConInstance();
            ArrayList<Entity> integratorList = new ArrayList<>();
            PreparedStatement preparedStatement = _conn.prepareCall(SELECT_ALL_INTEGRATORS_BY_CHANNEL);
            preparedStatement.setInt(1, channel.get_id());
            ResultSet result = preparedStatement.executeQuery();

            while (result.next())
                integratorList.add(IntegratorDAO.getIntegrator(result));
            if (integratorList.size() == 0)
                throw new ChannelNotFoundException("El canal no existe");

            return integratorList;
        }
        catch (SQLException ex) {
            throw new DatabaseConnectionProblemException("Error de comunicacion con la base de datos.", ex);
        }
        finally {
            Sql.bdClose(_conn);
        }
    }

    /**
     * Retorna una lista de canales.
     * Este método retorna una lista de canales, en caso de no tener
     * el archivo se encontrará en blanco.
     *
     * @return Lista de canales
     * @see Channel
     */

    public ArrayList<Entity> listChannel() throws DatabaseConnectionProblemException {
        try {
            ArrayList<Entity> channelList = new ArrayList<>();
            PreparedStatement st = _conn.prepareCall(SELECT_ALL_CHANNELS);
            ResultSet result = st.executeQuery();

            while (result.next()) {
                Channel channel = extractChannel(result);
                channel.set_integrators(listIntegratorByChannel(channel));
                channelList.add(channel);
            }
            return channelList;
        }
        catch (SQLException e) {
            throw new DatabaseConnectionProblemException("Error de comunicacion con la base de datos.", e);
        }
        catch (ChannelNotFoundException e) {
            throw new DatabaseConnectionProblemException("No se han encontrado canales.", e);
        }
        finally {
            Sql.bdClose(_conn);
        }
    }

    private Channel extractChannel(ResultSet rs) throws SQLException {
        Channel channel;
        channel = EntityFactory.CreateChannel(rs.getInt("cha_id"), rs.getString("cha_name"),
                rs.getString("cha_description"));

        return channel;
    }
}
