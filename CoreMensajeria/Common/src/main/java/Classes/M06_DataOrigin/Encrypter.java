package Classes.M06_DataOrigin;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class Encrypter {

    private String SITE_KEY = "CoreMensajeria";

    public Encrypter() { }

    public Encrypter(String SITE_KEY) {
        this.SITE_KEY = SITE_KEY;
    }

    public String encrypt(String token){
        return Hashing.sha256()
                .hashString(SITE_KEY + token, StandardCharsets.UTF_8)
                .toString();
    }
}
