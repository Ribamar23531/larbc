package persistence.DAO;

import beans.Administrador;
import exceptions.AdministradorNotFoundException;
import exceptions.LoginAlreadyRegisteredException;
import exceptions.PermissionDeniedException;

public interface AdministradorDAO {
	
	public void saveAdministrador(Administrador admin) throws LoginAlreadyRegisteredException;
	
	public void removeAdministrador(Administrador admin) throws AdministradorNotFoundException, PermissionDeniedException;
	
	public Administrador getAdministrador(String login) throws AdministradorNotFoundException;
	
	public Administrador getAdministrador(long idAdmin) throws AdministradorNotFoundException;
	
	public void createRootIfNeeded();
	
	public void updateAdministrador(Administrador admin) throws AdministradorNotFoundException;
	
	public void removeAllAdministradores();
	
	public void removeNotRoots();

}
