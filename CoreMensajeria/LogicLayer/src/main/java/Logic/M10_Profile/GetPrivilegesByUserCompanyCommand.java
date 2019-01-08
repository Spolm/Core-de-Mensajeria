package Logic.M10_Profile;

import Entities.M01_Login.Privilege;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAOProfile;

import java.util.ArrayList;

public class GetPrivilegesByUserCompanyCommand extends Command {

    private int userId;
    private int companyId;
    private ArrayList<Privilege> privileges;

    public GetPrivilegesByUserCompanyCommand(int userId, int companyId){
        this.userId = userId;
        this.companyId = companyId;
    }

    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        IDAOProfile dao = factory.createDAOProfile();
        privileges = dao.getPrivilegesByUserAndCompany(userId, companyId);
    }

    @Override
    public Object Return() {
        return privileges;
    }
}
