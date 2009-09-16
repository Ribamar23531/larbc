package facade;

import java.sql.SQLException;
import java.util.List;

import beans.Administrador;
import beans.Caso;
import beans.Demanda;
import beans.Foto;
import exceptions.AdministradorNotFoundException;
import exceptions.CasoNotFoundException;
import exceptions.DemandaNotFoundException;
import exceptions.FotoAlreadySavedException;
import exceptions.FotoNotFoundException;
import exceptions.LoginAlreadyRegisteredException;
import exceptions.PermissionDeniedException;

public interface SystemFacade {
	
//=== Administrator operations ===
	public Administrador doLogin(String login, String password) throws PermissionDeniedException;
	
	public void saveAdministrador(Administrador root, Administrador admin) throws LoginAlreadyRegisteredException, PermissionDeniedException;
	
	public void removeAdministrador(Administrador root, Administrador admin) throws AdministradorNotFoundException, PermissionDeniedException;
	
	public void updateAdministrador(Administrador root, Administrador admin) throws AdministradorNotFoundException, PermissionDeniedException;
	
	public Administrador getAdministrador(String login) throws AdministradorNotFoundException;
	
	public List<Administrador> getAdministradores();
	
	public void createCaso(Administrador admin, Caso caso) throws AdministradorNotFoundException;
	
	public void removeCaso(Administrador admin, Caso caso) throws AdministradorNotFoundException, PermissionDeniedException, CasoNotFoundException;
	
	public void updateCaso(Administrador admin, Caso caso) throws PermissionDeniedException, AdministradorNotFoundException, CasoNotFoundException;
	
	public List<Caso> getCasos(String login) throws AdministradorNotFoundException;
	
//=== Photo operations ===
	public void removeFoto(Caso caso, String path) throws FotoNotFoundException;
	
	public Foto getFoto(long idCaso, String path) throws FotoNotFoundException;
	
	public void updateFoto(Foto oldPhoto, Foto newPhoto) throws FotoNotFoundException, FotoAlreadySavedException;
	
//=== Demand operations ===
	public void saveDemanda(Demanda demand);
	
	public void removeDemanda(Demanda demand) throws DemandaNotFoundException;
	
	public Demanda getDemanda(long idDemand) throws DemandaNotFoundException;
	
	public List<Demanda> getDemandas();
	
	public void updateDemanda(Demanda demand) throws DemandaNotFoundException;
	
//=== Case operations ===
	public void saveFoto(Caso caso, String path) throws FotoAlreadySavedException;
	
	public List<Caso> getAllCasos();
	
	public List<Foto> getFotos(Caso caso);
	
	public Administrador getCasoOwner(Caso caso) throws AdministradorNotFoundException;
	
	public Caso getCaso(long idCaso) throws CasoNotFoundException;
	
//=== Query operation ===
	public List<Caso> doQuery(int resultNumber, String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, int businessType);
	
	public List<Caso> doQuery(String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, int businessType);

//===Listing operation ===
	public List<String> listBairros();
	
	public List<String> listEstados();
	
	public String getCasoLocation(long id) throws SQLException;
	
	
	
}
