package com.googlecode.projeto1.client.panels.results;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class ContactWindowPanel extends Panel{
	
	private AbsolutePanel panel;
	private TextBox emailTextBox;
	private TextBox telefoneTextBox;
	
	public ContactWindowPanel(){
		panel = new AbsolutePanel();
		Label loginLabel = new Label("E-mail:");
		panel.add(loginLabel, 5, 5);
		emailTextBox = new TextBox();
		panel.add(emailTextBox, 39, 5);
		emailTextBox.setSize("145px", "22px");
		Label passwordLabel = new Label("Telefone:");
		panel.add(passwordLabel, 5, 36);
		telefoneTextBox = new TextBox();
		panel.add(telefoneTextBox, 50, 36);
		this.add(panel);
		panel.setSize("150px", "67px");
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}
	
	public String getLogin(){
		return emailTextBox.getText();
	}
	
	public String getPassword(){
		return telefoneTextBox.getText();
	}
	
}
