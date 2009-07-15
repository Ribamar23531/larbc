package com.googlecode.projeto1.client.panels.query;

import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;

public class BodyPanel extends Panel {
	private ImagePanel imagePanel;
	private FormularyPanel formPanel;
	private Button searchButton;
	
	public BodyPanel(){
		super();
		setLayoutOfThis();
		setFormPanel();
		setImagePanel();
		setSearchButton();
	}

	private void setLayoutOfThis() {
		ColumnLayout columnLayout = new ColumnLayout();
		this.setLayout(columnLayout);
		this.setButtonAlign(Position.CENTER);
		this.setFrame(true);
	}
	
	private void setFormPanel() {
		this.formPanel = new FormularyPanel();
		ColumnLayoutData columnData = new ColumnLayoutData(0.6);
		this.add(this.formPanel, columnData);
	}
	
	private void setImagePanel() {
		this.imagePanel = new ImagePanel();
		ColumnLayoutData columnData = new ColumnLayoutData(0.4);
		this.add(this.imagePanel, columnData);
	}

	private void setSearchButton() {
		this.searchButton = new Button("Procurar");
		this.addButton(this.searchButton);
	}
}
