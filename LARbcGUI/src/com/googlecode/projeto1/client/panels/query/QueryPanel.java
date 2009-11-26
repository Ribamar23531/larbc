package com.googlecode.projeto1.client.panels.query;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.panels.Util;
import com.googlecode.projeto1.client.panels.modality.ModalityPanel;
import com.googlecode.projeto1.client.panels.results.ResultsPanel;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.core.Position;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.FitLayout;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author JoÃ£o Felipe
 * @version LARbc 1.0
*/
public class QueryPanel extends Panel{
	
	private AbsolutePanel queryPanel;
	private Image pesquisarButton;
	private Image selectedPesquisarButton;
	private boolean isSelectedPesquisarButton;
	private String street;
	private String neighborhood;
	private String city;
	private String name;
	private float builtArea;
	private int garageSpace;
	private int bathroom;
	private String type;
	private float price;
	private String businessType;
	private String state;
	private float totalArea;
	private int suite;
	private int bedroom;
	private TextField textStreet;
	private ComboBox textNeighborhood;
	private TextField textCity;
	private TextField textName;
	private TextField textAreaConstruida;
	private TextField textGaragem;
	private TextField textQuartos;
	private ComboBox comboTipo;
	private TextField textPreco;
	private ComboBox listbusinessType;
	private ComboBox listEstado;
	private ComboBox relevanciaPreco;
	private ComboBox relevanciaPontos;
	private TextField textAreaTotal;
	private TextField textSuites;
	private TextField textBanheiros;
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	private List<CaseBean> cases;
	
	private Image voltarButtonImage;
	private Image selectedVoltarButtonImage;
	private boolean isSelectedVoltarButton;
	private Panel buttonsVoltarPanel;
	private FormPanel formPanel;
	
	private CheckBox escola;
	private CheckBox universidade;
	private CheckBox viaAcesso;
	private CheckBox areaVerde;
	private CheckBox shopping;
	private CheckBox setorIndustrial;
	private CheckBox downtown;
	
