package Logic.M10_Profile;

import Entities.M01_Login.Privilege;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAOProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class GetPrivilegesByUserCompanyCommand extends Command {

    private int userId;
    private int companyId;
    private ArrayList<Privilege> privileges;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    public GetPrivilegesByUserCompanyCommand(int userId, int companyId){
        //region Instrumentation Debug
        log.debug("Entrando al constructor GetPrivilegesByUserCompanyCommand("+userId+","+companyId+")" );
        //endregion
        this.userId = userId;
        this.companyId = companyId;
    }

    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        IDAOProfile dao = factory.createDAOProfile();
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente de " +
                "GetPrivilegesByUserCompanyCommand("+userId+","+companyId+")");
        //endregion
        privileges = dao.getPrivilegesByUserAndCompany(userId, companyId);
        //region Instrumentation Debug
        log.debug("Saliendo de execute() de GetPrivilegesByUserCompanyCommand con " +
                "Privilegios obtenidos : \n" + privileges.toString());
        //endregion
    }

    @Override
    public Object Return() {
        return privileges;
    }
}