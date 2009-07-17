package com.googlecode.projeto1.server;

import java.util.ArrayList;
import java.util.List;

import rbcCycle.caseElement.ImmobileSolution;

import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import facade.SystemFacade;
import facade.SystemManager;


public class PersistenceServiceImpl extends RemoteServiceServlet implements PersistenceService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SystemManager systemManager = null;
	
	public List<CaseBean> doQuery(int resultNumber, String state, String city,
			String neighborhood, String street, String name, float builtArea,
			float totalArea, int garageSpace, int bedroom, int suite,
			int bathroom, String type, float price, int businessType) {
		
		List<ImmobileSolution> results = this.getSystemFacade().doQuery(resultNumber, state, city, 
										 neighborhood, street, name, builtArea, totalArea, 
										 garageSpace, bedroom, suite, bathroom, type, price, businessType);
		List<CaseBean> returnedCases = new ArrayList<CaseBean>();
		for (ImmobileSolution immobileSolution : results) {
			returnedCases.add(this.getCaseBean(immobileSolution));
		}
		return returnedCases;
	}
	
	
//=== Conversors ===	

	private CaseBean getCaseBean(ImmobileSolution result){
		CaseBean caseResult = new CaseBean();
		caseResult.setBathroom(result.getBathroom());
		caseResult.setBedroom(result.getBedroom());
		caseResult.setBuiltArea(result.getBuiltArea());
		caseResult.setBusinessType(result.getBusinessType());
		caseResult.setCity(result.getCity());
		caseResult.setGarageSpace(result.getGarageSpace());
		caseResult.setId(result.getId());
		caseResult.setName(result.getName());
		caseResult.setNeighborhood(result.getNeighborhood());
		caseResult.setNumber(result.getNumber());
		caseResult.setPrice(result.getPrice());
		caseResult.setState(result.getState());
		caseResult.setStreet(result.getStreet());
		caseResult.setSuite(result.getSuite());
		caseResult.setTotalArea(result.getTotalArea());
		caseResult.setType(result.getType());
		return caseResult;
	}
	
	
//=== To get the facade instance method ===	
	private SystemFacade getSystemFacade(){
		if(this.systemManager == null){
			this.systemManager = new SystemManager();
		}
		return this.systemManager;
	}
}
