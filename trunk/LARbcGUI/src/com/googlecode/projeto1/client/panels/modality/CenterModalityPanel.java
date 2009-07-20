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

public class CenterModalityPanel extends Panel{
	
	private AbsolutePanel vp;
	private AbsolutePanel p1;
	private Panel p2;
	private Image venderButton;
	private Image selectedVenderButton;
	private boolean isSelectedVenderButton;
	private Panel buttonsPanel;
	private Panel buttonsComprarPanel;
	private Image comprarButton;
	private Image selectedComprarButton;
	private boolean isSelectedComprarButton;

	public CenterModalityPanel(){
		super();
		this.vp = new AbsolutePanel();
		this.p1 = new AbsolutePanel();
		this.p2 = new Panel();
		this.isSelectedVenderButton = false;
		this.isSelectedComprarButton = false;
		buttonsPanel = new Panel();		
		buttonsComprarPanel = new Panel();
		createVenderButton();
		createComprarButton();
		Panel panel1 = new Panel();
		panel1.add(Util.createImage(Util.QUESTION));
		p1.add(panel1, 350, 135);
		vp.add(p1);
		p2.setLayout(new ColumnLayout());
		vp.add(buttonsPanel, 600, 240);
		vp.add(buttonsComprarPanel, 200, 240);
		this.add(vp);
//		this.setAutoScroll(true);
	}

	//BOTAO COMPRAR
	private void createComprarButton() {
		comprarButton = Util.createImage(Util.COMPRAR_BUTTON_IMAGE);
		comprarButton.addMouseListener(new MouseListenerAdapter(){

			

			public void onMouseEnter(Widget arg0) {
				isSelectedComprarButton = true;
				rebuildComprarPanel();
				
			}

			
			
		});
		selectedComprarButton = Util.createImage(Util.SELECTED_COMPRAR_BUTTON_IMAGE);
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
		venderButton.addMouseListener(new MouseListenerAdapter(){

			

			public void onMouseEnter(Widget arg0) {
				isSelectedVenderButton = true;
				rebuildPanel();
				
			}

			
			
		});
		selectedVenderButton = Util.createImage(Util.SELECTED_VENDER_BUTTON_IMAGE);
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

}
