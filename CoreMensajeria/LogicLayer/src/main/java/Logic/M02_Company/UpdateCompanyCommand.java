package Logic.M02_Company;

import Entities.Entity;
import Logic.Command;

public class UpdateCompanyCommand extends Command {
    private static Entity _co;

    public  UpdateCompanyCommand ( Entity _company ){
        this._co = _company;
    }

    @Override
    public void execute() throws Exception {

    }

    @Override
    public Entity Return() {
        return null;
    }
}
