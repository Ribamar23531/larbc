package com.googlecode.projeto1.client.panels.modality;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.panels.Util;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;

public class NorthModalityPanel extends Panel{
	
	private Image managerButtonImage;
	private Image selectedManagerButtonImage;
	private Panel wrapperPanel;
	private LoginWindow loginWindow;
	
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
		this.setAutoScroll(true);
		this.loginWindow = new LoginWindow();
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
		selectedManagerButtonImage = Util.createImage(Util.SELECTED_ADMINISTRAR_BUTTON_IMAGE);
		selectedManagerButtonImage.setPixelSize(160, 40);
		selectedManagerButtonImage.addMouseListener(new MouseListenerAdapter(){

			

			public void onMouseLeave(Widget arg0) {
				rebuildNorthPanel(managerButtonImage);
				
			}			
			
			
		});
		
		selectedManagerButtonImage.addClickListener(new ClickListener(){

			public void onClick(Widget arg0) {
				loginWindow.show(selectedManagerButtonImage.getElement());
				
			}
			
		});		
		
	}

	private void createManagerButton() {
		managerButtonImage = Util.createImage(Util.ADMINISTRAR_BUTTON_IMAGE);
		managerButtonImage.setPixelSize(160, 40);
		managerButtonImage.addMouseListener(new MouseListenerAdapter(){
			
			public void onMouseEnter(Widget arg0) {
				rebuildNorthPanel(selectedManagerButtonImage);
				
			}
			
		});
		  
		
	}

}
