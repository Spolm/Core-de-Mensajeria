package Logic.M09_Statistics;

import Entities.Entity;
import Exceptions.CompanyDoesntExistsException;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M09_Statistics.DAOStatistic;
import java.util.ArrayList;

public class GetAllCompaniesByUserCommand extends Command<ArrayList<Entity>> {

    private Integer userId;
    private ArrayList<Entity> companies;
    private DAOStatistic dao;

    public GetAllCompaniesByUserCommand(Integer userId){ this.userId = userId;}

    @Override
    public void execute () throws CompanyDoesntExistsException {
            dao = DAOFactory.instanciateDAOStatistic();
            companies = dao.getAllCompanies(userId);
    }

    @Override
    public ArrayList<Entity> Return() {
        return companies;
    }

    private void setUserId(Integer userId) {
        this.userId = userId;
    }
}
