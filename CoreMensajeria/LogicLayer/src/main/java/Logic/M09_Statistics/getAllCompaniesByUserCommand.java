package Logic.M09_Statistics;

import Entities.Entity;
import Exceptions.CompanyDoesntExistsException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatistic;
import java.util.ArrayList;

public class getAllCompaniesByUserCommand extends Command {

    private static Integer userId;
    ArrayList<Entity> companies;
    DAOStatistic dao;

    public getAllCompaniesByUserCommand(Integer userId){ getAllCompaniesByUserCommand.userId = userId;}

    @Override
    public void execute () throws CompanyDoesntExistsException {
            dao = DAOFactory.instanciateDaoStatistics();
            companies = dao.getAllCompanies(userId);
    }

    @Override
    public Entity Return() {
        return null;
    }

    public ArrayList<Entity> returnList() {
        return companies;
    }

}
