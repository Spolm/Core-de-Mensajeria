package M08_Validation.M08_XMLManagement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

class CommandGetMessageTest {

    private static File _file;

    @BeforeEach
    public void init(){
        ClassLoader classLoader = new CommandGetMessageTest().getClass().getClassLoader();
        _file = new File(classLoader.getResource("xml/prueba4.xml").getFile());
    }

    @Test
    public void prueba4xml(){
        System.out.println(_file.getPath());
        assertEquals("prueba4.xml", _file.getName());
    }

}