package Logic.M08_Validation;

import Logic.Command;

public abstract class CommandValidateParameter extends Command<Boolean> {
    private String _response;
    private boolean _valid;

    @Override
    public abstract void execute() throws Exception;

    public String get_response() {
        return _response;
    }

    @Override
    public abstract Boolean Return();


    public void set_response(String _response) {
        this._response = _response;
    }

    public void set_valid(boolean _valid) {
        this._valid = _valid;
    }

    public boolean get_valid() {
        return this._valid;
    }


}
