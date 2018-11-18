package Classes.M08_Validation;



public class ValidationTemplate {


    private int _idTemplate;
    private String _name;
    private String _lastName;
    private String _personId;
    private String _sendDate;
    private String _sendHour;
    private String _chanel;
    private String _message;





    //Get
    public int get_idTemplate() {
        return _idTemplate;
    }

    public String get_message() {
        return _message;
    }

    public String get_name() {
        return _name;
    }

    public String get_lastName() {
        return _lastName;
    }

    public String get_personId() {
        return _personId;
    }

    public String get_sendDate() {
        return _sendDate;
    }

    public String get_sendHour() {
        return _sendHour;
    }

    public String get_chanel() {
        return _chanel;
    }


    ///Set

    public void set_idTemplate(int _idTemplate) {
        this._idTemplate = _idTemplate;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public void set_personId(String _personId) {
        this._personId = _personId;
    }

    public void set_sendDate(String _sendDate) {
        this._sendDate = _sendDate;
    }

    public void set_sendHour(String _sendHour) {
        this._sendHour = _sendHour;
    }

    public void set_chanel(String _chanel) {
        this._chanel = _chanel;
    }

    public void set_message(String _message) {
        this._message = _message;
    }

    ///CONSTRUCTOR

    /**
     * Constructor Vacio
     */

    public ValidationTemplate() {
    }

    /*** Construtor de para Validacion de parametros de envio completos
     @param _idTemplate
     @param _name
     @param _lastName
     @param _personId
     @param _sendDate
     @param _sendHour
     @param _chanel
     @param _message
     * */
    public ValidationTemplate(int _idTemplate, String _name, String _lastName, String _personId, String _sendDate, String _sendHour, String _chanel, String _message) {
        this._idTemplate = _idTemplate;
        this._name = _name;
        this._lastName = _lastName;
        this._personId = _personId;
        this._sendDate = _sendDate;
        this._sendHour = _sendHour;
        this._chanel = _chanel;
        this._message = _message;
    }



}
