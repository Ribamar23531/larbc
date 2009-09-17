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
				verticalPanel.add(new CreateAdminPanel());
				for (AdminBean admin : admins) {
					EditAdminPanel adminPanel = new EditAdminPanel(admin, index);					
					verticalPanel.add(adminPanel);
					index++;
				}
				
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Não foi possível carregar os administradores");
				
			}
		});

	}

}
