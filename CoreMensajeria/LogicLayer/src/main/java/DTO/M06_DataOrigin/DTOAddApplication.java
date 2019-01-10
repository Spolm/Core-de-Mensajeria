package DTO.M06_DataOrigin;


import DTO.DTO;

public class DTOAddApplication extends DTO {

    private String _nameApplication;
    private String _descriptionApplication;
    private int _userId;
    private int _companyId;

    /**
     * Constructor para AddApplicationsData Vacio
     */
    public DTOAddApplication (){
    }

    /**
     * Constructor para AddApplicationsData con parametros
     * @param nameApplication nombre para una Application que sera recien creada
     * @param descriptionApplication descripcion para una Application que sera recien creada
     * @param userId id del usuario asociado
     * @param companyId id de la compania asociada
     */
    public DTOAddApplication(String nameApplication, String descriptionApplication, int userId, int companyId){
        _nameApplication = nameApplication;
        _descriptionApplication = descriptionApplication;
        _userId = userId;
        _companyId = companyId;
    }

    /**
     * GET
     * @return nombre de la AddApplicationData
     */
    public String get_nameApplication() {
        return _nameApplication;
    }

    /**
     * SET
     * @param nameApplication nombre de la AddApplicationData
     */
    public void set_nameApplication(String nameApplication) {
        this._nameApplication = nameApplication;
    }

    /**
     * GET
     * @return descripcion de la AddApplicationData
     */
    public String get_descriptionApplication() {
        return _descriptionApplication;
    }

    /**
     * SET
     * @param descriptionApplication descripcion de la AddApplicationData
     */
    public void set_descriptionApplication(String descriptionApplication) {
        this._descriptionApplication = descriptionApplication;
    }

    /**
     * GET
     * @return id del usuario de la AddApplicationData
     */
    public int get_userId() {
        return _userId;
    }

    /**
     * SET
     * @param _userId id del usario de la AddApplicationData
     */
    public void set_userId(int _userId) {
        this._userId = _userId;
    }

    /**
     * GET
     * @return id de la compania de la AddApplicationData
     */
    public int get_companyId() {
        return _companyId;
    }

    /**
     * SET
     * @param _companyId id de la compania de la AddApplicationData
     */
    public void set_companyId(int _companyId) {
        this._companyId = _companyId;
    }
}
