package rbcCycle.retrieve.localSimilarityFunctions;

import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import persistence.util.NeighborComparator;

public class NeighborsProximity implements LocalSimilarityFunction{

	private NeighborComparator comparator;

	public NeighborsProximity(){
		this.comparator = NeighborComparator.getInstance();
	}
	
	public double compute(Object caseObject, Object queryObject)
			throws NoApplicableSimilarityFunctionException {
		if(!(caseObject instanceof String) || !this.comparator.isANeighbor((String)caseObject)){
			throw new NoApplicableSimilarityFunctionException(this.getClass(), caseObject.getClass());
		}
		if(!(queryObject instanceof String) || !this.comparator.isANeighbor((String)queryObject)){
			throw new NoApplicableSimilarityFunctionException(this.getClass(), queryObject.getClass());
		}
		return 1./this.comparator.getDistance((String) caseObject, (String) queryObject);
	}

	public boolean isApplicable(Object caseObject, Object queryObject) {
		if(caseObject instanceof String && queryObject instanceof String){
			String caseValue = (String) caseObject;
			String queryValue = (String) queryObject;
			if(this.comparator.isANeighbor(caseValue) && this.comparator.isANeighbor(queryValue)){
				return true;
			}
		}
		return false;
	}

}
