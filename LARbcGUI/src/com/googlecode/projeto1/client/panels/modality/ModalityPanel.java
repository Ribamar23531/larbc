package com.googlecode.projeto1.client.panels.modality;

import com.googlecode.projeto1.client.panels.Util;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;

public class ModalityPanel extends Panel {	
	
	public ModalityPanel(){
		super();
		  
		this.setBorder(false);  
		this.setPaddings(15);  
		this.setFrame(true);
//		this.setLayout(new FitLayout());  

		//add wrapper panel just to dispaly the borders  
		Panel wrapperPanel = new Panel();  
		wrapperPanel.setBorder(true);  
		wrapperPanel.setBodyStyle("border-style:dotted;border-color:blue;");  

		wrapperPanel.setLayout(new ColumnLayout());  
//		wrapperPanel.setTitle("Column Layout - Percentage Only");
		Panel p1 = new Panel();
		p1.add(Util.createImage(Util.LARBC_IMAGE_PATH));
		Panel p2 = new Panel();
		p2.add(Util.createImage(Util.ADMINISTRAR_BUTTON_IMAGE));
		wrapperPanel.add(p1, new ColumnLayoutData(.86));
		wrapperPanel.add(p2, new ColumnLayoutData(.14));  

		this.add(wrapperPanel); 
	}

}
