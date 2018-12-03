package Classes.M01_Login;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

public class MailSender {

    static Properties _mailServerProperties;
    static Session _getMailSession;
    static MimeMessage _generateMailMessage;

    /**
     * This method allows the host to send emails for password recovery
     * @param code
     * @param email
     */
    public static void generateAndSendEmail(String code,String email) throws AddressException, MessagingException {

        try {
            File file = new File("emailCoreMensajeria.txt");



            //Write Content
            PrintStream fileStream = new PrintStream(file);
            fileStream.println("Subject: Recuperación de contraseña ");
            fileStream.println("To: "+email);
            fileStream.println(" ");
            fileStream.println(" ");
            fileStream.println("    Tu código de validación es:" + code);
            fileStream.println(" ");
            fileStream.println(" Saludos cordiales,");
            fileStream.println(" CoreMensajeria Support.");
            fileStream.close();

            Logger logger = Logger.getLogger(MailSender.class.getName());
            logger.info("path: "+file.getAbsolutePath());

            /*
            //ENVIO POR CORREO (No disponible)
            // Step1
            System.out.println("\n 1st ===> setup Mail Server Properties..");
            _mailServerProperties = System.getProperties();
            _mailServerProperties.put("mail.smtp.port", "587");
            _mailServerProperties.put("mail.smtp.auth", "true");
            _mailServerProperties.put("mail.smtp.starttls.enable", "true");
            System.out.println("Mail Server Properties have been setup successfully..");

            // Step2
            System.out.println("\n\n 2nd ===> get Mail Session..");
            _getMailSession = Session.getDefaultInstance(_mailServerProperties, null);
            _generateMailMessage = new MimeMessage(_getMailSession);
            _generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            _generateMailMessage.setSubject("Recuperación de contraseña");
            String emailBody = " Tu codigo de validacion es: " + code + "<br><br> Saludos cordiales, <br>CoreMensajeria Support";
            _generateMailMessage.setContent(emailBody, "text/html");
            System.out.println("Mail Session has been created successfully..");

            // Step3
            System.out.println("\n\n 3rd ===> Get Session and Send mail");
            Transport transport = _getMailSession.getTransport("smtp");

            // Enter your correct gmail UserID and Password
            // if you have 2FA enabled then provide App Specific Password
            transport.connect("smtp.gmail.com", "coremensajeria20182", "coremensajeria1234");
            transport.sendMessage(_generateMailMessage, _generateMailMessage.getAllRecipients());
            transport.close();
            */
        }catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}

