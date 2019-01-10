package Persistence.M08_SendMessage;

import Entities.Entity;
import Entities.M08_Validation.SentMessage;
import Persistence.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Objeto de accesos a datos que nos permite tener acceso
 * a la base de datos, especificamente a la tabla sent_message
 *
 * @see DAO
 * @see IDAOSentMessage
 */
public class DAOSentMessage extends DAO implements IDAOSentMessage {
    private Connection _conn;
    private final String CALL_INSERT = "{CALL m08_insertSentMessage(?,?,?,?,?,?)}";

    /**
     * Metodo que nos permite insertar el mensaje que fue enviado a la base de datos.
     *
     * @param e La entidad sentMessage que se va insertar en la bd
     * @throws SQLException excepcion que se lanza para que capas superiores lo manejen
     */
    @Override
    public void create(Entity e) throws SQLException {
        _conn = getBdConnect();
        SentMessage sentMessage = (SentMessage) e;
        System.out.println("El timestamp: " + sentMessage.get_sentTime());
        try {
            PreparedStatement preparedStatement = _conn.prepareCall(CALL_INSERT);
            preparedStatement.setTimestamp(1, sentMessage.get_sentTime());
            preparedStatement.setInt(2, sentMessage.get_message());
            preparedStatement.setInt(3, sentMessage.get_campaignId());
            preparedStatement.setInt(4, sentMessage.get_channel());
            preparedStatement.setInt(5, sentMessage.get_integratorId());
            preparedStatement.setInt(6, sentMessage.get_applicationId());
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                e.set_id(result.getInt("id"));
            }
        } catch (SQLException error) {
            throw error;
        }

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
