package Classes.M07_Template.Test;

import Classes.M07_Template.HandlerPackage.ParameterHandler;
import Classes.M07_Template.MessagePackage.Parameter;
import Classes.Sql;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParameterHandlerTest {
    private ParameterHandler parameterHandler;

    @BeforeEach
    public void init(){
        parameterHandler = new ParameterHandler();
    }

    @Test
    public void isNotNull(){
        assertNotNull(parameterHandler);
        ArrayList<Parameter> list = new ArrayList<>();
        assertNotNull(list);
        for (int i=0;i<list.size();i++){
            assertNotNull(list.get(i));
        }
    }
    @Test
    public void postCorrectly(){
        String name="param x";
        Sql sql = new Sql();
        parameterHandler.postParameter(name);
        String query =  "select par_name from public.parameter order by par_id desc limit 1";
        try {
            ResultSet resultset = sql.sqlConn(query);
            if (resultset.next()) {
                assertNotNull(resultset.getString("par_name"));
                assertEquals(name,resultset.getString("par_name"));
                query = "delete from public.parameter where par_id = (select MAX(par_id) from public.parameter)";
                sql.sqlNoReturn(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown(){
        parameterHandler = null;
    }
}
