package com.googlecode.projeto1.client.panels.results;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class QuestionPanel extends Panel{
	
	AbsolutePanel panel;
	
	public QuestionPanel(){
		panel = new AbsolutePanel();
		Label loginLabel = new Label("Voce deseja enviar essas informacoes para o administrador?");
		panel.add(loginLabel, 5, 5);
		this.add(panel);
		panel.setSize("191px", "67px");
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}
	
}
