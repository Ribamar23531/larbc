package persistence.DAO;

import beans.Administrador;
import exceptions.AdministradorNaoEncontradoException;
import exceptions.LoginJaRegistradoException;

public interface AdministradorDAO {
	
	public void gravarAdministrador(Administrador admin) throws LoginJaRegistradoException;
	
	public void removerAdministrador(Administrador admin) throws AdministradorNaoEncontradoException;
	
	public Administrador getAdministrador(String login) throws AdministradorNaoEncontradoException;
	
	public void atualizarAdministrador(Administrador admin) throws AdministradorNaoEncontradoException;
	
	public void apagarTodosAdministradores();

}
