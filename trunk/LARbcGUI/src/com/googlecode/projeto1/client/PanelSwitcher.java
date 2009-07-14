package com.googlecode.projeto1.client;

import com.gwtext.client.widgets.Panel;
import com.google.gwt.user.client.ui.RootPanel;

public class PanelSwitcher {
	
	private static Panel currentPanel = null;
	
	public static void switchPanel(Panel newPanel){
		if(currentPanel != null){
			currentPanel.removeFromParent();
		}
		currentPanel = newPanel;
		RootPanel.get().add(currentPanel);
		
	}

}
