package rbcCycle.retrieve.localSimilarityFunctions;

import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class RelativePreferencingGreater implements LocalSimilarityFunction {

	@Override
	public double compute(Object caseObject, Object queryObject)
			throws NoApplicableSimilarityFunctionException {
		if(!(caseObject instanceof Number)){
			throw new NoApplicableSimilarityFunctionException(this.getClass(), caseObject.getClass());
		}
		if(!(queryObject instanceof Number)){
			throw new NoApplicableSimilarityFunctionException(this.getClass(), queryObject.getClass());
		}
		Number caseNumber = (Number)(caseObject);
		Double caseDouble = caseNumber.doubleValue();
		Number queryNumber = (Number)(queryObject);
		Double queryDouble = queryNumber.doubleValue();
		return Math.min(caseDouble, queryDouble)/Math.max(caseDouble, queryDouble);
	}

	@Override
	public boolean isApplicable(Object caseObject, Object queryObject) {
		if(caseObject instanceof Number && queryObject instanceof Number){
			return true;
		}
		return false;
	}		
}
