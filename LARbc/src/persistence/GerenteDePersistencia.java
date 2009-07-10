package persistence;

import java.util.List;

import persistence.DAO.AdministradorDAO;
import persistence.DAO.AdministradorHibernateDAO;
import persistence.DAO.CasoDAO;
import persistence.DAO.CasosHibernateDAO;
import persistence.DAO.DemandaDAO;
import persistence.DAO.DemandasHibernateDAO;
import persistence.DAO.FotoDAO;
import persistence.DAO.FotoHibernateDAO;
import persistence.DAO.SystemPasswordDAO;
import persistence.DAO.SystemPasswordHibernateDAO;
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
import exceptions.PermissionDaniedException;

public class GerenteDePersistencia {	
	
	private AdministradorDAO administradorDAO;
	private FotoDAO fotoDAO;
	private DemandaDAO demandaDAO;
	private CasoDAO casoDAO;
	private SystemPasswordDAO systemPasswordDAO;
	private static GerenteDePersistencia minhaInstancia = null;
	
	public GerenteDePersistencia(boolean testing){
		this.administradorDAO = new AdministradorHibernateDAO(testing);
		this.fotoDAO = new FotoHibernateDAO(testing);
		this.demandaDAO = new DemandasHibernateDAO(testing);
		this.casoDAO = new CasosHibernateDAO(testing);
		this.systemPasswordDAO = new SystemPasswordHibernateDAO(testing);
	}
	
	public GerenteDePersistencia(){
		this.administradorDAO = new AdministradorHibernateDAO();
		this.fotoDAO = new FotoHibernateDAO();
		this.demandaDAO = new DemandasHibernateDAO();
		this.casoDAO = new CasosHibernateDAO();
		this.systemPasswordDAO = new SystemPasswordHibernateDAO();
	}
	
	public static GerenteDePersistencia getInstance(boolean testando){
		if(minhaInstancia == null){			
			minhaInstancia = new GerenteDePersistencia(testando);			
		}
		return minhaInstancia;
	}
	
	//=============================Operacoes sobre senha======================================\\
	
	public void resetSystemPassword(){
		systemPasswordDAO.resetPassword();
	}
	
	private void verifyPermission(String password) throws PermissionDaniedException{
		if(!password.equals(systemPasswordDAO.getPassword())){
			throw new PermissionDaniedException();
		}
	}
	
	public void setPassword(String oldPassword, String newPassword) throws PermissionDaniedException{
		verifyPermission(oldPassword);
		systemPasswordDAO.setPassword(newPassword);
	}
	
	//=============================Operacoes sobre Administrador===============================\\	
	
	public void saveAdministrador(Administrador admin, String password) throws LoginAlreadyRegisteredException, PermissionDaniedException/*, RequiredArgumentException*/{
//		if(admin.getLogin() == null || admin.getLogin().equals("")){
//			throw new RequiredArgumentException("login");
//		}
//		if(admin.getPassword() == null || admin.getPassword().equals("")){
//			throw new RequiredArgumentException("password");
//		}
//		if(admin.getNome() == null || admin.getNome().equals("")){
//			throw new RequiredArgumentException("nome");
//		}		
		verifyPermission(password);
		administradorDAO.saveAdministrador(admin);
	}
	
	public void removeAdministrador(Administrador admin, String password) throws AdministradorNotFoundException, PermissionDaniedException{
		verifyPermission(password);
		administradorDAO.removeAdministrador(admin);
	}
	
	public Administrador getAdministrador(long idAdmin) throws AdministradorNotFoundException{
		return administradorDAO.getAdministrador(idAdmin);
	}
	
	public Administrador getAdministrador(String login) throws AdministradorNotFoundException{
		return administradorDAO.getAdministrador(login);
	}
	
	public void updateAdministrador(Administrador admin, String password) throws AdministradorNotFoundException, PermissionDaniedException{
		verifyPermission(password);
		administradorDAO.updateAdministrador(admin);
	}
	
	public void removeAllAdministradores(){
		administradorDAO.removeAllAdministradores();
	}
	
	public List<Caso> getCasos(long idAdministrador){
		return casoDAO.getCasos(idAdministrador);
	}
	
	public void createCaso(Administrador admin, Caso caso) throws AdministradorNotFoundException{
		getAdministrador(admin.getLogin());//testa se o administrador existe
		caso.setIdAdministradorResponsavel(admin.getIdAdministrador());
		saveCaso(caso);
	}	
	
	//===================================Operacoes sobre Fotos=====================================\\
	
	public void saveFoto(Foto foto) throws FotoAlreadySavedException{
		fotoDAO.saveFoto(foto);
	}
	
	public void removeFoto(Foto foto) throws FotoNotFoundException{
		fotoDAO.removeFoto(foto);
	}
	
	public Foto getFoto(long idCaso, String path) throws FotoNotFoundException{
		return fotoDAO.getFoto(idCaso, path);
	}
	
	public void updateFoto(Foto oldPicture, Foto newPicture) throws FotoNotFoundException, FotoAlreadySavedException{
		fotoDAO.updateFoto(oldPicture, newPicture);
	}
	
	public void removeAllFotos(){
		fotoDAO.removeAllFotos();
	}
	
	//===================================Operacoes sobre Demandas=====================================\\
	
	public void saveDemanda(Demanda demanda){
		demandaDAO.saveDemanda(demanda);
	}
	
	public void removeDemanda(Demanda demanda) throws DemandaNotFoundException{
		demandaDAO.removeDemanda(demanda);
	}
	
	public Demanda getDemanda(long idDemanda) throws DemandaNotFoundException{
		return demandaDAO.getDemanda(idDemanda);
	}
	
	public List<Demanda> getDemandas(){
		return demandaDAO.getDemandas();
	}
	
	public void updateDemanda(Demanda demanda) throws DemandaNotFoundException{
		demandaDAO.updateDemanda(demanda);
	}
	
	public void removeAllDemandas(){
		demandaDAO.removeAllDemandas();
	}
	
	//===================================Operacoes sobre Casos=====================================\\
	
	public void saveCaso(Caso caso){
		casoDAO.saveCaso(caso);
	}
	
	public void saveFoto(Caso caso, String path) throws FotoAlreadySavedException{
		saveFoto(new Foto(caso.getIdCaso(), path));
	}
	
	public void removeCaso(Caso caso) throws CasoNotFoundException{
		casoDAO.removeCaso(caso);
	}
	
	public Caso getCaso(long idCaso) throws CasoNotFoundException{
		return casoDAO.getCaso(idCaso);
	}
	
	public List<Caso> getAllCasos(){
		return casoDAO.getAllCasos();
	}
	
	public List<Foto> getFotos(Caso caso){
		return casoDAO.getFotos(caso);
	}
	
	public void updateCaso(Caso caso) throws CasoNotFoundException{
		casoDAO.updateCaso(caso);
	}
	
	public void removeAllCasos(){
		casoDAO.removeAllCasos();
	}
	
	public Administrador getCasoOwner(Caso caso) throws AdministradorNotFoundException{
		Administrador a = getAdministrador(caso.getIdAdministradorResponsavel());
		return a;
	}

}