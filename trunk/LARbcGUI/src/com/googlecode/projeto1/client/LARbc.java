package com.googlecode.projeto1.client;

import com.google.gwt.core.client.EntryPoint;
import com.googlecode.projeto1.client.panels.welcome.WelcomePanel;

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
		PanelSwitcher.switchPanel(new WelcomePanel());
	}
}
