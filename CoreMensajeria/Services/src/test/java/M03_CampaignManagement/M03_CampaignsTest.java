package M03_CampaignManagement;

import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;
import Entities.M03_Campaign.CampaignDAO;
import Exceptions.CampaignDoesntExistsException;
import Persistence.DAOFactory;
import com.google.gson.Gson;

import java.util.ArrayList;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webService.M03_CampaignManagement.M03_Campaigns;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class M03_CampaignsTest {

    private ArrayList<Company> _caList;
    private Campaign _ca;
    private CampaignDAO _coMng = new CampaignDAO();


    /**
     * Pruebas unitarias para el Metodo getCampaignsByUser en web service M03_Campaigns
     *
     * */
    @Test
    void getCampaignsByUserTest() throws CampaignDoesntExistsException {

        try {
            M03_Campaigns _instance = new M03_Campaigns();
            Response _salida =  _instance.getCampaignsByUser(1);
            assertEquals(200, _salida.getStatus() );
            assertNotNull( _salida.getEntity());
        } catch ( Exception e ){
            e.printStackTrace();
            throw new CampaignDoesntExistsException( e );
        }
    }


    /**
     * Pruebas unitarias para el Metodo getCampaignsByCompanyUser en web service M03_Campaigns
     *
     * */
    @Test
    void getCampaignsByCompanyUserTest()throws CampaignDoesntExistsException{

        try {
            M03_Campaigns _instance = new M03_Campaigns();
            Response _salida =  _instance.getCampaignsByCompanyUser(1,1);
            assertEquals(202, _salida.getStatus() );
            assertNotNull( _salida.getEntity());
        } catch ( Exception e ){
            e.printStackTrace();
            throw new CampaignDoesntExistsException( e );
        }
    }

    /**
     * Pruebas unitarias para el Metodo getCampaignDetails en web service M03_Campaigns
     *
     * */
    @Test
    void getCampaignDetailsTest() throws CampaignDoesntExistsException{

        try {
            M03_Campaigns _instance = new M03_Campaigns();
            Response _salida =  _instance.getCampaignDetails(1);
            assertEquals(202, _salida.getStatus() );
            assertNotNull( _salida.getEntity());
        } catch ( Exception e ){
            e.printStackTrace();
            throw new CampaignDoesntExistsException( e );
        }
    }

    @Test
    void D() throws CampaignDoesntExistsException{



    }

}
