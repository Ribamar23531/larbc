package com.googlecode.projeto1.client.panels.welcome;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.HorizontalLayout;

public class WelcomePanel extends Panel{
	
	private final String familyImagePath = "images/familia.PNG";
	private final String image1Path = "images/imagem1.PNG";
	private Image familyImage;
	private Image image1;
	
	public WelcomePanel(){
		super();
		this.familyImage = new Image(familyImagePath);
		this.image1 = new Image(image1Path);
		this.setHeight("auto");
		this.setFrame(true);
		this.setLayout(new HorizontalLayout(15));
		this.add(familyImage);
		VerticalPanel p = new VerticalPanel();
		p.add(image1);
		this.add(p);
	}

}
