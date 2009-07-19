package persistence;

import persistence.util.MailSender;
import beans.Demanda;

public class GerenteDeEMail {

	private static final String ADMIN_MAIL = "joaofso@gmail.com";
	private static final String SUBJECT = "Submissão de demanda de usuário";
	
	private MailSender mailSender;
	
	public GerenteDeEMail(){
		this.mailSender = new MailSender();
	}

	public void sendMail(String personWhoSubmittedDemand, String email,	String phone) {
		this.mailSender.sendMail(ADMIN_MAIL, SUBJECT, this.getMessage(personWhoSubmittedDemand, email, phone));
	}
	
	public void sendMail(Demanda demanda){
		this.mailSender.sendMail(ADMIN_MAIL, SUBJECT, 
				this.getMessage(demanda.getNomeCliente(), demanda.getEmailCliente(), demanda.getTelefone()));
	}
	
	private String getMessage(String personWhoSubmittedDemand, String email, String phone){
		String result = "O usuário " + personWhoSubmittedDemand + " (Contato: " + email + " - " + phone + ")" +
		"submeteu uma demanda a ser analisada pela aplicação.";
		return result;
	}

}
