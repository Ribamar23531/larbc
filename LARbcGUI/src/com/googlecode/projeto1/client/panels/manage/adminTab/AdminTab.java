package com.googlecode.projeto1.client.panels.manage.adminTab;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.projeto1.client.beans.AdminBean;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.widgets.MessageBox;

public class AdminTab extends ScrollPanel{	
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	private VerticalPanel verticalPanel;
	
	public AdminTab(){
		super();
		verticalPanel = new VerticalPanel();
		this.setWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		PERSISTENCE_SERVICE.getAdministradores(new AsyncCallback<List<AdminBean>>() {
			
			public void onSuccess(List<AdminBean> admins) {
				int index = 1;
				for (AdminBean admin : admins) {
					AdminPanel adminPanel = new AdminPanel(admin, index);					
					verticalPanel.add(adminPanel);
					index++;
				}
				
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Não foi possível carregar os administradores");
				
			}
		});
//		(new AsyncCallback<List<CaseBean>>() {
//			
//			public void onSuccess(List<CaseBean> cases) {
//				int index = 1;
//				for (CaseBean caseBean : cases) {
//					CaseEditPanel editPanel = new CaseEditPanel(caseBean, index);
//					verticalPanel.add(editPanel);
//					index++;
//				}
//				
//			}
//			
//			public void onFailure(Throwable arg0) {
//				MessageBox.alert("Não foi possível carregar os casos");
//				
//			}
//		});		

	}
	
//	public AdminTab(){
//		super();	
//		{
//			ScrollPanel scrollPanel = new ScrollPanel();
//			this.add(scrollPanel, 0, 0);
//			scrollPanel.setSize("432px", "215px");
//			{
//				VerticalPanel adminVerticalPanel = new VerticalPanel();
//				scrollPanel.setWidget(adminVerticalPanel);
//				adminVerticalPanel.setSize("100%", "100%");				
//				{
//					CaptionPanel captionPanel = new CaptionPanel("Administrador 1");
//					captionPanel.setSize("427px", "116px");
//					adminVerticalPanel.add(captionPanel);
//					{
//						AbsolutePanel absolutePanel = new AbsolutePanel();
//						captionPanel.setContentWidget(absolutePanel);
//						absolutePanel.setSize("423px", "3cm");
//						{
//							Label nomeLabel = new Label("Nome:");
//							absolutePanel.add(nomeLabel, 5, 5);
//						}
//						{
//							TextBox nomeTextBox = new TextBox();
//							absolutePanel.add(nomeTextBox, 61, 5);
//							nomeTextBox.setSize("248px", "22px");
//						}
//						{
//							Label loginLabel = new Label("Login:");
//							absolutePanel.add(loginLabel, 5, 31);
//						}
//						{
//							TextBox loginTextBox = new TextBox();
//							absolutePanel.add(loginTextBox, 61, 31);
//						}
//						{
//							Label senhaLabel = new Label("Senha:");
//							absolutePanel.add(senhaLabel, 5, 57);
//						}
//						{
//							TextBox senhaTextBox = new TextBox();
//							absolutePanel.add(senhaTextBox, 61, 57);
//						}
//						{
//							Button alterarButton = new Button("New button");
//							alterarButton.setText("Alterar");
//							absolutePanel.add(alterarButton, 335, 5);
//						}
//						{
//							Button removerButton = new Button("Remover");
//							absolutePanel.add(removerButton, 309, 44);
//						}
//					}
//				}
//			}
//		}
//		{
//			Button criarAdministradorButton = new Button("Criar Novo");
//			this.add(criarAdministradorButton, 310, 220);
//		}
//	}

}
