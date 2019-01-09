package Persistence.M08_SendMessage;

import Entities.Entity;
import Persistence.IDAO;

import java.sql.SQLException;

public interface IDAOSentMessage extends IDAO {
    @Override
    void create(Entity e) throws SQLException;
}
