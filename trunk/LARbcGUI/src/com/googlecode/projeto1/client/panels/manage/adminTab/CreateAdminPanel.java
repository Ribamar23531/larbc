package com.googlecode.projeto1.client.panels.manage.adminTab;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.LoginManager;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.beans.AdminBean;
import com.googlecode.projeto1.client.panels.manage.ManagePanel;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.widgets.MessageBox;

public class CreateAdminPanel extends CaptionPanel{
		
	private TextBox nameTextBox;
	private TextBox loginTextBox;
	private CheckBox isRootCheckBox;
	private PasswordTextBox passwordTextBox;
	private PasswordTextBox confirmPassTextBox;
	private Button createButton;	
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);

	public CreateAdminPanel() {
		super("Criar Novo Administrador");

		AbsolutePanel absolutePanel = new AbsolutePanel();
		this.add(absolutePanel);
		absolutePanel.setSize("368px", "135px");

		Label namelabel = new Label("Nome:");
		absolutePanel.add(namelabel, 5, 5);
		namelabel.setSize("46px", "18px");

		nameTextBox = new TextBox();
		absolutePanel.add(nameTextBox, 104, 1);
		nameTextBox.setSize("260px", "22px");

		Label loginLabel = new Label("Login:");
		absolutePanel.add(loginLabel, 5, 32);
		loginLabel.setSize("43px", "18px");

		loginTextBox = new TextBox();
		absolutePanel.add(loginTextBox, 104, 28);
		loginTextBox.setSize("179px", "22px");

		isRootCheckBox = new CheckBox("Super Usuário");
		absolutePanel.add(isRootCheckBox, 104, 109);
		isRootCheckBox.setSize("124px", "18px");

		createButton = getCreateButton();
		absolutePanel.add(createButton, 291, 28);
		createButton.setSize("73px", "72px");

		Label passLabel = new Label("Senha:");
		absolutePanel.add(passLabel, 5, 59);

		passwordTextBox = new PasswordTextBox();
		absolutePanel.add(passwordTextBox, 104, 55);
		passwordTextBox.setSize("179px", "22px");

		Label confPassLabel = new Label("Confirmação:");
		absolutePanel.add(confPassLabel, 5, 82);

		confirmPassTextBox = new PasswordTextBox();
		absolutePanel.add(confirmPassTextBox, 104, 82);
		passwordTextBox.setSize("179px", "22px");

	}
	
	private Button getCreateButton(){
		Button button = new Button("Criar");
		button.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {
				if(fieldsOK()){
					if(!passwordTextBox.getText().equals(confirmPassTextBox.getText())){
						MessageBox.alert("Senha e Confirmação não conferem");
					}else{
						createAdmin();						
					}
				}else{
					MessageBox.alert("Todos os campos são obrigatórios, favor preencher todos");
				}
			}		
			
		});
		button.setSize("73px", "72px");
		return button;
	}
	
	private boolean fieldsOK() {
		if(nameTextBox.getText().equals("")){
			return false;
		}
		if(loginTextBox.getText().equals("")){
			return false;
		}
		if(passwordTextBox.getText().equals("")){
			return false;
		}
		if(confirmPassTextBox.getText().equals("")){
			return false;
		}
		return true;
	}
	
	private void createAdmin() {
		AdminBean admin = new AdminBean();
		admin.setNome(nameTextBox.getText());
		admin.setLogin(loginTextBox.getText());
		admin.setPassword(passwordTextBox.getText());
		admin.setIsRoot(isRootCheckBox.isChecked());
		PERSISTENCE_SERVICE.saveAdministrador(LoginManager.getLogedAdministrator(), admin, new AsyncCallback<String>() {
			
			public void onSuccess(String arg0) {
				if(arg0.equals("")){
					PanelSwitcher.switchPanel(new ManagePanel());
					MessageBox.alert("Administrador armazenado com sucesso");
				}else{
					MessageBox.alert("Erro: " + arg0);
				}
				
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Não foi possível salvar esse administrador");
				
			}
		});
		nameTextBox.setText("");
		loginTextBox.setText("");
		isRootCheckBox.setChecked(false);
		passwordTextBox.setText("");
		confirmPassTextBox.setText("");
	}	

}