	public QueryPanel(){
		formPanel = new FormPanel();
		formPanel.setFrame(true); 
		formPanel.setBorder(true);
		formPanel.setWidth(310); 
		formPanel.setHeight(60);
		formPanel.setButtonAlign(Position.LEFT);
		
		buttonsVoltarPanel = new Panel();
		buttonsVoltarPanel.setLayout(new ColumnLayout());
		this.isSelectedVoltarButton = false;
		createVoltarButton();
		createSelectedVoltarButton();
		buttonsVoltarPanel.add(voltarButtonImage);

		queryPanel = new AbsolutePanel();
		queryPanel.add(buttonsVoltarPanel, 827, 40);

		queryPanel.setSize("809px", "546px");
		VerticalPanel verticalPanel = new VerticalPanel();
		queryPanel.add(verticalPanel, 0, 0);
		verticalPanel.setSize("440px", "59px");
		Image pesquisaDeImoveis = new Image("images/pesquisaDeImoveis.PNG");
		verticalPanel.add(pesquisaDeImoveis);
		pesquisaDeImoveis.setSize("60%", "100%");
		
		//Nome da Rua
		textStreet = new TextField();
		queryPanel.add(textStreet, 135, 111);
		textStreet.setSize("307px", "21px");
		Label rua = new Label("Nome da rua:");
		queryPanel.add(rua, 17, 114);
		rua.setSize("80px", "18px");
		
		//Bairro
		Label lblBairro = new Label("Bairro:");
		queryPanel.add(lblBairro, 17, 140);
		lblBairro.setSize("41px", "18px");
		textNeighborhood = new ComboBox("Bairro *", "bairro", 230);
		textNeighborhood.setHideLabel(true);
		textNeighborhood.setSize("57px", "21px");
		textNeighborhood.setDisplayField("abbr1");  
		textNeighborhood.setTypeAhead(true);  
		textNeighborhood.setMode(ComboBox.LOCAL);  
		textNeighborhood.setTriggerAction(ComboBox.ALL);
		textNeighborhood.setWidth(307);
		textNeighborhood.setDisabled(false);
		textNeighborhood.setSelectOnFocus(true);				
		queryPanel.add(textNeighborhood, 135, 137);
		getBairros();
		
		//Cidade
		textCity = new TextField();
		queryPanel.add(textCity, 135, 173);
		textCity.setSize("224px", "21px");
		textCity.setValue("Campina Grande");
		textCity.setDisabled(true);		
		Label lblCidade = new Label("Cidade:");
		queryPanel.add(lblCidade, 17, 176);
		lblCidade.setSize("41px", "18px");
		
		//Estado
		Label lblUf = new Label("UF:");
		queryPanel.add(lblUf, 363, 175);
		lblUf.setSize("14px", "18px");
		listEstado = new ComboBox("UF *", "uf", 110);
		listEstado.setSize("57px", "21px");
		listEstado.setHideLabel(true);
		PERSISTENCE_SERVICE.listEstados(new AsyncCallback<List<String>>() {
			
			public void onSuccess(List<String> states) {
				listEstado.setValue(states.get(14));
				listEstado.setDisabled(true);			
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Os estados não puderam ser carregados do disco");
				
			}
		});
		queryPanel.add(listEstado, 382, 173);
				
		//Nome do imovel
		textName = new TextField();
		queryPanel.add(textName, 135, 204);
		textName.setSize("307px", "21px");
		Label lblNome = new Label("Nome do imóvel:");
		queryPanel.add(lblNome, 17, 207);
		lblNome.setSize("80px", "18px");
		
		//Area construida
		textAreaConstruida = new TextField();
		queryPanel.add(textAreaConstruida, 135, 232);
		textAreaConstruida.setSize("78px", "21px");
		Label lblreaConstruda = new Label("Área construída:");
		queryPanel.add(lblreaConstruda, 17, 235);
		lblreaConstruda.setSize("112px", "18px");
		Label unidadeAreaConstruida = new Label("m²");
		queryPanel.add(unidadeAreaConstruida, 212, 235);

		//Area total
		textAreaTotal = new TextField();
		queryPanel.add(textAreaTotal, 305, 232);
		textAreaTotal.setSize("78px", "21px");
		Label lblreaTotal = new Label("Área total:");
		queryPanel.add(lblreaTotal, 248, 235);
		lblreaTotal.setSize("71px", "18px");
		Label unidadeAreaTotal= new Label("m²");
		queryPanel.add(unidadeAreaTotal, 384, 235);
		
		//Vagas na garagem
		textGaragem = new TextField();
		queryPanel.add(textGaragem, 135, 258);
		textGaragem.setSize("78px", "21px");
		Label lblVagasNaGaragem = new Label("Vagas na garagem:");
		queryPanel.add(lblVagasNaGaragem, 17, 261);
		lblVagasNaGaragem.setSize("147px", "18px");
		
		//Quartos
		textQuartos = new TextField();
		queryPanel.add(textQuartos, 135, 287);
		textQuartos.setSize("78px", "21px");
		Label lblQuartos = new Label("Quartos:");
		queryPanel.add(lblQuartos, 17, 290);
		lblQuartos.setSize("55px", "18px");

		//Suites
		textSuites = new TextField();
		queryPanel.add(textSuites, 305, 287);
		textSuites.setSize("78px", "21px");
		Label lblSutes = new Label("Suítes:");
		queryPanel.add(lblSutes, 248, 290);
		lblSutes.setSize("37px", "18px");

		//Banheiros
		textBanheiros = new TextField();
		queryPanel.add(textBanheiros, 135, 313);
		textBanheiros.setSize("78px", "21px");
		Label lblBanheirosSociais = new Label("Banheiros sociais:");
		queryPanel.add(lblBanheirosSociais, 17, 316);
		lblBanheirosSociais.setSize("100px", "24px");
		
		//Tipo de imovel
		Label lblTipoDeImvel = new Label("Tipo de imóvel:");
		lblTipoDeImvel.setSize("131px", "24px");
		queryPanel.add(lblTipoDeImvel, 17, 344);
		comboTipo = new ComboBox("Tipo do imóvel *", "tipo do imóvel", 110);
		comboTipo.setHideLabel(true);
		comboTipo.setSize("205px", "21px");	
		Store store = new SimpleStore(new String[]{"abbr", "state"}, getTipos());  
		store.load();  
		comboTipo.setHiddenName("tipo do imóvel");  
		comboTipo.setStore(store);  
		comboTipo.setDisplayField("abbr");  
		comboTipo.setTypeAhead(true);  
		comboTipo.setMode(ComboBox.LOCAL);  
		comboTipo.setTriggerAction(ComboBox.ALL); 
		comboTipo.setValue("Casa");
		comboTipo.setDisabled(false);
		comboTipo.setSelectOnFocus(true);  
		comboTipo.setWidth(190); 
		queryPanel.add(comboTipo, 135, 339);

		//Preco do imovel
		textPreco = new TextField();
		queryPanel.add(textPreco, 135, 371);
		textPreco.setSize("146px", "21px");		
		Label lblPreoEmTorno = new Label("Preço em torno de (R$):");
		queryPanel.add(lblPreoEmTorno, 17, 374);
		lblPreoEmTorno.setSize("115px", "24px");
		Label relevancia = new Label("Relevância:");
		queryPanel.add(relevancia, 310, 374);
		relevancia.setSize("115px", "24px");
		relevanciaPreco = new ComboBox("Preço *", "preço", 110);
		relevanciaPreco.setHideLabel(true);
		relevanciaPreco.setSize("57px", "21px");	
		Store storePreco = new SimpleStore(new String[]{"abbr", "state"}, getRelevancia());  
		store.load();  
		relevanciaPreco.setHiddenName("preco");  
		relevanciaPreco.setStore(storePreco);  
		relevanciaPreco.setDisplayField("abbr");  
		relevanciaPreco.setTypeAhead(true);  
		relevanciaPreco.setMode(ComboBox.LOCAL);  
		relevanciaPreco.setTriggerAction(ComboBox.ALL); 
		relevanciaPreco.setValue("1");
		relevanciaPreco.setDisabled(false);
		relevanciaPreco.setSelectOnFocus(true);  
		queryPanel.add(relevanciaPreco, 382, 371);

		//Tipo de negocio
		listbusinessType = new ComboBox("Tipo de negócio *", "tipo de negócio", 110);
		listbusinessType.setHideLabel(true);
		Store storeNegocio = new SimpleStore(new String[]{"abbr", "state"}, getTipoNegocio());  
		store.load();  
		listbusinessType.setHiddenName("tipo de negócio");  
		listbusinessType.setSize("166px", "21px");
		listbusinessType.setStore(storeNegocio);  
		listbusinessType.setDisplayField("abbr");  
		listbusinessType.setTypeAhead(true);  
		listbusinessType.setMode(ComboBox.LOCAL);  
		listbusinessType.setTriggerAction(ComboBox.ALL); 
		listbusinessType.setValue("Comprar");
		listbusinessType.setDisabled(false);
		listbusinessType.setSelectOnFocus(true);  
		listbusinessType.setWidth(190); 
		Label lblTipoDeNegcio = new Label("Tipo de negócio:");
		queryPanel.add(lblTipoDeNegcio, 17, 402);
		lblTipoDeNegcio.setSize("131px", "24px");
		queryPanel.add(listbusinessType, 135, 397);
		
		//Pontos de interesse
		Label pontos = new Label("Pontos de interesse:");
		queryPanel.add(pontos, 17, 430);
		formPanel.setButtonAlign(Position.RIGHT);
		Label relevanciapontos = new Label("Relevância:");
		queryPanel.add(relevanciapontos, 30, 448);
		relevanciapontos.setSize("115px", "24px");
		relevanciaPontos = new ComboBox("Pontos *", "pontos", 110);
		relevanciaPontos.setSize("57px", "21px");	
		relevanciaPontos.setHideLabel(true);
		Store storePontos = new SimpleStore(new String[]{"abbr", "state"}, getRelevancia());  
		store.load();  
		relevanciaPontos.setHiddenName("pontos");  
		relevanciaPontos.setStore(storePontos);  
		relevanciaPontos.setDisplayField("abbr");  
		relevanciaPontos.setTypeAhead(true);  
		relevanciaPontos.setMode(ComboBox.LOCAL);  
		relevanciaPontos.setTriggerAction(ComboBox.ALL); 
		relevanciaPontos.setValue("1");
		relevanciaPontos.setDisabled(false);
		relevanciaPontos.setSelectOnFocus(true);  
		queryPanel.add(relevanciaPontos, 30, 465);
		escola = new CheckBox("Escola");
		universidade = new CheckBox("Universidade");
		viaAcesso = new CheckBox("Via principal de acesso");
		areaVerde = new CheckBox("Área verde");
		shopping = new CheckBox("Shopping");
		setorIndustrial = new CheckBox("Setor industrial");
		downtown = new CheckBox("Centro");
		formPanel.add(escola);
		formPanel.add(universidade);
		formPanel.add(viaAcesso);
		formPanel.add(areaVerde);
		formPanel.add(shopping);
		formPanel.add(setorIndustrial);
		formPanel.add(downtown);
		//Adicionando formulário
		queryPanel.add(formPanel, 135,430);

		Image image1 = new Image("images/familia.png");
		queryPanel.add(image1, 650, 96);
		image1.setSize("193px", "300px");
		
		createEntrarButton();

		this.add(queryPanel);
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}
	
