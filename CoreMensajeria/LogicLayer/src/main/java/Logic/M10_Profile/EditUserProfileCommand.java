package Logic.M10_Profile;

import Entities.M01_Login.User;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAOProfile;

public class EditUserProfileCommand extends Command {
    private User user;
    private String r;

    public EditUserProfileCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        IDAOProfile dao = factory.createDAOProfile();
        r = dao.editProfile(user.get_idUser(), user.get_nameUser(), user.get_lastnameUser(), user.get_identificationNumberUser(),
                user.get_rgUser(), user.get_addressUser(), user.get_bdUser(), user.get_genderUser(), user.get_emailUser(),
                user.get_phoneUser());
    }

    @Override
    public String Return() {
        return r;
    }
}
