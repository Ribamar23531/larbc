package exceptions;

public class AdministradorNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AdministradorNotFoundException(){
		super("Administrador Nao Encontrado");
	}

}