	private void createEntrarButton() {
		pesquisarButton = Util.createImage(Util.PESQUISAR_BUTTON_IMAGE);
		pesquisarButton.setSize("11%", "5%");
		pesquisarButton.addMouseListener(new MouseListenerAdapter(){

			

			public void onMouseEnter(Widget arg0) {
				isSelectedPesquisarButton = true;
				buildButtonsPanel();
				
			}

		});
		selectedPesquisarButton = Util.createImage(Util.PESQUISAR_SELECTED_BUTTON_IMAGE);
		selectedPesquisarButton.setSize("11%", "5%");
		selectedPesquisarButton.addMouseListener(new MouseListenerAdapter(){			

			public void onMouseLeave(Widget arg0) {
				isSelectedPesquisarButton = false;
				buildButtonsPanel();			
			}
			
		});
		selectedPesquisarButton.addClickListener(new ClickListener(){
			public void onClick(Widget arg0) {			
				street = textStreet.getText();
				neighborhood = textNeighborhood.getText();
				city = textCity.getText();
				name = textName.getText();
				String message = "Digite um valor numérico válido para: ";
				try{
					builtArea = Float.parseFloat(textAreaConstruida.getText());					
				}catch(Exception e){
					if(!textAreaConstruida.getText().equals("")){
						MessageBox.alert(message + "Área construída");
						return;
					}
				}
				try{
					garageSpace = Integer.parseInt(textGaragem.getText());					
				}catch(Exception e){
					if(!textGaragem.getText().equals("")){
						MessageBox.alert(message + "Vagas na garagem");
						return;
					}
				}
				try{
					bathroom = Integer.parseInt(textBanheiros.getText());					
				}catch(Exception e){
					if(!textBanheiros.getText().equals("")){
						MessageBox.alert(message + "Banheiros sociais");
						return;
					}
				}				
				
				type = comboTipo.getText();
				
				try{
					price = Float.parseFloat(textPreco.getText());
				}catch(Exception e){
					if(!textPreco.getText().equals("")){
						MessageBox.alert(message + "Preço");
						return;
					}
				}				
				
				businessType = listbusinessType.getText();
				
				state = listEstado.getText();
				
				try{
					totalArea = Float.parseFloat(textAreaTotal.getText());
				}catch(Exception e){
					if(!textAreaTotal.getText().equals("")){
						MessageBox.alert(message + "Área total");
						return;
					}
				}
				try{
					suite = Integer.parseInt(textSuites.getText());
				}catch(Exception e){
					if(!textSuites.getText().equals("")){
						MessageBox.alert(message + "Suítes");
						return;
					}
				}	
				try{
					bedroom = Integer.parseInt(textQuartos.getText());
				}catch(Exception e){
					if(!textQuartos.getText().equals("")){
						MessageBox.alert(message + "Quartos");
						return;
					}
				}
				

				Geocoder streetQuery = new Geocoder();
				streetQuery.getLatLng("Campina Grande, " + street, new LatLngCallback() {
					
					public void onSuccess(LatLng point) {
						if(street.equals("")){
							doQuery(Double.MAX_VALUE, Double.MAX_VALUE);
						}else{
							doQuery(point.getLatitude(), point.getLongitude());							
						}
						
					}					
					

					public void onFailure() {
						
						doQuery(Double.MAX_VALUE, Double.MAX_VALUE);					
						
					}
				});
				
				
				
				
			}
		});
		
		buildButtonsPanel();
		
	}
	
