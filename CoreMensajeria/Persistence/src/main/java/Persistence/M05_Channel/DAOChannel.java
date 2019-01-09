package Persistence.M05_Channel;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M05_Channel.Channel;
import Entities.Sql;
import Exceptions.M05_Channel.ChannelNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import Persistence.DAO;
import Persistence.DAOFactory;
import Persistence.M04_Integrator.DAOIntegrator;
import Persistence.M04_Integrator.IDAOIntegrator;

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
public class DAOChannel extends DAO implements IDAOChannel {
    private final String SELECT_ALL_CHANNELS = "{call m04_getchannels()}";
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
            IDAOIntegrator daoIntegrator = DAOFactory.instanciateDaoIntegrator();
            PreparedStatement st = _conn.prepareCall(SELECT_ALL_CHANNELS);
            ResultSet result = st.executeQuery();

            while (result.next()) {
                Channel channel = extractChannel(result);
                channel.set_integrators(daoIntegrator.listIntegratorByChannel(channel.get_id()));
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
    }

    private Channel extractChannel(ResultSet result) throws SQLException {
        return EntityFactory.createChannel(result.getInt("cha_id"), result.getString("cha_name"),
                result.getString("cha_description"));
    }

    @Override
    public void create(Entity e) throws SQLException {

    }

    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }
}
