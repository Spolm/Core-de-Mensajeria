package Classes.M03_Campaign;

import Classes.M02_Company.Company;

import java.util.Date;

public class Campaign {

    private int _idCampaign;
    private String _nameCampaign;
    private String _descCampaign;
    private boolean _statusCampaign;
    private Date _startCampaign;
    private Date _endCampaign;
    private Company _company;

    public int get_idCampaign() {
        return _idCampaign;
    }

    public void set_idCampaign(int idCampaign) {
        _idCampaign = idCampaign;
    }

    public String get_nameCampaign() {
        return _nameCampaign;
    }

    public void set_nameCampaign(String nameCampaign) {
        _nameCampaign = nameCampaign;
    }

    public String get_descCampaign() {
        return _descCampaign;
    }

    public void set_descCampaign(String descCampaign) {
        _descCampaign = descCampaign;
    }

    public boolean is_statusCampaign() {
        return _statusCampaign;
    }

    public void set_statusCampaign(boolean statusCampaign) {
        _statusCampaign = statusCampaign;
    }

    public Date get_startCampaign() {
        return _startCampaign;
    }

    public void set_startCampaign(Date startCampaign) {
        _startCampaign = startCampaign;
    }

    public Date get_endCampaign() {
        return _endCampaign;
    }

    public void set_endCampaign(Date endCampaign) {
        _endCampaign = endCampaign;
    }

    /**
     * Constructor Vacio
     */
    public Campaign() {
    }

    /**
     * Constructor para campañas sin id
     * @param nameCampaign nombre para una campaña recien creada
     * @param descCampaign descripcion de la campaña
     * @param statusCampaign el estatus de la campaña, puede ser activada o desactivada
     * @param startCampaign fecha de inicio de la campaña
     * @param endCampaign fecha de finalización de la campaña
     */
    public Campaign(String nameCampaign, String descCampaign, boolean statusCampaign, Date startCampaign, Date endCampaign) {
        _nameCampaign = nameCampaign;
        _descCampaign = descCampaign;
        _statusCampaign = statusCampaign;
        _startCampaign = startCampaign;
        _endCampaign = endCampaign;
    }

    /**
     *
     * @param idCampaign
     * @param nameCampaign
     * @param descCampaign
     * @param statusCampaign
     * @param startCampaign
     * @param endCampaign
     */
    public Campaign(int idCampaign, String nameCampaign, String descCampaign, boolean statusCampaign, Date startCampaign, Date endCampaign) {
        _idCampaign = idCampaign;
        _nameCampaign = nameCampaign;
        _descCampaign = descCampaign;
        _statusCampaign = statusCampaign;
        _startCampaign = startCampaign;
        _endCampaign = endCampaign;
    }

    /**
     *
     * @param idCampaign
     * @param nameCampaign
     * @param descCampaign
     * @param statusCampaign
     * @param startCampaign
     * @param endCampaign
     * @param company
     */
    public Campaign (int idCampaign, String nameCampaign, String descCampaign, boolean statusCampaign, Date startCampaign, Date endCampaign, Company company) {
        _idCampaign = idCampaign;
        _nameCampaign = nameCampaign;
        _descCampaign = descCampaign;
        _statusCampaign = statusCampaign;
        _startCampaign = startCampaign;
        _endCampaign = endCampaign;
        _company = company;
    }
}