	private void doQuery(double latitude, double longitude) {
		double POIWeight = Double.parseDouble(relevanciaPontos.getText());
		float priceWeight = Float.parseFloat(relevanciaPreco.getText());
		List<String> kindsOfPOI = new ArrayList<String>(6);
		if(escola.isChecked()){
			kindsOfPOI.add("SCHOOL");
		}
		if(universidade.isChecked()){
			kindsOfPOI.add("UNIVERSITY");
		}
		if(viaAcesso.isChecked()){
			kindsOfPOI.add("ACCESS_ROAD");
		}
		if(areaVerde.isChecked()){
			kindsOfPOI.add("GREEN_AREA");
		}
		if(shopping.isChecked()){
			kindsOfPOI.add("SHOPPING_CENTER");
		}
		if(setorIndustrial.isChecked()){
			kindsOfPOI.add("INDUSTRIAL");
		}
		if(downtown.isChecked()){
			kindsOfPOI.add("DOWNTOWN");
		}	
		
		PERSISTENCE_SERVICE.doQuery(state, city, neighborhood, street, name, builtArea, totalArea,
									garageSpace, bedroom, suite, bathroom, type, price, priceWeight,
									businessType, latitude, longitude, POIWeight, kindsOfPOI,
									new AsyncCallback<List<CaseBean>>() {
			
			public void onSuccess(List<CaseBean> arg0) {
				PanelSwitcher.switchPanel(new ResultsPanel(arg0));
				
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("A consulta não pode ser realizada.");
				
			}
		});
		
	}
	
