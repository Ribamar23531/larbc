package facade;

import java.sql.SQLException;
import java.util.List;

import persistence.GerenteDeEMail;
import persistence.GerenteDeListagens;
import persistence.GerenteDePersistencia;
import rbcCycle.GerenteDeRBC;
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


public class SystemManager implements SystemFacade {

	private GerenteDePersistencia persistenceManager;
	private GerenteDeRBC rbcManager;
	private GerenteDeListagens listingManager;
	private GerenteDeEMail mailManager;
	
	public SystemManager(){
		this.persistenceManager = new GerenteDePersistencia();
		this.rbcManager = new GerenteDeRBC(false);
		this.listingManager = new GerenteDeListagens();
		this.mailManager = new GerenteDeEMail();
	}

	/**
	 * {@inheritDoc}
	 */
	public void createCaso(Administrador admin, Caso caso) throws AdministradorNotFoundException {
		this.persistenceManager.createCaso(admin, caso);
	}

	/**
	 * {@inheritDoc}
	 */
	public Administrador getAdministrador(String login) throws AdministradorNotFoundException {
		return this.persistenceManager.getAdministrador(login);
	}

	@Override
	public List<Administrador> getAdministradores() {		
		return this.persistenceManager.getAdministradores();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Caso> getAllCasos() {
		return this.persistenceManager.getAllCasos();
	}

	@Override
	public Caso getCaso(long idCaso) throws CasoNotFoundException {
		return this.persistenceManager.getCaso(idCaso);
	}
	/**
	 * {@inheritDoc}
	 */
	public Administrador getCasoOwner(Caso caso) throws AdministradorNotFoundException {
		return this.persistenceManager.getCasoOwner(caso);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Caso> getCasos(String login) throws AdministradorNotFoundException {
		return this.persistenceManager.getCasos(login);
	}

	/**
	 * {@inheritDoc}
	 */
	public Demanda getDemanda(long idDemand) throws DemandaNotFoundException {
		return this.persistenceManager.getDemanda(idDemand);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Demanda> getDemandas() {
		return this.persistenceManager.getDemandas();
	}

	/**
	 * {@inheritDoc}
	 */
	public Foto getFoto(long idCaso, String path) throws FotoNotFoundException {
		return this.persistenceManager.getFoto(idCaso, path);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Foto> getFotos(Caso caso) {
		return this.persistenceManager.getFotos(caso);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeAdministrador(Administrador root, Administrador admin) throws AdministradorNotFoundException, PermissionDeniedException {
		this.persistenceManager.removeAdministrador(root, admin);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void removeCaso(Administrador admin, Caso caso) throws AdministradorNotFoundException, PermissionDeniedException, CasoNotFoundException {
		this.persistenceManager.removeCaso(admin, caso);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeDemanda(Demanda demand) throws DemandaNotFoundException {
		this.persistenceManager.removeDemanda(demand);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeFoto(Caso caso, String path) throws FotoNotFoundException {
		this.persistenceManager.removeFoto(caso, path);
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveAdministrador(Administrador root, Administrador admin) throws LoginAlreadyRegisteredException, PermissionDeniedException {
		this.persistenceManager.saveAdministrador(root, admin);
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveDemanda(Demanda demand) {
		this.persistenceManager.saveDemanda(demand);
		this.mailManager.sendMail(demand);
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveFoto(Caso caso, String path) throws FotoAlreadySavedException {
		this.persistenceManager.saveFoto(caso, path);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateAdministrador(Administrador root, Administrador admin) throws AdministradorNotFoundException, PermissionDeniedException {
		this.persistenceManager.updateAdministrador(root, admin);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateCaso(Administrador admin, Caso caso) throws PermissionDeniedException, AdministradorNotFoundException, CasoNotFoundException {
		this.persistenceManager.updateCaso(admin, caso);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateDemanda(Demanda demand) throws DemandaNotFoundException {
		this.persistenceManager.updateDemanda(demand);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateFoto(Foto oldPhoto, Foto newPhoto) throws FotoNotFoundException, FotoAlreadySavedException {
		this.persistenceManager.updateFoto(oldPhoto, newPhoto);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Caso> doQuery(int resultNumber, String state,
			String city, String neighborhood, String street, String name,
			float builtArea, float totalArea, int garageSpace, int bedroom,
			int suite, int bathroom, String type, float price, String businessType) {
		return this.rbcManager.doQuery(resultNumber, state, city, neighborhood, street, name, builtArea, 
									   totalArea, garageSpace, bedroom, suite, bathroom, type, price, businessType);
	}

	@Override
	public List<Caso> doQuery(String state, String city, String neighborhood,
			String street, String name, float builtArea, float totalArea,
			int garageSpace, int bedroom, int suite, int bathroom, String type,
			float price, String businessType) {

		return this.rbcManager.doQuery(state, city, neighborhood, street, name, builtArea, 
				   totalArea, garageSpace, bedroom, suite, bathroom, type, price, businessType);
	}
	
	public List<Caso> doQuery(String state, String city, String neighborhood,
			String street, String name, float builtArea, float totalArea,
			int garageSpace, int bedroom, int suite, int bathroom, String type,
			float price, float priceWeight, String businessType, String location, double POIWeight) {

		return this.rbcManager.doQuery(state, city, neighborhood, street, name, builtArea, 
				   totalArea, garageSpace, bedroom, suite, bathroom, type, price, priceWeight, businessType, location, POIWeight);
	}

	public List<String> listBairros() {
		return this.listingManager.getBairros();

	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<String> listEstados() {
		return this.listingManager.getEstados();
	}

	@Override
	public Administrador doLogin(String login, String password) throws PermissionDeniedException {		
		return this.persistenceManager.doLogin(login, password);
	}

	@Override
	public String getCasoLocation(long id) throws SQLException {
		return this.persistenceManager.getCasoLocation(id);
	}

	@Override
	public List<Point> getPoints() {
		return this.persistenceManager.getPoints();
	}

	@Override
	public void removePoint(Point point) throws PointNotFoundException {
		this.persistenceManager.removePoint(point);
		
	}

	@Override
	public void savePoint(Point point) throws PointAlreadySavedException {
		this.persistenceManager.savePoint(point);
		
	}

	@Override
	public void updatePoint(Point point) throws PointAlreadySavedException {
		this.persistenceManager.updatePoint(point);
		
	}

	@Override
	public List<Line> getLines() {
		return this.persistenceManager.getLines();
	}

	@Override
	public void removeLine(Line line) {
		this.persistenceManager.removeLine(line);
		
	}

	@Override
	public void saveLine(Line line) {
		this.persistenceManager.saveLine(line);
		
	}

	@Override
	public void updateLine(Line line) {
		this.persistenceManager.updateLine(line);		
	}

	@Override
	public List<Polygon> getPolygons() {
		return this.persistenceManager.getPolygons();
	}

	@Override
	public void removePolygon(Polygon polygon) {
		this.persistenceManager.removePolygon(polygon);
	}

	@Override
	public void savePolygon(Polygon polygon) {
		this.persistenceManager.savePolygon(polygon);		
	}

	@Override
	public void updatePolygon(Polygon polygon) {
		this.persistenceManager.updatePolygon(polygon);	
	}
	
}
