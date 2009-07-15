package com.googlecode.projeto1.client.panels.query;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.layout.VerticalLayout;

public class FooterPanel extends Panel {


	public FooterPanel() {
		super();
		setLayoutOfThis();
		insertTheLabel();
	}

	private void setLayoutOfThis() {
		this.setHeight(35);
		this.setFrame(true);
		this.setBorder(false);
		this.setLayout(new VerticalLayout());
	}

	private void insertTheLabel() {
		Label label = new Label("Imobiliária Souto Maior");
		this.add(label);
		int position = ((int)this.getWidth()/2)-((int)label.getWidth()/2);
		label.setPosition(position, 0);
	}
}