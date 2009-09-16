package com.googlecode.projeto1.client.panels.manage.editTab;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.widgets.MessageBox;

public class EditTab extends ScrollPanel{
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	private VerticalPanel verticalPanel;
	
	public EditTab(){
		super();
		verticalPanel = new VerticalPanel();
		this.setWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		PERSISTENCE_SERVICE.getAllCasos(new AsyncCallback<List<CaseBean>>() {
			
			public void onSuccess(List<CaseBean> cases) {
				int index = 1;
				for (CaseBean caseBean : cases) {
					CaseEditPanel editPanel = new CaseEditPanel(caseBean, index);
					verticalPanel.add(editPanel);
					index++;
				}
				
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Não foi possível carregar os casos");
				
			}
		});		

	}

}
