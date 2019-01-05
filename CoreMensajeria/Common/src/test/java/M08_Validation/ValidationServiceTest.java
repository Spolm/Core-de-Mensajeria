package M08_Validation;


import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M08_Validation.XMLManagement.Command;
import Entities.M08_Validation.XMLManagement.CommandValidateMessage;
import Entities.M08_Validation.XMLManagement.CommandValidateParameter;
import Entities.M08_Validation.XMLManagement.CommandValidateTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Exceptions.SMSTooLongException;

import java.lang.reflect.Executable;

import static  org.junit.jupiter.api.Assertions.assertThrows;
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
        assertEquals(true, c.is_valid());
    }

    @Test
    public void testTemplateDoesntExist() {
        CommandValidateParameter c = new CommandValidateTemplate(312873618);
        c.execute();
        assertEquals(false, c.is_valid());
    }

    @Test
    public void testMessageValid() {
        CommandValidateParameter c = new CommandValidateMessage(1, "esto es un mensaje de template " +
                "con + un [.$Parametro$.]", "SMS");
        c.execute();
        assertEquals(true, c.is_valid());
    }

    @Test
    public void testMessageNotValid() {
        CommandValidateParameter c = new CommandValidateMessage(2, "They say we're young and we " +
                "don't know\n" +
                "Won't find out till we grow\n" +
                "Well I don't know why that's true\n" +
                "Cause you got me baby, I got you\n" +
                "Babe, I got you babe, I got you, Babe.\n" +
                "They say our love won't pay the rent\n" +
                "Before it's earned our money's always spent\n" +
                "I guess that's so, we don't have a pot\n" +
                "But at least I'm sure of all the things we got\n" +
                "Babe, I got you babe, I got you, Babe.\n" +
                "I got flowers in the spring\n" +
                "I got you, you wear my ring\n" +
                "And when I'm sad, you're a clown\n" +
                "And if I get scared you're always around\n" +
                "And then they say your hair's too long\n" +
                "But I don't care, with you I can't do wrong\n" +
                "Then put your warm little hand in mine\n" +
                "There ain't no hill or mountain we can't climb\n" +
                "Babe, I got you babe, I got you, Babe.\n" +
                "I got you to hold my\n" +
                "I got you to understand\n" +
                "I got you to walk with me\n" +
                "I got you to talk with\n" +
                "I got you to kiss goodnight\n" +
                "I got you to hold me tight\n" +
                "I got you I won't let go\n" +
                "I got you who loves me so\n" +
                "I got you, babe", "SMS");
        assertThrows(SMSTooLongException.class, () -> c.execute());

    }
}

