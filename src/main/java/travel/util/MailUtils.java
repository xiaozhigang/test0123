package travel.util;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public final class MailUtils {
    private static final String USER = "";
    private static final String PASSWORD = "";

    public static boolean sendMail(String to, String text, String title) {
        try {
            final Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.host", "smtp.qq.com");

            properties.put("mail.user", USER);
            properties.put("mail.password", PASSWORD);

            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = properties.getProperty("mail.user");
                    String password = properties.getProperty("mail.password");

                    return new PasswordAuthentication(userName, password);
                }
            };
            Session mailSessin = Session.getInstance(properties, authenticator);
            MimeMessage mimeMessage = new MimeMessage(mailSessin);
            String userName = properties.getProperty("mail.user");

            InternetAddress from = new InternetAddress(userName);
            InternetAddress toAddress = new InternetAddress(to);

            mimeMessage.setFrom(from);
            mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);
            mimeMessage.setSubject(title);
            mimeMessage.setContent(text, "text/html;charset=UTF-8");
            Transport.send(mimeMessage);

            return true;
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
