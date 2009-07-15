package com.googlecode.projeto1.client.panels.modality;

import com.gwtext.client.widgets.Panel;

public class ModalityPanel extends Panel {
	
	private NorthModalityPanel northPanel;
	
	public ModalityPanel(){
		super(); 
		this.setFrame(true);
		this.northPanel = new NorthModalityPanel();
		this.add(northPanel);
		  
		
	}

}
