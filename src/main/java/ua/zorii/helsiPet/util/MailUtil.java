package ua.zorii.helsiPet.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {
    private static final String PASSWORD = "ejqxgmrziuumzsek";

    public static void initializeMessage(final String email, String subject, String text) {
        final Properties properties = new Properties();

        try {
            properties.load(MailUtil.class.getClassLoader().getResourceAsStream("mail.properties"));
            Session mailSession = Session.getDefaultInstance(properties);
            MimeMessage message = getMessage(email, mailSession, subject, text);

            Transport tr = mailSession.getTransport();
            tr.connect(null, PASSWORD);
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static MimeMessage getMessage(final String email, final Session mailSession, String subject, String text) throws MessagingException {
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("helsiPetUkraine@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject(subject);
        message.setText(text);
        return message;
    }
}
