package Logic.M08_Validation;

import Logic.Command;

public abstract class CommandValidateParameter extends Command {
    private String _response;
    private boolean _valid;

    @Override
    public abstract void execute();

    public String get_response() {
        return _response;
    }

    public boolean is_valid() {
        return _valid;
    }

    public void set_response(String _response) {
        this._response = _response;
    }

    public void set_valid(boolean _valid) {
        this._valid = _valid;
    }
}
