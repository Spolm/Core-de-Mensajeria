package M07_Template;

import Entities.M07_Template.PlanningPackage.Planning;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.M07_Template.IDAOPlanning;
import Persistence.M07_Template.IDAOTemplate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlanningDAOTest {
    private static int _templateId;
    private static Planning _planning = null;
    private static IDAOPlanning _daoPlanning = DAOAbstractFactory.getFactory().createDaoPlanning();
    private static IDAOTemplate _daoTemplate = DAOAbstractFactory.getFactory().createDaoTemplate();

    @BeforeAll
    public static void init(){
        String[] _parameters = {"2019-01-09","2019-01-26","22:22","23:03"};
        _templateId = _daoTemplate.postTemplate(1,1,1);
        _planning = (Planning)_daoPlanning.postPlanning(_parameters, _templateId);
    }

    @Test
    public void postPlanningTest(){
        try{
            String[] _parameters = {"2019-01-09","2019-01-26","22:22","23:03"};
            int _templateIdTest = _daoTemplate.postTemplate(1,1,1);

            Planning _planningTest = (Planning)_daoPlanning.postPlanning(_parameters, _templateId);

            _daoPlanning.deletePlanning(_templateIdTest);
            _daoTemplate.deleteTemplate(_templateIdTest);

            assertNotNull(_planningTest);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePlanningTest(){
        try{
            String[] _parameters = {"2019-01-09","2019-01-30","22:22","23:03"};
            _daoPlanning.updatePlanning(_parameters,_templateId);
            Planning _planningTest = (Planning)_daoPlanning.getPlanning(_templateId);

            assertEquals("2019-01-30",_planningTest.getEndDate().toString());
        }catch ( Exception e ){
            e.printStackTrace();
        }
    }

    @Test
    public void getPlanningTest(){
        try{
            Planning _p = (Planning) _daoPlanning.getPlanning(_templateId);
            assertNotNull(_p);
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void destroy(){
        try{
            _daoPlanning.deletePlanning(_templateId);
            _daoTemplate.deleteTemplate(_templateId);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
