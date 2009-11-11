package rbcCycle.retrieve.localSimilarityFunctions;

import java.util.List;

import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class POIVerification implements LocalSimilarityFunction {

	private List<String> kindsOfPOI;

	public POIVerification(List<String> kindsOfPOI){
		this.kindsOfPOI = kindsOfPOI;
	}
	
	@Override
	public double compute(Object caseObject, Object queryObject)
			throws NoApplicableSimilarityFunctionException {
		double caseResult = 0;//this.pOIAnalyzer.haveAround(this.kindsOfPOI, (String) caseObject); 
		double queryResult = 0;//this.pOIAnalyzer.haveAround(this.kindsOfPOI, (String) queryObject);
		return 0;
	}

	@Override
	public boolean isApplicable(Object caseObject, Object queryObject) {
		if(caseObject instanceof String && queryObject instanceof String){
			String caseValue = (String) caseObject;
			String queryValue = (String) queryObject;
			if(isALocation(caseValue) && isALocation(queryValue)){
				return true;
			}
		}
		return false;
	}
	
	private boolean isALocation(String location){
		//verificar se o dado string é uma localização.
		return true;
	}

}
