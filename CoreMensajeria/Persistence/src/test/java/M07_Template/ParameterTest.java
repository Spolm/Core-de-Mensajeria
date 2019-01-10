package M07_Template;

import Entities.Entity;
import Entities.M07_Template.MessagePackage.Parameter;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.M07_Template.IDAOParameter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class ParameterTest {
    private static ArrayList<Entity> _parameters = new ArrayList<>();
    private static IDAOParameter _daoParameter = DAOAbstractFactory.getFactory().createDaoParameter();

    @BeforeAll
    public static void init(){
    }

    @Test
    public void postParametersTest(){
        try{
            String[] _parametersText = {"Parameter Test"};
            _parameters = _daoParameter.postParameter(_parametersText,1);

            assertTrue(_parameters.size() > 0);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void postParameterTest(){
        try{
            String _parametersText = "Parameter Test";
            Parameter _parameter = (Parameter) _daoParameter.postParameter(_parametersText,1);
            _daoParameter.deleteParameter( _parameter.get_id() );
            assertNotNull(_parameter);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void getParametersTest(){
        try{
            ArrayList<Parameter> _parametersTest = _daoParameter.getParameters(1);
            assertTrue(_parametersTest.size() > 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getParametersByMessageTest(){
        try{
            ArrayList<Parameter> _parametersTest = _daoParameter.getParametersByMessage(1);
            assertTrue(_parametersTest.size() > 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void destroy(){
        try{
            for (Entity _e: _parameters ) {
                Parameter _p = (Parameter) _e;
                _daoParameter.deleteParameter(_p.get_id());
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
