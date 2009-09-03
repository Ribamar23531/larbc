package com.googlecode.projeto1.client.panels;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.BorderLayoutData;

public class WindowFieldsAlert extends Window{
	
	private Panel content;
	
	public WindowFieldsAlert(){
		super();
		this.setTitle("Falha na coleta de dados");		
		content = new Panel();
		content.setAutoScroll(true);
		BorderLayoutData centerData = new BorderLayoutData(RegionPosition.CENTER);	
		this.add(content, centerData);
		this.setSize(350, 200);
		this.setAutoScroll(true);
		this.setButtonAlign(Position.CENTER);
		this.addButton(getOkButton());
//		content.setHtml(e.getMessage());
//		alert.add(content);
	}
	
	private Button getOkButton() {
		Button ok = new Button("OK");
		ok.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				hide();
			}
		});
		return ok;
	}

	public void show(String message){
		content.setHtml(message);		
		this.show();
	}

}
