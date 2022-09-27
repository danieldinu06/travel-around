package controller.utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;


public class SendEmail {

    private final String senderEmailID = "barket1357";
    private final String senderPassword = ".Transfer112";
    private final String emailSMTPserver = "smtp.gmail.com";
    private final String emailServerPort = "465";

    private String receiverEmailID = null;
    private String emailSubject;
    private String emailBody;

    public SendEmail(String receiverEmailID, String emailSubject, String emailBody)
    {
        this.receiverEmailID=receiverEmailID;
        this.emailSubject=emailSubject;
        this.emailBody=emailBody;

        Properties properties = new Properties();
        setProperties(properties);

        SecurityManager security = System.getSecurityManager();
        try
        {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(properties, auth);

            MimeMessage msg = new MimeMessage(session);

            msg.setText(emailBody);
            msg.setSubject(emailSubject);
            msg.setFrom(new InternetAddress(senderEmailID));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(receiverEmailID));

            Transport.send(msg);
            System.out.println("Message send Successfully:)");
        }
        catch (Exception mex)
        {
            mex.printStackTrace();
        }
    }

    private void setProperties(Properties properties) {
        properties.put("mail.smtp.user",senderEmailID);
        properties.put("mail.smtp.host", emailSMTPserver);
        properties.put("mail.smtp.port", emailServerPort);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", emailServerPort);
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
    }
    public class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(senderEmailID, senderPassword);
        }
    }
}