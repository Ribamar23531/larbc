package rbcCycle.retrieve.localSimilarityFunctions;

import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class RelativePreferencingGreater implements LocalSimilarityFunction {

	@Override
	public double compute(Object caseObject, Object queryObject)
			throws NoApplicableSimilarityFunctionException {
		
		return 0;
	}

	@Override
	public boolean isApplicable(Object caseObject, Object queryObject) {
		return false;
	}

}
