package com.googlecode.projeto1.client.panels.modality;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.FitLayout;

public class LoginWindowPanel extends Panel{
	
	private AbsolutePanel panel;
	private TextBox loginTextBox;
	private PasswordTextBox passwordTextBox;
	
	public LoginWindowPanel(){
		panel = new AbsolutePanel();
		Label loginLabel = new Label("Login:");
		panel.add(loginLabel, 5, 5);
		loginTextBox = new TextBox();
		panel.add(loginTextBox, 39, 5);
		loginTextBox.setSize("145px", "22px");
		Label passwordLabel = new Label("Senha:");
		panel.add(passwordLabel, 5, 36);
		passwordTextBox = new PasswordTextBox();
		panel.add(passwordTextBox, 39, 36);
		this.add(panel);
		panel.setSize("191px", "67px");
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}
	
	public String getLogin(){
		return loginTextBox.getText();
	}
	
	public String getPassword(){
		return passwordTextBox.getText();
	}
	
}
