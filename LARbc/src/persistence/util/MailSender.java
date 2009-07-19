package persistence.util;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import com.sun.net.ssl.internal.ssl.Provider;

/**
 * This class is responsible to send emails.
 * @author Jo�o Felipe
 *
 */
public class MailSender {
	
	private Properties mailProperties;
	
	/**
	 * 
	 */
	public MailSender() {
		Security.addProvider(new Provider());
		this.mailProperties = this.setProperties();
	}
	
	private Properties setProperties() {
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");  
		properties.put("mail.smtp.starttls.enable",true);   
		properties.put("mail.smtp.host", "smtp.gmail.com");  
		properties.put("mail.smtp.auth", true);  
		properties.put("mail.smtp.user", "larbc.application@gmail.com");  
		properties.put("mail.debug", true);  
		properties.put("mail.smtp.port", 465);  
		properties.put("mail.smtp.socketFactory.port", 465);  
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
		properties.put("mail.smtp.socketFactory.fallback", false);  
		return properties;
	}

	public void sendMail(String to, String subject, String message){
		Session session = Session.getDefaultInstance(this.mailProperties, new SimpleAuth("larbc.application@gmail.com", "soutomaior"));
		try {
			MimeMessage mailMessage = new MimeMessage(session);
			mailMessage.setRecipients(Message.RecipientType.TO, to);
			mailMessage.setSubject(subject);
			mailMessage.setSentDate(new Date());
			mailMessage.setText(message);
			Transport.send(mailMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private class SimpleAuth extends Authenticator {
		public String username = null;
		public String password = null;

		public SimpleAuth(String user, String pwd) {
			username = user;
			password = pwd;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	} 	


	public static void main(String[] args) {
		String to = "ourixilva@gmail.com";
		String subject = "Testing";
		String message = "Hello fuckin' World";
		
		MailSender mailSender = new MailSender();
		mailSender.sendMail(to, subject, message);
		
	}
	
	
}
