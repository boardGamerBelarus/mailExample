package mailApiCore;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Created by barkou on 12/17/2016.
 */
class SessionContainer {

    private String email = null;
    private String password = null;
    private static Session session = null;

    private SessionContainer(String[] from) {
        email = from[0];
        password = from[1];
    }


    private void setUpProperty() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);//change accordingly
                    }
                });
    }
    static Session getSession(String[] from) {
        if (session == null) {
            new SessionContainer(from).setUpProperty();
            return session;
        } else {
            return session;
        }
    }
}
