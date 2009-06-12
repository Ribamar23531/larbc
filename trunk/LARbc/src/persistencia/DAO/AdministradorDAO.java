package persistencia.DAO;

import exceptions.AdministradorNaoEncontradoException;
import exceptions.LoginJaRegistradoException;
import modelo.Administrador;

public interface AdministradorDAO {
	
	public void gravarAdministrador(Administrador admin) throws LoginJaRegistradoException;
	
	public void removerAdministrador(Administrador admin) throws AdministradorNaoEncontradoException;
	
	public Administrador getAdministrador(String login) throws AdministradorNaoEncontradoException;
	
	public void atualizarAdministrador(Administrador admin) throws AdministradorNaoEncontradoException;
	
	public void apagarTodosAdministradores();

}
