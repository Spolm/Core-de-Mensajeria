package Persistence.M09_Statistics;

import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.Sql;
import Exceptions.CompanyDoesntExistsException;
import Persistence.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
}
