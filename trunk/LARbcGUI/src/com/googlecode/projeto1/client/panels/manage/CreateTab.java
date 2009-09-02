package com.googlecode.projeto1.client.panels.manage;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.LoginManager;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.form.Label;

public class CreateTab extends AbsolutePanel{
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
		
	private TextBox cityTextBox;
	private TextBox neighborhoodTextBox;
	private TextBox numberTextBox;
	private ListBox stateListBox;
	private TextBox streetTextBox;
	private TextBox nameTextBox;
	private TextBox areaConstruidaTextBox;
	private TextBox areaTotalTextBox;
	private TextBox garageTextBox;
	private TextBox qteBedroomsTextBox;
	private TextBox qteSuitesTextBox;
	private TextBox qteBathroomsTextBox;
	private ListBox typeComboBox;
	private TextBox priceTextBox;	
	private MappingWindow mappingWindow;
	private Button criarButton;
	private Button mapButton;
	
	public CreateTab(){
		super();		
		mappingWindow = new MappingWindow();		
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
			cityTextBox.setText("Campina Grande");
			cityTextBox.setEnabled(false);
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
			PERSISTENCE_SERVICE.listEstados(new AsyncCallback<List<String>>() {
				
				public void onSuccess(List<String> states) {
					states.add(0, "UF");
					for (String state : states) {
						stateListBox.addItem(state);
					}
					stateListBox.setSelectedIndex(15);
					stateListBox.setEnabled(false);
				}
				
				public void onFailure(Throwable arg0) {
					MessageBox.alert("Os estados não puderam ser carregados do disco");
					
				}
			});
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
			qteBedroomsTextBox = new TextBox();
			this.add(qteBedroomsTextBox, 369, 138);
			qteBedroomsTextBox.setSize("54px", "22px");
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
		{
			mapButton = getMapButton();
			this.add(mapButton, 157, 218);
			mapButton.setSize("189px", "34px");
		}
		
	}

	

	private Button getMapButton() {
		Button button = new Button("Ajustar Coordenadas");
		button.addClickListener(new ClickListener() {

			public void onClick(Widget arg0) {				
				mappingWindow.show();				
			}
			
		});
		return button;
	}



	private Button getCreateButton() {
		Button button = new Button("Criar");
		button.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {
				if(allFilled()){
					CaseBean caseBean = new CaseBean();
					caseBean.setCity(cityTextBox.getText());
					caseBean.setNeighborhood(neighborhoodTextBox.getText());
					caseBean.setNumber(Integer.parseInt(numberTextBox.getText()));
//					caseBean.setState(stateListBox.getValue(stateListBox.getSelectedIndex()));
					caseBean.setState("state");
					caseBean.setStreet(streetTextBox.getText());
					caseBean.setName(nameTextBox.getText());
					caseBean.setBuiltArea(Float.parseFloat(areaConstruidaTextBox.getText()));
					caseBean.setTotalArea(Float.parseFloat(areaTotalTextBox.getText()));
					caseBean.setGarageSpace(Integer.parseInt(garageTextBox.getText()));
					caseBean.setBedroom(Integer.parseInt(qteBedroomsTextBox.getText()));
					caseBean.setSuite(Integer.parseInt(qteSuitesTextBox.getText()));
					caseBean.setBathroom(Integer.parseInt(qteBathroomsTextBox.getText()));
//					caseBean.setType(typeComboBox.getValue(typeComboBox.getSelectedIndex()));
					caseBean.setType("type");
					caseBean.setPrice(Float.parseFloat(priceTextBox.getText()));				
					PERSISTENCE_SERVICE.crateCaso(LoginManager.getLogedAdministrator(), caseBean, new AsyncCallback<String>() {

						public void onFailure(Throwable arg0) {
							MessageBox.alert("Houve problemas durante o armazenamento desse caso.");
							
						}

						public void onSuccess(String arg0) {
							MessageBox.alert("Caso armazenado com sucesso");
							PanelSwitcher.switchPanel(new ManagePanel());	
						}
					});
				}				
				
			}
			
		});
		return button;
	}
	
	private boolean allFilled() {
		String message = "Favor digitar ";		
		if(isEmpty(cityTextBox)){
			MessageBox.alert(message + "a cidade.");
//			fields.add("Cidade");
			return false;
		}
		if(isEmpty(neighborhoodTextBox)){
			MessageBox.alert(message + "o bairro.");
//			fields.add("Bairro");
			return false;
		}
		if(isEmpty(numberTextBox)){
			MessageBox.alert(message + "o número.");
//			fields.add("Número");
			return false;
		}
		if(stateListBox.getSelectedIndex() == 0){
			MessageBox.alert(message + "o estado.");
//			fields.add("Estado");
			return false;
		}
		if(isEmpty(streetTextBox)){
			MessageBox.alert(message + "a rua.");
			return false;
		}
		if(!isFloatFormat(areaConstruidaTextBox)){
			MessageBox.alert(message + "a área construída.");
			return false;
		}
		if(!isFloatFormat(areaTotalTextBox)){
			MessageBox.alert(message + "a área total.");
			return false;
		}
		if(!isIntFormat(garageTextBox)){
			MessageBox.alert(message + "a quantidade de garagens.");
			return false;
		}
		if(!isIntFormat(qteBedroomsTextBox)){
			MessageBox.alert(message + "a quantidade de quartos.");
			return false;
		}
		if(!isIntFormat(qteSuitesTextBox)){
			MessageBox.alert(message + "a quantidade de suítes.");
			return false;
		}
		if(!isIntFormat(qteBathroomsTextBox)){
			MessageBox.alert(message + "a quantidade de banheiros.");
			return false;
		}
//		if(typeComboBox.getSelectedIndex() == 0){
//			fields.add(message + "Tipo de Negócio");
//			result = false;
//		}
		if(!isFloatFormat(priceTextBox)){
			MessageBox.alert(message + "o preço.");
			return false;
		}
		if(SelectedLocation.getLocation().equals("")){
			MessageBox.alert("Favor ajustar coordenadas.");
			return false;
		}
		return true;
	}

	private boolean isIntFormat(TextBox textBox) {
		if(isEmpty(textBox)){
			return false;
		}
		try{
			Integer.parseInt(textBox.getText());					
		}catch(Exception e){
			return false;
		}
		return true;
	}

	private boolean isFloatFormat(TextBox textBox) {
		if(isEmpty(textBox)){
			return false;
		}
		try{
			Float.parseFloat(textBox.getText());					
		}catch(Exception e){
			return false;
		}
		return true;
	}

	private boolean isEmpty(TextBox textBox) {
		return textBox.getText().equals("");		
	}

}
