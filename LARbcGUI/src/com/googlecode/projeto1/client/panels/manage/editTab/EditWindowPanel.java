package com.googlecode.projeto1.client.panels.manage.editTab;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.projeto1.client.LoginManager;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.panels.FieldsChecker;
import com.googlecode.projeto1.client.panels.WindowFieldsAlert;
import com.googlecode.projeto1.client.panels.exceptions.FieldsNotFilledExeption;
import com.googlecode.projeto1.client.panels.manage.SelectedLocation;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.core.NameValuePair;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
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
	private ListBox businessTypeComboBox;
	private TextBox priceTextBox;
	private ListBox propertyType;
	private CaseBean myCaseBean;	
	
	public EditWindowPanel(CaseBean caseBean) {
		super();
		this.myCaseBean = caseBean;

		Label stateLabel = new Label("Estado:");
		this.add(stateLabel, 308, 5);

		Label cityLabel = new Label("Cidade:");
		this.add(cityLabel, 5, 5);

		Label bairroLabel = new Label("Bairro:");
		this.add(bairroLabel, 5, 31);

		Label streetLabel = new Label("Rua:");
		this.add(streetLabel, 5, 57);

		cityTextBox = new TextBox();
		cityTextBox.setText(myCaseBean.getCity());
		cityTextBox.setEnabled(false);
		this.add(cityTextBox, 65, 5);
		cityTextBox.setSize("238px", "22px");

		neighborhoodTextBox = new TextBox();
		neighborhoodTextBox.setText(myCaseBean.getNeighborhood());
		this.add(neighborhoodTextBox, 65, 31);
		neighborhoodTextBox.setSize("238px", "22px");

		streetTextBox = new TextBox();
		streetTextBox.setText(myCaseBean.getStreet());
		this.add(streetTextBox, 65, 57);
		streetTextBox.setSize("358px", "22px");

		Label numberLabel = new Label("N\u00FAmero:");
		this.add(numberLabel, 308, 31);

		numberTextBox = new TextBox();
		numberTextBox.setText(Integer.toString(myCaseBean.getNumber()));
		this.add(numberTextBox, 374, 31);
		numberTextBox.setSize("49px", "22px");

		stateListBox = new ListBox();
		PERSISTENCE_SERVICE.listEstados(new AsyncCallback<List<String>>() {

			public void onSuccess(List<String> states) {
				for (String state : states) {
					stateListBox.addItem(state);
					stateListBox.setSelectedIndex(14);
				}
			}

			public void onFailure(Throwable arg0) {
				MessageBox
						.alert("Os estados não puderam ser carregados do disco");

			}
		});
		this.add(stateListBox, 374, 5);
		stateListBox.setSize("49px", "27px");
		stateListBox.setEnabled(false);

		Label nameLabel = new Label("Nome:");
		this.add(nameLabel, 5, 83);

		nameTextBox = new TextBox();
		nameTextBox.setText(myCaseBean.getName());
		this.add(nameTextBox, 65, 84);
		nameTextBox.setSize("358px", "22px");

		Label areaConstruidaLabel = new Label("Area Construída:");
		this.add(areaConstruidaLabel, 5, 111);

		areaConstruidaTextBox = new TextBox();
		areaConstruidaTextBox
				.setText(Float.toString(myCaseBean.getBuiltArea()));
		this.add(areaConstruidaTextBox, 157, 111);
		areaConstruidaTextBox.setSize("54px", "22px");

		Label areaTotalLabel = new Label("Area Total:");
		this.add(areaTotalLabel, 235, 111);

		areaTotalTextBox = new TextBox();
		areaTotalTextBox.setText(Float.toString(myCaseBean.getTotalArea()));
		this.add(areaTotalTextBox, 369, 111);
		areaTotalTextBox.setSize("54px", "22px");

		Label garageLabel = new Label("Vagas na Garagem:");
		this.add(garageLabel, 5, 137);

		garageTextBox = new TextBox();
		garageTextBox.setText(Integer.toString(myCaseBean.getGarageSpace()));
		this.add(garageTextBox, 157, 138);
		garageTextBox.setSize("54px", "22px");

		Label roomsLabel = new Label("Qte de Quartos:");
		this.add(roomsLabel, 235, 137);

		qteBedroomsTextBox = new TextBox();
		qteBedroomsTextBox.setText(Integer.toString(myCaseBean.getBedroom()));
		this.add(qteBedroomsTextBox, 369, 138);
		qteBedroomsTextBox.setSize("54px", "22px");

		Label qteSuitesLabel = new Label("Qte de Suítes:");
		this.add(qteSuitesLabel, 5, 163);

		qteSuitesTextBox = new TextBox();
		qteSuitesTextBox.setText(Integer.toString(myCaseBean.getSuite()));
		this.add(qteSuitesTextBox, 157, 163);
		qteSuitesTextBox.setSize("54px", "22px");

		Label bathroomsLabel = new Label("Qte de Banheiros:");
		this.add(bathroomsLabel, 235, 163);

		qteBathroomsTextBox = new TextBox();
		qteBathroomsTextBox.setText(Integer.toString(myCaseBean.getBathroom()));
		this.add(qteBathroomsTextBox, 369, 163);
		qteBathroomsTextBox.setSize("54px", "22px");

		Label businesTypeLabel = new Label("Tipo de negócio:");
		this.add(businesTypeLabel, 5, 189);

		businessTypeComboBox = new ListBox();
		businessTypeComboBox.addItem("Comprar");
		businessTypeComboBox.addItem("Alugar");
		if (myCaseBean.getBusinessType().equals("Comprar")) {
			businessTypeComboBox.setSelectedIndex(0);
		} else {
			businessTypeComboBox.setSelectedIndex(1);
		}
		this.add(businessTypeComboBox, 120, 190);
		businessTypeComboBox.setSize("90px", "27px");

		Label priceLabel = new Label("Preço: R$");
		this.add(priceLabel, 235, 189);

		priceTextBox = new TextBox();
		priceTextBox.setText(Float.toString(myCaseBean.getPrice()));
		this.add(priceTextBox, 338, 189);
		priceTextBox.setSize("85px", "22px");		
		
		Label propertyTypeLabel = new Label("Tipo de Imóvel:");
		this.add(propertyTypeLabel, 5, 220);

		propertyType = new ListBox();
		propertyType.setSize("135px", "21px");
		propertyType.addItem("Casa");
		propertyType.addItem("Apartamento");
		propertyType.addItem("Terreno");
		propertyType.addItem("Sala Comercial");
		
		if(myCaseBean.getType().equals("Casa")){
			propertyType.setSelectedIndex(0);
		}else if(myCaseBean.getType().equals("Apartamento")){
			propertyType.setSelectedIndex(1);
		}else if(myCaseBean.getType().equals("Terreno")){
			propertyType.setSelectedIndex(2);
		}else if(myCaseBean.getType().equals("Sala Comercial")){
			propertyType.setSelectedIndex(3);
		}
		this.add(propertyType, 120, 220);

	}
	
	public void updateCase(boolean mapOpend){
		if(changed(mapOpend)){
			List<String[]> fields = new ArrayList<String[]>();				
			fields.add(getStrArray("String", cityTextBox, "Cidade"));					
			fields.add(getStrArray("String", neighborhoodTextBox, "Bairro"));
			fields.add(getStrArray("int", numberTextBox, "Número"));
			fields.add(getStrArray("String", streetTextBox, "Rua"));
			fields.add(getStrArray("float", areaConstruidaTextBox, "Area Construída"));
			fields.add(getStrArray("float", areaTotalTextBox, "Area Total"));
			fields.add(getStrArray("int", garageTextBox, "Vagas na Garagem"));
			fields.add(getStrArray("int", qteBedroomsTextBox, "Qte de Quartos"));
			fields.add(getStrArray("int", qteSuitesTextBox, "Qte de Suítes"));
			fields.add(getStrArray("int", qteBathroomsTextBox, "Qte de Banheiros"));
			fields.add(getStrArray("float", priceTextBox, "Preço"));
			if(myCaseBean.getLocation().equals("")){
				fields.add(getStrArray("String", SelectedLocation.getLocation(), "Coordenadas"));				
			}
			try {
				FieldsChecker.checkFields(fields);
//				myCaseBean = new CaseBean();
				myCaseBean.setCity(cityTextBox.getText());
				myCaseBean.setNeighborhood(neighborhoodTextBox.getText());
				myCaseBean.setNumber(Integer.parseInt(numberTextBox.getText()));
//				myCaseBean.setState(stateListBox.getValue(stateListBox.getSelectedIndex()));
				myCaseBean.setState("state");
				myCaseBean.setStreet(streetTextBox.getText());
				myCaseBean.setName(nameTextBox.getText());
				myCaseBean.setBuiltArea(Float.parseFloat(areaConstruidaTextBox.getText()));
				myCaseBean.setTotalArea(Float.parseFloat(areaTotalTextBox.getText()));
				myCaseBean.setGarageSpace(Integer.parseInt(garageTextBox.getText()));
				myCaseBean.setBedroom(Integer.parseInt(qteBedroomsTextBox.getText()));
				myCaseBean.setSuite(Integer.parseInt(qteSuitesTextBox.getText()));
				myCaseBean.setBathroom(Integer.parseInt(qteBathroomsTextBox.getText()));
				myCaseBean.setType(propertyType.getValue(propertyType.getSelectedIndex()));
				myCaseBean.setBusinessType(businessTypeComboBox.getItemText(businessTypeComboBox.getSelectedIndex()));
				myCaseBean.setPrice(Float.parseFloat(priceTextBox.getText()));
				if(!SelectedLocation.getLocation().equals("") && !SelectedLocation.getLocation().equals(myCaseBean.getLocation())){
					myCaseBean.setLocation(SelectedLocation.getLocation());					
				}
				
				update();				
			} catch (FieldsNotFilledExeption e) {
				new WindowFieldsAlert().show(e.getMessage());
			}
			
		}		
	}
	
	private void update() {
		Geocoder streetQuery = new Geocoder();
		streetQuery.getLatLng("Campina Grande, " + streetTextBox.getText(), new LatLngCallback() {

			public void onFailure() {
				MessageBox.show(new MessageBoxConfig() {

					{
						setTitle("Rua não Encontrada");
						setMsg("Essa rua não consta entre as existentes em Campina Grande. "
								+ "Deseja salvar esse imóvel assim mesmo?");
						setIconCls(MessageBox.QUESTION);
						setButtons(MessageBox.YESNO);
						setButtons(new NameValuePair[] {
								new NameValuePair("yes", "Sim"),
								new NameValuePair("no", "Não") });
						setCallback(new MessageBox.PromptCallback() {
							public void execute(String btnID, String text) {
								if (btnID.equals("yes")) {
									update(myCaseBean);
								}

							}
						});
					}
				});
				
			}			

			public void onSuccess(LatLng point) {
				update(myCaseBean);
				
			}
			
			private void update(CaseBean myCaseBean) {
				PERSISTENCE_SERVICE.updateCaso(LoginManager.getLogedAdministrator(), myCaseBean, new AsyncCallback<String>() {

					public void onFailure(Throwable arg0) {
						MessageBox.alert("Não foi possível atualizar o caso.");
						
					}

					public void onSuccess(String arg0) {
						MessageBox.alert("Caso atualizado com sucesso.");				
						
					}
				});
				
			}
			
		});
		
	}

	private boolean changed(boolean mapOpend) {
		if(!cityTextBox.getText().equals(myCaseBean.getCity())){
			return true;
		}
		if(!neighborhoodTextBox.getText().equals(myCaseBean.getNeighborhood())){
			return true;
		}
		if(Integer.parseInt(numberTextBox.getText()) != myCaseBean.getNumber()){
			return true;
		}
//		if(!stateListBox.getItemText(stateListBox.getSelectedIndex()).equals(myCaseBean.getState())){
//			return true;
//		}
		if(!streetTextBox.getText().equals(myCaseBean.getStreet())){
			return true;
		}
		if(!nameTextBox.getText().equals(myCaseBean.getName())){
			return true;
		}
		if(Float.parseFloat(areaConstruidaTextBox.getText()) != myCaseBean.getBuiltArea()){
			return true;
		}
		if(Float.parseFloat(areaTotalTextBox.getText()) != myCaseBean.getTotalArea()){
			return true;
		}
		if(Integer.parseInt(garageTextBox.getText()) != myCaseBean.getGarageSpace()){
			return true;
		}
		if(Integer.parseInt(qteBedroomsTextBox.getText()) != myCaseBean.getBedroom()){
			return true;
		}
		if(Integer.parseInt(qteSuitesTextBox.getText()) != myCaseBean.getSuite()){
			return true;
		}
		if(Integer.parseInt(qteBathroomsTextBox.getText()) != myCaseBean.getBathroom()){
			return true;
		}
		if(!businessTypeComboBox.getItemText(businessTypeComboBox.getSelectedIndex()).equals(myCaseBean.getBusinessType())){
			return true;
		}
		if(!propertyType.getItemText(propertyType.getSelectedIndex()).equals(myCaseBean.getType())){
			return true;
		}
		if(Float.parseFloat(priceTextBox.getText()) != myCaseBean.getPrice()){
			return true;
		}		
		if(mapOpend && !myCaseBean.getLocation().equals(SelectedLocation.getLocation())){
			return true;
		}
		return false;
	}

	private String[] getStrArray(String type, TextBox value, String field){
		String[] result = {type, value.getText(), field};
		return result;
	}
	
	private String[] getStrArray(String type, String value, String field){
		String[] result = {type, value, field};
		return result;
	}	
	

}
