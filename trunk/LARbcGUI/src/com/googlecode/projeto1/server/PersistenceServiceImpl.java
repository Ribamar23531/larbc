package com.googlecode.projeto1.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Administrador;
import beans.Caso;
import beans.Demanda;
import beans.Foto;
import beans.poi.Line;
import beans.poi.Point;
import beans.poi.Polygon;
import beans.poi.Vertex;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.projeto1.client.beans.AdminBean;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.beans.DemandBean;
import com.googlecode.projeto1.client.beans.LineBean;
import com.googlecode.projeto1.client.beans.PhotoBean;
import com.googlecode.projeto1.client.beans.PointBean;
import com.googlecode.projeto1.client.beans.PolygonBean;
import com.googlecode.projeto1.client.beans.Type;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;

import exceptions.AdministradorNotFoundException;
import exceptions.CasoNotFoundException;
import exceptions.DemandaNotFoundException;
import exceptions.FotoAlreadySavedException;
import exceptions.FotoNotFoundException;
import exceptions.LoginAlreadyRegisteredException;
import exceptions.PermissionDeniedException;
import exceptions.PointAlreadySavedException;
import facade.SystemFacade;
import facade.SystemManager;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class PersistenceServiceImpl extends RemoteServiceServlet implements PersistenceService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SystemManager systemManager = null;
	
