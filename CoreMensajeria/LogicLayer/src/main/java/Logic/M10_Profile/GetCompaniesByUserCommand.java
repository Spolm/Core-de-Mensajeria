package Logic.M10_Profile;

import Entities.M02_Company.Company;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAOProfile;

import java.util.ArrayList;

public class GetCompaniesByUserCommand extends Command {

    private int userId;
    ArrayList<Company> companies;

    public GetCompaniesByUserCommand(int userId){
        this.userId = userId;
    }

    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        IDAOProfile dao = factory.createDAOProfile();
        companies = dao.getCompaniesByUser(userId);
    }

    @Override
    public Object Return() {
        return companies;
    }
}
