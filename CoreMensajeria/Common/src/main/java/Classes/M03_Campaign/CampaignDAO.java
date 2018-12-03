package Classes.M03_Campaign;

import Classes.Sql;
import Exceptions.CampaignDoesntExistsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CampaignDAO {

    private Connection conn = Sql.getConInstance();

    //region API detalle Campaña

    public Campaign getDetails (int id) throws CampaignDoesntExistsException {

        String select = "SELECT * FROM campaign where cam_id = ?";
        Campaign ca = new Campaign();
        try {

            PreparedStatement ps = conn.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                ca.set_idCampaign(result.getInt("cam_id"));
                ca.set_nameCampaign(result.getString("cam_name"));
                ca.set_descCampaign(result.getString("cam_description"));
                ca.set_startCampaign(result.getDate("cam_start_date"));
                ca.set_endCampaign(result.getDate("cam_end_date"));
                ca.set_statusCampaign(result.getBoolean("cam_status"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new CampaignDoesntExistsException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ca;
    }

    //endregion

    //region Obtener Lista de Campañas
    public void getCampaignList(int id, String select, ArrayList<Campaign> caList) throws CampaignDoesntExistsException {
        try {
            PreparedStatement ps = conn.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while(result.next()){
                Campaign ca = new Campaign();
                ca.set_idCampaign(result.getInt("cam_id"));
                ca.set_nameCampaign(result.getString("cam_name"));
                ca.set_descCampaign(result.getString("cam_description"));
                ca.set_startCampaign(result.getDate("cam_start_date"));
                ca.set_endCampaign(result.getDate("cam_end_date"));
                ca.set_statusCampaign(result.getBoolean("cam_status"));
                caList.add(ca);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new CampaignDoesntExistsException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    //endregion

    //region API Obtener Campañas por compañia

    public ArrayList<Campaign> campaignList(int id) throws CampaignDoesntExistsException {
        String select = "SELECT * FROM campaign where cam_company_id = ?";
        ArrayList<Campaign> caList= new ArrayList<>();
        getCampaignList(id, select, caList);
        return caList;
    }
    //endregion

    //region Campaña por Usuario
    public ArrayList<Campaign> campaignListByUser(int id) throws CampaignDoesntExistsException {
        String select = "SELECT DISTINCT  ca.* from \"campaign\" ca INNER JOIN " +
                "\"company\" co ON ca.cam_company_id = co.com_id INNER JOIN \"user\" " +
                "u ON co.com_user_id = ? ORDER BY  cam_id";
        ArrayList<Campaign> caList= new ArrayList<>();

        getCampaignList(id, select, caList);
        return caList;
    }
    //endregion

    public boolean changeStatusCampaign (int id) throws CampaignDoesntExistsException {

        Campaign ca = new Campaign();

        try {
            ca = getDetails(id);
            ca.set_statusCampaign(!ca.is_statusCampaign());
            String query = "UPDATE public.campaign SET" +
                    " cam_status ="+ca.is_statusCampaign()+
                    " WHERE cam_id =?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ps.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new CampaignDoesntExistsException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ca.is_statusCampaign();
    }

}