//=== To get the facade instance method ===	
	private SystemFacade getSystemFacade(){
		if(this.systemManager == null){
			this.systemManager = new SystemManager();
		}
		return this.systemManager;
	}

	public List<CaseBean> doQuery(int resultNumber, String state, String city,
			String neighborhood, String street, String name, float builtArea,
			float totalArea, int garageSpace, int bedroom, int suite,
			int bathroom, String type, float price, String businessType) {
		
		List<Caso> results = this.getSystemFacade().doQuery(resultNumber, state, city, 
										 neighborhood, street, name, builtArea, totalArea, 
										 garageSpace, bedroom, suite, bathroom, type, price, businessType);
		List<CaseBean> returnedCases = new ArrayList<CaseBean>();
		for (Caso caso : results) {
			returnedCases.add(this.getCaseBean(caso));
		}
		return returnedCases;
	}

	public List<CaseBean> doQuery(String state, String city,
			String neighborhood, String street, String name, float builtArea,
			float totalArea, int garageSpace, int bedroom, int suite,
			int bathroom, String type, float price, String businessType) {

		List<Caso> results = this.getSystemFacade().doQuery(state, city, 
				 neighborhood, street, name, builtArea, totalArea, 
				 garageSpace, bedroom, suite, bathroom, type, price, businessType);
		
		List<CaseBean> returnedCases = new ArrayList<CaseBean>();
		for (Caso caso : results) {
			returnedCases.add(this.getCaseBean(caso));
		}
		return returnedCases;
	}

	public void createCaso(AdminBean admin, CaseBean caso) {
		try {
			this.getSystemFacade().createCaso(this.getAdmin(admin), this.getCaso(caso));
		} catch (AdministradorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public AdminBean getAdministrador(String login) {
		try {
			return this.getAdminBean(this.getSystemFacade().getAdministrador(login));
		} catch (AdministradorNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<AdminBean> getAdministradores() {
		List<Administrador> admins = this.getSystemFacade().getAdministradores();
		List<AdminBean> adminBeans = new ArrayList<AdminBean>();
		for (Administrador admin : admins) {
			adminBeans.add(getAdminBean(admin));
		}
		return adminBeans;
	}	

	public List<CaseBean> getAllCasos() {
		List<Caso> casos = this.getSystemFacade().getAllCasos();
		List<CaseBean> result = new ArrayList<CaseBean>();
		for (Caso caso : casos) {
			result.add(this.getCaseBean(caso));
		}
		return result;
	}


	public AdminBean getCasoOwner(CaseBean caso) {
		try {
			this.getAdminBean(this.getSystemFacade().getCasoOwner(this.getCaso(caso)));
		} catch (AdministradorNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}


	public List<CaseBean> getCasos(String login) {
		try {
			List<Caso> casos = this.getSystemFacade().getCasos(login);
			List<CaseBean> result = new ArrayList<CaseBean>();
			for (Caso caso : casos) {
				result.add(this.getCaseBean(caso));
			}
			return result;
		} catch (AdministradorNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}


	public DemandBean getDemanda(long idDemand) {
		try {
			return this.getDemandBean(this.getSystemFacade().getDemanda(idDemand));
		} catch (DemandaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public List<DemandBean> getDemandas() {
		List<Demanda> demandas = this.getSystemFacade().getDemandas();
		List<DemandBean> result = new ArrayList<DemandBean>();
		for (Demanda demanda : demandas) {
			result.add(this.getDemandBean(demanda));
		}
		return result;
	}


	public List<PhotoBean> getFotos(CaseBean caso) {
		List<Foto> fotos = this.getSystemFacade().getFotos(this.getCaso(caso));
		List<PhotoBean> result = new ArrayList<PhotoBean>();
		for (Foto foto : fotos) {
			result.add(this.getPhotoBean(foto));
		}
		return result;
	}


	public PhotoBean getfoto(long idCaso, String path) {
		try {
			return this.getPhotoBean(this.getSystemFacade().getFoto(idCaso, path));
		} catch (FotoNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public List<String> listBairros() {
		return this.getSystemFacade().listBairros();
	}


	public List<String> listEstados() {
		return this.getSystemFacade().listEstados();
	}


	public String removeAdministrador(AdminBean root, AdminBean adminToRemove) {
		try {
			this.getSystemFacade().removeAdministrador(this.getAdmin(root), this.getAdmin(adminToRemove));
		} catch (AdministradorNotFoundException e) {
			return e.getMessage();
		} catch (PermissionDeniedException e) {
			return e.getMessage();
		}
		return "";
	}


	public void removeCaso(AdminBean admin, CaseBean caso) {
		try {
			this.getSystemFacade().removeCaso(this.getAdmin(admin), this.getCaso(caso));
		} catch (AdministradorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PermissionDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CasoNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void removeDemanda(DemandBean demanda) {
		try {
			this.getSystemFacade().removeDemanda(this.getDemanda(demanda));
		} catch (DemandaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void removeFoto(CaseBean caso, String path) {
		try {
			this.getSystemFacade().removeFoto(this.getCaso(caso), path);
		} catch (FotoNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String saveAdministrador(AdminBean root, AdminBean adminToInsert) {
		try {
			this.getSystemFacade().saveAdministrador(this.getAdmin(root), this.getAdmin(adminToInsert));
		} catch (LoginAlreadyRegisteredException e) {
			return e.getMessage();
		} catch (PermissionDeniedException e) {
			return e.getMessage();
		}
		return "";
	}


	public void saveDemanda(DemandBean demanda) {
		this.getSystemFacade().saveDemanda(this.getDemanda(demanda));
	}

	public String updateAdministrador(AdminBean root,
									AdminBean adminToUpdate) {
		try {
			this.getSystemFacade().updateAdministrador(this.getAdmin(root), this.getAdmin(adminToUpdate));
		} catch (AdministradorNotFoundException e) {
			return e.getMessage();
		} catch (PermissionDeniedException e) {
			return e.getMessage();
		}
		return "";
	}


	public void updateCaso(AdminBean admin, CaseBean caso) {
		try {
			this.getSystemFacade().updateCaso(this.getAdmin(admin), this.getCaso(caso));
		} catch (PermissionDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AdministradorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CasoNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCaseLocation(CaseBean caseBean)  {
		String location = null;
		try {
			location = this.getSystemFacade().getCasoLocation(caseBean.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return location;
	}

	public void updateDemanda(DemandBean demanda) {
		try {
			this.getSystemFacade().updateDemanda(this.getDemanda(demanda));
		} catch (DemandaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void updateFoto(PhotoBean oldPhoto, PhotoBean newPhoto) {
		try {
			this.getSystemFacade().updateFoto(this.getFoto(oldPhoto), this.getFoto(newPhoto));
		} catch (FotoNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FotoAlreadySavedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AdminBean doLogin(String login, String password) {
		AdminBean adminBean = null;
		try {
			this.getSystemFacade().doLogin(login, password);
			adminBean = getAdministrador(login);
		} catch (PermissionDeniedException e) {
			return null;
		}
		return adminBean;
	}

//=== Conversors ===	
	
//	private CaseBean getCaseBean(ImmobileSolution result){
//		CaseBean caseResult = new CaseBean();
//		caseResult.setBathroom(result.getBathroom());
//		caseResult.setBedroom(result.getBedroom());
//		caseResult.setBuiltArea(result.getBuiltArea());
//		caseResult.setBusinessType(result.getBusinessType());
//		caseResult.setCity(result.getCity());
//		caseResult.setGarageSpace(result.getGarageSpace());
//		caseResult.setId(result.getId());
//		caseResult.setName(result.getName());
//		caseResult.setNeighborhood(result.getNeighborhood());
//		caseResult.setNumber(result.getNumber());
//		caseResult.setPrice(result.getPrice());
//		caseResult.setState(result.getState());
//		caseResult.setStreet(result.getStreet());
//		caseResult.setSuite(result.getSuite());
//		caseResult.setTotalArea(result.getTotalArea());
//		caseResult.setType(result.getType());
//		return caseResult;
//	}
	
	private CaseBean getCaseBean(Caso result){
		CaseBean caseResult = new CaseBean();
		caseResult.setBathroom(result.getBanheiros());
		caseResult.setBedroom(result.getQuartos());
		caseResult.setBuiltArea(result.getAreaConstruida());
		caseResult.setBusinessType(result.getTipoNegocio());
		caseResult.setCity(result.getCidade());
		caseResult.setGarageSpace(result.getVagasGaragem());
		caseResult.setId(result.getIdCaso());
		caseResult.setName(result.getNome());
		caseResult.setNeighborhood(result.getBairro());
		caseResult.setNumber(result.getNumero());
		caseResult.setPrice(result.getPreco());
		caseResult.setState(result.getEstado());
		caseResult.setStreet(result.getRua());
		caseResult.setSuite(result.getSuites());
		caseResult.setTotalArea(result.getAreaTotal());
		caseResult.setType(result.getTipo());
		caseResult.setLocation(result.getLocation());
		return caseResult;
	}
	
	private Caso getCaso(CaseBean caseBean){
		Caso caso = new Caso();
		caso.setBanheiros(caseBean.getBathroom());
		caso.setQuartos(caseBean.getBedroom());
		caso.setAreaConstruida(caseBean.getBuiltArea());
		caso.setTipoNegocio(caseBean.getBusinessType());
		caso.setCidade(caseBean.getCity());
		caso.setVagasGaragem(caseBean.getGarageSpace());
		caso.setIdCaso(caseBean.getId());
		caso.setNome(caseBean.getName());
		caso.setBairro(caseBean.getNeighborhood());
		caso.setNumero(caseBean.getNumber());
		caso.setPreco(caseBean.getPrice());
		caso.setEstado(caseBean.getState());
		caso.setRua(caseBean.getStreet());
		caso.setSuites(caseBean.getSuite());
		caso.setAreaTotal(caseBean.getTotalArea());
		caso.setTipo(caseBean.getType());
		caso.setLocation(caseBean.getLocation());
		return caso;
	}
	

	private AdminBean getAdminBean(Administrador admin){
		AdminBean adminBean = new AdminBean();
		adminBean.setIdAdministrador(admin.getIdAdministrador());
		adminBean.setLogin(admin.getLogin());
		adminBean.setNome(admin.getNome());
		adminBean.setPassword(admin.getPassword());
		adminBean.setIsRoot(admin.isRoot());
		return adminBean;
	}
	
	private Administrador getAdmin(AdminBean adminBean) {
		Administrador admin = new Administrador();
		admin.setIdAdministrador(adminBean.getIdAdministrador());
		admin.setLogin(adminBean.getLogin());
		admin.setNome(adminBean.getNome());
		admin.setPassword(adminBean.getPassword());
		admin.setIsRoot(adminBean.getIsRoot()?"true":"false");
		return admin;
	}
	
	private DemandBean getDemandBean(Demanda demanda){
		DemandBean result = new DemandBean();
		result.setAreaConstruida(demanda.getAreaConstruida());
		result.setAreaTotal(demanda.getAreaTotal());
		result.setBairro(demanda.getBairro());
		result.setBanheiros(demanda.getBanheiros());
		result.setCidade(demanda.getCidade());
		result.setEmailCliente(demanda.getEmailCliente());
		result.setEstado(demanda.getEstado());
		result.setIdDemanda(demanda.getIdDemanda());
		result.setJahModerado(demanda.isJahModerado());
		result.setNome(demanda.getNome());
		result.setNomeCliente(demanda.getNomeCliente());
		result.setNumero(demanda.getNumero());
		result.setPreco(demanda.getPreco());
		result.setQuartos(demanda.getQuartos());
		result.setRua(demanda.getRua());
		result.setSuites(demanda.getSuites());
		result.setTelefone(demanda.getTelefone());
		result.setTipo(demanda.getTipo());
		result.setTipoNegocio(demanda.getTipoNegocio());
		result.setVagasGaragem(demanda.getVagasGaragem());
		return result;
	}
	
	private Demanda getDemanda(DemandBean demandBean){
		Demanda result = new Demanda();
		result.setAreaConstruida(demandBean.getAreaConstruida());
		result.setAreaTotal(demandBean.getAreaTotal());
		result.setBairro(demandBean.getBairro());
		result.setBanheiros(demandBean.getBanheiros());
		result.setCidade(demandBean.getCidade());
		result.setEmailCliente(demandBean.getEmailCliente());
		result.setEstado(demandBean.getEstado());
		result.setIdDemanda(demandBean.getIdDemanda());
		result.setJahModerado(demandBean.isJahModerado());
		result.setNome(demandBean.getNome());
		result.setNomeCliente(demandBean.getNomeCliente());
		result.setNumero(demandBean.getNumero());
		result.setPreco(demandBean.getPreco());
		result.setQuartos(demandBean.getQuartos());
		result.setRua(demandBean.getRua());
		result.setSuites(demandBean.getSuites());
		result.setTelefone(demandBean.getTelefone());
		result.setTipo(demandBean.getTipo());
		result.setTipoNegocio(demandBean.getTipoNegocio());
		result.setVagasGaragem(demandBean.getVagasGaragem());
		return result;
	}
	
	private Point getPoint(PointBean point) {
		Point p = new Point();
		p.setIdPoint(point.getIdPoint());
		p.setObs(point.getObs());
		p.setType(point.getType().toString());
		p.setLatitude(point.getLatitude());
		p.setLongitude(point.getLongitude());
		return p;
	}
	
	private PointBean getPointBean(Point point){
		PointBean pb = new PointBean();
		pb.setIdPoint(point.getIdPoint());
		pb.setObs(point.getObs());		
		double latitude = point.getLatitude();
		double longitude = point.getLongitude();
		if(point.getType().equals(Type.SCHOOL.toString())){
			return new PointBean(Type.SCHOOL, latitude, longitude);
		}else if(point.getType().equals(Type.UNIVERSITY.toString())){
			return new PointBean(Type.UNIVERSITY, latitude, longitude);
		}else if(point.getType().equals(Type.SHOPPING_CENTER.toString())){
			return new PointBean(Type.SHOPPING_CENTER, latitude, longitude);
		}else if(point.getType().equals(Type.DOWNTOWN.toString())){
			return new PointBean(Type.DOWNTOWN, latitude, longitude);
		}
		return new PointBean(Type.UNDEFINED, latitude, longitude);
	}
	
	private Line getLine(LineBean line) {
		Line l = new Line();
		l.setIdLine(line.getIdLine());
		l.setType(line.getType().toString());
		l.setObs(line.getObs());
		
		List<Vertex> vertexes = new ArrayList<Vertex>();
		List<Double> latitudes = line.getLatitudes();
		List<Double> longitudes = line.getLongitudes();
		
		for (int i = 0; i < latitudes.size(); i++) {				
			vertexes.add(new Vertex(line.getIdLine(), i, latitudes.get(i), longitudes.get(i)));			
		}
		l.setVertexes(vertexes);
		return l;
	}
	
	private LineBean getLineBean(Line line) {
		LineBean lineBean = new LineBean();
		lineBean.setIdLine(line.getIdLine());
		if(line.getType().equals(Type.ACCESS_ROAD.toString())){
			lineBean.setType(Type.ACCESS_ROAD);
		}else{
			lineBean.setType(Type.UNDEFINED);
		}
		lineBean.setObs(line.getObs());
		
		List<Vertex> vertexes = line.getVertexes();
		List<Double> latitudes = new ArrayList<Double>(vertexes.size());
		List<Double> longitudes = new ArrayList<Double>(vertexes.size());
		for (Vertex vertex : vertexes) {
			int index = (int) vertex.getIndex();
			latitudes.add(index, vertex.getLatitude());
			longitudes.add(index, vertex.getLongitude());
		}
		lineBean.setLatitudes(latitudes);
		lineBean.setLongitudes(longitudes);
		return lineBean;
	}
	
	private PolygonBean getPolygonBean(Polygon polygon) {
		PolygonBean polygonBean = new PolygonBean();
		polygonBean.setIdPolygon(polygon.getIdPolygon());
		if(polygon.getType().equals(Type.GREEN_AREA.toString())){
			polygonBean.setType(Type.GREEN_AREA);
		}else if(polygon.getType().equals(Type.INDUSTRIAL.toString())){
			polygonBean.setType(Type.INDUSTRIAL);
		}
		polygonBean.setObs(polygon.getObs());
		
		List<Vertex> vertexes = polygon.getVertexes();
		List<Double> latitudes = new ArrayList<Double>(vertexes.size());
		List<Double> longitudes = new ArrayList<Double>(vertexes.size());
		for (Vertex vertex : vertexes) {
			int index = (int) vertex.getIndex();
			latitudes.add(index, vertex.getLatitude());
			longitudes.add(index, vertex.getLongitude());
		}
		polygonBean.setLatitudes(latitudes);
		polygonBean.setLongitudes(longitudes);
		return polygonBean;
	}
	
	private Polygon getPolygon(PolygonBean polygonBean) {
		Polygon p = new Polygon();
		p.setIdPolygon(polygonBean.getIdPolygon());
		p.setType(polygonBean.getType().toString());
		p.setObs(polygonBean.getObs());
		
		List<Vertex> vertexes = new ArrayList<Vertex>();
		List<Double> latitudes = polygonBean.getLatitudes();
		List<Double> longitudes = polygonBean.getLongitudes();
		
		for (int i = 0; i < latitudes.size(); i++) {				
			vertexes.add(new Vertex(polygonBean.getIdPolygon(), i, latitudes.get(i), longitudes.get(i)));			
		}
		p.setVertexes(vertexes);
		return p;
	}	
	
	private Foto getFoto(PhotoBean photoBean){
		return new Foto(photoBean.getIdCaso(), photoBean.getPath());
	}
	
	private PhotoBean getPhotoBean(Foto foto){
		PhotoBean result = new PhotoBean();
		result.setIdCaso(foto.getIdCaso());
		result.setPath(foto.getPath());
		return result;
	}

	public boolean savePoint(PointBean point) {
		try {
			this.systemManager.savePoint(getPoint(point));
		} catch (PointAlreadySavedException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<PointBean> getPoints() {
		List<Point> points = this.systemManager.getPoints();
		List<PointBean> pointBeans = new ArrayList<PointBean>();
		for (Point point : points) {
			pointBeans.add(getPointBean(point));
		}
		return pointBeans;
	}

	public boolean saveLine(LineBean line) {
		try {
			this.systemManager.saveLine(getLine(line));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}	

	public List<LineBean> getLines() {
		List<Line> lines = this.systemManager.getLines();
		List<LineBean> lineBeans = new ArrayList<LineBean>();
		for (Line line : lines) {
			lineBeans.add(getLineBean(line));
		}
		return lineBeans;
	}

	public boolean savePolygon(PolygonBean polygonBean) {
		try{
			this.systemManager.savePolygon(getPolygon(polygonBean));			
		}catch(Exception e){
			return false;
		}
		return true;
	}

	public List<PolygonBean> getPolygons() {
		List<Polygon> polygons = this.systemManager.getPolygons();
		List<PolygonBean> polygonBeans = new ArrayList<PolygonBean>();
		for (Polygon polygon : polygons) {
			polygonBeans.add(getPolygonBean(polygon));
		}
		return polygonBeans;
	}
	
}
