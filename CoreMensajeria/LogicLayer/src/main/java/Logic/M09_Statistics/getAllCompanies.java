package Logic.M09_Statistics;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatistic;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;

public class getAllCompanies extends Command {

    private static Integer userId;
    Gson gson = new Gson();

    public getAllCompanies(Integer userId){ getAllCompanies.userId = userId;}

    @Override
    public void execute () throws SQLException {
        try {
            DAOStatistic dao = DAOFactory.instanciateDaoStatistics();
            ArrayList<Entity> companies = dao.getAllCompanies(userId);
            //return Response.ok(gson.toJson(companies)).build();
        }
        catch (Exception e){

        }
    }

    @Override
    public Entity Return() {
        return null;
    }

}
