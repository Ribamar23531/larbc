package com.googlecode.projeto1.client;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Viewport;

public class PanelSwitcher {
	
//	private static Panel currentPanel = null;
	
	public static void switchPanel(Panel newPanel){
//		if(currentPanel != null){
//			currentPanel.removeFromParent();
//		}
		new Viewport(newPanel).doLayout();
//		currentPanel = newPanel;
//		RootPanel.get().add(currentPanel);
		
	}

}
