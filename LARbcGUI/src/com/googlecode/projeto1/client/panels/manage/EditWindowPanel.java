package com.googlecode.projeto1.client.panels.manage;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.projeto1.client.LoginManager;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.widgets.MessageBox;

public class EditWindowPanel extends AbsolutePanel{
	
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
	private CaseBean myCaseBean;
	
	public EditWindowPanel(CaseBean caseBean){
		super();
		this.myCaseBean = caseBean;
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
			cityTextBox.setText(myCaseBean.getCity());
			this.add(cityTextBox, 65, 5);
			cityTextBox.setSize("238px", "22px");
		}
		{
			neighborhoodTextBox = new TextBox();
			neighborhoodTextBox.setText(myCaseBean.getNeighborhood());
			this.add(neighborhoodTextBox, 65, 31);
			neighborhoodTextBox.setSize("238px", "22px");
		}
		{
			streetTextBox = new TextBox();
			streetTextBox.setText(myCaseBean.getStreet());
			this.add(streetTextBox, 65, 57);
			streetTextBox.setSize("358px", "22px");
		}
		{
			Label numberLabel = new Label("N\u00FAmero:");
			this.add(numberLabel, 308, 31);
		}
		{
			numberTextBox = new TextBox();
			numberTextBox.setText(Integer.toString(myCaseBean.getNumber()));
			this.add(numberTextBox, 374, 31);
			numberTextBox.setSize("49px", "22px");
		}
		{
			stateListBox = new ListBox();
			PERSISTENCE_SERVICE.listEstados(new AsyncCallback<List<String>>() {
				
				public void onSuccess(List<String> states) {
					for (String state : states) {
						stateListBox.addItem(state);
					}
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
			nameTextBox.setText(myCaseBean.getName());
			this.add(nameTextBox, 65, 84);
			nameTextBox.setSize("358px", "22px");
		}
		{
			Label areaConstruidaLabel = new Label("Area Constru\u00EDda:");
			this.add(areaConstruidaLabel, 5, 111);
		}
		{
			areaConstruidaTextBox = new TextBox();
			areaConstruidaTextBox.setText(Float.toString(myCaseBean.getBuiltArea()));
			this.add(areaConstruidaTextBox, 157, 111);
			areaConstruidaTextBox.setSize("54px", "22px");
		}
		{
			Label areaTotalLabel = new Label("Area Total:");
			this.add(areaTotalLabel, 235, 111);
		}
		{
			areaTotalTextBox = new TextBox();
			areaTotalTextBox.setText(Float.toString(myCaseBean.getTotalArea()));
			this.add(areaTotalTextBox, 369, 111);
			areaTotalTextBox.setSize("54px", "22px");
		}
		{
			Label garageLabel = new Label("Vagas na Garagem:");
			this.add(garageLabel, 5, 137);
		}
		{
			garageTextBox = new TextBox();
			garageTextBox.setText(Integer.toString(myCaseBean.getGarageSpace()));
			this.add(garageTextBox, 157, 138);
			garageTextBox.setSize("54px", "22px");
		}
		{
			Label roomsLabel = new Label("Qte de Quartos:");
			this.add(roomsLabel, 235, 137);
		}
		{
			qteBedroomsTextBox = new TextBox();
			qteBedroomsTextBox.setText(Integer.toString(myCaseBean.getBedroom()));
			this.add(qteBedroomsTextBox, 369, 138);
			qteBedroomsTextBox.setSize("54px", "22px");
		}
		{
			Label qteSuitesLabel = new Label("Qte de Su\u00EDtes:");
			this.add(qteSuitesLabel, 5, 163);
		}
		{
			qteSuitesTextBox = new TextBox();
			qteSuitesTextBox.setText(Integer.toString(myCaseBean.getSuite()));
			this.add(qteSuitesTextBox, 157, 163);
			qteSuitesTextBox.setSize("54px", "22px");
		}
		{
			Label bathroomsLabel = new Label("Qte de Banheiros:");
			this.add(bathroomsLabel, 235, 163);
		}
		{
			qteBathroomsTextBox = new TextBox();
			qteBathroomsTextBox.setText(Integer.toString(myCaseBean.getBathroom()));
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
			priceTextBox.setText(Float.toString(myCaseBean.getPrice()));
			this.add(priceTextBox, 338, 189);
			priceTextBox.setSize("85px", "22px");
		}		
		
	}
	
	public void updateCase(){
		CaseBean caseBean = new CaseBean();
		caseBean.setCity(cityTextBox.getText());
		caseBean.setNeighborhood(neighborhoodTextBox.getText());
		caseBean.setNumber(Integer.parseInt(numberTextBox.getText()));
//		caseBean.setState(stateListBox.getValue(stateListBox.getSelectedIndex()));
		caseBean.setState("state");
		caseBean.setStreet(streetTextBox.getText());
		caseBean.setName(nameTextBox.getText());
		caseBean.setBuiltArea(Float.parseFloat(areaConstruidaTextBox.getText()));
		caseBean.setTotalArea(Float.parseFloat(areaTotalTextBox.getText()));
		caseBean.setGarageSpace(Integer.parseInt(garageTextBox.getText()));
		caseBean.setBedroom(Integer.parseInt(qteBedroomsTextBox.getText()));
		caseBean.setSuite(Integer.parseInt(qteSuitesTextBox.getText()));
		caseBean.setBathroom(Integer.parseInt(qteBathroomsTextBox.getText()));
//		caseBean.setType(typeComboBox.getValue(typeComboBox.getSelectedIndex()));
		caseBean.setType("type");
		caseBean.setPrice(Float.parseFloat(priceTextBox.getText()));
		PERSISTENCE_SERVICE.updateCaso(LoginManager.getLogedAdministrator(), caseBean, new AsyncCallback<String>() {

			public void onFailure(Throwable arg0) {
				MessageBox.alert("Não foi possível atualizar o caso.");
				
			}

			public void onSuccess(String arg0) {
				MessageBox.alert("Caso atualizado com sucesso.");				
				
			}
		});
	}

	

}
