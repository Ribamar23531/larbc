package rbcCycle.retrieve;

import java.net.URL;
import java.util.Collection;

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
	
	private boolean testing;
	
	public CasesRetriever(boolean testing){
		try {
			this.testing = testing;
			this.configure();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void configure() throws ExecutionException {
		try{
			this.connector = new DataBaseConnector();
			String URLString = "com/googlecode/projeto/larbc/rbccycle/databaseconfig/";
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
		NNConfig config = new NNConfig();
		Collection<RetrievalResult> evaluation = NNScoringMethod.evaluateSimilarity(this.caseBase.getCases(), queryToDo, config);
	}

	@Override
	public void postCycle() throws ExecutionException {
		this.connector.close();
	}
	
	public static void main(String[] args) {
		CasesRetriever app = new CasesRetriever(true);
		try {
			app.configure();
			app.preCycle();

			CBRQuery query = new CBRQuery();
			
			app.cycle(query);
			app.postCycle();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
