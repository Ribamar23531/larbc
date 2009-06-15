package com.googlecode.projeto.larbc.rbccycle.application;

import java.net.URL;

import com.googlecode.projeto.larbc.rbccycle.database.HSQLDBServer;

import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.util.FileIO;

public class Application implements StandardCBRApplication {

	private Connector connector;
	private CBRCaseBase caseBase;

	public void configure() throws ExecutionException {
		try{
			this.connector = new DataBaseConnector();
			HSQLDBServer.init();
			URL configFileURL = FileIO.findFile("com/googlecode/projeto/larbc/rbccycle/databaseconfig/databaseconfig.xml");
			this.connector.initFromXMLfile(configFileURL);
			this.caseBase = new LinealCaseBase();
		}catch(Exception exc){
			System.out.println("Exception in method configure");
			exc.printStackTrace();
		}
	}

	public void cycle(CBRQuery arg0) throws ExecutionException {

	}

	@Override
	public void postCycle() throws ExecutionException {
		// TODO Auto-generated method stub

	}

	@Override
	public CBRCaseBase preCycle() throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
