package Persistence.M09_Statistics;

import Entities.Entity;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;
import Entities.M09_Statistics.SqlEstrella;
import Entities.Sql;
import Exceptions.CampaignDoesntExistsException;
import Exceptions.CompanyDoesntExistsException;
import Persistence.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOStatistic extends DAO implements IDAO_Statistic {

    private Connection connStar = SqlEstrella.getConInstance();
    private Connection conn = Sql.getConInstance();

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
                //Entity company = EntityFactory.CreateCompanyWithOutLink(result.getInt("com_id"), result.getString("com_name"), "", true);
                Company company = new Company(result.getInt("com_id"), result.getString("com_name"), "", true);
                companies.add(company);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new CompanyDoesntExistsException(e);
        } finally {
            Sql.bdClose(conn);
        }
        return companies;
    }

    @Override
    public ArrayList<Campaign> CampaignsForCompany(List<Integer> companyIds) throws CampaignDoesntExistsException{
        String query = "SELECT DISTINCT cam_id, cam_name FROM m09_getAllCampaigns(";
        for (int i = 0; i < companyIds.size() - 1;  i++) {
            query += companyIds.get(i) + ", ";
        }
        query += companyIds.get(companyIds.size() - 1) + ") ORDER BY cam_id;";
        ArrayList<Campaign> campaigns = new ArrayList<>();
        try {
            Statement statement = connStar.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                Campaign campaign = new Campaign();
                campaign.set_idCampaign(result.getInt("cam_id"));
                campaign.set_nameCampaign(result.getString("cam_name"));
                campaigns.add(campaign);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new CampaignDoesntExistsException(e);
        } finally {
            SqlEstrella.bdClose(connStar);
            return  campaigns;
        }
    }
}
