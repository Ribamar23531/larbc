package com.googlecode.projeto1.client.panels.manage.editTab;

import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.panels.manage.SelectedLocation;
import com.googlecode.projeto1.client.panels.maps.EditCaseMap;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class EditWindow extends Window{
	
	private EditWindowPanel windowPanel;
	private EditCaseMap editCaseMap;
	private CaseBean myCaseBean;
	private boolean mapOpened;
	
	public EditWindow(CaseBean caseBean){
		super();
		this.myCaseBean = caseBean;
		this.mapOpened = false;
		this.editCaseMap = new EditCaseMap();
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
		this.setSize("450px", "335px");
		this.setResizable(false);
	}

	private Button getOkButton() {
		Button button = new Button("OK");
		button.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				windowPanel.updateCase(mapOpened);
				editCaseMap.clearMap();
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
						editCaseMap.setLocation(myCaseBean.getLocation());						
					}
					editCaseMap.show(button.getElement());
					mapOpened = true;
				}

			}

		});
		return button;
	}

}
