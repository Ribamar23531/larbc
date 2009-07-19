package com.googlecode.projeto1.client.rpcServices;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.googlecode.projeto1.client.beans.AdminBean;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.beans.DemandBean;
import com.googlecode.projeto1.client.beans.PhotoBean;

@RemoteServiceRelativePath("persistence")
public interface PersistenceService extends RemoteService {

	public List<CaseBean> doQuery(int resultNumber, String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, int businessType);
	
	public List<CaseBean> doQuery(String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, int businessType);
	
//	public String verifyAdministrador(String login, String password);
	
	public AdminBean doLogin(String login, String password);
	
	public void crateCaso(AdminBean admin, CaseBean caso); 
	
	public AdminBean getAdministrador(String login);
	
	public List<CaseBean> getAllCasos();
	
	public AdminBean getCasoOwner(CaseBean caso);
	
	public List<CaseBean> getCasos(String login);
	
	public DemandBean getDemanda(long idDemand);
	
	public List<DemandBean> getDemandas();
	
	public PhotoBean getfoto(long idCaso, String path);
	
	public List<PhotoBean> getFotos(CaseBean caso);
	
	public void removeAdministrador(AdminBean adminToRemove, String adminPassword);
	
	public void removeCaso(AdminBean admin, CaseBean caso);
	
	public void removeDemanda(DemandBean demanda);
	
	public void removeFoto(CaseBean caso, String path);
	
	public void saveAdministrador(AdminBean adminToInsert, String adminPassword);
	
	public void saveDemanda(DemandBean demanda);
	
	public void updateAdministrador(AdminBean adminToUpdate, String adminPassword);
	
	public void updateCaso(AdminBean admin, CaseBean caso);
	
	public void updateDemanda(DemandBean demanda);
	
	public void updateFoto(PhotoBean oldPhoto, PhotoBean newPhoto);
	
//	public void setAdminPassword(String oldAdminPassword, String newAdminPassword);
	
	public List<String> listEstados();
	
	public List<String> listBairros();
}
