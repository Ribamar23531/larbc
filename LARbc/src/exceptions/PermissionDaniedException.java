package exceptions;

public class PermissionDaniedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PermissionDaniedException(){
		super("Permissao negada");
	}

}
