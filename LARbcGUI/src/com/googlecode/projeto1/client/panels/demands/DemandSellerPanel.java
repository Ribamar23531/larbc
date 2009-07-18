package com.googlecode.projeto1.client.panels.demands;

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
import com.googlecode.projeto1.client.panels.Util;
import com.googlecode.projeto1.client.panels.modality.ModalityPanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.FitLayout;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DemandSellerPanel extends Panel{
	
	private AbsolutePanel rootPanel;
	private Image pesquisarButton;
	private Image selectedPesquisarButton;
	private boolean isSelectedPesquisarButton;
	
	public DemandSellerPanel(){
		rootPanel = new AbsolutePanel();
		rootPanel.setSize("809px", "546px");
		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel, 0, 0);
		verticalPanel.setSize("440px", "59px");
		Image image = new Image("images/larbc.png");
		verticalPanel.add(image);
		image.setSize("70%", "100%");
		
		//Nome da Rua
		TextBox textBox = new TextBox();
		rootPanel.add(textBox, 91, 111);
		textBox.setSize("278px", "21px");
		Label rua = new Label("Nome da Rua:");
		rootPanel.add(rua, 17, 114);
		rua.setSize("127px", "18px");
		
		//Bairro
		TextBox textBox1 = new TextBox();
		rootPanel.add(textBox1, 59, 137);
		textBox1.setSize("265px", "21px");
		Label lblBairro = new Label("Bairro:");
		rootPanel.add(lblBairro, 17, 140);
		lblBairro.setSize("61px", "18px");
		
		//Cidade
		TextBox textBox2 = new TextBox();
		rootPanel.add(textBox2, 60, 163);
		textBox2.setSize("224px", "21px");
		Label lblCidade = new Label("Cidade:");
		rootPanel.add(lblCidade, 17, 166);
		lblCidade.setSize("61px", "18px");
		
		//Nome do imovel
		TextBox textBox3 = new TextBox();
		rootPanel.add(textBox3, 55, 194);
		textBox3.setSize("265px", "21px");
		Label lblNome = new Label("Nome:");
		rootPanel.add(lblNome, 17, 197);
		lblNome.setSize("61px", "18px");
		
		//Area construida
		TextBox textBox4 = new TextBox();
		rootPanel.add(textBox4, 108, 222);
		textBox4.setSize("108px", "21px");
		Label lblreaConstruda = new Label("Area Construida:");
		rootPanel.add(lblreaConstruda, 17, 225);
		lblreaConstruda.setSize("127px", "18px");
		
		//Vagas na garagem
		TextBox textBox5 = new TextBox();
		rootPanel.add(textBox5, 121, 248);
		textBox5.setSize("146px", "21px");
		Label lblVagasNaGaragem = new Label("Vagas na Garagem:");
		rootPanel.add(lblVagasNaGaragem, 17, 251);
		lblVagasNaGaragem.setSize("167px", "18px");
		
		//Quartos
		TextBox textBox6 = new TextBox();
		rootPanel.add(textBox6, 63, 277);
		textBox6.setSize("78px", "21px");
		Label lblQuartos = new Label("Quartos:");
		rootPanel.add(lblQuartos, 17, 280);
		lblQuartos.setSize("75px", "18px");
		
		//Tipo de imovel
		ListBox comboBox = new ListBox();
		rootPanel.add(comboBox, 98, 329);
		comboBox.setSize("205px", "21px");		
		Label lblTipoDeImvel = new Label("Tipo de imovel:");
		rootPanel.add(lblTipoDeImvel, 17, 334);
		lblTipoDeImvel.setSize("151px", "24px");
		
		//Preco do imovel
		TextBox textBox7 = new TextBox();
		rootPanel.add(textBox7, 120, 361);
		textBox7.setSize("146px", "21px");		
		Label lblPreoEmTorno = new Label("Preco em torno de:");
		rootPanel.add(lblPreoEmTorno, 17, 364);
		lblPreoEmTorno.setSize("151px", "24px");
		
		//Email
		TextBox textBox11 = new TextBox();
		rootPanel.add(textBox11, 52, 387);
		textBox11.setSize("220px", "21px");		
		Label lblEmail = new Label("Email:");
		rootPanel.add(lblEmail, 17, 392);
		lblEmail.setSize("151px", "24px");
		
		//Telefone
		TextBox textBox12 = new TextBox();
		rootPanel.add(textBox12, 63, 415);
		textBox12.setSize("150px", "21px");		
		Label lblTelefone = new Label("Telefone:");
		rootPanel.add(lblTelefone, 17, 418);
		lblTelefone.setSize("100px", "24px");
		
		//Estado
		ListBox listBox1 = new ListBox();
		rootPanel.add(listBox1, 312, 160);
		listBox1.setSize("57px", "21px");
		Label lblUf = new Label("UF:");
		rootPanel.add(lblUf, 292, 165);
		lblUf.setSize("34px", "18px");
		
		//Area total
		TextBox textBox8 = new TextBox();
		rootPanel.add(textBox8, 290, 222);
		textBox8.setSize("108px", "21px");
		Label lblreaTotal = new Label("Area Total:");
		rootPanel.add(lblreaTotal, 226, 225);
		lblreaTotal.setSize("91px", "18px");
		
		//Suites
		TextBox textBox9 = new TextBox();
		rootPanel.add(textBox9, 197, 277);
		textBox9.setSize("78px", "21px");
		Label lblSutes = new Label("Suites:");
		rootPanel.add(lblSutes, 156, 280);
		lblSutes.setSize("57px", "18px");

		//Banheiros
		TextBox textBox10 = new TextBox();
		rootPanel.add(textBox10, 113, 303);
		textBox10.setSize("78px", "21px");
		Label lblBanheirosSociais = new Label("Banheiros Sociais:");
		rootPanel.add(lblBanheirosSociais, 17, 306);
		lblBanheirosSociais.setSize("151px", "24px");
		
		Image image1 = new Image("images/familia.png");
		rootPanel.add(image1, 463, 96);
		image1.setSize("193px", "300px");
		
		createEntrarButton();

		Label lblPesquisaDeImoveis = new Label("CADASTRO DE IMOVEIS PARA VENDA");
		rootPanel.add(lblPesquisaDeImoveis, 17, 76);
		lblPesquisaDeImoveis.setSize("200px", "13px");
		
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
		selectedPesquisarButton = Util.createImage(Util.SELECTED_CADASTRAR_BUTTON_IMAGE);
		selectedPesquisarButton.setSize("11%", "5%");
		selectedPesquisarButton.addMouseListener(new MouseListenerAdapter(){			

			public void onMouseLeave(Widget arg0) {
				isSelectedPesquisarButton = false;
				buildButtonsPanel();			
			}
			
		});
		selectedPesquisarButton.addClickListener(new ClickListener(){
			public void onClick(Widget arg0) {
				PanelSwitcher.switchPanel(new ModalityPanel());
			}
		});
		
		buildButtonsPanel();
		
	}
	
	private void buildButtonsPanel() {
		if(!isSelectedPesquisarButton){
			rootPanel.add(pesquisarButton, 326, 423);
		}else{
			rootPanel.add(selectedPesquisarButton, 326, 423);
		}
	}

}
