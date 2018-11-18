package Classes.M08_Validation;



public class ValidationParameter {

    private int _idTemplate;
    private String _name;
    private String _lastName;
    private String _message;
    private  String _chanel;

    //GET
    public int get_idTemplate() {
        return _idTemplate;
    }

    public String get_name() {
        return _name;
    }

    public String get_lastName() {
        return _lastName;
    }

    public String get_message() {
        return _message;
    }

    public String get_chanel() {
        return _chanel;
    }

    public void set_idTemplate(int _idTemplate) {
        this._idTemplate = _idTemplate;
    }

    //SET
    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public void set_message(String _message) {
        this._message = _message;
    }

    public void set_canal(String _chanel) {
        this._chanel = _chanel;
    }

    //CONSTRUCTOR

    /**
     * Constructor Vacio
     */

    public ValidationParameter() {
    }

    /*** Construtor de para Validacion de parametros de envio completos
     * @param _name
     * @param _chanel
     * @param _lastName
     * @param _message
     * @param _idTemplate
     * */
    public ValidationParameter(int _idTemplate, String _name, String _lastName, String _message, String _chanel) {
        this._idTemplate = _idTemplate;
        this._name = _name;
        this._lastName = _lastName;
        this._message = _message;
        this._chanel =_chanel;
    }

}
