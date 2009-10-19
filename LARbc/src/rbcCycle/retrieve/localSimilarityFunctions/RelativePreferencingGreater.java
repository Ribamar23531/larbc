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
		Number queryNumber = (Number)(queryObject);
		Double caseDouble = caseNumber.doubleValue();
		Double queryDouble = queryNumber.doubleValue();
		Double min = Math.min(caseDouble, queryDouble);
		Double max = Math.max(caseDouble, queryDouble);
		return min/max;
	}

	@Override
	public boolean isApplicable(Object caseObject, Object queryObject) {
		if(caseObject instanceof Number && queryObject instanceof Number){
			Number queryValue = (Number)queryObject;
			Number caseValue = (Number) queryObject;
			Number maxValue;
			if(queryValue.doubleValue() <= caseValue.doubleValue()){
				maxValue = caseValue;
			}else{
				maxValue = queryValue;
			}
			if(!maxValue.equals("0")){
				return true;
			}
		}
		return false;
	}		
}
