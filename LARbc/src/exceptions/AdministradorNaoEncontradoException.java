package exceptions;

public class AdministradorNaoEncontradoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AdministradorNaoEncontradoException(){
		super("Administrador Nao Encontrado");
	}

}
