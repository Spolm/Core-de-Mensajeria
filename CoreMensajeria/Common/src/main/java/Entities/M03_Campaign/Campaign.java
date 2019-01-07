package Entities.M03_Campaign;

import Entities.Entity;
import Entities.M02_Company.Company;

import java.util.Date;

public class Campaign extends Entity {

    private int _idCampaign;
    private String _nameCampaign;
    private String _descCampaign;
    private boolean _statusCampaign;
    private Date _startCampaign;
    private Date _endCampaign;
    private int _idCompany;
    private Company _company;
    private boolean _habilitado; // Anadido por la estructura del mapper

    /**
     * Constructor Vacio
     */
    public Campaign() {
    }

    /**
     * Constructor para campañas sin id y sin Company
     * @param nameCampaign nombre para una campaña recien creada
     * @param descCampaign descripcion de la campaña
     * @param statusCampaign el estatus de la campaña, puede ser activada o desactivada
     * @param startCampaign fecha de inicio de la campaña
     * @param endCampaign fecha de finalización de la campaña
     */
    public Campaign( String nameCampaign, String descCampaign, boolean statusCampaign, Date startCampaign,
                     Date endCampaign ) {
        _nameCampaign = nameCampaign;
        _descCampaign = descCampaign;
        _statusCampaign = statusCampaign;
        _startCampaign = startCampaign;
        _endCampaign = endCampaign;
    }

    /**
     * Constructor para campañas sin Company
     * @param idCampaign   id de la campaña
     * @param nameCampaign nombre para una campaña recien creada
     * @param descCampaign descripcion de la campaña
     * @param statusCampaign el estatus de la campaña, puede ser activada o desactivada
     * @param startCampaign fecha de inicio de la campaña
     * @param endCampaign fecha de finalización de la campaña
     */
    public Campaign( int idCampaign, String nameCampaign, String descCampaign, boolean statusCampaign,
                    Date startCampaign, Date endCampaign ) {
        _idCampaign = idCampaign;
        _nameCampaign = nameCampaign;
        _descCampaign = descCampaign;
        _statusCampaign = statusCampaign;
        _startCampaign = startCampaign;
        _endCampaign = endCampaign;
    }

    /**
     * Constructor para campañas
     * @param idCampaign   id de la campaña
     * @param nameCampaign nombre para una campaña recien creada
     * @param descCampaign descripcion de la campaña
     * @param statusCampaign el estatus de la campaña, puede ser activada o desactivada
     * @param startCampaign fecha de inicio de la campaña
     * @param endCampaign fecha de finalización de la campaña
     * @param idCompany objeto del tipo compania
     */
    public Campaign ( int idCampaign, String nameCampaign, String descCampaign, boolean statusCampaign,
                     Date startCampaign, Date endCampaign, int idCompany ) {
        _idCampaign = idCampaign;
        _nameCampaign = nameCampaign;
        _descCampaign = descCampaign;
        _statusCampaign = statusCampaign;
        _startCampaign = startCampaign;
        _endCampaign = endCampaign;
        _idCompany = idCompany;
    }

    /**
     * Constructor para campañas
     * @param idCampaign   id de la campaña

     */
    public Campaign ( int idCampaign ) {
        _idCampaign = idCampaign;

    }



    /**
     * Constructor para campañas
     * @param idCampaign   id de la campaña
     * @param statusCampaign el estatus de la campaña, puede ser activada o desactivada
     */
    public Campaign ( int idCampaign ,boolean statusCampaign ){
        _idCampaign = idCampaign;
        _statusCampaign = statusCampaign;

    }


    public int get_idCampaign() {
        return _idCampaign;
    }


    public int get_idCompany() {
        return _idCompany;
    }

    public void set_idCampaign( int idCampaign ) {
        this._idCampaign = idCampaign;
    }

    public String get_nameCampaign() {
        return _nameCampaign;
    }

    public void set_nameCampaign( String nameCampaign ) {
        this._nameCampaign = nameCampaign;
    }

    public String get_descCampaign() {
        return _descCampaign;
    }

    public void set_descCampaign( String descCampaign ) {
        this._descCampaign = descCampaign;
    }

    public boolean get_statusCampaign() {
        return _statusCampaign;
    }

    public void set_statusCampaign( boolean statusCampaign ) {
        this._statusCampaign = statusCampaign;
    }

    public Date get_startCampaign() {
        return _startCampaign;
    }

    public void set_startCampaign( Date startCampaign ) {
        this._startCampaign = startCampaign;
    }

    public Date get_endCampaign() {
        return _endCampaign;
    }

    public void set_endCampaign( Date endCampaign ) {
        this._endCampaign = endCampaign;
    }

    public Company get_company() { return _company; }

    public void set_company( Company _company ) { this._company = _company; }



}
