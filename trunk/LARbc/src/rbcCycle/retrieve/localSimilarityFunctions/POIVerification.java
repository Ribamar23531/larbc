package rbcCycle.retrieve.localSimilarityFunctions;

import java.sql.SQLException;
import java.util.List;

import persistence.GerenteDePersistencia;
import persistence.util.Coordenates;

import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class POIVerification implements LocalSimilarityFunction {

	private List<String> kindsOfPOI;
	private GerenteDePersistencia persistenceManager;
	private Coordenates queryCoordinate;

	public POIVerification(List<String> kindsOfPOI, Coordenates queryCoordinate){
		this.kindsOfPOI = kindsOfPOI;
		this.queryCoordinate = queryCoordinate;
		this.persistenceManager = new GerenteDePersistencia();
	}
	
	@Override
	public double compute(Object caseObject, Object queryObject)
			throws NoApplicableSimilarityFunctionException {
		
		if(!(caseObject instanceof Integer)){
			throw new NoApplicableSimilarityFunctionException(this.getClass(), caseObject.getClass());
		}
		Integer caseId = (Integer) caseObject;
		Coordenates caseCoordinate = this.persistenceManager.getCoordenadaCaso(caseId);
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
		return 0;
	}

	@Override
	public boolean isApplicable(Object caseObject, Object queryObject) {
		if(caseObject instanceof Integer){
			Integer caseId = (Integer) caseObject;
			Coordenates caseCoordinate = this.persistenceManager.getCoordenadaCaso(caseId);
			if(caseCoordinate != null){
				return true;
			}
		}
		return false;
	}
}
