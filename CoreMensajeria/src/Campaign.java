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

    /**
     * Constructor Vacio
     */
    public Campaign() {
    }

    /**
     * Constructor de campañas sin el parametro id
     * @param nameCampaign
     * @param descCampaign
     * @param statusCampaign
     */
    public Campaign(String nameCampaign, String descCampaign, boolean statusCampaign) {
        _nameCampaign = nameCampaign;
        _descCampaign = descCampaign;
        _statusCampaign = statusCampaign;
    }

    /**
     * Constructor de campaña con todos los parametros
     * @param idCampaign
     * @param nameCampaign
     * @param descCampaign
     * @param statusCampaign
     */
    public Campaign(int idCampaign, String nameCampaign, String descCampaign, boolean statusCampaign) {
        _idCampaign = idCampaign;
        _nameCampaign = nameCampaign;
        _descCampaign = descCampaign;
        _statusCampaign = statusCampaign;
    }
}
