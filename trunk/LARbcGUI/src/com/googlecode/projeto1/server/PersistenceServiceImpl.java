package com.googlecode.projeto1.server;

import java.util.ArrayList;
import java.util.List;

import beans.Administrador;
import beans.Caso;
import beans.Demanda;
import beans.Foto;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.projeto1.client.beans.AdminBean;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.beans.DemandBean;
import com.googlecode.projeto1.client.beans.PhotoBean;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;

import exceptions.AdministradorNotFoundException;
import exceptions.CasoNotFoundException;
import exceptions.DemandaNotFoundException;
import exceptions.FotoAlreadySavedException;
import exceptions.FotoNotFoundException;
import exceptions.LoginAlreadyRegisteredException;
import exceptions.PermissionDaniedException;
import facade.SystemFacade;
import facade.SystemManager;


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
			int bathroom, String type, float price, int businessType) {
		
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
			int bathroom, String type, float price, int businessType) {

		List<Caso> results = this.getSystemFacade().doQuery(state, city, 
				 neighborhood, street, name, builtArea, totalArea, 
				 garageSpace, bedroom, suite, bathroom, type, price, businessType);
		
		List<CaseBean> returnedCases = new ArrayList<CaseBean>();
		for (Caso caso : results) {
			returnedCases.add(this.getCaseBean(caso));
		}
		return returnedCases;
	}

	public void crateCaso(AdminBean admin, CaseBean caso) {
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


	public void removeAdministrador(AdminBean adminToRemove, String adminPassword) {
		try {
			this.getSystemFacade().removeAdministrador(this.getAdmin(adminToRemove), adminPassword);
		} catch (AdministradorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PermissionDaniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void removeCaso(AdminBean admin, CaseBean caso) {
		try {
			this.getSystemFacade().removeCaso(this.getAdmin(admin), this.getCaso(caso));
		} catch (AdministradorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PermissionDaniedException e) {
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


	public void saveAdministrador(AdminBean adminToInsert, String adminPassword) {
		try {
			this.getSystemFacade().saveAdministrador(this.getAdmin(adminToInsert), adminPassword);
		} catch (LoginAlreadyRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PermissionDaniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void saveDemanda(DemandBean demanda) {
		this.getSystemFacade().saveDemanda(this.getDemanda(demanda));
	}


	public void setAdminPassword(String oldAdminPassword,
			String newAdminPassword) {
		try {
			this.getSystemFacade().setAdministrationPassword(oldAdminPassword, newAdminPassword);
		} catch (PermissionDaniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void updateAdministrador(AdminBean adminToUpdate,
			String adminPassword) {
		try {
			this.getSystemFacade().updateAdministrador(this.getAdmin(adminToUpdate), adminPassword);
		} catch (AdministradorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PermissionDaniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void updateCaso(AdminBean admin, CaseBean caso) {
		try {
			this.getSystemFacade().updateCaso(this.getAdmin(admin), this.getCaso(caso));
		} catch (PermissionDaniedException e) {
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


	public String verifyAdministrador(String login, String password) {
		try {
			this.getSystemFacade().verifyAdministrador(login, password);
		} catch (PermissionDaniedException e) {
			return "NOT OK";
		} catch (AdministradorNotFoundException e) {
			return "NOT OK";
		}
		return "OK";
	}

//=== Conversors ===	
	
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
		return caso;
	}
	

	private AdminBean getAdminBean(Administrador admin){
		AdminBean adminBean = new AdminBean();
		adminBean.setIdAdministrador(admin.getIdAdministrador());
		adminBean.setLogin(admin.getLogin());
		adminBean.setNome(admin.getNome());
		adminBean.setPassword(admin.getPassword());
		return adminBean;
	}
	
	private Administrador getAdmin(AdminBean adminBean) {
		Administrador admin = new Administrador();
		admin.setIdAdministrador(adminBean.getIdAdministrador());
		admin.setLogin(adminBean.getLogin());
		admin.setNome(adminBean.getNome());
		admin.setPassword(adminBean.getPassword());
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
	
	private Foto getFoto(PhotoBean photoBean){
		return new Foto(photoBean.getIdCaso(), photoBean.getPath());
	}
	
	private PhotoBean getPhotoBean(Foto foto){
		PhotoBean result = new PhotoBean();
		result.setIdCaso(foto.getIdCaso());
		result.setPath(foto.getPath());
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
