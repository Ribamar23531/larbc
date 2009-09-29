package com.googlecode.projeto1.client.panels.modality;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.panels.Util;
import com.googlecode.projeto1.client.panels.demands.DemandSellerPanel;
import com.googlecode.projeto1.client.panels.query.QueryPanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class NewModality extends Panel {
	
	private AbsolutePanel rootPanel;
	private Image comprarButton;
	private Image selectedComprarButton;
	private boolean isSelectedComprarButton;
	private Image venderButton;
	private Image selectedVenderButton;
	private boolean isSelectedVenderButton;
	private Panel buttonsComprarPanel;
	private Panel buttonsPanel;
	private Image managerButtonImage;
	private Image selectedManagerButtonImage;
	private LoginWindow loginWindow;
	private Panel wrapperPanel;

	public NewModality() {
		super();
		rootPanel = new AbsolutePanel();
		{
			Image image = new Image("images/larbc.png");
			rootPanel.add(image, 35, 25);
			image.setPixelSize(280, 100);
		}
		buttonsPanel = new Panel();		
		buttonsComprarPanel = new Panel();
		wrapperPanel = new Panel();
		wrapperPanel.setLayout(new ColumnLayout());
		this.isSelectedVenderButton = false;
		this.isSelectedComprarButton = false;
		Image image = Util.createImage(Util.QUESTION);
		image.setPixelSize(280, 70);
		createVenderButton();
		createComprarButton();
		rootPanel.add(image, 300, 250);
		rootPanel.add(buttonsComprarPanel, 220, 360);
		rootPanel.add(buttonsPanel, 490, 360);
		this.add(rootPanel);
		this.setLayout(new FitLayout());
		this.setFrame(true);
		this.loginWindow = new LoginWindow();

		
		createManagerButton();
		createSelectedManagerButton();

		wrapperPanel = new Panel();
		Panel rightPanel = new Panel();
		rightPanel.add(managerButtonImage);
		wrapperPanel.add(rightPanel);  
		rootPanel.add(wrapperPanel, 1080, 40);
		this.loginWindow = new LoginWindow();

	}
	
	//BOTAO COMPRAR
	private void createComprarButton() {
		comprarButton = Util.createImage(Util.COMPRAR_BUTTON_IMAGE);
		comprarButton.setPixelSize(160, 40);
		comprarButton.addMouseListener(new MouseListenerAdapter(){

			public void onMouseEnter(Widget arg0) {
				isSelectedComprarButton = true;
				rebuildComprarPanel();
				
			}
		});
		selectedComprarButton = Util.createImage(Util.SELECTED_COMPRAR_BUTTON_IMAGE);
		selectedComprarButton.setPixelSize(160, 40);
		selectedComprarButton.addMouseListener(new MouseListenerAdapter(){			

			public void onMouseLeave(Widget arg0) {
				isSelectedComprarButton = false;
				rebuildComprarPanel();				
			}			
			
		});
		selectedComprarButton.addClickListener(new ClickListener(){

			public void onClick(Widget arg0) {
				PanelSwitcher.switchPanel(new QueryPanel());				
			}
			
		});
		
		buildButtonsComprarPanel();
		
	}
	
	private void buildButtonsComprarPanel() {
		buttonsComprarPanel.removeAll();
		if(!isSelectedComprarButton){
			buttonsComprarPanel.add(comprarButton);
		}else{
			buttonsComprarPanel.add(selectedComprarButton);
		}
		buttonsComprarPanel.doLayout();		
	}

	private void rebuildComprarPanel() {
		buildButtonsComprarPanel();		
	}
	
	
	//BOTAO VENDER
	private void buildButtonsPanel() {
		buttonsPanel.removeAll();
		if(!isSelectedVenderButton){
			buttonsPanel.add(venderButton);
		}else{
			buttonsPanel.add(selectedVenderButton);
		}
		buttonsPanel.doLayout();		
	}

	private void rebuildPanel() {
		buildButtonsPanel();		
	}
	
	private void createVenderButton() {
		venderButton = Util.createImage(Util.VENDER_BUTTON_IMAGE);
		venderButton.setPixelSize(160, 40);
		venderButton.addMouseListener(new MouseListenerAdapter(){

			public void onMouseEnter(Widget arg0) {
				isSelectedVenderButton = true;
				rebuildPanel();
				
			}
			
		});
		selectedVenderButton = Util.createImage(Util.SELECTED_VENDER_BUTTON_IMAGE);
		selectedVenderButton.setPixelSize(160, 40);
		selectedVenderButton.addMouseListener(new MouseListenerAdapter(){			

			public void onMouseLeave(Widget arg0) {
				isSelectedVenderButton = false;
				rebuildPanel();				
			}			
			
		});
		
		selectedVenderButton.addClickListener(new ClickListener(){

			public void onClick(Widget arg0) {
				PanelSwitcher.switchPanel(new DemandSellerPanel());				
			}
			
		});
		
		buildButtonsPanel();
	}
	
	private void rebuildNorthPanel(Image buttonImage){
		wrapperPanel.removeAll();
		Panel rightPanel = new Panel();
		rightPanel.add(buttonImage);
		wrapperPanel.add(rightPanel);
		rootPanel.add(wrapperPanel, 1080, 40);
	}

	//BOTAO ADMINISTRAR
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

