package persistence;

import beans.Administrador;
import persistence.DAO.AdministradorDAO;
import persistence.DAO.AdministradorHibernateDAO;
import exceptions.AdministradorNaoEncontradoException;
import exceptions.LoginJaRegistradoException;

public class GerenteDePersistencia {	
	
	private AdministradorDAO administradorDAO;
	private static GerenteDePersistencia minhaInstancia = null;
	
	public GerenteDePersistencia(boolean testando){
		this.administradorDAO = new AdministradorHibernateDAO(testando);	
	}
	
	public static GerenteDePersistencia getInstance(boolean testando){
		if(minhaInstancia == null){			
			minhaInstancia = new GerenteDePersistencia(testando);			
		}
		return minhaInstancia;
	}
	
	public void gravarAdministrador(Administrador admin) throws LoginJaRegistradoException{
		administradorDAO.gravarAdministrador(admin);		
	}
	
	public void removerAdministrador(Administrador admin) throws AdministradorNaoEncontradoException{
		administradorDAO.removerAdministrador(admin);
	}
	
	public Administrador getAdministrador(String login) throws AdministradorNaoEncontradoException{
		return administradorDAO.getAdministrador(login);
	}
	
	public void atualizarAdministrador(Administrador admin) throws AdministradorNaoEncontradoException{
		administradorDAO.atualizarAdministrador(admin);
	}
	
	public void apagarTodosAdministradores(){
		administradorDAO.apagarTodosAdministradores();
	}

}
