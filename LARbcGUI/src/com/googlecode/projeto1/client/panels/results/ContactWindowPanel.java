package com.googlecode.projeto1.client.panels.results;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.TextField;
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
	private TextField emailTextBox;
	private TextField telefoneTextBox;
	private TextField nomeTextBox;
	
	public ContactWindowPanel(){
		panel = new AbsolutePanel();
		
		Label nomeLabel = new Label("Nome:");
		panel.add(nomeLabel, 5, 5);
		nomeTextBox = new TextField();
		panel.add(nomeTextBox, 50, 5);
		nomeTextBox.setSize("160px", "22px");

		Label emailLabel = new Label("E-mail:");
		panel.add(emailLabel, 5, 36);
		emailTextBox = new TextField();
		panel.add(emailTextBox, 50, 36);
		emailTextBox.setSize("160px", "22px");
		
		Label telefoneLabel = new Label("Telefone:");
		panel.add(telefoneLabel, 5, 67);
		telefoneTextBox = new TextField();
		panel.add(telefoneTextBox, 50, 67);
		telefoneTextBox.setSize("160px", "22px");

		this.add(panel);
		panel.setSize("150px", "150px");
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}
	
	public String getEmail(){
		return emailTextBox.getText();
	}
	
	public String getTelefone(){
		return telefoneTextBox.getText();
	}
	
	public String getNome(){
		return nomeTextBox.getText();
	}
}
