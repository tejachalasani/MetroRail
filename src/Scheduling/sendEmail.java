package Scheduling;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class is used to send email Creates a new thread for the email
 * 
 * @author Nalini Krishna Teja Chalasani 
 *
 */
public class sendEmail implements Runnable {
	private Thread t;
	private String threadName;
	private String Message;
	private String toEmail;

	/**
	 * Constructor to initiate variables
	 * 
	 * @param name
	 *            name of thread
	 * @param message
	 *            Message to be sent
	 * @param toemail
	 *            Email address
	 */
	public sendEmail(String name, String message, String toemail) {
		threadName = name;
		Message = message;
		toEmail = toemail;
		System.out.println("Creating " + threadName);
	}

	/**
	 * To send email
	 */
	public void run() {
		System.out.println("Running " + threadName);
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		final String username = "testwmata777";
		final String password = "ap37ar3889";
		String fromEmailAddress = "testwmata777@gmail.com";
		String toEmailAddress = toEmail;
		String subject = "Requested Status";
		String textMessage = Message;

		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmailAddress));
			message.setRecipients(RecipientType.TO, InternetAddress.parse(toEmailAddress));
			message.setSubject(subject);
			message.setText(textMessage);
			Transport.send(message);
			System.out.println("Message delivered successfully");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("Thread " + threadName + " exiting.");
	}

	/**
	 * Used to start the thread
	 */
	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}
