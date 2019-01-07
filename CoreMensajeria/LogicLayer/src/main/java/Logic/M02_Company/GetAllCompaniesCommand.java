package Logic.M02_Company;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.IDAOCompany;

import java.util.ArrayList;

public class GetAllCompaniesCommand extends Command {

    private static ArrayList<Entity> _coList;

    /**
     * Constructor de la clase.
     * instancia de la Lista de Compania que se desea agregar
     */
    public GetAllCompaniesCommand( ) {

        _coList = new ArrayList<Entity>();

    }

    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws Exception {
        try {
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany();
           _coList = _dao.allCompanies();


        }catch(Exception exc) {

        }
    }

    @Override
    public Entity Return() {
        return null;
    }

    //@Override
    public ArrayList<Entity> ReturnList() { return _coList; }

}
