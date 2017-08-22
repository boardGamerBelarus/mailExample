package mailApiCore;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CreateMessage {

	private String to;
	private String[] from = new String[2];
	private String subject;
	private String text;
	private Session session;

	public CreateMessage(String to, String[] from, String subject, String text) {
		this.from = from;
		this.subject = subject;
		this.text = text;
		this.to = to;
		session = SessionContainer.getSession(from);
	}

	public void sendMessage() {

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from[0]));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);

			System.out.println("message from "+ from[0]+ " to "+ to +" is sent successfully");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
