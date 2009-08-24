package persistence;

import java.io.File;
import java.util.List;

import persistence.DAO.AdministradorDAO;
import persistence.DAO.AdministradorHibernateDAO;
import persistence.DAO.CasoDAO;
import persistence.DAO.CasosHibernateDAO;
import persistence.DAO.DemandaDAO;
import persistence.DAO.DemandasHibernateDAO;
import persistence.DAO.FotoDAO;
import persistence.DAO.FotoHibernateDAO;
import persistence.util.Paths;
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
	private static GerenteDePersistencia minhaInstancia = null;
	
	public GerenteDePersistencia(boolean testing){
		this.administradorDAO = new AdministradorHibernateDAO(testing);
		this.fotoDAO = new FotoHibernateDAO(testing);
		this.demandaDAO = new DemandasHibernateDAO(testing);
		this.casoDAO = new CasosHibernateDAO(testing);
	}
	
	public GerenteDePersistencia(){
		this.administradorDAO = new AdministradorHibernateDAO();
		this.fotoDAO = new FotoHibernateDAO();
		this.demandaDAO = new DemandasHibernateDAO();
		this.casoDAO = new CasosHibernateDAO();
	}
	
	public static GerenteDePersistencia getInstance(boolean testando){
		if(minhaInstancia == null){			
			minhaInstancia = new GerenteDePersistencia(testando);			
		}
		return minhaInstancia;
	}
	
	//=============================Operacoes sobre sistema======================================\\
		
	private void verifyPermission(Administrador admin) throws PermissionDaniedException{
		Administrador a;
		try {
			a = getAdministrador(admin.getLogin());
		} catch (AdministradorNotFoundException e) {
			throw new PermissionDaniedException();//se nao encontrar administrador entao alguem tentou um login invalido
		}
		if(!a.getPassword().equals(admin.getPassword()) || !a.isRoot()){
			throw new PermissionDaniedException();
		}		
	}
	
	public Administrador doLogin(String login, String password) throws PermissionDaniedException{
		try {
			Administrador admin = getAdministrador(login);
			if(!admin.getPassword().equals(password)){
				throw new PermissionDaniedException();
			}
			return admin;
		} catch (AdministradorNotFoundException e) {
			throw new PermissionDaniedException();
		}
	}
	
	//=============================Operacoes sobre Administrador===============================\\	
	
	public void saveAdministrador(Administrador root, Administrador admin) throws LoginAlreadyRegisteredException, PermissionDaniedException/*, RequiredArgumentException*/{
		verifyPermission(root);
		administradorDAO.saveAdministrador(admin);
	}
	
	public void removeAdministrador(Administrador root, Administrador admin) throws AdministradorNotFoundException, PermissionDaniedException{
		verifyPermission(root);		
		administradorDAO.removeAdministrador(admin);
	}
	
	public Administrador getAdministrador(long idAdmin) throws AdministradorNotFoundException{
		return administradorDAO.getAdministrador(idAdmin);
	}
	
	public Administrador getAdministrador(String login) throws AdministradorNotFoundException{
		return administradorDAO.getAdministrador(login);
	}
	
	public void updateAdministrador(Administrador root, Administrador admin) throws AdministradorNotFoundException, PermissionDaniedException{
		verifyPermission(root);
		administradorDAO.updateAdministrador(admin);
	}
	
	public void removeAllAdministradores(){
		administradorDAO.removeAllAdministradores();
	}
	
	public void removeNotRoots(){
		administradorDAO.removeNotRoots();
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
		long casoId = caso.getIdCaso();
		casoDAO.removeCaso(caso);
		this.eraseDirectory(Paths.FILES_PATH + Paths.PATH_SEPARATOR + casoId);
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

	public void removeCaso(Administrador admin, Caso caso) throws AdministradorNotFoundException, PermissionDaniedException, CasoNotFoundException {
//		if(!getCasoOwner(caso).equals(admin)){
//			throw new PermissionDaniedException();
//		}
		this.removeCaso(caso);
	}

	public void removeFoto(Caso caso, String path) throws FotoNotFoundException {
		for (Foto foto : getFotos(caso)) {
			if(foto.getPath().equals(path)){
				removeFoto(foto);
			}
		}
	}

	public void updateCaso(Administrador admin, Caso caso) throws PermissionDaniedException, AdministradorNotFoundException, CasoNotFoundException {
//		if(!getCasoOwner(caso).equals(admin)){
//			throw new PermissionDaniedException();
//		}
		this.casoDAO.updateCaso(caso);
	}

	public List<Caso> getCasos(String login) throws AdministradorNotFoundException {
		return getCasos(getAdministrador(login).getIdAdministrador());
	}

//	public void verifyAdministrador(String login, String password) throws PermissionDaniedException, AdministradorNotFoundException {
//		if(login.equals("admin")){
//			if(systemPasswordDAO.getPassword().equals(password)){
//				return;
//			}else{
//				throw new PermissionDaniedException();				
//			}
//		}
//		Administrador admin = this.getAdministrador(login);
//		if(!admin.getPassword().equals(password)){
//			throw new PermissionDaniedException();
//		}
//	}
	
	public void eraseDirectory(String path){
		File directory = new File(path);
		if(directory.exists() && directory.isDirectory()){
			for (File subFile : directory.listFiles()) {
				if(subFile.isFile()){
					subFile.delete();
				}else{
					eraseDirectory(subFile.getPath());
				}
			}
			directory.delete();
		}
	}

}
