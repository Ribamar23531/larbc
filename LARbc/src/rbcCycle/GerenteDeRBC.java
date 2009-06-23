package rbcCycle;

import java.util.List;

import beans.Caso;

import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;
import rbcCycle.retrieve.CasesRetriever;
import rbcCycle.retrieve.QueryConfig;

public class GerenteDeRBC {

	private CasesRetriever casesRetriever;
	private boolean testing;
	
	public GerenteDeRBC(boolean testing) {
		this.testing = testing;
		this.casesRetriever = new CasesRetriever(testing);
	}
	
	public List<Caso> doQuery(String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, int businessType){
		try {
			this.casesRetriever.configure();
			QueryConfig queryConfigurer = new QueryConfig();
			queryConfigurer.setQuery(state, city, neighborhood, street, name, builtArea, totalArea, garageSpace, 
					bedroom, suite, bathroom, type, price, businessType);
			CBRQuery query = queryConfigurer.getQuery(); 
			this.casesRetriever.cycle(query);
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
}
