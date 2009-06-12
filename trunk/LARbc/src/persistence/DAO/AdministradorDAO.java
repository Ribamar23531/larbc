package persistence.DAO;

import beans.Administrador;
import exceptions.AdministradorNotFoundException;
import exceptions.LoginAlreadyRegisteredException;

public interface AdministradorDAO {
	
	public void saveAdministrador(Administrador admin) throws LoginAlreadyRegisteredException;
	
	public void removeAdministrador(Administrador admin) throws AdministradorNotFoundException;
	
	public Administrador getAdministrador(String login) throws AdministradorNotFoundException;
	
	public void updateAdministrador(Administrador admin) throws AdministradorNotFoundException;
	
	public void removeAllAdministradores();

}
