package Logic.M10_Profile;

import Entities.M10_Profile.Responsability;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAOProfile;

import java.util.ArrayList;

public class GetResponsabilityByCompanyCommand extends Command {
    private int companyId;
    private ArrayList<Responsability> responsabilities;

    public GetResponsabilityByCompanyCommand(int companyId){
        this.companyId = companyId;
    }
    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        IDAOProfile dao = factory.createDAOProfile();
        responsabilities = dao.getResponsabilitiesByCompany(companyId);
    }

    @Override
    public Object Return() {
        return responsabilities;
    }
}
