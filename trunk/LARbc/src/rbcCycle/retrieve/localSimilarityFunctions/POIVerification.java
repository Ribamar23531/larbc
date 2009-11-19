package rbcCycle.retrieve.localSimilarityFunctions;

import java.sql.SQLException;
import java.util.List;

import persistence.GerenteDePersistencia;
import persistence.util.Coordinates;

import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class POIVerification implements LocalSimilarityFunction {

	private List<String> kindsOfPOI;
	private GerenteDePersistencia persistenceManager;
	private Coordinates queryCoordinate;

	public POIVerification(List<String> kindsOfPOI, Coordinates queryCoordinate){
		this.kindsOfPOI = kindsOfPOI;
		this.queryCoordinate = queryCoordinate;
		this.persistenceManager = new GerenteDePersistencia();
	}
	
	@Override
	public double compute(Object caseObject, Object queryObject)
			throws NoApplicableSimilarityFunctionException {
		
		if(this.queryCoordinate.getLatitude() == Double.MAX_VALUE && this.queryCoordinate.getLongitude() == Double.MAX_VALUE){
			return 0;
		}
		if(!(caseObject instanceof Integer)){
			throw new NoApplicableSimilarityFunctionException(this.getClass(), caseObject.getClass());
		}
		Integer caseId = (Integer) caseObject;
		Coordinates caseCoordinate = null;
		try {
			caseCoordinate = this.persistenceManager.getCasoLocation(caseId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int queryPOINumber = 0;
		int casePOINumber = 0;
			 try {
				 for (String kindOfPOI : this.kindsOfPOI) {
				 queryPOINumber += this.persistenceManager.qteOfNearPOIByType(this.queryCoordinate, kindOfPOI);
				 casePOINumber += this.persistenceManager.qteOfNearPOIByType(caseCoordinate, kindOfPOI);
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return 1./(Math.max(queryPOINumber, casePOINumber) - Math.min(queryPOINumber, casePOINumber));
	}

	@Override
	public boolean isApplicable(Object caseObject, Object queryObject) {
		if(caseObject instanceof Integer){
			Integer caseId = (Integer) caseObject;
			Coordinates caseCoordinate;
			try {
				caseCoordinate = this.persistenceManager.getCasoLocation(caseId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			if(caseCoordinate != null){
				return true;
			}
		}
		return false;
	}
}
