package M07_Template;


import Persistence.Factory.DAOAbstractFactory;
import Persistence.M07_Template.IDAOStatus;
import Persistence.M07_Template.IDAOTemplate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;

public class StatusDAOTest {

    private static IDAOStatus _daoStatus = DAOAbstractFactory.getFactory().createDAOStatus();
    private static IDAOTemplate _daoTemplate = DAOAbstractFactory.getFactory().createDaoTemplate();
    private static int _templateId = 0;


    @BeforeAll
    public static void init(){
        try{
            _templateId = _daoTemplate.postTemplate(1,1,1);

        }catch ( Exception e ){
            e.printStackTrace();
        }
    }

    @Test
    public void postTemplateStatusNotApproved(){
        try{
            Boolean success = _daoStatus.postTemplateStatusNotApproved(_templateId);
            assertTrue(success);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void postTemplateStatusApproved(){
        try{
            Boolean success = _daoStatus.postTemplateStatusApproved(_templateId, 1);
            assertTrue(success);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void destroy(){
        try{
            _daoStatus.deleteStatusTemplate(_templateId);
            _daoTemplate.deleteTemplate(_templateId);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
