package Logic.M02_Company;

import Entities.Entity;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M02_Company.DAOCompany;
import Persistence.M02_Company.IDAOCompany;

import java.util.ArrayList;

public class ChangeStatusCommand extends Command {

    private static Entity _co;

    /**
     * Constructor de la clase.
     * @param _company instancia de la Compania que se desea agregar
     */
    public  ChangeStatusCommand ( Entity _company ){
        this._co = _company;
    }


    /**
     * Metodo que ejecuta la Accion del comando
     */
    @Override
    public void execute() throws Exception {

        try {
            IDAOCompany _dao = DAOFactory.instanciateDaoCompany ( );
            _dao.changeStatus( _co );
        }

        catch ( Exception e ){

        }


    }

    @Override
    public Entity Return() {
        return null;
    }

    //@Override
    public ArrayList<Entity> ReturnList() { return null ; }



}
