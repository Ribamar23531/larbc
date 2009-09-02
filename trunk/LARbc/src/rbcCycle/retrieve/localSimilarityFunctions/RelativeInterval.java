package rbcCycle.retrieve.localSimilarityFunctions;

import exceptions.InvalidArgumentException;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class RelativeInterval implements LocalSimilarityFunction{

	private double percentage;
	private double interval;
	private boolean isSetInterval;
	
	/**
	 * The constructor of a RelativeInterval function.
	 * @param percentage The percentage of the value of the query that have to be considered in the comparison.
	 * @throws InvalidArgumentException Thrown when the percentage is out of ]0, 1] interval.
	 */
	public RelativeInterval(double percentage) throws InvalidArgumentException{
		if(!(new Double("0") < percentage && new Double("1") >= percentage)){
			throw new InvalidArgumentException("The percentage value should be between 0(exclusive) and 1(inclusive");
		}
		this.percentage = percentage;
		this.interval = 0.0;
		this.isSetInterval = false;
	}
	
	/**
	 * Applies the similarity function, returning the similarity coefficient that belongs to [0,1].
	 * @param caseObject The object from the case to be analyzed.
	 * @param queryObject The object from the query to be analyzed.
	 * @return The similarity coefficient between the two objects.
	 */
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
			this.isSetInterval = true;
		}
		return 1 - ((double) Math.abs(caseDouble - queryDouble) / this.interval);
	}

	/**
	 * Verify if the given arguments are compatibles for this function.
	 * @param caseObject The object from the case to be verified.
	 * @param queryObject The object from the query to be verified.
	 * @return A boolean value indicating if the two objects are compatibles for this similarity 
	 * function.
	 */
	public boolean isApplicable(Object caseObject, Object queryObject) {
		if(caseObject instanceof Number && queryObject instanceof Number){
			return true;
		}
		return false;
	}
	
}
