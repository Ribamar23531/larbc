package rbcCycle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.selection.SelectCases;
import rbcCycle.caseElement.ImmobileSolution;
import rbcCycle.retrieve.CasesRetriever;
import rbcCycle.retrieve.QueryConfig;

public class GerenteDeRBC {

	private CasesRetriever casesRetriever;
	private boolean testing;
	
	public GerenteDeRBC(boolean testing) {
		this.testing = testing;
		this.casesRetriever = new CasesRetriever(testing);
	}
	
	public List<ImmobileSolution> doQuery(int resultNumber, String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, int businessType){
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
			List<ImmobileSolution> result = new ArrayList<ImmobileSolution>();
			for (CBRCase case1 : selectedcases) {
				result.add((ImmobileSolution) case1.getSolution());
			}
			return result;
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
}