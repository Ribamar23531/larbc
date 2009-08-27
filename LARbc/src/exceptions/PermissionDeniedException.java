package exceptions;

public class PermissionDeniedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PermissionDeniedException(){
		super("Permissao negada");
	}

}
