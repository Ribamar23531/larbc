package exceptions;

public class RequiredArgumentException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RequiredArgumentException(String argument){
		super("Argumento obrigatorio: " + argument);
	}

}
