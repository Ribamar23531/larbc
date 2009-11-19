package rbcCycle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import persistence.GerenteDePersistencia;

import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.selection.SelectCases;
import rbcCycle.caseElement.ImmobileSolution;
import rbcCycle.retrieve.CasesRetriever;
import rbcCycle.retrieve.QueryConfig;
import beans.Caso;

public class GerenteDeRBC {

	private CasesRetriever casesRetriever;
	private boolean testing;
	
	public GerenteDeRBC(boolean testing) {
		this.testing = testing;
		this.casesRetriever = new CasesRetriever(testing);
	}
	
	public List<Caso> doQuery(int resultNumber, String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, String businessType){
		try {
			this.casesRetriever.configure();
			QueryConfig queryConfigurer = new QueryConfig();
			queryConfigurer.setQuery(state, city, neighborhood, street, name, builtArea, totalArea, garageSpace, 
					bedroom, suite, bathroom, type, price, businessType);
			CBRQuery query = queryConfigurer.getQuery(); 
			this.casesRetriever.preCycle();
			this.casesRetriever.cycle(query);
			Collection<RetrievalResult> results = this.casesRetriever.getResults();
			Collection<CBRCase> selectedcases = SelectCases.selectTopK(results, resultNumber);
			List<Caso> result = new ArrayList<Caso>();
			for (CBRCase case1 : selectedcases) {
				result.add(this.getCaso((ImmobileSolution)case1.getSolution()));
			}
			return result;
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	public List<Caso> doQuery(String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, String businessType){
		try {
			this.casesRetriever.configure();
			QueryConfig queryConfigurer = new QueryConfig();
			queryConfigurer.setQuery(state, city, neighborhood, street, name, builtArea, totalArea, garageSpace, 
					bedroom, suite, bathroom, type, price, businessType);
			CBRQuery query = queryConfigurer.getQuery(); 
			this.casesRetriever.preCycle();
			this.casesRetriever.cycle(query);
			Collection<RetrievalResult> results = this.casesRetriever.getResults();
			List<Caso> result = new ArrayList<Caso>();
			for (RetrievalResult case1 : results) {
				result.add(this.getCaso((ImmobileSolution)case1.get_case().getSolution()));
			}
			return result;
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	private Caso getCaso(ImmobileSolution caseBean){
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
		caso.setObservacoes(caseBean.getObservations());
		try {
			caso.setLocation(new GerenteDePersistencia().getCasoLocation(caseBean.getLocation()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return caso;
	}

	public List<Caso> doQuery(String state, String city, String neighborhood,
			String street, String name, float builtArea, float totalArea,
			int garageSpace, int bedroom, int suite, int bathroom, String type,
			float price, float priceWeight, String businessType,
			double latitude, double longitude, double pOIWeight, List<String> poiKinds) {

		try {
			this.casesRetriever.configure();
			this.casesRetriever.setWeights(priceWeight, pOIWeight);
			this.casesRetriever.setPOI(poiKinds);
			this.casesRetriever.setQueryLocation(latitude, longitude);
			QueryConfig queryConfigurer = new QueryConfig();
			queryConfigurer.setQuery(state, city, neighborhood, street, name, builtArea, totalArea, garageSpace, 
					bedroom, suite, bathroom, type, price, businessType, 0);
			CBRQuery query = queryConfigurer.getQuery(); 
			this.casesRetriever.preCycle();
			this.casesRetriever.cycle(query);
			Collection<RetrievalResult> results = this.casesRetriever.getResults();
			List<Caso> result = new ArrayList<Caso>();
			for (RetrievalResult case1 : results) {
				result.add(this.getCaso((ImmobileSolution)case1.get_case().getSolution()));
			}
			return result;
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
