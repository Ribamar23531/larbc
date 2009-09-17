package com.googlecode.projeto1.client.panels.manage.adminTab;

import com.googlecode.projeto1.client.beans.AdminBean;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

public class PasswordChangerWindow extends Window{
	
	private ChangePasswordPanel changePasswordPanel;
	private AdminBean adminBean;
	private String password;
	
	public PasswordChangerWindow(AdminBean admin){
		super();
		this.adminBean = admin;
		password = admin.getPassword();
		this.changePasswordPanel = new ChangePasswordPanel();
		this.setTitle("Alterar Senha");		
		this.setClosable(true);
		this.setPlain(true);		
		this.setPaddings(5);  
		this.setButtonAlign(Position.CENTER);
		this.addButton(getOkButton());		
		this.setResizable(true);
		this.setCloseAction(Window.HIDE);  
		this.setPlain(true);
		this.add(changePasswordPanel);
		this.setSize("300px", "220px");
		this.setResizable(false);
	}
	
	private Button getOkButton() {
		Button okButton = new Button("OK");
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				if(changePasswordPanel.emptyOldPassword()){
					MessageBox.alert("Favor digitar a antiga senha");
				}else if(changePasswordPanel.emptyNewPassword()){
					MessageBox.alert("Favor digitar a nova senha");
				}else if(!changePasswordPanel.newPasswordConfirmed()){
					MessageBox.alert("Favor confirme a nova senha corretamente");
				}else if(!changePasswordPanel.isValidOldPassword(adminBean.getPassword())){
					MessageBox.alert("A antiga senha não está correta!!!");
					hide();
				}else{
					password = changePasswordPanel.getNewPassword();					
					hide();
				}
			}

		});
		return okButton;
	}
	
	public String getPassword(){
		return this.password;
	}

}
