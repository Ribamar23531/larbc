package com.googlecode.projeto1.client.panels.manage.adminTab;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.LoginManager;
import com.googlecode.projeto1.client.beans.AdminBean;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.core.NameValuePair;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;

public class AdminPanel extends CaptionPanel{
	
	private String defaultName;
	private String defaultLogin;
	private String defaultPassword;
	private boolean defaultIsRoot;
	private AdminBean myAdmin;
	private TextBox nameTextBox;
	private TextBox loginTextBox;
	private CheckBox isRootCheckBox;
	private Button changePassButton;
	private Button changeButton;
	private PasswordChangerWindow passwordChangerWindow;
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);

	public AdminPanel(AdminBean admin, int index) {
		super("Administrador " + index);
		this.myAdmin = admin;
		this.defaultName = myAdmin.getNome();
		this.defaultLogin = myAdmin.getLogin();
		this.defaultPassword = myAdmin.getPassword();
		this.defaultIsRoot = myAdmin.getIsRoot();
		this.passwordChangerWindow = new PasswordChangerWindow(myAdmin);
		this.setSize("427px", "116px");

		AbsolutePanel absolutePanel = new AbsolutePanel();
		this.setContentWidget(absolutePanel);
		absolutePanel.setSize("368px", "100px");

		Label namelabel = new Label("Nome:");
		absolutePanel.add(namelabel, 5, 5);
		nameTextBox = new TextBox();
		absolutePanel.add(nameTextBox, 56, 5);
		nameTextBox.setSize("308px", "22px");
		nameTextBox.setText(myAdmin.getNome());
		Label loginLabel = new Label("Login:");
		absolutePanel.add(loginLabel, 5, 28);
		loginLabel.setSize("43px", "18px");
		loginTextBox = new TextBox();
		absolutePanel.add(loginTextBox, 56, 32);
		loginTextBox.setSize("179px", "22px");
		loginTextBox.setText(myAdmin.getLogin());
		isRootCheckBox = new CheckBox("Super Usuário");
		absolutePanel.add(isRootCheckBox, 240, 32);
		isRootCheckBox.setSize("124px", "18px");
		isRootCheckBox.setChecked(myAdmin.getIsRoot());
		changePassButton = getChangePassButton();
		
		absolutePanel.add(changePassButton, 5, 59);
		changeButton = getChangeButton();
		absolutePanel.add(changeButton, 167, 59);
		Button removeButton = new Button("Remover");
		absolutePanel.add(removeButton, 255, 59);
		removeButton.setSize("109px", "34px");

	}
	
	private Button getChangePassButton(){
		Button button = new Button("Mudar Senha");
		button.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {
				passwordChangerWindow.show(arg0.getElement());				
			}
		});
		button.setSize("157px", "34px");
		return button;
	}
	
	private Button getChangeButton(){
		Button button = new Button("Alterar");
		button.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {
				MessageBox.show(new MessageBoxConfig() {

					{
						setTitle("Alterar Administrador");
						setMsg("Você deseja realmente realizar a alteração?");
						setIconCls(MessageBox.QUESTION);
						setButtons(MessageBox.YESNO);
						setButtons(new NameValuePair[] {
								new NameValuePair("yes", "Sim"),
								new NameValuePair("no", "Não") });
						setCallback(new MessageBox.PromptCallback() {
							public void execute(String btnID, String text) {
								if (btnID.equals("yes")) {
									updateAdmin();
								}else{
									setDefaultFields();									
								}

							}							
						});
					}
				});
			}
		});
		button.setSize("83px", "34px");
		return button;
	}
	
	private void setDefaultFields(){
		nameTextBox.setText(defaultName);
		loginTextBox.setText(defaultLogin);
		isRootCheckBox.setChecked(defaultIsRoot);		
	}
	
	private void recoverAdminBean(){
		myAdmin.setNome(defaultName);
		myAdmin.setLogin(defaultLogin);
		myAdmin.setIsRoot(defaultIsRoot);
		myAdmin.setPassword(defaultPassword);
	}
	
	private void updateAdmin() {
		myAdmin.setNome(nameTextBox.getText());
		myAdmin.setLogin(loginTextBox.getText());
		myAdmin.setIsRoot(isRootCheckBox.isChecked());
		myAdmin.setPassword(passwordChangerWindow.getPassword());
		PERSISTENCE_SERVICE.updateAdministrador(LoginManager.getLogedAdministrator(), myAdmin, new AsyncCallback<String>() {

			public void onFailure(Throwable arg0) {
				MessageBox.alert("Não foi possível realizar a atualização");
				recoverAdminBean();
				setDefaultFields();	
			}

			public void onSuccess(String response) {
				if(response.equals("")){
					defaultName = myAdmin.getNome();
					defaultLogin = myAdmin.getLogin();
					defaultIsRoot = myAdmin.getIsRoot();
					defaultPassword = myAdmin.getPassword();					
					MessageBox.alert("Alteração realizada com sucesso");
				}else{
					MessageBox.alert("Erro: " + response);
					recoverAdminBean();
					setDefaultFields();	
				}
				
			}
		});
		
	}

}
