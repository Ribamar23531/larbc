package exceptions;

public class CasoNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CasoNotFoundException(){
		super("Caso nao encontrado");
	}

}
