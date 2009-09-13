package com.googlecode.projeto1.client.panels.manage;

import com.googlecode.projeto1.client.beans.CaseBean;
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
	private boolean mapOpened;
	
	
//	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	
	public EditWindow(CaseBean caseBean){
		super();
		this.myCaseBean = caseBean;
		this.mapOpened = false;
		this.mappingWindow = MappingWindow.getInstance(true);
		this.windowPanel = new EditWindowPanel(myCaseBean);
		this.setTitle("Editar");
		this.setClosable(false);
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
				windowPanel.updateCase(mapOpened);
				mappingWindow.clearMap();
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
				if(myCaseBean.getLocation() == null){
					MessageBox.alert("Não foi possível localizar a posição geográfica desse caso");
				}else{
					if(SelectedLocation.getLocation() == null || SelectedLocation.getLocation().equals("")){
						mappingWindow.setLocation(myCaseBean.getLocation());						
					}
					mappingWindow.show(button.getElement());
					mapOpened = true;
				}

			}

		});
		return button;
	}

}
