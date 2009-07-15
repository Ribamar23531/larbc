package com.googlecode.projeto1.client.panels.query;

import com.google.gwt.user.client.ui.HTML;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.MultiFieldPanel;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.layout.ColumnLayoutData;

public class FormularyPanel extends FormPanel{
	
	private TextField street;
	private TextField neighboor;
	private TextField city;
	private ComboBox state;
	private TextField name;
	private TextField builtArea;
	private TextField totalArea;
	private TextField garage;
	private TextField rooms;
	private TextField suites;
	private TextField socialBathrooms;
	private ComboBox type;
	private TextField price;
	private ComboBox typeOfBusiness;
	
	public FormularyPanel(){
		super();
		setLayoutOfThis();
		putTheHeader();
		setFields();
	}

	private void setLayoutOfThis() {
		this.setHeight("800px");
		this.setAutoScroll(true);
	}

	private void putTheHeader() {
		this.add(new HTML("<H1>Pesquisa de Imóveis</H1>"));
		this.add(new HTML("<BR><BR><BR>"));
	}

	private void setFields() {
		this.street = new TextField("Nome da rua");
		this.add(this.street);
		this.neighboor = new TextField("Bairro");
		this.add(this.neighboor);
		this.city = new TextField("Cidade");
		this.state = new ComboBox("Estado");
		MultiFieldPanel mfPanel1 = new MultiFieldPanel();
		mfPanel1.addToRow(this.city, new ColumnLayoutData(0.5));
		mfPanel1.addToRow(this.state, new ColumnLayoutData(0.5));
		this.add(mfPanel1);
		this.name = new TextField("Nome");
		this.add(this.name);
		this.builtArea = new TextField("Área construída");
		this.add(this.builtArea);
		this.totalArea = new TextField("Área total");
		this.add(this.totalArea);
		this.garage = new TextField("Vagas na garagem");
		this.add(this.garage);
		this.rooms = new TextField("Quantidade de quartos");
		this.add(this.rooms);
		this.suites = new TextField("Quantos deles são suítes");
		this.add(this.suites);
		this.socialBathrooms = new TextField("Quantos banheiros sociais");
		this.add(this.socialBathrooms);
		this.type = new ComboBox("Tipo de imóvel");
		this.add(this.type);
		this.price = new TextField("Preço");
		this.add(this.price);
		this.typeOfBusiness = new ComboBox("Tipo de negócio");
		this.add(this.typeOfBusiness);
	}
	
}
