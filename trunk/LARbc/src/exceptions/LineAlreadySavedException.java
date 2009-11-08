package exceptions;

public class LineAlreadySavedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LineAlreadySavedException(){
		super("Linha já salva");
	}

}
