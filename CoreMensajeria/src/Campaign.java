import Modulo_2.Company;

public class Campaign {

    private int _idCampaign;
    private String _nameCampaign;
    private String _descCampaign;
    private boolean _statusCampaign;
    private Company _company;

    public int get_idCampaign() {
        return _idCampaign;
    }

    public void set_idCampaign(int _idCampaign) {
        this._idCampaign = _idCampaign;
    }

    public String get_nameCampaign() {
        return _nameCampaign;
    }

    public void set_nameCampaign(String _nameCampaign) {
        this._nameCampaign = _nameCampaign;
    }

    public String get_descCampaign() {
        return _descCampaign;
    }

    public void set_descCampaign(String _descCampaign) {
        this._descCampaign = _descCampaign;
    }

    public boolean is_statusCampaign() {
        return _statusCampaign;
    }

    public void set_statusCampaign(boolean _statusCampaign) {
        this._statusCampaign = _statusCampaign;
    }

    /**
     * Constructor Vacio
     */
    public Campaign() {
    }

    /**
     * Constructor de campañas sin el parametro id
     * @param _nameCampaign
     * @param _descCampaign
     * @param _statusCampaign
     */
    public Campaign(String _nameCampaign, String _descCampaign, boolean _statusCampaign) {
        this._nameCampaign = _nameCampaign;
        this._descCampaign = _descCampaign;
        this._statusCampaign = _statusCampaign;
    }

    /**
     * Constructor de campaña con todos los parametros
     * @param _idCampaign
     * @param _nameCampaign
     * @param _descCampaign
     * @param _statusCampaign
     */
    public Campaign(int _idCampaign, String _nameCampaign, String _descCampaign, boolean _statusCampaign) {
        this._idCampaign = _idCampaign;
        this._nameCampaign = _nameCampaign;
        this._descCampaign = _descCampaign;
        this._statusCampaign = _statusCampaign;
    }
}
