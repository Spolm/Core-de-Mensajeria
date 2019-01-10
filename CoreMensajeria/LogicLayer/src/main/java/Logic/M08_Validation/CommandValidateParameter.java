package Logic.M08_Validation;

import Logic.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class CommandValidateParameter extends Command<Boolean> {
    private boolean _valid;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    @Override
    public abstract void execute() throws Exception;

    @Override
    public abstract Boolean Return();

    public void set_valid(boolean _valid) {
        this._valid = _valid;
    }

    public boolean get_valid() {
        return this._valid;
    }


}
