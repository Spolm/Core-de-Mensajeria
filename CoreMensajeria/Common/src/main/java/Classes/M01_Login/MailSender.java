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
import java.util.Properties;

public class MailSender {

    static Properties _mailServerProperties;
    static Session _getMailSession;
    static MimeMessage _generateMailMessage;

    /**
     * This method allows the host to send emails for password recovery
     * @param code
     * @param email
     * @throws AddressException
     * @throws MessagingException
     */
    public static void generateAndSendEmail(String code,String email) throws AddressException, MessagingException {

        try {

            File file = new File("C:/Users/marco/Downloads/testFile1.txt");

//Create the file
            if (file.createNewFile())
            {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

            try {
                file.createNewFile();
                System.out.println(file.getPath());
                System.out.println(file.getAbsolutePath());
                System.out.println(file.getCanonicalPath());
            } catch (Exception e) {
                e.printStackTrace();
            }

//Write Content
            PrintStream fileStream = new PrintStream(file);
            fileStream.println("Subject: Recuperación de contraseña ");
            fileStream.println(" ");
            fileStream.println("   Tu código de validación es:" + code);
            fileStream.println(" Saludos cordiales,");
            fileStream.println(" CoreMensajeria Support.");
            System.out.println(file.getAbsolutePath());
            /*
            FileWriter writer = new FileWriter(file);
            writer.write("Recuperación de contraseña  Tu codigo de validacion es:" + code +"<br><br> Saludos cordiales, <br>CoreMensajeria Support");
*/
            //writer.close();
            /*
            PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
            writer.println("The first line");
            writer.println("The second line");
            writer.close();*/
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
        }catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}

