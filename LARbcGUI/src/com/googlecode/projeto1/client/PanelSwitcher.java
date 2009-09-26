package com.googlecode.projeto1.client;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Viewport;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class PanelSwitcher {
	
	public static void switchPanel(Panel newPanel){
		new Viewport(newPanel).doLayout();
	}

}
