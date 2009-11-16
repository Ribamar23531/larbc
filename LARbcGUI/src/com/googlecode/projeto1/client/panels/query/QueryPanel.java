package com.googlecode.projeto1.client.panels.query;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.RadioButton;
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
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
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
	private TextField textNeighborhood;
	private TextField textCity;
	private TextField textName;
	private TextField textAreaConstruida;
	private TextField textGaragem;
	private TextField textQuartos;
	private ListBox comboTipo;
	private TextField textPreco;
	private ListBox listbusinessType;
	private ListBox listEstado;
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
		textNeighborhood = new TextField();
		queryPanel.add(textNeighborhood, 135, 137);
		textNeighborhood.setSize("307px", "21px");
		Label lblBairro = new Label("Bairro:");
		queryPanel.add(lblBairro, 17, 140);
		lblBairro.setSize("41px", "18px");
		
		//Cidade
		textCity = new TextField();
		queryPanel.add(textCity, 135, 163);
		textCity.setSize("224px", "21px");
		textCity.setValue("Campina Grande");
		textCity.setDisabled(true);		
		Label lblCidade = new Label("Cidade:");
		queryPanel.add(lblCidade, 17, 166);
		lblCidade.setSize("41px", "18px");
		
		//Estado
		listEstado = new ListBox();
		queryPanel.add(listEstado, 382, 160);
		listEstado.setSize("57px", "21px");
		PERSISTENCE_SERVICE.listEstados(new AsyncCallback<List<String>>() {
			
			public void onSuccess(List<String> states) {
				for (String state : states) {
					listEstado.addItem(state);
					listEstado.setSelectedIndex(14);
					listEstado.setEnabled(false);
				}
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Os estados não puderam ser carregados do disco");
				
			}
		});
		Label lblUf = new Label("UF:");
		queryPanel.add(lblUf, 363, 165);
		lblUf.setSize("14px", "18px");
				
		//Nome do imovel
		textName = new TextField();
		queryPanel.add(textName, 135, 194);
		textName.setSize("307px", "21px");
		Label lblNome = new Label("Nome do imóvel:");
		queryPanel.add(lblNome, 17, 197);
		lblNome.setSize("80px", "18px");
		
		//Area construida
		textAreaConstruida = new TextField();
		queryPanel.add(textAreaConstruida, 135, 222);
		textAreaConstruida.setSize("78px", "21px");
		Label lblreaConstruda = new Label("Área construída:");
		queryPanel.add(lblreaConstruda, 17, 225);
		lblreaConstruda.setSize("112px", "18px");
		Label unidadeAreaConstruida = new Label("m²");
		queryPanel.add(unidadeAreaConstruida, 212, 225);

		//Area total
		textAreaTotal = new TextField();
		queryPanel.add(textAreaTotal, 305, 222);
		textAreaTotal.setSize("78px", "21px");
		Label lblreaTotal = new Label("Área total:");
		queryPanel.add(lblreaTotal, 248, 225);
		lblreaTotal.setSize("71px", "18px");
		Label unidadeAreaTotal= new Label("m²");
		queryPanel.add(unidadeAreaTotal, 384, 225);
		
		//Vagas na garagem
		textGaragem = new TextField();
		queryPanel.add(textGaragem, 135, 248);
		textGaragem.setSize("78px", "21px");
		Label lblVagasNaGaragem = new Label("Vagas na garagem:");
		queryPanel.add(lblVagasNaGaragem, 17, 251);
		lblVagasNaGaragem.setSize("147px", "18px");
		
		//Quartos
		textQuartos = new TextField();
		queryPanel.add(textQuartos, 135, 277);
		textQuartos.setSize("78px", "21px");
		Label lblQuartos = new Label("Quartos:");
		queryPanel.add(lblQuartos, 17, 280);
		lblQuartos.setSize("55px", "18px");

		//Suites
		textSuites = new TextField();
		queryPanel.add(textSuites, 305, 277);
		textSuites.setSize("78px", "21px");
		Label lblSutes = new Label("Suítes:");
		queryPanel.add(lblSutes, 248, 280);
		lblSutes.setSize("37px", "18px");

		//Banheiros
		textBanheiros = new TextField();
		queryPanel.add(textBanheiros, 135, 303);
		textBanheiros.setSize("78px", "21px");
		Label lblBanheirosSociais = new Label("Banheiros sociais:");
		queryPanel.add(lblBanheirosSociais, 17, 306);
		lblBanheirosSociais.setSize("100px", "24px");
		
		//Tipo de imovel
		comboTipo = new ListBox();
		queryPanel.add(comboTipo, 135, 329);
		comboTipo.setSize("205px", "21px");	
		comboTipo.addItem("Casa");
		comboTipo.addItem("Apartamento");
		comboTipo.addItem("Terreno");
		comboTipo.addItem("Sala Comercial");
		Label lblTipoDeImvel = new Label("Tipo de imóvel:");
		queryPanel.add(lblTipoDeImvel, 17, 334);
		lblTipoDeImvel.setSize("131px", "24px");
		
		//Preco do imovel
		textPreco = new TextField();
		queryPanel.add(textPreco, 135, 361);
		textPreco.setSize("146px", "21px");		
		Label lblPreoEmTorno = new Label("Preço em torno de (R$):");
		queryPanel.add(lblPreoEmTorno, 17, 364);
		lblPreoEmTorno.setSize("115px", "24px");

		//Tipo de negocio
		listbusinessType = new ListBox();
		queryPanel.add(listbusinessType, 135, 387);
		listbusinessType.setSize("166px", "21px");
		listbusinessType.addItem("Comprar");
		listbusinessType.addItem("Alugar");
		Label lblTipoDeNegcio = new Label("Tipo de negócio:");
		queryPanel.add(lblTipoDeNegcio, 17, 392);
		lblTipoDeNegcio.setSize("131px", "24px");
		
		//Pontos de interesse
		Label pontos = new Label("Pontos de interesse:");
		queryPanel.add(pontos, 17, 420);
		formPanel.setButtonAlign(Position.RIGHT);
		RadioButton escola = new RadioButton("Escola", "Escola");
		RadioButton universidade = new RadioButton("Universidade", "Universidade");
		RadioButton viaAcesso = new RadioButton("Via principal de acesso", "Via principal de acesso");
		RadioButton areaVerde = new RadioButton("Área verde", "Área verde");
		RadioButton shopping = new RadioButton("Shopping", "Shopping");
		RadioButton setorIndustrial = new RadioButton("Setor industrial", "Setor industrial");
		formPanel.add(escola);
		formPanel.add(universidade);
		formPanel.add(viaAcesso);
		formPanel.add(areaVerde);
		formPanel.add(shopping);
		formPanel.add(setorIndustrial);
		//Adicionando formulário
		queryPanel.add(formPanel, 135,420);

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
				
				int indexType = comboTipo.getSelectedIndex();
				type = comboTipo.getItemText(indexType);
				
				try{
					price = Float.parseFloat(textPreco.getText());
				}catch(Exception e){
					if(!textPreco.getText().equals("")){
						MessageBox.alert(message + "Preço");
						return;
					}
				}				
				
				businessType = listbusinessType.getItemText(listbusinessType.getSelectedIndex());
				
				int indexState = listEstado.getSelectedIndex();
				state = listEstado.getItemText(indexState);
				
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
				
				PERSISTENCE_SERVICE.doQuery(state, city, neighborhood, street, name, builtArea, totalArea, garageSpace, bedroom, suite, bathroom, type, price, businessType, new AsyncCallback<List<CaseBean>>() {
					public void onSuccess(List<CaseBean> arg0) {
						PanelSwitcher.switchPanel(new ResultsPanel(arg0));
						
					}
					
					public void onFailure(Throwable arg0) {
						MessageBox.alert("A consulta não pode ser realizada.");
						
					}
				});
			}
		});
		
		buildButtonsPanel();
		
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

}
