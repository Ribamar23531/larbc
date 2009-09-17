package exceptions;

public class LoginAlreadyRegisteredException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoginAlreadyRegisteredException(){
		super("Login já Registrado");
	}

}
