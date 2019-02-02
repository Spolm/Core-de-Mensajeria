package M08_SendMessage;

import DTO.DTO;
import DTO.M08_DTO.ParametersDTO;
import Entities.M08_Validation.XMLManagement.Message;
import Entities.M08_Validation.XMLManagement.ParameterXML;
import org.junit.jupiter.api.Test;

import javax.ws.rs.WebApplicationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static  org.junit.jupiter.api.Assertions.assertThrows;

import webService.M08_MessageCoreManagement.M08_MessageValidation;

import java.util.ArrayList;

public class M08_MessageValidationTest {

    @Test
    public void testDtoEmpty(){
        M08_MessageValidation service = new M08_MessageValidation();
        assertThrows(WebApplicationException.class,() -> service.sendMessage(null));
    }
}
