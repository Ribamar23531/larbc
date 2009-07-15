package com.googlecode.projeto1.client.panels.modality;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.VerticalLayout;

public class ModalityPanel extends Panel {
	
	private NorthModalityPanel northPanel;
	private CenterModalityPanel centerPanel;
	
	public ModalityPanel(){
		super(); 
		this.setFrame(true);
		this.setLayout(new VerticalLayout());
		this.northPanel = new NorthModalityPanel();
		this.centerPanel = new CenterModalityPanel();
		this.add(northPanel);
		this.add(centerPanel);
		  
		
	}

}
