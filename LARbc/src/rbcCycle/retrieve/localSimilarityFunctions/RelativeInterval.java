package rbcCycle.retrieve.localSimilarityFunctions;

import exceptions.InvalidArgumentException;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class RelativeInterval implements LocalSimilarityFunction{

	private double percentage;
	private double interval;
	private boolean isSetInterval;
	
	public RelativeInterval(double percentage) throws InvalidArgumentException{
		if(!(new Double("0") < percentage && new Double("1") >= percentage)){
			throw new InvalidArgumentException("The percentage value should be between 0(exclusive) and 1(inclusive");
		}
		this.percentage = percentage;
		this.interval = 0.0;
		this.isSetInterval = false;
	}
	
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
		if(!this.isSetInterval){
			this.interval = queryDouble * this.percentage;
		}
		return 1 - ((double) Math.abs(caseDouble - queryDouble) / this.interval);
	}

	@Override
	public boolean isApplicable(Object caseObject, Object queryObject) {
		if(caseObject instanceof Number && queryObject instanceof Number){
			return true;
		}
		return false;
	}
	
}
