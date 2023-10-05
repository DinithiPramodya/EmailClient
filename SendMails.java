package emailclient;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMails {

    public static void sendEmails(String recipient, String subject, String content) {

        final String username = "pramodyamad.20@uom.lk";
        final String password = "#sanNfran5";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "submit.uom.lk");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pramodyamad.20@uom.lk"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient)
            );
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Email sent.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    

}