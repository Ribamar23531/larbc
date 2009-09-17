package com.googlecode.projeto1.client.panels.manage.adminTab;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.FitLayout;

public class ChangePasswordPanel extends Panel {
	
	private AbsolutePanel absolutePanel;
	private PasswordTextBox oldPassTextBox;
	private PasswordTextBox newPassTextBox;
	private PasswordTextBox passwordConfirmationTextBox;
	
	public ChangePasswordPanel(){
		absolutePanel = new AbsolutePanel();
		this.add(absolutePanel);
		absolutePanel.setSize("259px", "164px");

		Label oldPassLabel = new Label("Senha Antiga:");
		absolutePanel.add(oldPassLabel, 5, 5);

		oldPassTextBox = new PasswordTextBox();
		absolutePanel.add(oldPassTextBox, 108, 5);

		Label newPassLabel = new Label("Nova Senha:");
		absolutePanel.add(newPassLabel, 5, 28);
		
		newPassTextBox = new PasswordTextBox();
		absolutePanel.add(newPassTextBox, 108, 28);
		Label confLabel = new Label("Confirmação:");
		absolutePanel.add(confLabel, 5, 51);
		
		passwordConfirmationTextBox = new PasswordTextBox();
		absolutePanel.add(passwordConfirmationTextBox, 108, 51);
		HTML explanation = new HTML("New HTML", true);
		explanation.setText("Para sua própria segurança é necessário" +
				" confirmar sua nova senha digitando-a novamente.");
		absolutePanel.add(explanation, 24, 86);
		explanation.setSize("214px", "60px");
		
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}
	
	public boolean emptyOldPassword(){
		return oldPassTextBox.getText().equals("");
	}
	
	public boolean emptyNewPassword(){
		return newPassTextBox.getText().equals("");
	}
	
	public boolean newPasswordConfirmed(){
		return newPassTextBox.getText().equals(passwordConfirmationTextBox.getText()) && !emptyNewPassword();
	}
	
	public boolean isValidOldPassword(String password){
		return oldPassTextBox.getText().equals(password);
	}
	
	public String getNewPassword(){
		return newPassTextBox.getText();
	}
	

}
