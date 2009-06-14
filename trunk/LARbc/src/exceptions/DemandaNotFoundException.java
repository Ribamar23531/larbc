package exceptions;

public class DemandaNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DemandaNotFoundException(){
		super("Demanda Nao Encontrada");
	}

}
