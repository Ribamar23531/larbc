package com.googlecode.projeto1.client.panels.manage.createTab;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
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
import com.googlecode.projeto1.client.panels.FieldsChecker;
import com.googlecode.projeto1.client.panels.WindowFieldsAlert;
import com.googlecode.projeto1.client.panels.exceptions.FieldsNotFilledExeption;
import com.googlecode.projeto1.client.panels.manage.ManagePanel;
import com.googlecode.projeto1.client.panels.manage.SelectedLocation;
import com.googlecode.projeto1.client.panels.maps.AjustCoordinatesMap;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.core.NameValuePair;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.form.Label;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class CreateTab extends AbsolutePanel{
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
		
	private TextBox cityTextBox;
	private ListBox stateListBox;
	private ListBox neighborhoodListBox;
	private TextBox numberTextBox;
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
	private Button criarButton;
	private Button mapButton;
	
	public CreateTab() {
		super();
		
		SelectedLocation.setLocation("");

		Label stateLabel = new Label("Estado:");
		this.add(stateLabel, 308, 5);

		Label cityLabel = new Label("Cidade:");
		this.add(cityLabel, 5, 5);

		Label bairroLabel = new Label("Bairro:");
		this.add(bairroLabel, 5, 31);

		Label streetLabel = new Label("Rua:");
		this.add(streetLabel, 5, 57);

		cityTextBox = new TextBox();
		cityTextBox.setText("Campina Grande");
		cityTextBox.setEnabled(false);
		this.add(cityTextBox, 65, 5);
		cityTextBox.setSize("238px", "22px");
		
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
				MessageBox.alert("Os estados não puderam ser carregados da base de dados");

			}
		});
		this.add(stateListBox, 374, 5);
		stateListBox.setSize("49px", "27px");		

		neighborhoodListBox = new ListBox();
		getNeighborhoods();
		this.add(neighborhoodListBox, 65, 31);
		neighborhoodListBox.setSize("238px", "22px");
		
		Label numberLabel = new Label("Número:");
		this.add(numberLabel, 308, 31);		
		
		numberTextBox = new TextBox();
		this.add(numberTextBox, 374, 31);
		numberTextBox.setSize("49px", "22px");

		streetTextBox = new TextBox();
		this.add(streetTextBox, 65, 57);
		streetTextBox.setSize("358px", "22px");		

		Label nameLabel = new Label("Nome:");
		this.add(nameLabel, 5, 83);

		nameTextBox = new TextBox();
		this.add(nameTextBox, 65, 84);
		nameTextBox.setSize("358px", "22px");

		Label areaConstruidaLabel = new Label("Area Construída:");
		this.add(areaConstruidaLabel, 5, 111);

		areaConstruidaTextBox = new TextBox();
		this.add(areaConstruidaTextBox, 157, 111);
		areaConstruidaTextBox.setSize("54px", "22px");

		Label areaTotalLabel = new Label("Area Total:");
		this.add(areaTotalLabel, 235, 111);

		areaTotalTextBox = new TextBox();
		this.add(areaTotalTextBox, 369, 111);
		areaTotalTextBox.setSize("54px", "22px");

		Label garageLabel = new Label("Vagas na Garagem:");
		this.add(garageLabel, 5, 137);

		garageTextBox = new TextBox();
		this.add(garageTextBox, 157, 138);
		garageTextBox.setSize("54px", "22px");

		Label roomsLabel = new Label("Qte de Quartos:");
		this.add(roomsLabel, 235, 137);

		qteBedroomsTextBox = new TextBox();
		this.add(qteBedroomsTextBox, 369, 138);
		qteBedroomsTextBox.setSize("54px", "22px");

		Label qteSuitesLabel = new Label("Qte de Suítes:");
		this.add(qteSuitesLabel, 5, 163);

		qteSuitesTextBox = new TextBox();
		this.add(qteSuitesTextBox, 157, 163);
		qteSuitesTextBox.setSize("54px", "22px");

		Label bathroomsLabel = new Label("Qte de Banheiros:");
		this.add(bathroomsLabel, 235, 163);

		qteBathroomsTextBox = new TextBox();
		this.add(qteBathroomsTextBox, 369, 163);
		qteBathroomsTextBox.setSize("54px", "22px");

		Label businesTypeLabel = new Label("Tipo de negócio:");
		this.add(businesTypeLabel, 5, 189);

		businessTypeComboBox = new ListBox();
		businessTypeComboBox.addItem("Comprar");
		businessTypeComboBox.addItem("Alugar");
		this.add(businessTypeComboBox, 157, 190);
		businessTypeComboBox.setSize("90px", "27px");
		
		Label priceLabel = new Label("Preço: R$");
		this.add(priceLabel, 250, 189);

		priceTextBox = new TextBox();
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
		this.add(propertyType, 157, 220);

		
		criarButton = getCreateButton();
		this.add(criarButton, 361, 250);

		mapButton = getMapButton();
		this.add(mapButton, 157, 250);
		mapButton.setSize("189px", "34px");

	}

	

	private void getNeighborhoods() {
		PERSISTENCE_SERVICE.listBairros(new AsyncCallback<List<String>>() {
			
			public void onSuccess(List<String> neighborhoods) {
				for (String neighborhood : neighborhoods) {
					neighborhoodListBox.addItem(neighborhood);
				}
				
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Os estados não puderam ser carregados da base de dados");
				
			}
		});
		
	}



	private Button getMapButton() {
		final Button button = new Button("Ajustar Coordenadas");
		button.addClickListener(new ClickListener() {

			public void onClick(Widget arg0) {
				new AjustCoordinatesMap().show(button.getElement());
			}
			
		});
		return button;
	}



	private Button getCreateButton() {
		Button button = new Button("Criar");
		button.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {
				List<String[]> fields = new ArrayList<String[]>();				
				fields.add(getStrArray("String", cityTextBox, "Cidade"));					
				fields.add(getStrArray("int", numberTextBox, "Número"));
				fields.add(getStrArray("String", streetTextBox, "Rua"));
				fields.add(getStrArray("float", areaConstruidaTextBox, "Area Construída"));
				fields.add(getStrArray("float", areaTotalTextBox, "Area Total"));
				fields.add(getStrArray("int", garageTextBox, "Vagas na Garagem"));
				fields.add(getStrArray("int", qteBedroomsTextBox, "Qte de Quartos"));
				fields.add(getStrArray("int", qteSuitesTextBox, "Qte de Suítes"));
				fields.add(getStrArray("int", qteBathroomsTextBox, "Qte de Banheiros"));
				fields.add(getStrArray("float", priceTextBox, "Preço"));
				fields.add(getStrArray("String", SelectedLocation.getLocation(), "Coordenadas"));
				try {
					FieldsChecker.checkFields(fields);
					CaseBean caseBean = new CaseBean();
					caseBean.setCity(cityTextBox.getText());					 
					caseBean.setNeighborhood(neighborhoodListBox.getItemText(neighborhoodListBox.getSelectedIndex()));//					
					caseBean.setNumber(Integer.parseInt(numberTextBox.getText()));
					caseBean.setState("state");
					caseBean.setStreet(streetTextBox.getText());
					caseBean.setName(nameTextBox.getText());
					caseBean.setBuiltArea(Float.parseFloat(areaConstruidaTextBox.getText()));
					caseBean.setTotalArea(Float.parseFloat(areaTotalTextBox.getText()));
					caseBean.setGarageSpace(Integer.parseInt(garageTextBox.getText()));
					caseBean.setBedroom(Integer.parseInt(qteBedroomsTextBox.getText()));
					caseBean.setSuite(Integer.parseInt(qteSuitesTextBox.getText()));
					caseBean.setBathroom(Integer.parseInt(qteBathroomsTextBox.getText()));
					caseBean.setBusinessType(businessTypeComboBox.getValue(businessTypeComboBox.getSelectedIndex()));
					caseBean.setType(propertyType.getValue(propertyType.getSelectedIndex()));					
					caseBean.setPrice(Float.parseFloat(priceTextBox.getText()));
					caseBean.setLocation(SelectedLocation.getLocation());
					
					createCase(caseBean);
					
					
				} catch (FieldsNotFilledExeption e) {
					new WindowFieldsAlert().show(e.getMessage());
				}
				
			}				
			
		});
		return button;
	}
	
	private void createCase(final CaseBean caseBean) {
		Geocoder streetQuery = new Geocoder();
		streetQuery.getLatLng("Campina Grande, " + streetTextBox.getText(), new LatLngCallback() {
			
			public void onSuccess(LatLng point) {
				create(caseBean);
			}		

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
									create(caseBean);
								}

							}
						});
					}
				});
				
			}
			
			private void create(CaseBean caseBean) {
				PERSISTENCE_SERVICE.createCaso(LoginManager.getLogedAdministrator(), caseBean, new AsyncCallback<String>() {

					public void onFailure(Throwable arg0) {
						MessageBox.alert("Houve problemas durante o armazenamento desse caso.");
						
					}

					public void onSuccess(String arg0) {
						MessageBox.alert("Caso armazenado com sucesso");
						PanelSwitcher.switchPanel(new ManagePanel());							
					}
				});
				
			}
		});
		
		
		
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
