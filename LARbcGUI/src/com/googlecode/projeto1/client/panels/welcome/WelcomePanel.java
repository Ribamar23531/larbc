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
			image.setSize("271px", "100px");
		}
		{
			Image image = new Image("images/familia.png");
			rootPanel.add(image, 38, 164);
			image.setSize("238px", "325px");
		}
		{
			Image image = new Image("images/soltoMaior.png");
			rootPanel.add(image, 325, 155);
			image.setSize("463px", "168px");
		}
		createEntrarButton();
		this.add(rootPanel);
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}
	
	private void createEntrarButton() {
		entrarButton = Util.createImage(Util.ENTRAR_BUTTON_IMAGE);
		entrarButton.setSize("90px", "33px");
		entrarButton.addMouseListener(new MouseListenerAdapter(){

			

			public void onMouseEnter(Widget arg0) {
				isSelectedEntrarButton = true;
				buildButtonsPanel();
				
			}

		});
		entrarSelectedButton = Util.createImage(Util.ENTRAR_SELECTED_BUTTON_IMAGE);
		entrarSelectedButton.setSize("90px", "33px");
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
			rootPanel.add(entrarButton, 526, 365);
		}else{
			rootPanel.add(entrarSelectedButton, 526, 365);
		}
	}

}
