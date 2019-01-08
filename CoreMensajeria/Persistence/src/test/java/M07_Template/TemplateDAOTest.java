package M07_Template;


import Persistence.DAOFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Console;
import java.util.ArrayList;

class TemplateDAOTest {
    private static String json;

    @BeforeAll
    public static void init(){
        json = "{\n" +
                "    \"campaign\": 1,\n" +
                "    \"applicationId\": 1,\n" +
                "    \"userId\": 1,\n" +
                "    \"newParameters\": [\"New 22\"],\n" +
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
    }

    @Test
    public void createTemplateTest(){
        try{
            boolean success = DAOFactory.instaciateDaoTemplate().postTemplateData(json);
            assertTrue(success);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
