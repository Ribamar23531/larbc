package com.googlecode.projeto1.client.panels.demands;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.beans.DemandBean;
import com.googlecode.projeto1.client.panels.Util;
import com.googlecode.projeto1.client.panels.modality.ModalityPanel;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.VType;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.FitLayout;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
 public class DemandSellerPanel extends Panel{
	
	private AbsolutePanel rootPanel;
	private Image pesquisarButton;
	private Image selectedCadastrarButton;
	private boolean isSelectedPesquisarButton;
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	private String street;
	private String neighborhood;
	private String city;
	private String name;
	private float builtArea;
	private int garageSpace;
	private int bathroom;
	private String type;
	private float price;
	private String state;
	private float totalArea;
	private int suite;
	private int bedroom;
	private String email;
	private TextField textEmail;
	private String telefone;
	private TextField textTelefone;
	private TextField textRua;
	private TextField textBairro;
	private TextField textCidade;
	private TextField textNomePessoa;
	private TextField textAreaConstruida;
	private TextField textGaragem;
	private TextField textQuartos;
	private ListBox comboTipo;
	private TextField textPreco;
	private ListBox listEstado;
	private TextField textAreaTotal;
	private TextField textSuites;
	private TextField textBanheiros;
	private TextField textNome;

	private Image voltarButtonImage;
	private Image selectedVoltarButtonImage;
	private boolean isSelectedVoltarButton;
	private Panel buttonsVoltarPanel;
	
	public DemandSellerPanel(){
		buttonsVoltarPanel = new Panel();
		buttonsVoltarPanel.setLayout(new ColumnLayout());
		this.isSelectedVoltarButton = false;
		createVoltarButton();
		createSelectedVoltarButton();
		buttonsVoltarPanel.add(voltarButtonImage);
		
		rootPanel = new AbsolutePanel();
		rootPanel.add(buttonsVoltarPanel, 827, 40);

		rootPanel.setSize("809px", "546px");
		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel, 0, 0);
		verticalPanel.setSize("440px", "59px");
		Image image = new Image("images/larbc.png");
		verticalPanel.add(image);
		image.setSize("70%", "100%");
		
		//Nome da Rua
		textRua = new TextField("Nome da rua", "nome da rua", 230);
		textRua.setAllowBlank(false);  
		rootPanel.add(textRua, 135, 111);
		textRua.setSize("307px", "21px");
		Label rua = new Label("Nome da rua: *");
		rootPanel.add(rua, 17, 114);
		rua.setSize("80px", "18px");
		
		//Bairro
		textBairro = new TextField("Bairro", "bairro", 230);
		textBairro.setAllowBlank(false);  
		rootPanel.add(textBairro, 135, 137);
		textBairro.setSize("307px", "21px");
		Label lblBairro = new Label("Bairro: *");
		rootPanel.add(lblBairro, 17, 140);
		lblBairro.setSize("41px", "18px");
		
		//Cidade
		textCidade = new TextField();
		textCidade.setAllowBlank(false);  
		rootPanel.add(textCidade, 135, 163);
		textCidade.setSize("224px", "21px");
		textCidade.setValue("Campina Grande");
		textCidade.setDisabled(true);
		Label lblCidade = new Label("Cidade: *");
		rootPanel.add(lblCidade, 17, 166);
		lblCidade.setSize("43px", "18px");
		
		//Estado
		listEstado = new ListBox();
		rootPanel.add(listEstado, 382, 160);
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
		Label lblUf = new Label("UF: *");
		rootPanel.add(lblUf, 363, 165);
		lblUf.setSize("14px", "18px");
		
		//Nome do imóvel
		textNome = new TextField();
		rootPanel.add(textNome, 135, 194);
		textNome.setSize("307px", "21px");
		Label lblNome = new Label("Nome do imóvel:");
		rootPanel.add(lblNome, 17, 197);
		lblNome.setSize("80px", "18px");
		
		//Area construida
		textAreaConstruida = new TextField();
		textAreaConstruida.setAllowBlank(false);  
		rootPanel.add(textAreaConstruida, 135, 222);
		textAreaConstruida.setSize("78px", "21px");
		Label lblreaConstruda = new Label("Área construída: *");
		rootPanel.add(lblreaConstruda, 17, 225);
		lblreaConstruda.setSize("112px", "18px");
		Label unidadeAreaConstruida = new Label("m²");
		rootPanel.add(unidadeAreaConstruida, 212, 225);
		
		//Area total
		textAreaTotal = new TextField();
		textAreaTotal.setAllowBlank(false);  
		rootPanel.add(textAreaTotal, 308, 222);
		textAreaTotal.setSize("78px", "21px");
		Label lblreaTotal = new Label("Área total: *");
		rootPanel.add(lblreaTotal, 248, 225);
		lblreaTotal.setSize("71px", "18px");
		Label unidadeAreaTotal= new Label("m²");
		rootPanel.add(unidadeAreaTotal, 387, 225);
		
		//Vagas na garagem
		textGaragem = new TextField();
		textGaragem.setAllowBlank(false);  
		rootPanel.add(textGaragem, 135, 248);
		textGaragem.setSize("78px", "21px");
		Label lblVagasNaGaragem = new Label("Vagas na garagem: *");
		rootPanel.add(lblVagasNaGaragem, 17, 251);
		lblVagasNaGaragem.setSize("147px", "18px");
				
		//Quartos
		textQuartos = new TextField();
		textQuartos.setAllowBlank(false);  
		rootPanel.add(textQuartos, 135, 277);
		textQuartos.setSize("78px", "21px");
		Label lblQuartos = new Label("Quartos: *");
		rootPanel.add(lblQuartos, 17, 280);
		lblQuartos.setSize("55px", "18px");

		//Suites
		textSuites = new TextField();
		textSuites.setAllowBlank(false);  
		rootPanel.add(textSuites, 295, 277);
		textSuites.setSize("78px", "21px");
		Label lblSutes = new Label("Suítes: *");
		rootPanel.add(lblSutes, 248, 280);
		lblSutes.setSize("40px", "18px");
		
		//Banheiros
		textBanheiros = new TextField();
		textBanheiros.setAllowBlank(false);  
		rootPanel.add(textBanheiros, 135, 303);
		textBanheiros.setSize("78px", "21px");
		Label lblBanheirosSociais = new Label("Banheiros sociais: *");
		rootPanel.add(lblBanheirosSociais, 17, 306);
		lblBanheirosSociais.setSize("100px", "24px");
		
		//Tipo de imovel
		comboTipo = new ListBox();
		rootPanel.add(comboTipo, 135, 329);
		comboTipo.setSize("205px", "21px");	
		comboTipo.addItem("Casa");
		comboTipo.addItem("Apartamento");
		comboTipo.addItem("Terreno");
		comboTipo.addItem("Sala comercial");
		Label lblTipoDeImvel = new Label("Tipo de imóvel: *");
		rootPanel.add(lblTipoDeImvel, 17, 334);
		lblTipoDeImvel.setSize("131px", "24px");
		
		//Preco do imovel
		textPreco = new TextField();
		rootPanel.add(textPreco, 135, 361);
		textPreco.setSize("146px", "21px");		
		Label lblPreoEmTorno = new Label("Preço em torno de (R$):");
		rootPanel.add(lblPreoEmTorno, 17, 364);
		lblPreoEmTorno.setSize("115px", "24px");
		
		//Nome da pessoa
		textNomePessoa = new TextField();
		textNomePessoa.setAllowBlank(false);  
		rootPanel.add(textNomePessoa, 135, 390);
		textNomePessoa.setSize("265px", "21px");
		Label lblNomePessoa = new Label("Seu nome: *");
		rootPanel.add(lblNomePessoa, 17, 392);
		lblNomePessoa.setSize("58px", "18px");
		
		//Email
		textEmail = new TextField();
		textEmail.setAllowBlank(false);
		textEmail.setVtype(VType.EMAIL);
		rootPanel.add(textEmail, 135, 415);
		textEmail.setSize("220px", "21px");		
		Label lblEmail = new Label("Email: *");
		rootPanel.add(lblEmail, 17, 418);
		lblEmail.setSize("40px", "24px");
		
		//Telefone
		textTelefone = new TextField();
		textTelefone.setAllowBlank(false);  
		rootPanel.add(textTelefone, 135, 441);
		textTelefone.setSize("150px", "21px");		
		Label lblTelefone = new Label("Telefone: *");
		rootPanel.add(lblTelefone, 17, 444);
		lblTelefone.setSize("55px", "24px");
		
		Image image1 = new Image("images/familia.png");
		rootPanel.add(image1, 463, 96);
		image1.setSize("193px", "300px");
		
		createEntrarButton();

		Label lblPesquisaDeImoveis = new Label("CADASTRO DE IMÓVEIS PARA VENDA");
		rootPanel.add(lblPesquisaDeImoveis, 17, 76);
		lblPesquisaDeImoveis.setSize("200px", "13px");
		
		Label asteriscos = new Label("Campos marcados com asterisco são obrigatórios");
		rootPanel.add(asteriscos, 17, 88);
		
		this.add(rootPanel);
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}
	
	private void createEntrarButton() {
		pesquisarButton = Util.createImage(Util.CADASTRAR_BUTTON_IMAGE);
		pesquisarButton.setSize("11%", "5%");
		pesquisarButton.addMouseListener(new MouseListenerAdapter(){

			

			public void onMouseEnter(Widget arg0) {
				isSelectedPesquisarButton = true;
				buildButtonsPanel();
				
			}

		});
		selectedCadastrarButton = Util.createImage(Util.SELECTED_CADASTRAR_BUTTON_IMAGE);
		selectedCadastrarButton.setSize("11%", "5%");
		selectedCadastrarButton.addMouseListener(new MouseListenerAdapter(){			

			public void onMouseLeave(Widget arg0) {
				isSelectedPesquisarButton = false;
				buildButtonsPanel();			
			}
			
		});
		selectedCadastrarButton.addClickListener(new ClickListener(){
			public void onClick(Widget arg0) {
				street = textRua.getText();
				neighborhood = textBairro.getText();
				city = textCidade.getText();
				name = textNomePessoa.getText();
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
						
				try{
					price = Float.parseFloat(textPreco.getText());
				}catch(Exception e){
					if(!textPreco.getText().equals("")){
						MessageBox.alert(message + "Preço");
						return;
					}
				}				

				int index = listEstado.getSelectedIndex();
				state  = listEstado.getItemText(index);
				
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
				email = textEmail.getText();
				telefone = textTelefone.getText();
				DemandBean demanda = new DemandBean();
				demanda.setAreaConstruida(builtArea);
				demanda.setAreaTotal(totalArea);
				demanda.setBairro(neighborhood);
				demanda.setBanheiros(bathroom);
				demanda.setCidade(city);
				demanda.setTipo(type);
				demanda.setEmailCliente(email);
				demanda.setNomeCliente(name);
				demanda.setTelefone(telefone);
				demanda.setRua(street);
				demanda.setSuites(suite);
				demanda.setQuartos(bedroom);
				demanda.setEstado(state);
				demanda.setVagasGaragem(garageSpace);
				demanda.setPreco(price);
				PERSISTENCE_SERVICE.saveDemanda(demanda, new AsyncCallback<String>() {

					public void onFailure(Throwable arg0) {
						MessageBox.alert("O cadastro não pode ser relizado.");						
					}

					public void onSuccess(String arg0) {
						MessageBox.alert("Cadastro realizado com sucesso.");
						PanelSwitcher.switchPanel(new ModalityPanel());
					}
				});
			}
		});
		
		buildButtonsPanel();
		
	}
	
	private void buildButtonsPanel() {
		if(!isSelectedPesquisarButton){
			rootPanel.add(pesquisarButton, 326, 465);
		}else{
			rootPanel.add(selectedCadastrarButton, 326, 465);
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
