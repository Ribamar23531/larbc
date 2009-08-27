package facade;

import java.util.List;

import persistence.GerenteDeEMail;
import persistence.GerenteDeListagens;
import persistence.GerenteDePersistencia;
import rbcCycle.GerenteDeRBC;
import rbcCycle.caseElement.ImmobileSolution;
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
import exceptions.PermissionDeniedException;


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

//	@Override
//	public void verifyAdministrador(String login, String password) throws PermissionDaniedException, AdministradorNotFoundException {		
//		this.persistenceManager.verifyAdministrador(login, password);
//	}

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
	
//	public List<Caso> getCasos(String login) throws AdministradorNotFoundException;

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

//	/**
//	 * {@inheritDoc}
//	 */
//	public List<ImmobileSolution> doQuery(int resultNumber, String state,
//			String city, String neighborhood, String street, String name,
//			float builtArea, float totalArea, int garageSpace, int bedroom,
//			int suite, int bathroom, String type, float price, int businessType) {
//		return this.rbcManager.doQuery(resultNumber, state, city, neighborhood, street, name, builtArea, 
//									   totalArea, garageSpace, bedroom, suite, bathroom, type, price, businessType);
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	public List<ImmobileSolution> doQuery(String state, String city,
//			String neighborhood, String street, String name, float builtArea,
//			float totalArea, int garageSpace, int bedroom, int suite,
//			int bathroom, String type, float price, int businessType) {
//		// TODO Auto-generated method stub
//		return this.rbcManager.doQuery(state, city, neighborhood, street, name, builtArea, totalArea, garageSpace, bedroom, suite, bathroom, type, price, businessType);
//	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Caso> doQuery(int resultNumber, String state,
			String city, String neighborhood, String street, String name,
			float builtArea, float totalArea, int garageSpace, int bedroom,
			int suite, int bathroom, String type, float price, int businessType) {
		return this.rbcManager.doQuery(resultNumber, state, city, neighborhood, street, name, builtArea, 
									   totalArea, garageSpace, bedroom, suite, bathroom, type, price, businessType);
	}

	@Override
	public List<Caso> doQuery(String state, String city, String neighborhood,
			String street, String name, float builtArea, float totalArea,
			int garageSpace, int bedroom, int suite, int bathroom, String type,
			float price, int businessType) {

		return this.rbcManager.doQuery(state, city, neighborhood, street, name, builtArea, 
				   totalArea, garageSpace, bedroom, suite, bathroom, type, price, businessType);
	}
	
	
//	/**
//	 * {@inheritDoc}
//	 */
//	public void setAdministrationPassword(String oldPassword, String newPassword) throws PermissionDeniedException {
//		this.persistenceManager.setPassword(oldPassword, newPassword);
//	}

	

//	/**
//	 * {@inheritDoc}
//	 */
//	public void setAdministrationPassword(String oldPassword, String newPassword) throws PermissionDeniedException {
//		this.persistenceManager.setPassword(oldPassword, newPassword);

	public List<String> listBairros() {
		return this.listingManager.getBairros();

	}
//<<<<<<< .mine
//	@Override
//	public List<String> listBairros() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public List<String> listEstados() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//=======
	
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
}
