package Persistence.M09_Statistics;

import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M04_Integrator.Integrator;
import Entities.M04_Integrator.IntegratorFactory;
import Entities.Sql;
import Exceptions.ChannelNotFoundException;
import Exceptions.CompanyDoesntExistsException;
import Persistence.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOStatistic extends DAO implements IDAO_Statistic {
    private Connection conn = getBdConnect();

    @Override
    public void create(Entity e) {

    }

    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }

    @Override
    public ArrayList<Entity> getAllCompanies(Integer userId) throws CompanyDoesntExistsException {
        String query = "SELECT com_id, com_name from m02_getcompaniesbyresponsible(" + userId + ") ORDER BY com_id;";
        ArrayList<Entity> companies = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                Entity company = EntityFactory.CreateCompanyWithOutLink(result.getInt("com_id"), result.getString("com_name"), "", true);
                //Company company = new Company(result.getInt("com_id"), result.getString("com_name"), "", true);
                companies.add(company);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new CompanyDoesntExistsException(e);
        } finally {
            //checkear conexiones
            closeConnection();
        }
        return companies;
    }

    @Override
    public ArrayList<Integrator> getIntegratorsForChannel(List<Integer> channelIds) throws ChannelNotFoundException{
        String query = "select int_id, int_name from m09_getIntegratorsByChannels(";
        for (int i = 0; i < channelIds.size() - 1;  i++) {
            query += channelIds.get(i) + ", ";
        }
        query += channelIds.get(channelIds.size() - 1) + ") ORDER BY int_id;";
        ArrayList<Integrator> integrators = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                Integrator integrator = IntegratorFactory.getIntegrator(result.getString("int_name"), result.getInt("int_id"),
                        result.getString("int_name"), 0, 0, "", true);
                integrators.add(integrator);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new ChannelNotFoundException(e);
        } finally {
            //checkear conexiones
            closeConnection();
        }
        return integrators;
    }
}
