package Logic.M02_Company;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.DAOCompany;
import Persistence.M02_Company.IDAOCompany;

public class GetCompanyCommand extends Command {
    private static  Entity _co;


    /**
     * Constructor de la clase.
     * @param _company instancia de la Compania que se desea obtener
     */
    public GetCompanyCommand( Entity _company ) {
        this._co = _company;
    }

    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws Exception {
        try {
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany();
            _dao.companyById( _co );


        }catch(Exception exc) {

        }


    }

    @Override
    public Entity Return() {
        return null;
    }
}
