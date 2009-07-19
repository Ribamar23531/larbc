package com.googlecode.projeto1.client.panels.manage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.widgets.form.Label;

public class CreateTab extends AbsolutePanel{
	
	private static final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	
	private TextBox cityTextBox;
	private TextBox neighborhoodTextBox;
	private TextBox numberTextBox;
	private ListBox stateListBox;
	private TextBox streetTextBox;
	private TextBox nameTextBox;
	private TextBox areaConstruidaTextBox;
	private TextBox areaTotalTextBox;
	private TextBox garageTextBox;
	private TextBox qteRoomsTextBox;
	private TextBox qteSuitesTextBox;
	private TextBox qteBathroomsTextBox;
	private ListBox typeComboBox;
	private TextBox priceTextBox;
	private Button criarButton;
	
	public CreateTab(){
		super();
		{
			Label stateLabel = new Label("Estado:");
			this.add(stateLabel, 308, 5);
		}
		{
			Label cityLabel = new Label("Cidade:");
			this.add(cityLabel, 5, 5);
		}
		{
			Label bairroLabel = new Label("Bairro:");
			this.add(bairroLabel, 5, 31);
		}
		{
			Label streetLabel = new Label("Rua:");
			this.add(streetLabel, 5, 57);
		}
		{
			cityTextBox = new TextBox();
			this.add(cityTextBox, 65, 5);
			cityTextBox.setSize("238px", "22px");
		}
		{
			neighborhoodTextBox = new TextBox();
			this.add(neighborhoodTextBox, 65, 31);
			neighborhoodTextBox.setSize("238px", "22px");
		}
		{
			streetTextBox = new TextBox();
			this.add(streetTextBox, 65, 57);
			streetTextBox.setSize("358px", "22px");
		}
		{
			Label numberLabel = new Label("N\u00FAmero:");
			this.add(numberLabel, 308, 31);
		}
		{
			numberTextBox = new TextBox();
			this.add(numberTextBox, 374, 31);
			numberTextBox.setSize("49px", "22px");
		}
		{
			stateListBox = new ListBox();
			this.add(stateListBox, 374, 5);
			stateListBox.setSize("49px", "27px");
		}
		{
			Label nameLabel = new Label("Nome:");
			this.add(nameLabel, 5, 83);
		}
		{
			nameTextBox = new TextBox();
			this.add(nameTextBox, 65, 84);
			nameTextBox.setSize("358px", "22px");
		}
		{
			Label areaConstruidaLabel = new Label("Area Constru\u00EDda:");
			this.add(areaConstruidaLabel, 5, 111);
		}
		{
			areaConstruidaTextBox = new TextBox();
			this.add(areaConstruidaTextBox, 157, 111);
			areaConstruidaTextBox.setSize("54px", "22px");
		}
		{
			Label areaTotalLabel = new Label("Area Total:");
			this.add(areaTotalLabel, 235, 111);
		}
		{
			areaTotalTextBox = new TextBox();
			this.add(areaTotalTextBox, 369, 111);
			areaTotalTextBox.setSize("54px", "22px");
		}
		{
			Label garageLabel = new Label("Vagas na Garagem:");
			this.add(garageLabel, 5, 137);
		}
		{
			garageTextBox = new TextBox();
			this.add(garageTextBox, 157, 138);
			garageTextBox.setSize("54px", "22px");
		}
		{
			Label roomsLabel = new Label("Qte de Quartos:");
			this.add(roomsLabel, 235, 137);
		}
		{
			qteRoomsTextBox = new TextBox();
			this.add(qteRoomsTextBox, 369, 138);
			qteRoomsTextBox.setSize("54px", "22px");
		}
		{
			Label qteSuitesLabel = new Label("Qte de Su\u00EDtes:");
			this.add(qteSuitesLabel, 5, 163);
		}
		{
			qteSuitesTextBox = new TextBox();
			this.add(qteSuitesTextBox, 157, 163);
			qteSuitesTextBox.setSize("54px", "22px");
		}
		{
			Label bathroomsLabel = new Label("Qte de Banheiros:");
			this.add(bathroomsLabel, 235, 163);
		}
		{
			qteBathroomsTextBox = new TextBox();
			this.add(qteBathroomsTextBox, 369, 163);
			qteBathroomsTextBox.setSize("54px", "22px");
		}
		{
			Label businesTypeLabel = new Label("Tipo de neg\u00F3cio:");
			this.add(businesTypeLabel, 5, 189);
		}
		{
			typeComboBox = new ListBox();
			this.add(typeComboBox, 157, 190);
			typeComboBox.setSize("54px", "27px");
		}
		{
			Label priceLabel = new Label("Pre\u00E7o: R$");
			this.add(priceLabel, 235, 189);
		}
		{
			priceTextBox = new TextBox();
			this.add(priceTextBox, 338, 189);
			priceTextBox.setSize("85px", "22px");
		}
		{
			criarButton = getCreateButton();
			this.add(criarButton, 361, 218);
		}
		
	}

	private Button getCreateButton() {
		Button button = new Button("Criar");
		button.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {
				CaseBean caseBean = new CaseBean();
				caseBean.setCity(cityTextBox.getText());
				caseBean.setNeighborhood(neighborhoodTextBox.getText());
				caseBean.setNumber(Integer.parseInt(numberTextBox.getText()));
				caseBean.setState(stateListBox.getValue(stateListBox.getSelectedIndex()));
				
				
				//TODO CONTINUAR
				
				
				
//				PERSISTENCE_SERVICE
//				private TextBox cityTextBox;
//				private TextBox townTextBox;
//				private TextBox numberTextBox;
//				private ListBox stateListBox;
//				private TextBox streetTextBox;
//				private TextBox nameTextBox;
//				private TextBox areaConstruidaTextBox;
//				private TextBox areaTotalTextBox;
//				private TextBox garageTextBox;
//				private TextBox qteRoomsTextBox;
//				private TextBox qteSuitesTextBox;
//				private TextBox qteBathroomsTextBox;
//				private ListBox typeComboBox;
//				private TextBox priceTextBox;
//				private Button criarButton;
				
			}
		});
		return button;
	}

}
