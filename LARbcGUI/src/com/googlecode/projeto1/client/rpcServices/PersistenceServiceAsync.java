package com.googlecode.projeto1.client.rpcServices;

import java.util.List;

import com.googlecode.projeto1.client.beans.AdminBean;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.beans.DemandBean;
import com.googlecode.projeto1.client.beans.LineBean;
import com.googlecode.projeto1.client.beans.PhotoBean;
import com.googlecode.projeto1.client.beans.PointBean;
import com.googlecode.projeto1.client.beans.PolygonBean;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public interface PersistenceServiceAsync {

	public void doQuery(int resultNumber, String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, String businessType, AsyncCallback<List<CaseBean>> callback);
	
	public void doQuery(String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, String businessType, AsyncCallback<List<CaseBean>> callback);
	
	public void doQuery(String state, String city, String neighborhood,
			String street, String name, float builtArea, float totalArea,
			int garageSpace, int bedroom, int suite, int bathroom, String type,
			float price, float priceWeight, String businessType, double latitude, double longitude, 
			double POIWeight, List<String> kindsOfPOI, AsyncCallback<List<CaseBean>> callback);
	
	public void doLogin(String login, String password, AsyncCallback<AdminBean> callback);
	
	public void createCaso(AdminBean admin, CaseBean caso, AsyncCallback<?> callback); 
	
	public void getAdministrador(String login, AsyncCallback<AdminBean> callback);
	
	public void getAdministradores(AsyncCallback<List<AdminBean>> callback);
	
	public void getAllCasos(AsyncCallback<List<CaseBean>> callback);
	
	public void getCasoOwner(CaseBean caso, AsyncCallback<AdminBean> callback);
	
	public void getCasos(String login, AsyncCallback<List<CaseBean>> callback);
	
	public void getDemanda(long idDemand, AsyncCallback<DemandBean> callback);
	
	public void getDemandas(AsyncCallback<List<DemandBean>> callback);
	
	public void getfoto(long idCaso, String path, AsyncCallback<PhotoBean> callback);
	
	public void getFotos(CaseBean caso, AsyncCallback<List<PhotoBean>> callback);
	
	public void removeAdministrador(AdminBean root, AdminBean adminToRemove, AsyncCallback<String> callback);
	
	public void removeCaso(AdminBean admin, CaseBean caso, AsyncCallback<?> callback);
	
	public void removeDemanda(DemandBean demanda, AsyncCallback<Boolean> callback);
	
	public void removeFoto(CaseBean caso, String path, AsyncCallback<?> callback);
	
	public void saveAdministrador(AdminBean root, AdminBean adminToInsert, AsyncCallback<String> callback);
	
	public void saveDemanda(DemandBean demanda, AsyncCallback<?> callback);
	
	public void updateAdministrador(AdminBean root, AdminBean adminToUpdate, AsyncCallback<String> callback);
	
	public void updateCaso(AdminBean admin, CaseBean caso, AsyncCallback<?> callback);
	
	public void updateDemanda(DemandBean demanda, AsyncCallback<?> callback);
	
	public void updateFoto(PhotoBean oldPhoto, PhotoBean newPhoto, AsyncCallback<?> callback);
	
	public void listEstados(AsyncCallback<List<String>> callback);
	
	public void listBairros(AsyncCallback<List<String>> callback);
	
	public void getCaseLocation(CaseBean caseBean, AsyncCallback<String> callback) ;
	
	public void savePoint(PointBean point, AsyncCallback<Boolean> callback);
	
	public void removePoint(PointBean point, AsyncCallback<Boolean> callback);
	
	public void getPoints(AsyncCallback<List<PointBean>> callback);
	
	public void saveLine(LineBean line, AsyncCallback<Boolean> callback);
	
	public void removeLine(LineBean line, AsyncCallback<Boolean> callback);
	
	public void getLines(AsyncCallback<List<LineBean>> callback);
	
	public void savePolygon(PolygonBean polygonBean, AsyncCallback<Boolean> callback);
	
	public void removePolygon(PolygonBean polygonBean, AsyncCallback<Boolean> callback);
	
	public void getPolygons(AsyncCallback<List<PolygonBean>> callback);
	
}
