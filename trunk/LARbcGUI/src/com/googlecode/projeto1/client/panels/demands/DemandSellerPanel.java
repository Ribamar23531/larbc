package com.googlecode.projeto1.client.panels.demands;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.beans.DemandBean;
import com.googlecode.projeto1.client.panels.Util;
import com.googlecode.projeto1.client.panels.modality.ModalityPanel;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.MultiFieldPanel;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.VType;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
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
	private ComboBox comboTipo;
	private TextField textPreco;
	private ComboBox listEstado;
	private TextField textAreaTotal;
	private TextField textSuites;
	private TextField textBanheiros;
	private TextField textNome;
	private FormPanel formPanel;

	private Image voltarButtonImage;
	private Image selectedVoltarButtonImage;
	private boolean isSelectedVoltarButton;
	private Panel buttonsVoltarPanel;
	
	public DemandSellerPanel(){
		formPanel = new FormPanel();
		formPanel.setFrame(false); 
		formPanel.setBorder(false);
		formPanel.setWidth(800);  
		formPanel.setLabelWidth(140);  
		
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
		textRua = new TextField("Nome da rua *", "nome da rua", 230);
		textRua.setAllowBlank(false); 
		textRua.setBlankText("Este campo é obrigatório");
		formPanel.add(textRua);
		textRua.setSize("307px", "21px");
		
		//Bairro
		textBairro = new TextField("Bairro *", "bairro", 230);
		textBairro.setAllowBlank(false); 
		textBairro.setBlankText("Este campo é obrigatório");
		formPanel.add(textBairro);
		textBairro.setSize("307px", "21px");
		
		//Cidade
		textCidade = new TextField("Cidade *", "cidade", 110);
		textCidade.setAllowBlank(false); 
		textCidade.setBlankText("Este campo é obrigatório");
		textCidade.setSize("217px", "21px");
		textCidade.setValue("Campina Grande");
		textCidade.setDisabled(true);
		
		//Estado
		listEstado = new ComboBox("UF *", "uf", 110);
		listEstado.setSize("57px", "21px");
		listEstado.setHideLabel(true);
		Label uf = new Label("UF *:");
		rootPanel.add(uf, 385, 174);
		PERSISTENCE_SERVICE.listEstados(new AsyncCallback<List<String>>() {
			
			public void onSuccess(List<String> states) {
				listEstado.setValue(states.get(14));
				listEstado.setDisabled(true);			
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Os estados não puderam ser carregados do disco");
				
			}
		});
		
		MultiFieldPanel cidadeEstadoPanel = new MultiFieldPanel();  
		cidadeEstadoPanel.addToRow(textCidade,397);  
		cidadeEstadoPanel.addToRow(listEstado,new ColumnLayoutData(1));  
		formPanel.add(cidadeEstadoPanel);
		
		//Nome do imóvel
		textNome = new TextField("Nome do imóvel", "nome do imóvel", 230);
		formPanel.add(textNome);
		textNome.setSize("307px", "21px");
		
		//Area construida
		textAreaConstruida = new TextField("Área construída *", "área construída", 230);
		textAreaConstruida.setAllowBlank(false);  
		textAreaConstruida.setBlankText("Este campo é obrigatório");
		textAreaConstruida.setSize("78px", "21px");
		Label unidadeAreaConstruida = new Label("m²");
		rootPanel.add(unidadeAreaConstruida, 242, 225);
		
		//Area total
		textAreaTotal = new TextField("Área total *", "área total", 230);
		textAreaTotal.setAllowBlank(false); 
		textAreaTotal.setBlankText("Este campo é obrigatório");
		textAreaTotal.setSize("78px", "21px");
		textAreaTotal.setHideLabel(true); 
		Label areaTotal= new Label("Área Total *:");
		rootPanel.add(areaTotal, 308, 225);
		Label unidadeAreaTotal= new Label("m²");
		rootPanel.add(unidadeAreaTotal, 457, 225);
		
		MultiFieldPanel areasPanel = new MultiFieldPanel();  
		areasPanel.addToRow(textAreaConstruida,363);  
		areasPanel.addToRow(textAreaTotal,new ColumnLayoutData(1) );  
		formPanel.add(areasPanel);
		
		//Vagas na garagem
		textGaragem = new TextField("Vagas na garagem *", "vagas na garagem", 230);
		textGaragem.setAllowBlank(false);  
		textGaragem.setBlankText("Este campo é obrigatório");
		formPanel.add(textGaragem);
		textGaragem.setSize("78px", "21px");
				
		//Quartos
		textQuartos = new TextField("Quartos *", "quartos", 230);
		textQuartos.setAllowBlank(false);  
		textQuartos.setBlankText("Este campo é obrigatório");
		textQuartos.setSize("78px", "21px");

		//Suites
		textSuites = new TextField("Suítes *", "suítes", 230);
		textSuites.setAllowBlank(false);
		textSuites.setBlankText("Este campo é obrigatório");
		textSuites.setHideLabel(true);
		textSuites.setSize("78px", "21px");
		Label suites = new Label("Suítes *:");
		rootPanel.add(suites, 308, 270);
		
		MultiFieldPanel quartosPanel = new MultiFieldPanel();  
		quartosPanel.addToRow(textQuartos,363);  
		quartosPanel.addToRow(textSuites,new ColumnLayoutData(1) );  
		formPanel.add(quartosPanel);
		
		//Banheiros
		textBanheiros = new TextField("Banheiros sociais *", "banheiros sociais", 230);
		textBanheiros.setAllowBlank(false); 
		textBanheiros.setBlankText("Este campo é obrigatório");
		formPanel.add(textBanheiros);
		textBanheiros.setSize("78px", "21px");
		
		//Tipo de imovel
		comboTipo = new ComboBox("Tipo do imóvel *", "tipo do imóvel", 110);
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
		formPanel.add(comboTipo);
		
		//Preco do imovel
		textPreco = new TextField("Preço em torno de (R$)", "bairro", 230);
		formPanel.add(textPreco);
		textPreco.setSize("146px", "21px");		
		
		//Nome da pessoa
		textNomePessoa = new TextField("Seu nome *", "seu nome", 230);
		textNomePessoa.setAllowBlank(false); 
		textNomePessoa.setBlankText("Este campo é obrigatório");
		formPanel.add(textNomePessoa);
		textNomePessoa.setSize("265px", "21px");
		
		//Email
		textEmail = new TextField("Email *", "email", 230);
		textEmail.setAllowBlank(false);
		textEmail.setBlankText("Este campo é obrigatório");
		textEmail.setVtype(VType.EMAIL);
		textEmail.setVtypeText("Digite um e-mail válido com o formato user@domain.com");
		formPanel.add(textEmail);
		textEmail.setSize("220px", "21px");		
		
		//Telefone
		textTelefone = new TextField("Telefone *", "telefone", 230);
		textTelefone.setAllowBlank(false); 
		textTelefone.setBlankText("Este campo é obrigatório");
		formPanel.add(textTelefone);
		textTelefone.setSize("150px", "21px");		
		
		//Adicionando Formulario
		rootPanel.add(formPanel, 17, 120);
		
		
		Image image1 = new Image("images/familia.png");
		rootPanel.add(image1, 650, 96);
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
				if(textRua.getText().equals("")){
					MessageBox.alert("O campo Nome da rua é obrigatório");
					return;
				}else if(textBairro.getText().equals("")){
					MessageBox.alert("O campo Bairro é obrigatório");
					return;
				}else if(textCidade.getText().equals("")){
					MessageBox.alert("O campo Cidade é obrigatório");
					return;
				}else if(textAreaConstruida.getText().equals("")){
					MessageBox.alert("O campo Área construída é obrigatório");
					return;
				}else if(textAreaTotal.getText().equals("")){
					MessageBox.alert("O campo Área total é obrigatório");
					return;
				}else if(textGaragem.getText().equals("")){
					MessageBox.alert("o campo Vagas na garagem é obrigatório");
					return;
				}else if(textQuartos.getText().equals("")){
					MessageBox.alert("O campo Quartos é obrigatório");
					return;
				}else if(textSuites.getText().equals("")){
					MessageBox.alert("O campo Suítes é obrigatório");
					return;
				}else if(textBanheiros.getText().equals("")){
					MessageBox.alert("O campo Banheiros é obrigatório");
					return;
				}else if(textNomePessoa.getText().equals("")){
					MessageBox.alert("O campo Seu nome é obrigatório");
					return;
				}else if(textEmail.getText().equals("")){
					MessageBox.alert("O campo Email é obrigatório");
					return;
				}else if(textTelefone.getText().equals("")){
					MessageBox.alert("O campo Telefone é obrigatório");
					return;
				}
					
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
						
				try{
					price = Float.parseFloat(textPreco.getText());
				}catch(Exception e){
					if(!textPreco.getText().equals("")){
						MessageBox.alert(message + "Preço");
						return;
					}
				}				

				state  = listEstado.getText();
				
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
	
	private String[][] getTipos() {  
		return new String[][]{  
				new String[]{"Casa", "casa", "1"},  
				new String[]{"Apartamento", "2"},  
				new String[]{"Terreno", "terreno", "3"},  
				new String[]{"Sala comercial", "4"},  
		};  
	}  
}
