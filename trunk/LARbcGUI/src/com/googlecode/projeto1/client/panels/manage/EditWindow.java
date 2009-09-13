package com.googlecode.projeto1.client.panels.manage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

public class EditWindow extends Window{
	
	private EditWindowPanel windowPanel;
	private MappingWindow mappingWindow;
	private CaseBean myCaseBean;
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	
	public EditWindow(CaseBean caseBean){
		super();
		this.myCaseBean = caseBean;
		this.mappingWindow = MappingWindow.getInstance(true);
		this.windowPanel = new EditWindowPanel(myCaseBean);
		this.setTitle("Editar");
		this.setClosable(true);
		this.setPlain(true);		
		this.setPaddings(5);  
		this.setButtonAlign(Position.CENTER);
		this.addButton(getOkButton());
		this.addButton(getMapButton());
		this.setResizable(true);
		this.setCloseAction(Window.HIDE);  
		this.setPlain(true);
		this.add(windowPanel);
		this.setSize("450px", "300px");
		this.setResizable(false);
	}

	private Button getOkButton() {
		Button button = new Button("OK");
		button.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				windowPanel.updateCase();
//				PanelSwitcher.switchPanel(new ManagePanel());				
				hide();
			}

		});
		return button;
	}
	
	private Button getMapButton() {
		Button button = new Button("Visualizar Mapa");
		button.addListener(new ButtonListenerAdapter(){
			public void onClick(final Button button, EventObject e) {
				PERSISTENCE_SERVICE.getCaseLocation(myCaseBean, new AsyncCallback<String>() {
					
					public void onSuccess(String location) {
						mappingWindow.setLocation(location);
						myCaseBean.setLocation(location);
						mappingWindow.show(button.getElement());
						
					}
					
					public void onFailure(Throwable arg0) {
						MessageBox.alert("Não foi possível localizar a posição geográfica desse caso");						
					}
				});
			}

		});
		return button;
	}

}
