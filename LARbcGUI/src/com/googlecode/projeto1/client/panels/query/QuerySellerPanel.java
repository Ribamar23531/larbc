package com.googlecode.projeto1.client.panels.query;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.FitLayout;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class QuerySellerPanel extends Panel{
	
	private AbsolutePanel rootPanel;
	public QuerySellerPanel(){
		rootPanel = new AbsolutePanel();
		rootPanel.setSize("809px", "546px");
		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel, 0, 0);
		verticalPanel.setSize("440px", "59px");
		Image image = new Image("images/larbc.png");
		verticalPanel.add(image);
		image.setSize("171px", "83px");
		
		TextBox textBox = new TextBox();
		rootPanel.add(textBox, 91, 111);
		textBox.setSize("278px", "21px");
		
		TextBox textBox1 = new TextBox();
		rootPanel.add(textBox1, 63, 137);
		textBox1.setSize("265px", "21px");
		
		TextBox textBox2 = new TextBox();
		rootPanel.add(textBox2, 63, 163);
		textBox2.setSize("224px", "21px");
		
		TextBox textBox3 = new TextBox();
		rootPanel.add(textBox3, 63, 196);
		textBox3.setSize("265px", "21px");
		
		TextBox textBox4 = new TextBox();
		rootPanel.add(textBox4, 113, 222);
		textBox4.setSize("108px", "21px");
		
		TextBox textBox5 = new TextBox();
		rootPanel.add(textBox5, 121, 248);
		textBox5.setSize("146px", "21px");
		
		TextBox textBox6 = new TextBox();
		rootPanel.add(textBox6, 63, 277);
		textBox6.setSize("78px", "21px");
		
		ListBox comboBox = new ListBox();
		rootPanel.add(comboBox, 105, 329);
		comboBox.setSize("205px", "21px");
		
		TextBox textBox7 = new TextBox();
		rootPanel.add(textBox7, 134, 361);
		textBox7.setSize("146px", "21px");
		
		ListBox listBox = new ListBox();
		rootPanel.add(listBox, 124, 387);
		listBox.setSize("166px", "21px");
		
		Label rua = new Label("Nome da Rua:");
		rootPanel.add(rua, 17, 114);
		rua.setSize("127px", "18px");
		
		Label lblBairro = new Label("Bairro:");
		rootPanel.add(lblBairro, 17, 140);
		lblBairro.setSize("61px", "18px");
		
		Label lblCidade = new Label("Cidade:");
		rootPanel.add(lblCidade, 17, 166);
		lblCidade.setSize("61px", "18px");
		
		Label lblUf = new Label("UF:");
		rootPanel.add(lblUf, 292, 166);
		lblUf.setSize("34px", "18px");
		
		ListBox listBox1 = new ListBox();
		rootPanel.add(listBox1, 312, 157);
		listBox1.setSize("57px", "21px");
		
		Label lblNome = new Label("Nome:");
		rootPanel.add(lblNome, 17, 195);
		lblNome.setSize("61px", "18px");
		
		Label lblreaConstruda = new Label("Área Construída:");
		rootPanel.add(lblreaConstruda, 17, 225);
		lblreaConstruda.setSize("127px", "18px");
		
		Label lblreaTotal = new Label("Área Total:");
		rootPanel.add(lblreaTotal, 226, 225);
		lblreaTotal.setSize("91px", "18px");
		
		TextBox textBox8 = new TextBox();
		rootPanel.add(textBox8, 294, 222);
		textBox8.setSize("108px", "21px");
		
		Label lblVagasNaGaragem = new Label("Vagas na Garagem:");
		rootPanel.add(lblVagasNaGaragem, 17, 251);
		lblVagasNaGaragem.setSize("167px", "18px");
		
		Label lblQuartos = new Label("Quartos:");
		rootPanel.add(lblQuartos, 17, 280);
		lblQuartos.setSize("75px", "18px");
		
		Label lblSutes = new Label("Suítes:");
		rootPanel.add(lblSutes, 156, 280);
		lblSutes.setSize("57px", "18px");
		
		TextBox textBox9 = new TextBox();
		rootPanel.add(textBox9, 204, 277);
		textBox9.setSize("78px", "21px");
		
		Label lblBanheirosSociais = new Label("Banheiros Sociais:");
		rootPanel.add(lblBanheirosSociais, 17, 303);
		lblBanheirosSociais.setSize("151px", "24px");
		
		TextBox textBox10 = new TextBox();
		rootPanel.add(textBox10, 113, 303);
		textBox10.setSize("78px", "21px");
		
		Label lblTipoDeImvel = new Label("Tipo de imóvel:");
		rootPanel.add(lblTipoDeImvel, 17, 332);
		lblTipoDeImvel.setSize("151px", "24px");
		
		Label lblPreoEmTorno = new Label("Preço em torno de:");
		rootPanel.add(lblPreoEmTorno, 17, 361);
		lblPreoEmTorno.setSize("151px", "24px");
		
		Label lblTipoDeNegcio = new Label("Tipo de neg\u00F3cio:");
		rootPanel.add(lblTipoDeNegcio, 17, 387);
		lblTipoDeNegcio.setSize("151px", "24px");
		
		Image image1 = new Image("images/familia.png");
		rootPanel.add(image1, 463, 96);
		image1.setSize("193px", "300px");
		
		Button button = new Button("New Button");
		button.setText("PESQUISAR");
		button.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
			}
		});
		button.setSize("117px", "34px");
		rootPanel.add(button, 326, 423);

		this.add(rootPanel);
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}
}
