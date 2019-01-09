package Persistence.M03_Campaign;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;
import Entities.Sql;
import Exceptions.CompanyDoesntExistsException;
import Persistence.DAOFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DAOCampaignsTest {

    private Connection _conn = Sql.getConInstance();
    private DAOCampaign _dao;

    @BeforeEach
    void setUp(){

        _dao = DAOFactory.instanciateDaoCampaign();

    }

    @AfterEach
    void tearDown() {

        _dao = null;
    }

    /**
     * Pruebas unitarias para el Metodo campaignById en DAOCampaign
     *
     * */
    @Test
    void campaignByIdTest(){

        Campaign _com = EntityFactory.CreateCampaignId(1);
        try {
            Entity _e = _dao.campaignById(_com);
            assertNotNull(_e);
            assertEquals(((Campaign) _com).get_idCampaign(), ((Campaign) _e).get_idCampaign());

        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }



    /**
     * Pruebas unitarias para el Metodo campaignListByUserTest en DAOCampaign
     *
     * */
    @Test
    void campaignListByUserTest(){
        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        Entity _user = EntityFactory.CreateCompanyOnlyId(1);
        try {

            ArrayList<Entity> _campaignList = _dao.campaignListByUser( _user );
            assertNotNull(_campaignList);
            assertEquals(15, _campaignList.size());
        }
        catch (Exception e ){
            e.printStackTrace();
        }

    }

    /**
     * Pruebas unitarias para el Metodo changeStatusCampaign en DAOCampaign
     *
     * */
    @Test
    void changeStatusCampaignTest() {

        Entity _camp = EntityFactory.CreateCampaignIDStatus(1, true) ;

        try {
            _dao.changeStatusCampaign( _camp );
        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }


    /**
     * Pruebas unitarias para el Metodo campaignListByUserCompany en DAOCampaign
     *
     * */
    @Test
    void campaignListByUserCompanyTest(){

        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        Entity _camp = EntityFactory.CreateFullCompany(1 , "S" , "DS" ,
                                                        true , "d",1);

        try {


            ArrayList<Entity> _campList = _dao.campaignListByUserCompany(_camp);

            assertNotNull(_campList);
            assertEquals(5, _campList.size());

            assertEquals(((Campaign) _campList.get(0)).get_idCompany(), (1));
            assertEquals(((Campaign) _campList.get(1)).get_idCompany(), (1));
            assertEquals(((Campaign) _campList.get(2)).get_idCompany(), (1));
            assertEquals(((Campaign) _campList.get(3)).get_idCompany(), (1));
            assertEquals(((Campaign) _campList.get(4)).get_idCompany(), (1));

          //  assertEquals(((Campaign) _campList.get(0)).get_nameCampaign(), ("Campaign 2"));
          //  assertEquals(((Campaign) _campList.get(1)).get_nameCampaign(), ("Campaign 5"));
          //  assertEquals(((Campaign) _campList.get(2)).get_nameCampaign(), ("Campaign 4"));
          //  assertEquals(((Campaign) _campList.get(3)).get_nameCampaign(), ("Campaign 1"));
           // assertEquals(((Campaign) _campList.get(4)).get_nameCampaign(), ("Campaign 3"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    

}
