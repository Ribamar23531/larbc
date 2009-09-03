package com.googlecode.projeto1.client.rpcServices;

import java.util.List;

import com.googlecode.projeto1.client.beans.AdminBean;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.beans.DemandBean;
import com.googlecode.projeto1.client.beans.PhotoBean;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PersistenceServiceAsync {

	public void doQuery(int resultNumber, String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, int businessType, AsyncCallback<List<CaseBean>> callback);
	
	public void doQuery(String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, int businessType, AsyncCallback<List<CaseBean>> callback);
	
	public void doLogin(String login, String password, AsyncCallback<AdminBean> callback);
	
	public void createCaso(AdminBean admin, CaseBean caso, AsyncCallback<?> callback); 
	
	public void getAdministrador(String login, AsyncCallback<AdminBean> callback);
	
	public void getAllCasos(AsyncCallback<List<CaseBean>> callback);
	
	public void getCasoOwner(CaseBean caso, AsyncCallback<AdminBean> callback);
	
	public void getCasos(String login, AsyncCallback<List<CaseBean>> callback);
	
	public void getDemanda(long idDemand, AsyncCallback<DemandBean> callback);
	
	public void getDemandas(AsyncCallback<List<DemandBean>> callback);
	
	public void getfoto(long idCaso, String path, AsyncCallback<PhotoBean> callback);
	
	public void getFotos(CaseBean caso, AsyncCallback<List<PhotoBean>> callback);
	
	public void removeAdministrador(AdminBean root, AdminBean adminToRemove, AsyncCallback<?> callback);
	
	public void removeCaso(AdminBean admin, CaseBean caso, AsyncCallback<?> callback);
	
	public void removeDemanda(DemandBean demanda, AsyncCallback<?> callback);
	
	public void removeFoto(CaseBean caso, String path, AsyncCallback<?> callback);
	
	public void saveAdministrador(AdminBean root, AdminBean adminToInsert, AsyncCallback<?> callback);
	
	public void saveDemanda(DemandBean demanda, AsyncCallback<?> callback);
	
	public void updateAdministrador(AdminBean root, AdminBean adminToUpdate, AsyncCallback<?> callback);
	
	public void updateCaso(AdminBean admin, CaseBean caso, AsyncCallback<?> callback);
	
	public void updateDemanda(DemandBean demanda, AsyncCallback<?> callback);
	
	public void updateFoto(PhotoBean oldPhoto, PhotoBean newPhoto, AsyncCallback<?> callback);
	
//	public void setAdminPassword(String oldAdminPassword, String newAdminPassword);
	
	public void listEstados(AsyncCallback<List<String>> callback);
	
	public void listBairros(AsyncCallback<List<String>> callback);
}
