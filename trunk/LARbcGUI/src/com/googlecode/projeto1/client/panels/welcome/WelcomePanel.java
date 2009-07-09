package com.googlecode.projeto1.client.panels.welcome;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;

public class WelcomePanel extends Panel{
	
	private final String familyImagePath = "images/familia.png";
	private final String image1Path = "images/imagem1.png";
	private Image familyImage;
	private Image image1;
	
	public WelcomePanel(){
		super();
		this.setLayout(new BorderLayout());
		this.familyImage = new Image(familyImagePath);
		this.image1 = new Image(image1Path);
		this.setHeight("auto");
		this.setFrame(true);
//		this.setLayout(new HorizontalLayout(15));
//		this.add(familyImage);
		this.add(familyImage, new BorderLayoutData(RegionPosition.CENTER));
		VerticalPanel p = new VerticalPanel();
		p.add(image1);
//		this.add(p, new BorderLayoutData(RegionPosition.CENTER));
		Viewport viewport = new Viewport(this);  
	}

}