	public List<CaseBean> getCases(){
		return cases;
	}
	
	private void buildButtonsPanel() {
		if(!isSelectedPesquisarButton){
			queryPanel.add(pesquisarButton, 500, 500);
		}else{
			queryPanel.add(selectedPesquisarButton, 500, 500);
		}
	}
	
	//BOTAO VOLTAR
	private void createSelectedVoltarButton() {
		selectedVoltarButtonImage = Util.createImage(Util.VOLTAR_SELECTED_BUTTON_IMAGE);
		selectedVoltarButtonImage.setPixelSize(33, 10);
		selectedVoltarButtonImage.addMouseListener(new MouseListenerAdapter(){

			public void onMouseLeave(Widget arg0) {
				rebuildVoltarPanel(voltarButtonImage);
				
			}			
			
		});
		
		selectedVoltarButtonImage.addClickListener(new ClickListener(){

			public void onClick(Widget arg0) {
				PanelSwitcher.switchPanel(new ModalityPanel());				
			}
			
		});		
		
	}

	private void createVoltarButton() {
		voltarButtonImage = Util.createImage(Util.VOLTAR_BUTTON_IMAGE);
		voltarButtonImage.setPixelSize(33, 10);
		voltarButtonImage.addMouseListener(new MouseListenerAdapter(){
			
			public void onMouseEnter(Widget arg0) {
				rebuildVoltarPanel(selectedVoltarButtonImage);
			}
		});
	}
	
	private void rebuildVoltarPanel(Image buttonImage){
		buttonsVoltarPanel.removeAll();
		if(!isSelectedVoltarButton){
			buttonsVoltarPanel.add(buttonImage);
		}else{
			buttonsVoltarPanel.add(buttonImage);
		}
		buttonsVoltarPanel.doLayout();	
	}
	
	private void getBairros() {		
		PERSISTENCE_SERVICE.listBairros(new AsyncCallback<List<String>>() {
			
			public void onSuccess(List<String> bairros) {
				String[][] lista = new String [bairros.size()][3];
				for (int i = 0; i < bairros.size(); i++) {					
					lista[i][0] = bairros.get(i);
					lista[i][1] = bairros.get(i);
					lista[i][2] = i + 1 + "";
				}				
				Store storeBairro = new SimpleStore(new String[]{"abbr1", "state1"}, lista);		
				storeBairro.load();  
				textNeighborhood.setStore(storeBairro);
				textNeighborhood.setValue(lista[0][0]);		
				
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Não foi possível carregar os bairros da base de dados");
				
			}
		});		
	}
	
	private String[][] getTipos() {  
		return new String[][]{  
				new String[]{"Casa", "casa", "1"},  
				new String[]{"Apartamento", "2"},  
				new String[]{"Terreno", "terreno", "3"},  
				new String[]{"Sala comercial", "4"},  
		};  
	}
	
	private String[][] getRelevancia() {  
		return new String[][]{  
				new String[]{"1", "1", "1"},  
				new String[]{"2", "2"},  
				new String[]{"3", "3", "3"},  
				new String[]{"4", "4"},  
				new String[]{"5", "5", "5"},  
		};  
	}
	
	private String[][] getTipoNegocio() {  
		return new String[][]{  
				new String[]{"Comprar", "comprar", "1"},  
				new String[]{"Alugar", "alugar"},  
		};  
	}

}
