package com.googlecode.projeto1.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Image;
import com.gwtext.client.core.Margins;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;

public class LARbc implements EntryPoint {
	
	private final String larbcImagePath = "images/larbc.png";
	private final String familyImagePath = "images/familia.png";
	private final String image1Path = "images/imagem1.png";
	private Image larbcImage;
	private Image familiaImage;
	private Image image1;

	public void onModuleLoad() {
		// new Viewport(new WelcomePanel());
		Panel panel = new Panel();
		panel.setBorder(false);
		panel.setPaddings(15);
		panel.setLayout(new FitLayout());

		Panel borderPanel = new Panel();
		borderPanel.setLayout(new BorderLayout());
//		borderPanel.setLayout(new ColumnLayout());
//		panel.setAutoScroll(true);

		// add north panel
		Panel northPanel = new Panel();
		northPanel.setTitle("Bem Vindo");
		northPanel.setHeight("auto");		
		larbcImage = new Image(larbcImagePath);
		northPanel.add(larbcImage);
		northPanel.setFrame(true);
		northPanel.setBodyBorder(true);
		BorderLayoutData northBorderLayoutData = new BorderLayoutData(RegionPosition.NORTH);
		northBorderLayoutData.setSplit(false);
		borderPanel.add(northPanel, northBorderLayoutData);		

		Panel westPanel = new Panel();		
		familiaImage = new Image(familyImagePath);
		familiaImage.setHeight("auto");
		familiaImage.setWidth("auto");
		westPanel.add(familiaImage);
		westPanel.setFrame(true);
		westPanel.setWidth(285);
		westPanel.setHeight(500);

		BorderLayoutData westData = new BorderLayoutData(RegionPosition.WEST);
		westData.setSplit(true);
		westData.setMinSize(175);
		westData.setMaxSize(400);
		westData.setMargins(new Margins(0, 5, 0, 0));

		borderPanel.add(westPanel, westData);

		Panel centerPanel = new Panel();		
		image1 = new Image(image1Path);
		centerPanel.setFrame(true);
		centerPanel.add(image1);

		borderPanel.add(centerPanel, new BorderLayoutData(RegionPosition.CENTER));

		borderPanel.setBodyBorder(false);
		borderPanel.setBorder(false);
		panel.add(borderPanel);

		Viewport viewport = new Viewport(panel);
	}
}
