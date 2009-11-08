package exceptions;

public class PointNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PointNotFoundException(){
		super("Ponto de interesse não encontrado");
	}

}
