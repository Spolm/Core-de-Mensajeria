package M08_Validation;


import Entities.M08_Validation.XMLManagement.Command;
import Entities.M08_Validation.XMLManagement.CommandValidateParameter;
import Entities.M08_Validation.XMLManagement.CommandValidateTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ValidationServiceTest {
    /*
    static Validation i;

    @BeforeAll
    public static void before() {
        i = new Validation();
    }

    @Test
    public void testValidationFileSuccess() {

        String fileRoute ="C:\\\\archivo.json.txt";
        M08_MessageValidation validate = new M08_MessageValidation();
        assertEquals("Exito en Envio",  validate.ValidateFileParameters(fileRoute));
        System.out.println("\033[32mtestValidationFileSuccess:: Exito" );
    }

    @Test
    public void testValidationParametersSuccess() {

        String JsonParameters="{\n" +
                "\"_idTemplate\":123,\n" +
                "\"_name\":\"luis\",\n" +
                "\"_lastName\":\"gerardo\",\n" +
                "\"_message\":\"momo\",\n" +
                "\"_chanel\":\"SMS\"\n" +
                "}";
        M08_MessageValidation validate = new M08_MessageValidation();
        assertEquals("Exito en Envio",  validate.ValidateParameters(JsonParameters));
        System.out.println("\033[32mtestValidationParametersSuccess:: Exito" );
    }

    @Test
    public void testValidationFileFail() {

        String fileRoute ="C:\\\\archivofalso.json.txt";
        M08_MessageValidation validate = new M08_MessageValidation();
        assertEquals("El archivo no se encontro o no existe::"+fileRoute,  validate.ValidateFileParameters(fileRoute));
        System.out.println("\033[32mtestValidationFileFail:: Exito" );
    }

    @Test
    public void testValidationParametersFailMessage() {

        String JsonParameters="{\n" +
                "\"_idTemplate\":123,\n" +
                "\"_name\":\"luis\",\n" +
                "\"_lastName\":\"gerardo\",\n" +
                "\"_message\":\"\",\n" +
                "\"_chanel\":\"SMS\"\n" +
                "}";
        M08_MessageValidation validate = new M08_MessageValidation();
        assertEquals("Parametro de mensaje esta vacio o nulo",  validate.ValidateParameters(JsonParameters));
        System.out.println("\033[32mtestValidationParametersFailMessage::exito" );
    }

    @Test
    public void testValidationParametersFailMessageLength() {

        String JsonParameters="{\n" +
                "\"_idTemplate\":123,\n" +
                "\"_name\":\"luis\",\n" +
                "\"_lastName\":\"gerardo\",\n" +
                "\"_message\":\"mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" +
                "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" +
                "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" +
                "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" +
                "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" +
                "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" +
                "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" +
                "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm\",\n" +
                "\"_chanel\":\"SMS\"\n" +
                "}";
        M08_MessageValidation validate = new M08_MessageValidation();
        assertEquals("La longitud del sms es invalida",  validate.ValidateParameters(JsonParameters));
        System.out.println("\033[32mtestValidationParametersFailMessageLength:: Exito" );
    }

*/
    @Test
    public void testTemplateExist() {
        CommandValidateParameter c = new CommandValidateTemplate(1);
        c.execute();
        assertEquals(true,c.is_valid());
    }

    @Test
    public void testTemplateDoesntExist() {
        CommandValidateParameter c = new CommandValidateTemplate(-1);
        c.execute();
        assertEquals(false,c.is_valid());
    }
}