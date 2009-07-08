package com.googlecode.projeto1.client.panels.welcome;

import com.google.gwt.user.client.ui.Image;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.HorizontalLayout;

public class WelcomePanel extends Panel{
	
	private final String imagePath = "images/familia.PNG";	
	private Image image;
	
	public WelcomePanel(){
		super();
		this.image = new Image(imagePath);
		this.setHeight("auto");
		this.setFrame(true);
		this.setLayout(new HorizontalLayout(15));
		this.add(image);
	}

}
