package persistence;

import java.util.List;

import persistence.DAO.AdministradorDAO;
import persistence.DAO.AdministradorHibernateDAO;
import persistence.DAO.DemandaDAO;
import persistence.DAO.DemandasHibernateDAO;
import persistence.DAO.FotoDAO;
import persistence.DAO.FotoHibernateDAO;
import beans.Administrador;
import beans.Demanda;
import beans.Foto;
import exceptions.AdministradorNotFoundException;
import exceptions.DemandaNotFoundException;
import exceptions.FotoAlreadySavedException;
import exceptions.FotoNotFoundException;
import exceptions.LoginAlreadyRegisteredException;

public class GerenteDePersistencia {	
	
	private AdministradorDAO administradorDAO;
	private FotoDAO fotoDAO;
	private DemandaDAO demandaDAO;
	private static GerenteDePersistencia minhaInstancia = null;
	
	public GerenteDePersistencia(boolean testing){
		this.administradorDAO = new AdministradorHibernateDAO(testing);
		this.fotoDAO = new FotoHibernateDAO(testing);
		this.demandaDAO = new DemandasHibernateDAO(testing);
	}
	
	public static GerenteDePersistencia getInstance(boolean testando){
		if(minhaInstancia == null){			
			minhaInstancia = new GerenteDePersistencia(testando);			
		}
		return minhaInstancia;
	}
	
	//=============================Operacoes sobre Administrador===============================\\
	
	public void saveAdministrador(Administrador admin) throws LoginAlreadyRegisteredException{
		administradorDAO.saveAdministrador(admin);		
	}
	
	public void removeAdministrador(Administrador admin) throws AdministradorNotFoundException{
		administradorDAO.removeAdministrador(admin);
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

}
