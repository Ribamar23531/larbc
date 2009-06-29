package rbcCycle.retrieve.localSimilarityFunctions;

import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

/**
 * This function evaluates if the arguments are similar. 
 * @author Joao Felipe.
 *
 */
public class Like implements LocalSimilarityFunction {

	/**
	 * Applies the similarity function, returning the similarity coefficient that belongs to [0,1].
	 * @param caseObject The object from the case to be analyzed.
	 * @param queryObject The object from the query to be analyzed.
	 * @return The similarity coefficient between the two objects.
	 */
	public double compute(Object caseObject, Object queryObject)
			throws NoApplicableSimilarityFunctionException {

		
		
		return 0;
	}

	/**
	 * Verify if the given arguments are compatibles for this funcion.
	 * @param caseObject The object from the case to be verified.
	 * @param queryObject The object from the query to be verified.
	 * @return A boolean value indicating if the two objects are compatibles for this similarity 
	 * function.
	 */
	public boolean isApplicable(Object caseObject, Object queryObject) {
		if((caseObject==null)&&(queryObject==null))
			return true;
		else if(caseObject==null)
			return queryObject instanceof String;
		else if(queryObject==null)
			return caseObject instanceof String;
		else
			return (caseObject instanceof String)&&(queryObject instanceof String);
	}
	
//	"asj  aksj"
	
	private static String removeUnecessaryBlankSpaces(String toRemove){
		String result = toRemove.trim();
		boolean previousSpace = false;
		int size = 0;
		
		for (int i = 0; i < result.length(); i++) {
			String charInPositioni = ((Character)result.charAt(i)).toString();
			if(charInPositioni.equals(" ") && previousSpace == false){
				previousSpace = true;
			}else if(charInPositioni.equals(" ") && previousSpace == true){
				result = result.substring(0, i) + result.substring(i + 1, result.length());
			}else{
				previousSpace = false;
			}
		}
		return result;
	}
	
//	private int numberOfSimilarities(String first, String second){
//		if(first.length() != second.length() || first.split(" ").length != second.split(" ").length){
//			return 0;
//		}
//		int similarities = 0;
//		for (int i = 0; i < first.length(); i++) {
//			if( ((Character)first.charAt(i)).toString().equalsIgnoreCase(((Character)second.charAt(i)).toString()) ){
//				++similarities;
//			}
//		}
//	}
	
	public static void main(String[] args) {
		String test1 = " aaa ";
		String test2 = "aaa ";
		String test3 = " aaa";
		String test4 = " a  a  a ";
		String test5 = " a                        aa ";
		String test6 = "aa                       a";
		
		System.out.println("Original String: " + test1 + "  Converted one: " + removeUnecessaryBlankSpaces(test1));
		System.out.println("Original String: " + test2 + "  Converted one: " + removeUnecessaryBlankSpaces(test2));
		System.out.println("Original String: " + test3 + "  Converted one: " + removeUnecessaryBlankSpaces(test3));
		System.out.println("Original String: " + test4 + "  Converted one: " + removeUnecessaryBlankSpaces(test4));
		System.out.println("Original String: " + test5 + "  Converted one: " + removeUnecessaryBlankSpaces(test5));
		System.out.println("Original String: " + test6 + "  Converted one: " + removeUnecessaryBlankSpaces(test6));
	}

}
