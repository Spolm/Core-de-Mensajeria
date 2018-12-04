package Classes.M06_DataOrigin;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Encrypter {

    private String SITE_KEY = "CoreMensajeria";

    /**
     * Constructor de Encrypter Vacio
     */
    public Encrypter() {
    }

    /**
     * Constructor de Encrypter con parametros
     * @param SITE_KEY texto CoreMensajeria
     */
    public Encrypter(String SITE_KEY) {
        this.SITE_KEY = SITE_KEY;
    }

    //Return a String with encrypted Token
    /**
     *
     * @param token token de la Application
     * @return un string con el token encryptado
     */
    public String encryptToken(String token){
        return Hashing.sha256()
                .hashString(SITE_KEY + token, StandardCharsets.UTF_8)
                .toString();
    }

    //Get an string with the current DateTime
    /**
     *
     * @return un string con el tiempo actual (en el formato declarado)
     */
    static String getCurrentTime() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

    static String getCurrentDatePathFormat() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("ddMyyyyHHmmss");
        return dateFormat.format(date);
    }
}
