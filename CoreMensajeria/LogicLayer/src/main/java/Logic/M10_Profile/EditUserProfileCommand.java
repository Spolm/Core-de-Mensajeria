package Logic.M10_Profile;

import Entities.M01_Login.User;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAOProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditUserProfileCommand extends Command {
    private User user;
    private String r;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    public EditUserProfileCommand(User user) {
        //region Instrumentation Debug
        log.debug("Entrando al constructor EditUserProfileCommand(" + user.toString() +")");
        //endregion
        this.user = user;
    }

    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        IDAOProfile dao = factory.createDAOProfile();
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() " +
                "EditUserProfileCommand( " + user.toString() + ")" + " exitosamente");
        //endregion
        r = dao.editProfile(user.get_idUser(), user.get_nameUser(), user.get_lastnameUser(), user.get_identificationNumberUser(),
                user.get_rgUser(), user.get_addressUser(), user.get_bdUser(), user.get_genderUser(), user.get_emailUser(),
                user.get_phoneUser());
        //region Instrumentation Debug
        log.debug("Saliendo metodo execute() de EditUserProfileCommand con respuesta de la bd : " + r);
        //endregion
    }

    @Override
    public String Return() {
        return r;
    }
}
