package M07_Template;


import Entities.Entity;
import Entities.M01_Login.Privilege;
import Entities.M03_Campaign.Campaign;
import Entities.M05_Channel.Channel;
import Entities.M06_DataOrigin.Application;
import Entities.M07_Template.Template;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;
import Persistence.DAOFactory;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.M07_Template.IDAOTemplate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TemplateDAOTest {
    private static String json;
    private static Template _template;
    private static IDAOTemplate _daoTemplate = DAOAbstractFactory.getFactory().createDaoTemplate();

    @BeforeAll
    public static void init(){
        json = "{\n" +
                "    \"campaign\": 1,\n" +
                "    \"applicationId\": 1,\n" +
                "    \"userId\": 1,\n" +
                "    \"newParameters\": [\"New 52\"],\n" +
                "    \"company\": 1,\n" +
                "    \"parameters\": [\n" +
                "        \"Monto\"\n" +
                "    ],\n" +
                "    \"message\": \"Probando [.$Monto$.]\",\n" +
                "    \"channel_integrator\": [{\n" +
                "        \"channel\": {\n" +
                "            \"idChannel\": 1\n" +
                "        },\n" +
                "        \"integrator\": {\n" +
                "            \"idIntegrator\": 1\n" +
                "        }\n" +
                "    }],\n" +
                "    \"planning\": [\n" +
                "        \"2019-01-09\",\n" +
                "        \"2019-01-21\",\n" +
                "        \"22:22\",\n" +
                "        \"23:02\"\n" +
                "    ]\n" +
                "}";
        _template = (Template) _daoTemplate.postTemplateData(json);

    }

    @Test
    public void postTemplateDataTest(){
        try{
            Template _templateTest = (Template) _daoTemplate.postTemplateData(json);
            _daoTemplate.deleteTemplate(_templateTest.get_id());
            assertNotNull(_templateTest,String.valueOf(_template.get_id()));
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void postTemplateTest(){
        try {
            int _templateId = _daoTemplate.postTemplate(1,1,1);
            _daoTemplate.deleteTemplate( _templateId );
            assertTrue(_templateId > 0);
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }
    }

    @Test
    public void updateTemplateDataTest(){
        try{
            json = "{\n" +
                    "    \"templateId\": "+ _template.get_id() +",\n" +
                    "    \"campaign\": 1,\n" +
                    "    \"applicationId\": 1,\n" +
                    "    \"userId\": 1,\n" +
                    "    \"newParameters\": [\"New 52\"],\n" +
                    "    \"company\": 1,\n" +
                    "    \"parameters\": [\n" +
                    "        \"Monto\"\n" +
                    "    ],\n" +
                    "    \"message\": \"Probando [.$Monto$.]\",\n" +
                    "    \"channel_integrator\": [{\n" +
                    "        \"channel\": {\n" +
                    "            \"idChannel\": 1\n" +
                    "        },\n" +
                    "        \"integrator\": {\n" +
                    "            \"idIntegrator\": 1\n" +
                    "        }\n" +
                    "    }],\n" +
                    "    \"planning\": [\n" +
                    "        \"2019-01-09\",\n" +
                    "        \"2019-01-21\",\n" +
                    "        \"22:22\",\n" +
                    "        \"23:02\"\n" +
                    "    ]\n" +
                    "}";
            boolean success = DAOFactory.instaciateDaoTemplate().updateTemplateData(json);
            assertTrue(success);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateTemplateTest(){
        try {
            _daoTemplate.updateTemplate(2,1,_template.get_id());
            _template = (Template)_daoTemplate.get(_template.get_id());

            assertEquals(2,_template.getCampaign().get_idCampaign());
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }
    }

    @Test
    public void getTest(){
        try {
            Template _t = (Template) _daoTemplate.get(_template.get_id());
            assertNotNull(_t);
        } catch (MessageDoesntExistsException e) {
            e.printStackTrace();
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        } catch (ParameterDoesntExistsException e) {
            e.printStackTrace();
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        } catch ( TemplateDoesntExistsException e){
            e.printStackTrace();
        }
    }

    @Test
    public void templateDoesntExistsExceptionTest(){
        try {
            assertThrows(TemplateDoesntExistsException.class, () -> {
                _daoTemplate.get(100000);
            });
        } catch ( Exception e) {
            e.printStackTrace();
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }
    }

    @Test
    public void getAllTest(){
        try {
            ArrayList<Entity> list = DAOFactory.instaciateDaoTemplate().getAll();
            assertTrue(list.size()>=1);
        } catch (MessageDoesntExistsException e) {
            e.printStackTrace();
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        } catch (ParameterDoesntExistsException e) {
            e.printStackTrace();
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }

    }

    @Test
    public void getCampaignByTemplateTest(){
        try {
            Campaign _c = (Campaign) _daoTemplate.getCampaignByTemplate(_template.get_id());
            assertEquals(1,_c.get_idCampaign());
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }
    }

    @Test
    public void getTemplatesByCampaignTest(){
        try {
            ArrayList<Template> _t = _daoTemplate.getTemplatesByCampaign(3, 1);
            assertTrue(_t.size()>=1);
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }
    }

    @Test
    public void getApplicationByTemplateTest(){
        try {
            Application _app = _daoTemplate.getApplicationByTemplate(_template.get_id());
            assertEquals(1,_app.get_idApplication());
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }
    }

    @Test
    public void getChannelsByTemplateTest(){
        try {
            ArrayList<Channel> _c = _daoTemplate.getChannelsByTemplate(_template.get_id());
            assertTrue(_c.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }
    }

    @Test
    public void getTemplatePrivilegesByUserTest(){
        try {
            ArrayList<Privilege> _p = _daoTemplate.getTemplatePrivilegesByUser(3,1);
            assertTrue(_p.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }
    }

    @Test
    public void getCampaignsByUserOrCompanyTest(){
        try {
            ArrayList<Campaign> _c = _daoTemplate.getCampaignsByUserOrCompany(3,1);
            assertTrue(_c.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }
    }

    @AfterAll
    public static void destroy(){
        try {
            _daoTemplate.deleteTemplate(_template.get_id());
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }
    }


}
