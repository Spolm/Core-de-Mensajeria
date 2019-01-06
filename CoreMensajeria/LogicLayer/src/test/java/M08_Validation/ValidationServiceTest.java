package M08_Validation;

import Logic.Command;
import Logic.CommandsFactory;

import Logic.M08_Validation.CommandValidateTemplate;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.jupiter.api.Test;
import Exceptions.SMSTooLongException;

import static  org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ValidationServiceTest {

    @Test
    public void testTemplateExist() {
        Command c = CommandsFactory.createCommandValidateTemplate(1);
        try{
            c.execute();
            assertEquals(true, c.Return());
        } catch (Exception e) {}

    }

    @Test
    public void testTemplateDoesntExist() {
        Command c = new CommandsFactory().createCommandValidateTemplate(312873618);
        try {
            c.execute();
            assertEquals(false, c.Return());

        } catch (Exception e) {}
    }

    @Test
    public void testMessageValid() {
        Command c = CommandsFactory.createCommandValidateMessage(1, "esto es un mensaje de template " +
                "con + un [.$Parametro$.]", "SMS");
        try {
            c.execute();
            assertEquals(true, c.Return());
        } catch (Exception e) {}
    }

    @Test
    public void testMessageNotValid() {
        Command c = CommandsFactory.createCommandValidateMessage(2, "They say we're young and we " +
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

