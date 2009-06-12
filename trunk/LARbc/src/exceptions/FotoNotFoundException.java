package exceptions;

public class FotoNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FotoNotFoundException(){
		super("Foto nao encontrada");
	}

}
