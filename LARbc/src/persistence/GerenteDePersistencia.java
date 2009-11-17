package persistence;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import persistence.DAO.AdministradorDAO;
import persistence.DAO.CasoDAO;
import persistence.DAO.DemandaDAO;
import persistence.DAO.FotoDAO;
import persistence.DAO.LineDAO;
import persistence.DAO.PointDAO;
import persistence.DAO.PolygonDAO;
import persistence.hibernate.hibernateDAO.AdministradorHibernateDAO;
import persistence.hibernate.hibernateDAO.CasosHibernateDAO;
import persistence.hibernate.hibernateDAO.DemandasHibernateDAO;
import persistence.hibernate.hibernateDAO.FotoHibernateDAO;
import persistence.hibernate.hibernateDAO.LineHibernateDAO;
import persistence.hibernate.hibernateDAO.PointHibernateDAO;
import persistence.hibernate.hibernateDAO.PolygonHibernateDAO;
import persistence.jdbc.SpatialQueries;
import persistence.util.Coordenates;
import persistence.util.Paths;
import beans.Administrador;
import beans.Caso;
import beans.Demanda;
import beans.Foto;
import beans.poi.Line;
import beans.poi.Point;
import beans.poi.Polygon;
import exceptions.AdministradorNotFoundException;
import exceptions.CasoNotFoundException;
import exceptions.DemandaNotFoundException;
import exceptions.FotoAlreadySavedException;
import exceptions.FotoNotFoundException;
import exceptions.LoginAlreadyRegisteredException;
import exceptions.PermissionDeniedException;
import exceptions.PointAlreadySavedException;
import exceptions.PointNotFoundException;

public class GerenteDePersistencia {	
	
	private AdministradorDAO administradorDAO;
	private FotoDAO fotoDAO;
	private DemandaDAO demandaDAO;
	private CasoDAO casoDAO;
	private PointDAO pointDAO;
	private LineDAO lineDAO;
	private PolygonDAO polygonDAO;
	private SpatialQueries spatialQueries;
	private static GerenteDePersistencia minhaInstancia = null;
	
	public GerenteDePersistencia(boolean testing){
		this.administradorDAO = new AdministradorHibernateDAO(testing);
		this.fotoDAO = new FotoHibernateDAO(testing);
		this.demandaDAO = new DemandasHibernateDAO(testing);
		this.casoDAO = new CasosHibernateDAO(testing);
		this.pointDAO = new PointHibernateDAO(testing);
		this.lineDAO =  new LineHibernateDAO(testing);
		this.polygonDAO = new PolygonHibernateDAO(testing);
		spatialQueries = new SpatialQueries();
	}
	
	public GerenteDePersistencia(){
		this.administradorDAO = new AdministradorHibernateDAO();
		this.fotoDAO = new FotoHibernateDAO();
		this.demandaDAO = new DemandasHibernateDAO();
		this.casoDAO = new CasosHibernateDAO();
		this.pointDAO = new PointHibernateDAO();
		this.lineDAO =  new LineHibernateDAO();
		this.polygonDAO = new PolygonHibernateDAO();
	}
	
	public static GerenteDePersistencia getInstance(boolean testando){
		if(minhaInstancia == null){			
			minhaInstancia = new GerenteDePersistencia(testando);			
		}
		return minhaInstancia;
	}
	
	//=============================Operacoes sobre sistema======================================\\
		
	private void verifyPermission(Administrador admin) throws PermissionDeniedException{
		Administrador a;
		try {
			a = getAdministrador(admin.getLogin());
		} catch (AdministradorNotFoundException e) {
			throw new PermissionDeniedException();//se nao encontrar administrador entao alguem tentou um login invalido
		}
		if(!a.getPassword().equals(admin.getPassword()) || !a.isRoot()){
			throw new PermissionDeniedException();
		}		
	}
	
