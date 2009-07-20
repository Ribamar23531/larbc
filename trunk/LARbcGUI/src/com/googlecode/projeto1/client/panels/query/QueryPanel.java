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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.panels.Util;
import com.googlecode.projeto1.client.panels.results.ResultsPanel;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.FitLayout;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class QueryPanel extends Panel{
	
	private AbsolutePanel rootPanel;
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
	private int businessType;
	private String state;
	private float totalArea;
	private int suite;
	private int bedroom;
	private TextBox textRua;
	private TextBox textBairro;
	private TextBox textCidade;
	private TextBox textNome;
	private TextBox textAreaConstruida;
	private TextBox textGaragem;
	private TextBox textQuartos;
	private ListBox comboTipo;
	private TextBox textPreco;
	private ListBox listTipoNegocio;
	private ListBox listEstado;
	private TextBox textAreaTotal;
	private TextBox textSuites;
	private TextBox textBanheiros;
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	private List<CaseBean> cases;
	
	public QueryPanel(){
		rootPanel = new AbsolutePanel();
		rootPanel.setSize("809px", "546px");
		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel, 0, 0);
		verticalPanel.setSize("440px", "59px");
		Image image = new Image("images/larbc.png");
		verticalPanel.add(image);
		image.setSize("70%", "100%");
		
		//Nome da Rua
		textRua = new TextBox();
		rootPanel.add(textRua, 91, 111);
		textRua.setSize("278px", "21px");
		Label rua = new Label("Nome da Rua:");
		rootPanel.add(rua, 17, 114);
		rua.setSize("127px", "18px");
		
		//Bairro
		textBairro = new TextBox();
		rootPanel.add(textBairro, 59, 137);
		textBairro.setSize("265px", "21px");
		Label lblBairro = new Label("Bairro:");
		rootPanel.add(lblBairro, 17, 140);
		lblBairro.setSize("61px", "18px");
		
		//Cidade
		textCidade = new TextBox();
		rootPanel.add(textCidade, 60, 163);
		textCidade.setSize("224px", "21px");
		Label lblCidade = new Label("Cidade:");
		rootPanel.add(lblCidade, 17, 166);
		lblCidade.setSize("61px", "18px");
		
		//Nome do imovel
		textNome = new TextBox();
		rootPanel.add(textNome, 55, 194);
		textNome.setSize("265px", "21px");
		Label lblNome = new Label("Nome:");
		rootPanel.add(lblNome, 17, 197);
		lblNome.setSize("61px", "18px");
		
		//Area construida
		textAreaConstruida = new TextBox();
		rootPanel.add(textAreaConstruida, 108, 222);
		textAreaConstruida.setSize("108px", "21px");
		Label lblreaConstruda = new Label("Area Construida:");
		rootPanel.add(lblreaConstruda, 17, 225);
		lblreaConstruda.setSize("127px", "18px");
		
		//Vagas na garagem
		textGaragem = new TextBox();
		rootPanel.add(textGaragem, 121, 248);
		textGaragem.setSize("146px", "21px");
		Label lblVagasNaGaragem = new Label("Vagas na Garagem:");
		rootPanel.add(lblVagasNaGaragem, 17, 251);
		lblVagasNaGaragem.setSize("167px", "18px");
		
		//Quartos
		textQuartos = new TextBox();
		rootPanel.add(textQuartos, 63, 277);
		textQuartos.setSize("78px", "21px");
		Label lblQuartos = new Label("Quartos:");
		rootPanel.add(lblQuartos, 17, 280);
		lblQuartos.setSize("75px", "18px");
		
		//Tipo de imovel
		comboTipo = new ListBox();
		rootPanel.add(comboTipo, 98, 329);
		comboTipo.setSize("205px", "21px");	
		Label lblTipoDeImvel = new Label("Tipo de imovel:");
		rootPanel.add(lblTipoDeImvel, 17, 334);
		lblTipoDeImvel.setSize("151px", "24px");
		
		//Preco do imovel
		textPreco = new TextBox();
		rootPanel.add(textPreco, 120, 361);
		textPreco.setSize("146px", "21px");		
		Label lblPreoEmTorno = new Label("Preco em torno de:");
		rootPanel.add(lblPreoEmTorno, 17, 364);
		lblPreoEmTorno.setSize("151px", "24px");
		
		//Tipo de negocio
		listTipoNegocio = new ListBox();
		rootPanel.add(listTipoNegocio, 108, 387);
		listTipoNegocio.setSize("166px", "21px");
		listTipoNegocio.addItem("Comprar");
		listTipoNegocio.addItem("Alugar");
		Label lblTipoDeNegcio = new Label("Tipo de negocio:");
		rootPanel.add(lblTipoDeNegcio, 17, 392);
		lblTipoDeNegcio.setSize("151px", "24px");
		
		//Estado
		listEstado = new ListBox();
		rootPanel.add(listEstado, 312, 160);
		listEstado.setSize("57px", "21px");
		PERSISTENCE_SERVICE.listEstados(new AsyncCallback<List<String>>() {
			
			public void onSuccess(List<String> states) {
				for (String state : states) {
					listEstado.addItem(state);
				}
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Os estados não puderam ser carregados do disco");
				
			}
		});
		Label lblUf = new Label("UF:");
		rootPanel.add(lblUf, 292, 165);
		lblUf.setSize("34px", "18px");
		
		//Area total
		textAreaTotal = new TextBox();
		rootPanel.add(textAreaTotal, 290, 222);
		textAreaTotal.setSize("108px", "21px");
		Label lblreaTotal = new Label("Area Total:");
		rootPanel.add(lblreaTotal, 226, 225);
		lblreaTotal.setSize("91px", "18px");
		
		//Suites
		textSuites = new TextBox();
		rootPanel.add(textSuites, 197, 277);
		textSuites.setSize("78px", "21px");
		Label lblSutes = new Label("Suites:");
		rootPanel.add(lblSutes, 156, 280);
		lblSutes.setSize("57px", "18px");

		//Banheiros
		textBanheiros = new TextBox();
		rootPanel.add(textBanheiros, 113, 303);
		textBanheiros.setSize("78px", "21px");
		Label lblBanheirosSociais = new Label("Banheiros Sociais:");
		rootPanel.add(lblBanheirosSociais, 17, 306);
		lblBanheirosSociais.setSize("151px", "24px");
		
		Image image1 = new Image("images/familia.png");
		rootPanel.add(image1, 463, 96);
		image1.setSize("193px", "300px");
		
		createEntrarButton();

		Label lblPesquisaDeImoveis = new Label("PESQUISA DE IMOVEIS");
		rootPanel.add(lblPesquisaDeImoveis, 17, 76);
		lblPesquisaDeImoveis.setSize("151px", "13px");
		
		this.add(rootPanel);
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
				street = textRua.getText();
				neighborhood = textBairro.getText();
				city = textCidade.getText();
				name = textNome.getText();
				builtArea = Float.parseFloat(textAreaConstruida.getText());
				garageSpace = Integer.parseInt(textGaragem.getText());
				bathroom = Integer.parseInt(textBanheiros.getText());
//				type;
				price = Float.parseFloat(textPreco.getText());
//				businessType;
//				state;
				totalArea = Float.parseFloat(textAreaTotal.getText());
				suite = Integer.parseInt(textSuites.getText());
				bedroom = Integer.parseInt(textQuartos.getText());
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
			rootPanel.add(pesquisarButton, 326, 423);
		}else{
			rootPanel.add(selectedPesquisarButton, 326, 423);
		}
	}

}
