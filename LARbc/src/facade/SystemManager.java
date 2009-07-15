package facade;

import java.util.List;

import exceptions.AdministradorNotFoundException;
import exceptions.DemandaNotFoundException;
import exceptions.FotoAlreadySavedException;
import exceptions.FotoNotFoundException;
import exceptions.LoginAlreadyRegisteredException;
import exceptions.PermissionDaniedException;

import persistence.GerenteDePersistencia;
import rbcCycle.GerenteDeRBC;
import rbcCycle.caseElement.ImmobileSolution;

import beans.Administrador;
import beans.Caso;
import beans.Demanda;
import beans.Foto;

public class SystemManager implements SystemFacade {

	private GerenteDePersistencia persistenceManager;
	private GerenteDeRBC rbcManager;
	
	public SystemManager(){
		this.persistenceManager = new GerenteDePersistencia();
		this.rbcManager = new GerenteDeRBC(false);
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

	/**
	 * {@inheritDoc}
	 */
	public List<Caso> getAllCasos() {
		return this.persistenceManager.getAllCasos();
	}

	/**
	 * {@inheritDoc}
	 * @throws AdministradorNotFoundException 
	 */
	public Administrador getCasoOwner(Caso caso) throws AdministradorNotFoundException {
		return this.persistenceManager.getCasoOwner(caso);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Caso> getCasos(String login) {
//		return this.persistenceManager.getCasos(login);
		return null;
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
	public void removeAdministrador(Administrador admin, String adminPassword) throws AdministradorNotFoundException, PermissionDaniedException {
		this.persistenceManager.removeAdministrador(admin, adminPassword);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void removeCaso(Administrador admin, Caso caso) {
//		this.persistenceManager.removeCaso(admin, caso);
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
	public void removeFoto(Caso caso, String path) {
//		this.persistenceManager.removeFoto(caso, path);
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveAdministrador(Administrador admin, String adminPassword) throws LoginAlreadyRegisteredException, PermissionDaniedException {
		this.persistenceManager.saveAdministrador(admin, adminPassword);
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveDemanda(Demanda demand) {
		this.persistenceManager.saveDemanda(demand);
	}

	@Override
	public void saveFoto(Caso caso, String path) {
//		this.persistenceManager.saveFoto(caso, path);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateAdministrador(Administrador admin, String adminPassword) throws AdministradorNotFoundException, PermissionDaniedException {
		this.persistenceManager.updateAdministrador(admin, adminPassword);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateCaso(Administrador admin, Caso caso) {
//		this.persistenceManager.updateCaso(admin, caso);
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
	public List<ImmobileSolution> doQuery(int resultNumber, String state,
			String city, String neighborhood, String street, String name,
			float builtArea, float totalArea, int garageSpace, int bedroom,
			int suite, int bathroom, String type, float price, int businessType) {
		return this.rbcManager.doQuery(resultNumber, state, city, neighborhood, street, name, builtArea, 
									   totalArea, garageSpace, bedroom, suite, bathroom, type, price, businessType);
	}}
