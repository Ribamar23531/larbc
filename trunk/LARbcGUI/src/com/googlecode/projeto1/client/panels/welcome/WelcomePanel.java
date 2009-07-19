package com.googlecode.projeto1.client.panels.welcome;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.panels.Util;
import com.googlecode.projeto1.client.panels.modality.ModalityPanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WelcomePanel extends Panel {
	
	private AbsolutePanel rootPanel;
	private Image entrarButton;
	private Image entrarSelectedButton;
	private boolean isSelectedEntrarButton;
	
	public WelcomePanel() {
		rootPanel = new AbsolutePanel();
		{
			Image image = new Image("images/larbc.png");
			rootPanel.add(image, 5, 5);
			image.setSize("40%", "15%");
		}
		{
			Image image = new Image("images/familia.png");
			rootPanel.add(image, 38, 164);
			image.setSize("24%", "60%");
		}
		{
			Image image = new Image("images/soltoMaior.png");
			rootPanel.add(image, 360, 180);
			image.setSize("52%", "25%");
		}
		createEntrarButton();
		this.add(rootPanel);
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}
	
	private void createEntrarButton() {
		entrarButton = Util.createImage(Util.ENTRAR_BUTTON_IMAGE);
		entrarButton.setSize("11%", "5%");
		entrarButton.addMouseListener(new MouseListenerAdapter(){

			

			public void onMouseEnter(Widget arg0) {
				isSelectedEntrarButton = true;
				buildButtonsPanel();
				
			}

		});
		entrarSelectedButton = Util.createImage(Util.ENTRAR_SELECTED_BUTTON_IMAGE);
		entrarSelectedButton.setSize("11%", "5%");
		entrarSelectedButton.addMouseListener(new MouseListenerAdapter(){			

			public void onMouseLeave(Widget arg0) {
				isSelectedEntrarButton = false;
				buildButtonsPanel();			
			}
			
		});
		entrarSelectedButton.addClickListener(new ClickListener(){
			public void onClick(Widget arg0) {
				PanelSwitcher.switchPanel(new ModalityPanel());
			}
		});
		
		buildButtonsPanel();
		
	}
	
	private void buildButtonsPanel() {
		if(!isSelectedEntrarButton){
			rootPanel.add(entrarButton, 540, 365);
		}else{
			rootPanel.add(entrarSelectedButton, 540, 365);
		}
	}

}