package M06_DataOrigin;

import Classes.M06_DataOrigin.Encrypter;
import com.google.common.hash.Hashing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;

public class EncrypterTest {
    private Encrypter encrypter = new Encrypter();

    @Test
    private void encryptTokenTest(){
        assertNotNull(encrypter.encryptToken("RT5453E0B1BF09A7TY020A4B87W7N0A9W003YTUEB403E918E352D10WE35007Q"));
    }

}
