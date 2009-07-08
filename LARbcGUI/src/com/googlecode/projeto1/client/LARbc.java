package com.googlecode.projeto1.client;

import com.google.gwt.core.client.EntryPoint;
import com.googlecode.projeto1.client.panels.welcome.WelcomePanel;
import com.gwtext.client.widgets.Viewport;

public class LARbc implements EntryPoint {

	public void onModuleLoad() {
		new Viewport(new WelcomePanel());
	}
}
