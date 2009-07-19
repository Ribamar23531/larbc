package com.googlecode.projeto1.client.panels.manage;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.projeto1.client.beans.AdminBean;
import com.gwtext.client.widgets.form.Label;

public class AdminTab extends AbsolutePanel{
	
	private AdminBean administrator;
	
	public AdminTab(AdminBean admin){
		super();		
		this.administrator = admin;
		{
			ScrollPanel scrollPanel = new ScrollPanel();
			this.add(scrollPanel, 0, 0);
			scrollPanel.setSize("432px", "215px");
			{
				VerticalPanel adminVerticalPanel = new VerticalPanel();
				scrollPanel.setWidget(adminVerticalPanel);
				adminVerticalPanel.setSize("100%", "100%");				
				{
					CaptionPanel captionPanel = new CaptionPanel("Administrador 1");
					captionPanel.setSize("427px", "116px");
					adminVerticalPanel.add(captionPanel);
					{
						AbsolutePanel absolutePanel = new AbsolutePanel();
						captionPanel.setContentWidget(absolutePanel);
						absolutePanel.setSize("423px", "3cm");
						{
							Label nomeLabel = new Label("Nome:");
							absolutePanel.add(nomeLabel, 5, 5);
						}
						{
							TextBox nomeTextBox = new TextBox();
							absolutePanel.add(nomeTextBox, 61, 5);
							nomeTextBox.setSize("248px", "22px");
						}
						{
							Label loginLabel = new Label("Login:");
							absolutePanel.add(loginLabel, 5, 31);
						}
						{
							TextBox loginTextBox = new TextBox();
							absolutePanel.add(loginTextBox, 61, 31);
						}
						{
							Label senhaLabel = new Label("Senha:");
							absolutePanel.add(senhaLabel, 5, 57);
						}
						{
							TextBox senhaTextBox = new TextBox();
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
		}
		{
			Button criarAdministradorButton = new Button("Criar Novo");
			this.add(criarAdministradorButton, 310, 220);
		}
	}

}
