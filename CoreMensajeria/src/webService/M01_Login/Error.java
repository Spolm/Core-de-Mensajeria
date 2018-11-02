package webService.M01_Login;

import java.util.Map;

public class Error {
    String _error;
    Map<String,String> _errors;

    public Error(String _error) {
        this._error = _error;
    }

    public Error() {
    }

    public String getError() {
        return _error;
    }

    public void set_error(String _error) {
        this._error = _error;
    }

    public Map<String, String> get_errors() {
        return _errors;
    }

    public void set_errors(Map<String, String> _errors) {
        this._errors = _errors;
    }

    public void addError(String name, String description) {
        _errors.put(name,description);
    }

    public boolean get_error() {
        if(_errors.isEmpty()) return false;
        return true;
    }

}
