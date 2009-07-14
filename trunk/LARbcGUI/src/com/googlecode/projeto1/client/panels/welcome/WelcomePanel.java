package com.googlecode.projeto1.client.panels.welcome;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.panels.modality.ModalityPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

public class WelcomePanel extends Panel{
	
	private final String larbcImagePath = "images/larbc.png";
	private final String familyImagePath = "images/familia.png";
	private final String image1Path = "images/imagem1.png";
	private final String preenchimentoPath = "images/preenchimento.png";
	private Image larbcImage;
	private Image familiaImage;
	private Image image1;
	private FlexTable mainTable;
	private FlexTable secundaryTable;
	
	public WelcomePanel(){
		super();
		mainTable = new FlexTable();
		this.setTitle("Bem Vindo");
		larbcImage = createImage(larbcImagePath);
		familiaImage = createImage(familyImagePath);
		image1 = createImage(image1Path);		
		VerticalPanel vp = new VerticalPanel();
		vp.add(image1);
		secundaryTable = createSecundaryTable();
		vp.add(secundaryTable);
		mainTable.setWidget(0, 0, larbcImage);
		mainTable.setWidget(1, 0, familiaImage);
		mainTable.setWidget(1, 1, vp);
		this.add(mainTable);
		this.setFrame(true);
		
	}
	
	private FlexTable createSecundaryTable() {
		FlexTable ft = new FlexTable();
		ft.setWidget(0, 0, createImage(preenchimentoPath));
		ft.setWidget(0, 1, createEntrarButton());
		return ft;
	}

	private Button createEntrarButton() {
		Button entrarButton = new Button("Entrar");
		entrarButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				PanelSwitcher.switchPanel(new ModalityPanel());
			}

		});
		return entrarButton;
	}

	private Image createImage(String path){
		Image image = new Image(path);
		image.setSize("auto", "auto");
		return image;
	}

}
