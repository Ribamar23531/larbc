package com.googlecode.projeto1.client.panels.manage.demandTab;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.projeto1.client.beans.DemandBean;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.widgets.MessageBox;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class DemandsTab extends AbsolutePanel{
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	VerticalPanel verticalPanel;
	
	public DemandsTab(){
		super();
		ScrollPanel scrollPanel = new ScrollPanel();
		this.add(scrollPanel, 5, 5);
		scrollPanel.setSize("613px", "490px");
		{
			verticalPanel = new VerticalPanel();
			scrollPanel.setWidget(verticalPanel);
			verticalPanel.setSize("410px", "100%");
			PERSISTENCE_SERVICE.getDemandas(new AsyncCallback<List<DemandBean>>(){
				
				public void onSuccess(List<DemandBean> demands) {
					int index = 1;
					for (DemandBean demandBean : demands) {					
						DemandPanel demandPanel = new DemandPanel(demandBean, index);
						verticalPanel.add(demandPanel);						
						index++;
					}
					
				}
				
				public void onFailure(Throwable arg0) {
					MessageBox.alert("Não foi possível carregar os casos");					
					
				}

			});

		}
	}

}
