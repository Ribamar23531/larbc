package com.googlecode.projeto1.client.panels.results;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

public class ContactWindow extends Window{
	
//	private static final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);

	private ContactWindowPanel loginWindowPanel;
	
	public ContactWindow(){
		super();
		this.loginWindowPanel = new ContactWindowPanel();
		this.setTitle("Entre com os dados");		
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
					MessageBox.alert("Seu pedido foi enviado com sucesso.");
					hide();
				}
			}

		});
		return okButton;
	}

}
