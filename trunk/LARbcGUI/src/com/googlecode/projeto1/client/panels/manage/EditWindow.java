package com.googlecode.projeto1.client.panels.manage;

import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

public class EditWindow extends Window{
	
	private EditWindowPanel windowPanel;	
	
	public EditWindow(CaseBean caseBean){
		super();		
		this.windowPanel = new EditWindowPanel(caseBean);
		this.setTitle("Editar");
		this.setClosable(true);
		this.setPlain(true);		
		this.setPaddings(5);  
		this.setButtonAlign(Position.CENTER);
		this.addButton(getOkButton());
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

}
