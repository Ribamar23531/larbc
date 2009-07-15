package com.googlecode.projeto1.client.panels.modality;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.panels.Util;
import com.googlecode.projeto1.client.panels.manage.ManagePanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;

public class NorthModalityPanel extends Panel{
	
	private Image managerButtonImage;
	private Image selectedManagerButtonImage;
	private Panel wrapperPanel;
	
	public NorthModalityPanel(){
		super();
		this.setBorder(false);  
		this.setPaddings(15);  
		this.setFrame(true);
		createManagerButton();
		createSelectedManagerButton();

		//add wrapper panel just to dispaly the borders  
		wrapperPanel = new Panel();
		wrapperPanel.setLayout(new ColumnLayout());
		Panel leftPanel = new Panel();
		Panel rightPanel = new Panel();
		leftPanel.add(Util.createImage(Util.LARBC_IMAGE_PATH));
		rightPanel.add(managerButtonImage);
		wrapperPanel.add(leftPanel, new ColumnLayoutData(.86));
		wrapperPanel.add(rightPanel, new ColumnLayoutData(.14));  

		this.add(wrapperPanel); 
	}
	
	private void rebuildNorthPanel(Image buttonImage){
		wrapperPanel.removeAll();
		Panel leftPanel = new Panel();
		Panel rightPanel = new Panel();
		leftPanel.add(Util.createImage(Util.LARBC_IMAGE_PATH));
		rightPanel.add(buttonImage);
		wrapperPanel.add(leftPanel, new ColumnLayoutData(.86));
		wrapperPanel.add(rightPanel, new ColumnLayoutData(.14));
		this.doLayout();
	}

	private void createSelectedManagerButton() {
		selectedManagerButtonImage = Util.createImage(Util.ADMINISTRAR_BUTTON_IMAGE_SELECTED);		
		selectedManagerButtonImage.addMouseOutHandler(new MouseOutHandler(){

			public void onMouseOut(MouseOutEvent event) {
				rebuildNorthPanel(managerButtonImage);
				
			}
			
		});
		
		selectedManagerButtonImage.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				PanelSwitcher.switchPanel(new ManagePanel());
				
			}
			
		});
		
	}

	private void createManagerButton() {
		managerButtonImage = Util.createImage(Util.ADMINISTRAR_BUTTON_IMAGE);
		managerButtonImage.addMouseOverHandler(new MouseOverHandler(){

			public void onMouseOver(MouseOverEvent event) {
				rebuildNorthPanel(selectedManagerButtonImage);
			}
			
		});	
		  
		
	}

}
