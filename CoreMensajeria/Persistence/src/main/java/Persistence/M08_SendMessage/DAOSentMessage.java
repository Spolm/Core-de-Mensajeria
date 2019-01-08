package Persistence.M08_SendMessage;

import Entities.Entity;
import Entities.M08_Validation.SentMessage;
import Persistence.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOSentMessage extends DAO implements IDAOSentMessage{
    private Connection _conn;
    final String CALL_INSERT = "{CALL m08_insertSentMessagge(?,?,?,?,?,?)}";
    @Override
    public void create(Entity e) {
        _conn = getBdConnect();
        SentMessage sentMessage = (SentMessage) e;
        try {
            PreparedStatement preparedStatement = _conn.prepareCall(CALL_INSERT);
            preparedStatement.setTimestamp(1, sentMessage._sentTime);
            preparedStatement.setInt(2, sentMessage._messageId);
            preparedStatement.setInt(3, sentMessage._campaignId);
            preparedStatement.setInt(4, sentMessage._channelId);
            preparedStatement.setInt(5, sentMessage._integratorId);
            preparedStatement.setInt(6, sentMessage._applicationId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                e.set_id(result.getInt("id"));
            }
        } catch(SQLException error) {

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
