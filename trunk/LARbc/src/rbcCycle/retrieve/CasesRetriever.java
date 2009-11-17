package rbcCycle.retrieve;

import java.net.URL;
import java.util.Collection;
import java.util.List;

import persistence.util.Coordenates;

import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.util.FileIO;

public class CasesRetriever implements StandardCBRApplication {

	private Connector connector;
	private CBRCaseBase caseBase;
	
	private Collection<RetrievalResult> queryResult;
	
	private boolean testing;
	private double priceWeight;
	private double locationWeight;
	private List<String> pointsOfInterest;
	private Coordenates queryCoordinate;
	
	public CasesRetriever(boolean testing){
		try {
			this.testing = testing;
			this.queryResult = null;
			this.configure();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setWeights(double priceWeight, double locationWeight){
		this.priceWeight = priceWeight;
		this.locationWeight = locationWeight;
	}
	
	public void setPOI(List<String> pointsOfInterest){
		this.pointsOfInterest = pointsOfInterest;
	}

	public void configure() throws ExecutionException {
		try{
			this.connector = new DataBaseConnector();
			String URLString = "rbcCycle/dataBaseConfig/";
			URLString += (this.testing ? "testingdatabaseconfig.xml" : "databaseconfig.xml");
			URL configFileURL = FileIO.findFile(URLString);
			this.connector.initFromXMLfile(configFileURL);
			this.caseBase = new LinealCaseBase();
		}catch(Exception exc){
			System.out.println("Exception in method configure");
			exc.printStackTrace();
		}
	}

	@Override
	public CBRCaseBase preCycle() throws ExecutionException {
		this.caseBase.init(this.connector);
		Collection<CBRCase> allTheCases = this.caseBase.getCases();
		return this.caseBase;
	}

	public void cycle(CBRQuery queryToDo) throws ExecutionException {
		SimilarityConfiguration configuration = new SimilarityConfiguration(this.priceWeight, this.locationWeight, this.pointsOfInterest, this.queryCoordinate);
		NNConfig config = configuration.getConfiguration();
		this.queryResult = NNScoringMethod.evaluateSimilarity(this.caseBase.getCases(), queryToDo, config);
	}

	@Override
	public void postCycle() throws ExecutionException {
		this.caseBase.close();
		this.connector.close();
	}
	
	public Collection<RetrievalResult> getResults(){
		return this.queryResult;
	}

	public void setQueryLocation(double latitude, double longitude) {
		Coordenates coordenate = new Coordenates(latitude, longitude);
		this.queryCoordinate = coordenate;
	}
}
