package com.googlecode.projeto1.client;

import com.google.gwt.core.client.EntryPoint;
import com.googlecode.projeto1.client.panels.welcome.WelcomePanel;

public class LARbc implements EntryPoint {
	
	

	public void onModuleLoad() {		
		PanelSwitcher.switchPanel(new WelcomePanel());
//		PanelSwitcher.switchPanel(new ModalityPanel());
	}
}
