package Persistence.M08_SendMessage;

import Entities.Entity;
import Persistence.IDAO;

import java.sql.SQLException;

/**
 * Interface que contiene los metodos que
 * seran implementados en el DAO de envio de mensajes.
 *
 * @see IDAO
 */
public interface IDAOSentMessage extends IDAO {
    /**
     * Metodo que permite insertar a la base de datos
     * el mensaje que fue enviado
     */
    @Override
    void create(Entity e) throws SQLException;
}
