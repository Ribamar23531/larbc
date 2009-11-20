package com.googlecode.projeto1.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.projeto1.client.beans.AdminBean;
import com.googlecode.projeto1.client.panels.manage.ManagePanel;
import com.googlecode.projeto1.client.panels.query.QueryPanel;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.widgets.MessageBox;

/**
 * Classe chamada para iniciar a interface da aplicação.
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class LARbc implements EntryPoint {
	
	/**
	 * Carrega a página que deve iniciar a aplicacao.
	 */
	public void onModuleLoad() {
//		PanelSwitcher.switchPanel(new WelcomePanel());
//		PanelSwitcher.switchPanel(new QueryPanel());
		fazerLogin();
	}

	
	/** MÉTODO 100% GAMBIARRA SÓ PARA IMPLEMENTAR COISAS DE ADMINISTRAÇÃO SEM PRECISAR
	 * FICAR TODA VEZ FAZENDO LOGIN FIQUEM TRANQUILOS QUE EU APAGAREI ESSE MÉTODO
	 * ASSIM QUE NÃO SE FIZER MAIS ÚTIL =D
	 */
	
	private void fazerLogin() {
		final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
		PERSISTENCE_SERVICE.doLogin("root", "root", new AsyncCallback<AdminBean>() {

			public void onFailure(Throwable arg0) {
				MessageBox.alert("Falha na tentativa de login.");
				
			}

			public void onSuccess(AdminBean admin) {							
				if(admin != null){
					LoginManager.setLogged(admin);
					PanelSwitcher.switchPanel(new ManagePanel());													
				}else{
					MessageBox.alert("Login ou senha inválidos.");
				}
				
			}
		});
		
	}
}
