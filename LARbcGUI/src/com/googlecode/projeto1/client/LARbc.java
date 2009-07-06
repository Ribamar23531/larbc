package com.googlecode.projeto1.client;

import com.google.gwt.core.client.EntryPoint;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Viewport;

public class LARbc implements EntryPoint {

	public void onModuleLoad() {
		new Viewport(new Panel("Testing..."));
	}
}
