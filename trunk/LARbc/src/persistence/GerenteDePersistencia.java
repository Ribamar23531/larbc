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
	
	//=============================Operacoes sobre Administrador===============================\\
	
	public void saveAdministrador(Administrador admin) throws LoginAlreadyRegisteredException/*, RequiredArgumentException*/{
//		if(admin.getLogin() == null || admin.getLogin().equals("")){
//			throw new RequiredArgumentException("login");
//		}
//		if(admin.getPassword() == null || admin.getPassword().equals("")){
//			throw new RequiredArgumentException("password");
//		}
//		if(admin.getNome() == null || admin.getNome().equals("")){
//			throw new RequiredArgumentException("nome");
//		}
		administradorDAO.saveAdministrador(admin);
	}
	
	public void removeAdministrador(Administrador admin) throws AdministradorNotFoundException{
		administradorDAO.removeAdministrador(admin);
	}
	
	public Administrador getAdministrador(long idAdmin) throws AdministradorNotFoundException{
		return administradorDAO.getAdministrador(idAdmin);
	}
	
	public Administrador getAdministrador(String login) throws AdministradorNotFoundException{
		return administradorDAO.getAdministrador(login);
	}
	
	public void updateAdministrador(Administrador admin) throws AdministradorNotFoundException{
		administradorDAO.updateAdministrador(admin);
	}
	
	public void removeAllAdministradores(){
		administradorDAO.removeAllAdministradores();
	}
	
	public List<Caso> getCasos(long idAdministrador){
		return casoDAO.getCasos(idAdministrador);
	}
	
	public void createCaso(Administrador admin, Caso caso) throws AdministradorNotFoundException{
//		caso.setInseridoPor(admin);
		getAdministrador(admin.getLogin());//testa se o administrador existe
		caso.setIdAdministradorResponsavel(admin.getIdAdministrador());
		saveCaso(caso);
//		admin.addCaso(caso);
//		updateAdministrador(admin);
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
	
	public void removeCaso(Caso caso) throws CasoNotFoundException{
		casoDAO.removeCaso(caso);
	}
	
	public Caso getCaso(long idCaso) throws CasoNotFoundException{
		return casoDAO.getCaso(idCaso);
	}
	
	public List<Caso> getAllCasos(){
		return casoDAO.getAllCasos();
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
