package com.googlecode.projeto1.client.rpcServices;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.googlecode.projeto1.client.beans.AdminBean;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.beans.DemandBean;
import com.googlecode.projeto1.client.beans.LineBean;
import com.googlecode.projeto1.client.beans.PhotoBean;
import com.googlecode.projeto1.client.beans.PointBean;
import com.googlecode.projeto1.client.beans.PolygonBean;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
@RemoteServiceRelativePath("persistence")
public interface PersistenceService extends RemoteService {

	public List<CaseBean> doQuery(int resultNumber, String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, String businessType);
	
	public List<CaseBean> doQuery(String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, String businessType);
	
	public List<CaseBean> doQuery(String state, String city, String neighborhood,
			String street, String name, float builtArea, float totalArea,
			int garageSpace, int bedroom, int suite, int bathroom, String type,
			float price, float priceWeight, String businessType, double latitude, double longitude, 
			double POIWeight, List<String> kindsOfPOI);
	
	public AdminBean doLogin(String login, String password);
	
	public void createCaso(AdminBean admin, CaseBean caso); 
	
	public AdminBean getAdministrador(String login);
	
	public List<AdminBean> getAdministradores();
	
	public List<CaseBean> getAllCasos();
	
	public AdminBean getCasoOwner(CaseBean caso);
	
	public List<CaseBean> getCasos(String login);
	
	public DemandBean getDemanda(long idDemand);
	
	public List<DemandBean> getDemandas();
	
	public PhotoBean getfoto(long idCaso, String path);
	
	public List<PhotoBean> getFotos(CaseBean caso);
	
	public String removeAdministrador(AdminBean root, AdminBean adminToRemove);
	
	public void removeCaso(AdminBean admin, CaseBean caso);
	
	public boolean removeDemanda(DemandBean demanda);
	
	public void removeFoto(CaseBean caso, String path);
	
	public String saveAdministrador(AdminBean root, AdminBean adminToInsert);
	
	public void saveDemanda(DemandBean demanda);
	
	public String updateAdministrador(AdminBean root, AdminBean adminToUpdate);
	
	public void updateCaso(AdminBean admin, CaseBean caso);
	
	public void updateDemanda(DemandBean demanda);
	
	public void updateFoto(PhotoBean oldPhoto, PhotoBean newPhoto);
	
	public List<String> listEstados();
	
	public List<String> listBairros();
	
	public String getCaseLocation(CaseBean caseBean) ;
	
	public boolean savePoint(PointBean point);
	
	public boolean removePoint(PointBean point);
	
	public List<PointBean> getPoints();
	
	public boolean saveLine(LineBean line);
	
	public boolean removeLine(LineBean line);
	
	public List<LineBean> getLines();
	
	public boolean savePolygon(PolygonBean polygonBean);
	
	public boolean removePolygon(PolygonBean polygonBean);
	
	public List<PolygonBean> getPolygons();
	
}
