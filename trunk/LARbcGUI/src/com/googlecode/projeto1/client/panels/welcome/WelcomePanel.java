package com.googlecode.projeto1.client.panels.welcome;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.panels.modality.ModalityPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WelcomePanel extends Panel {
	
	private AbsolutePanel rootPanel;
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
		{
			Button button = new Button("New button");
			button.setText("ENTRAR");
			rootPanel.add(button, 526, 365);
			button.setSize("77px", "27px");
			button.addListener(new ButtonListenerAdapter(){
				public void onClick(Button button, EventObject e) {
					PanelSwitcher.switchPanel(new ModalityPanel());
				}

			});
		}
		this.add(rootPanel);
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}
}
