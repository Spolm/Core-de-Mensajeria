package Logic.M10_Profile;

import Entities.M10_Profile.Rol;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAOProfile;
import java.util.ArrayList;

public class GetRolesCommand extends Command {

    private ArrayList<Rol> roles = new ArrayList<>();

    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        IDAOProfile dao = factory.createDAOProfile();
        roles = dao.getRoles();
    }

    @Override
    public Object Return() {
        return roles;
    }
}