	public Administrador doLogin(String login, String password) throws PermissionDeniedException{
		try {
			Administrador admin = getAdministrador(login);
			if(!admin.getPassword().equals(password)){
				throw new PermissionDeniedException();
			}
			return admin;
		} catch (AdministradorNotFoundException e) {
			throw new PermissionDeniedException();
		}
	}
	//=============================Operacoes sobre Administrador===============================\\	
	
	public void saveAdministrador(Administrador root, Administrador admin) throws LoginAlreadyRegisteredException, PermissionDeniedException/*, RequiredArgumentException*/{
		verifyPermission(root);
		administradorDAO.saveAdministrador(admin);
	}
	
	public void removeAdministrador(Administrador root, Administrador admin) throws AdministradorNotFoundException, PermissionDeniedException{
		verifyPermission(root);
		administradorDAO.removeAdministrador(admin);
	}
	
	public Administrador getAdministrador(long idAdmin) throws AdministradorNotFoundException{
		return administradorDAO.getAdministrador(idAdmin);
	}
	
	public List<Administrador> getAdministradores() {
		return administradorDAO.getAdministradores();
	}
	
	public Administrador getAdministrador(String login) throws AdministradorNotFoundException{
		return administradorDAO.getAdministrador(login);
	}
	
	public void updateAdministrador(Administrador root, Administrador admin) throws AdministradorNotFoundException, PermissionDeniedException{
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

	public void removeCaso(Administrador admin, Caso caso) throws AdministradorNotFoundException, PermissionDeniedException, CasoNotFoundException {
		this.removeCaso(caso);
	}

	public void removeFoto(Caso caso, String path) throws FotoNotFoundException {
		for (Foto foto : getFotos(caso)) {
			if(foto.getPath().equals(path)){
				removeFoto(foto);
			}
		}
	}

	public void updateCaso(Administrador admin, Caso caso) throws PermissionDeniedException, AdministradorNotFoundException, CasoNotFoundException {
		this.casoDAO.updateCaso(caso);
	}

	public List<Caso> getCasos(String login) throws AdministradorNotFoundException {
		return getCasos(getAdministrador(login).getIdAdministrador());
	}

	public void verifyLogin(String login, String password) throws PermissionDeniedException {
		try {
			Administrador admin = this.getAdministrador(login);
			if(!admin.getPassword().equals(password)){
				throw new PermissionDeniedException();
			}
		} catch (AdministradorNotFoundException e) {
			throw new PermissionDeniedException();
		}
	}

	
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

	public String getCasoLocation(long id) throws SQLException {
		return this.casoDAO.getCasoLocation(id);
	}
	
	//=================================Operacoes sobre Pontos de Interesse===================================\\
	
	public void savePoint(Point point) throws PointAlreadySavedException{
		this.pointDAO.savePoint(point);
	}
	
	public void removePoint(Point point) throws PointNotFoundException{
		this.pointDAO.removePoint(point);
	}
	
	public List<Point> getPoints(){
		return this.pointDAO.getPoints();
	}
	
	public void saveLine(Line line){
		this.lineDAO.saveLine(line);
	}
	
	public void removeLine(Line line){
		this.lineDAO.removeLine(line);
	}
	
	public List<Line> getLines(){
		return this.lineDAO.getLines();
	}
	
	public void savePolygon(Polygon polygon){
		this.polygonDAO.savePolygon(polygon);
	}
	
	public void removePolygon(Polygon polygon){
		this.polygonDAO.removePolygon(polygon);
	}
	
	public List<Polygon> getPolygons(){
		return this.polygonDAO.getPolygons();
	}
	
	//=================================Operacoes sobre Consultas Espaciais===================================\\
	
	public void setPOIDistance(double distance){
		this.spatialQueries.setDistance(distance);
	}
	
	public int qteOfNearPOIByType(Coordenates coordenates, String type) throws SQLException{
		return spatialQueries.qteOfNearPOIByType(coordenates, type);
	}
	
}
