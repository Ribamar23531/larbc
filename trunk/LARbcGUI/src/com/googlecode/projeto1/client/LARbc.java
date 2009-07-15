package com.googlecode.projeto1.client;

import com.google.gwt.core.client.EntryPoint;
import com.googlecode.projeto1.client.panels.modality.NorthModalityPanel;

public class LARbc implements EntryPoint {
	
	

	public void onModuleLoad() {		
//		PanelSwitcher.switchPanel(new WelcomePanel());
//		PanelSwitcher.switchPanel(new ModalityPanel());
		PanelSwitcher.switchPanel(new NorthModalityPanel());
	
	}
}
