package Persistence.M06_Application;

import java.util.Date;

public class Application {

    private int _idApplication;
    private String _nameApplication;
    private String _descriptionApplication;
    private String _tokenApplication;
    private Date _dateOfCreateApplication;
    private int _statusApplication;
    private int _userCreatorId;
    private int _companyId;

    /**
     * Constructor para Application Vacio
     */
    public Application(){
    }

    /**
     * Constructor para Application con parametros
     * @param idApplication id de la Application
     * @param nameApplication nombre de la Application
     * @param descriptionApplication descripcion de la Application
     * @param tokenApplication token correspondiente de la Application
     * @param dateOfCreateApplication fecha de creacion de la Application
     * @param statusApplication status de la Application (puede ser activa o pausada)
     * @param userCreatorId id del usuario creador de la Application
     * @param companyId id de la compania asociada de la Application
     */
    public Application(int idApplication, String nameApplication, String descriptionApplication,
                       String tokenApplication, Date dateOfCreateApplication, int statusApplication,
                       int userCreatorId, int companyId) {
        _idApplication = idApplication;
        _nameApplication = nameApplication;
        _descriptionApplication = descriptionApplication;
        _tokenApplication = tokenApplication;
        _dateOfCreateApplication = dateOfCreateApplication;
        _statusApplication = statusApplication;
        _userCreatorId = userCreatorId;
        _companyId = companyId;
    }

    /**
     *  GET
     * @return id de la Application
     */
    public int get_idApplication() {
        return _idApplication;
    }

    /**
     * SET
     * @param _idApplication id de la Application
     */
    public void set_idApplication(int _idApplication) {
        this._idApplication = _idApplication;
    }

    /**
     * GET
     * @return nombre de la Application
     */
    public String get_nameApplication() {
        return _nameApplication;
    }

    /**
     * SET
     * @param _nameApplication nombre de la Application
     */
    public void set_nameApplication(String _nameApplication) {
        this._nameApplication = _nameApplication;
    }

    /**
     * GET
     * @return descripcion de la Application
     */
    public String get_descriptionApplication() {
        return _descriptionApplication;
    }

    /**
     * SET
     * @param _descriptionApplication descripcion de la Application
     */
    public void set_descriptionApplication(String _descriptionApplication) {
        this._descriptionApplication = _descriptionApplication;
    }

    /**
     * GET
     * @return token de la Application
     */
    public String get_tokenApplication() {
        return _tokenApplication;
    }

    /**
     * SET
     * @param _tokenApplication token de la Application
     */
    public void set_tokenApplication(String _tokenApplication) {
        this._tokenApplication = _tokenApplication;
    }

    /**
     * GET
     * @return fecha de creacion de la Application
     */
    public Date get_dateOfCreateApplication() {
        return _dateOfCreateApplication;
    }

    /**
     * SET
     * @param _dateOfCreateApplication fecha de creacion de la Application
     */
    public void set_dateOfCreateApplication(Date _dateOfCreateApplication) {
        this._dateOfCreateApplication = _dateOfCreateApplication;
    }

    /**
     * GET
     * @return status de la Application
     */
    public int get_statusApplication() {
        return _statusApplication;
    }

    /**
     * SET
     * @param _statusApplication status de la Application
     */
    public void set_statusApplication(int _statusApplication) {
        this._statusApplication = _statusApplication;
    }

    /**
     * GET
     * @return id del usuario creador de la Application
     */
    public int get_userCreatorId() {
        return _userCreatorId;
    }

    /**
     * SET
     * @param userCreatorId id del usuario creador de la Application
     */
    public void set_userCreatorId(int userCreatorId) {
        this._userCreatorId = userCreatorId;
    }

    /**
     * GET
     * @return id de la compania asociada de la Application
     */
    public int get_companyId() {
        return _companyId;
    }

    /**
     * SET
     * @param companyId id de la compania asociada de la Application
     */
    public void set_companyId(int companyId) {
        this._companyId = companyId;
    }
}
