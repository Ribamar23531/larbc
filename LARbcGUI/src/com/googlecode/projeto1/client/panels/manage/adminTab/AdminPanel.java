package com.googlecode.projeto1.client.panels.manage.adminTab;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.projeto1.client.beans.AdminBean;

public class AdminPanel extends CaptionPanel{
	
	private AdminBean myAdmin;
	private TextBox nomeTextBox;
	private TextBox loginTextBox;
	private TextBox senhaTextBox;

	public AdminPanel(AdminBean admin, int index) {
		super("Administrador " + index);
		this.myAdmin = admin;
		this.setSize("427px", "116px");		
		{
			AbsolutePanel absolutePanel = new AbsolutePanel();
			this.setContentWidget(absolutePanel);
			absolutePanel.setSize("423px", "3cm");
			{
				Label nomeLabel = new Label("Nome:");
				absolutePanel.add(nomeLabel, 5, 5);
			}
			{
				nomeTextBox = new TextBox();
				absolutePanel.add(nomeTextBox, 61, 5);
				nomeTextBox.setSize("248px", "22px");
				nomeTextBox.setText(myAdmin.getNome());
			}
			{
				Label loginLabel = new Label("Login:");
				absolutePanel.add(loginLabel, 5, 31);
			}
			{
				loginTextBox = new TextBox();
				absolutePanel.add(loginTextBox, 61, 31);
				loginTextBox.setText(myAdmin.getLogin());
			}
			{
				Label senhaLabel = new Label("Senha:");
				absolutePanel.add(senhaLabel, 5, 57);
			}
			{
				senhaTextBox = new TextBox();
				absolutePanel.add(senhaTextBox, 61, 57);
			}
			{
				Button alterarButton = new Button("New button");
				alterarButton.setText("Alterar");
				absolutePanel.add(alterarButton, 335, 5);
			}
			{
				Button removerButton = new Button("Remover");
				absolutePanel.add(removerButton, 309, 44);
			}
		}
	}

}
