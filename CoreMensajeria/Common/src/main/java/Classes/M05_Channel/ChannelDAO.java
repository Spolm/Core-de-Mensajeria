package Classes.M05_Channel;

import Classes.M04_Integrator.Integrator;
import Classes.M04_Integrator.IntegratorDAO;
import Classes.Sql;
import Exceptions.DatabaseConnectionProblemException;

import java.sql.*;
import java.util.ArrayList;

public class ChannelDAO {

    final String QUERY_SELECT = "SELECT * FROM CHANNEL";
    final String QUERY_SELECT_INTEGRATOR_BY_CHANNEL = "SELECT int_id, int_name, int_messageCost, int_threadCapacity, int_tokenApi, int_enabled FROM INTEGRATOR, CHANNEL_INTEGRATOR , CHANNEL WHERE INT_ID = CI_INTEGRATOR_ID AND CI_CHANNEL_ID = CHA_ID AND CHA_ID =?";
    private Connection _conn;
    private ResultSet _result;
    public ArrayList<Integrator> _integratorList;
    public ArrayList<Channel> _channelList;

    public ChannelDAO() {
        _conn = Sql.getConInstance();
    }

    public ArrayList<Integrator> listIntegratorByChannel(int id) throws DatabaseConnectionProblemException, ChannelNotFoundException {
        try {
            _conn = Sql.getConInstance();
            _integratorList = new ArrayList<>();
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_SELECT_INTEGRATOR_BY_CHANNEL);
            preparedStatement.setInt(1, id);
            _result = preparedStatement.executeQuery();

            while (_result.next()) {
                _integratorList.add(IntegratorDAO.getIntegrator(_result));
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionProblemException("Error de comunicacion con la base de datos.", e);
        } finally {
            Sql.bdClose(_conn);
        }
        if (_integratorList.size() == 0) {
            throw new ChannelNotFoundException("El canal no existe");
        }
        return _integratorList;
    }

    public ArrayList<Channel> listChannel() throws DatabaseConnectionProblemException {
        try {
            _channelList = new ArrayList<>();
            Statement st = _conn.createStatement();
            ResultSet _result = st.executeQuery(QUERY_SELECT);

            while (_result.next()) {
                _channelList.add(getChannel(_result));
            }
            return _channelList;

        } catch (SQLException e) {
            throw new DatabaseConnectionProblemException("Error de comunicacion con la base de datos.", e);
        } finally {
            Sql.bdClose(_conn);
        }
    }

    public Channel getChannel(ResultSet rs) throws SQLException {
        int id = rs.getInt("cha_id");
        Channel c = null;

        try {
            c = ChannelFactory.getChannel(rs.getInt("cha_id"), rs.getString("cha_name"), rs.getString("cha_description"), listIntegratorByChannel(id));
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (ChannelNotFoundException e) {
            e.printStackTrace();
        }

        return c;
    }
}
