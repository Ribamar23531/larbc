package com.googlecode.projeto1.client.panels.modality;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.beans.AdminBean;
import com.googlecode.projeto1.client.panels.manage.ManagePanel;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

public class LoginWindow extends Window{
	
	private static final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);

	private LoginWindowPanel loginWindowPanel;
	
	public LoginWindow(){
		super();
		this.loginWindowPanel = new LoginWindowPanel();
		this.setTitle("Entre com o Login");		
		this.setClosable(true);
		this.setPlain(true);		
		this.setPaddings(5);  
		this.setButtonAlign(Position.CENTER);
		this.addButton(getOkButton());		
		this.setResizable(true);
		this.setCloseAction(Window.HIDE);  
		this.setPlain(true);
		this.add(loginWindowPanel);
		this.setSize("250px", "170px");
		this.setResizable(false);
	}
	
	private Button getOkButton() {
		Button okButton = new Button("OK");
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				String login = loginWindowPanel.getLogin();
				String password = loginWindowPanel.getPassword();
				if(login.equals("") || password.equals("")){				
					MessageBox.alert("Favor preencher todos os campos.");
				}else{
					PERSISTENCE_SERVICE.doLogin(login, password, new AsyncCallback<AdminBean>() {

						public void onFailure(Throwable arg0) {
							MessageBox.alert("Falha na tentativa de login.");
							
						}

						public void onSuccess(AdminBean admin) {							
							if(admin != null){
								PanelSwitcher.switchPanel(new ManagePanel(admin));
								hide();								
							}else{
								MessageBox.alert("Login ou senha inválidos.");
							}
							
						}
					});
				}
			}

		});
		return okButton;
	}

}
