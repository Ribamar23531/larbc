package com.googlecode.projeto1.client.panels.manage.demandTab;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.projeto1.client.beans.DemandBean;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class EditWindowPanel extends Panel{
	
	private AbsolutePanel panel;
	private TextBox estadoTextBox;
	private TextBox cidadeTextBox;
	private TextBox bairroTextBox;
	private TextBox ruaTextBox;
	private TextBox numeroTextBox;
	private TextBox nomeTextBox;
	private TextBox areaConstruidaTextBox;
	private TextBox areaTotalTextBox;
	private TextBox garagemTextBox;
	private TextBox quartosTextBox;
	private TextBox suitesTextBox;
	private TextBox banheirosTextBox;
	private ListBox tipoTextBox;
	private TextBox precoTextBox;
	private TextBox nomeClienteTextBox;
	private TextBox emailTextBox;
	private ListBox moderadoListBox;
	private ListBox tipoNegociolistBox;
	private TextBox telefoneTextBox;

	public EditWindowPanel(DemandBean demand){
		panel = new AbsolutePanel();
		
		Label cidadeLabel = new Label("Cidade:");
		panel.add(cidadeLabel, 5, 5);
		cidadeTextBox = new TextBox();
		panel.add(cidadeTextBox, 100, 5);
		cidadeTextBox.setSize("160px", "22px");
		cidadeTextBox.setText(demand.getCidade());
		cidadeTextBox.setEnabled(false);
		
		Label estadoLabel = new Label("Estado:");
		panel.add(estadoLabel, 280, 5);
		estadoTextBox = new TextBox();
		panel.add(estadoTextBox, 360, 5);
		estadoTextBox.setSize("30px", "22px");
		estadoTextBox.setText(demand.getEstado());
		estadoTextBox.setEnabled(false);
		
		Label bairroLabel = new Label("Bairro:");
		panel.add(bairroLabel, 5, 35);
		bairroTextBox = new TextBox();
		panel.add(bairroTextBox, 100, 35);
		bairroTextBox.setSize("160px", "22px");
		bairroTextBox.setText(demand.getBairro());
			
		Label nomeLabel = new Label("Nome do imóvel:");
		panel.add(nomeLabel, 280, 35);
		nomeTextBox = new TextBox();
		panel.add(nomeTextBox, 360, 35);
		nomeTextBox.setSize("160px", "22px");
		nomeTextBox.setText(demand.getNome());
		
		Label ruaLabel = new Label("Rua:");
		panel.add(ruaLabel, 5, 65);
		ruaTextBox = new TextBox();
		panel.add(ruaTextBox, 100, 65);
		ruaTextBox.setSize("160px", "22px");
		ruaTextBox.setText(demand.getRua());
		
		Label numeroLabel = new Label("Número:");
		panel.add(numeroLabel, 280, 65);
		numeroTextBox = new TextBox();
		panel.add(numeroTextBox, 360, 65);
		numeroTextBox.setSize("30px", "22px");
		numeroTextBox.setText("" + demand.getNumero());
		
		Label areaConstruidaLabel = new Label("Área construída:");
		panel.add(areaConstruidaLabel, 5, 95);
		areaConstruidaTextBox = new TextBox();
		panel.add(areaConstruidaTextBox, 100, 95);
		areaConstruidaTextBox.setSize("130px", "22px");
		areaConstruidaTextBox.setText("" + demand.getAreaConstruida());
		Label unidadeAreaConstruida = new Label("m²");
		panel.add(unidadeAreaConstruida, 232, 95);
		
		Label areaTotalLabel = new Label("Área total:");
		panel.add(areaTotalLabel, 280, 95);
		areaTotalTextBox = new TextBox();
		panel.add(areaTotalTextBox, 360, 95);
		areaTotalTextBox.setSize("130px", "22px");
		areaTotalTextBox.setText("" + demand.getAreaTotal());
		Label unidadeAreaTotal = new Label("m²");
		panel.add(unidadeAreaTotal, 492, 95);
		
		Label garagemLabel = new Label("Vagas na garagem:");
		panel.add(garagemLabel, 5, 125);
		garagemTextBox = new TextBox();
		panel.add(garagemTextBox, 100, 125);
		garagemTextBox.setSize("30px", "22px");
		garagemTextBox.setText("" + demand.getVagasGaragem());
		
		Label banheirosLabel = new Label("Banheiros:");
		panel.add(banheirosLabel, 280, 125);
		banheirosTextBox = new TextBox();
		panel.add(banheirosTextBox, 360, 125);
		banheirosTextBox.setSize("30px", "22px");
		banheirosTextBox.setText("" + demand.getBanheiros());
		
		Label quartosLabel = new Label("Quartos:");
		panel.add(quartosLabel, 5, 155);
		quartosTextBox = new TextBox();
		panel.add(quartosTextBox, 100, 155);
		quartosTextBox.setSize("30px", "22px");
		quartosTextBox.setText("" + demand.getQuartos());
		
		Label suitesLabel = new Label("Suítes:");
		panel.add(suitesLabel, 280, 155);
		suitesTextBox = new TextBox();
		panel.add(suitesTextBox, 360, 155);
		suitesTextBox.setSize("30px", "22px");
		suitesTextBox.setText("" + demand.getSuites());
		
		Label tipoLabel = new Label("Tipo:");
		panel.add(tipoLabel, 5, 185);
		tipoTextBox = new ListBox();
		panel.add(tipoTextBox, 100, 185);
		tipoTextBox.setSize("160px", "22px");
		tipoTextBox.addItem("Casa");
		tipoTextBox.addItem("Apartamento");
		tipoTextBox.addItem("Terreno");
		tipoTextBox.addItem("Sala Comercial");
		tipoTextBox.setItemSelected(demand.getTipoNegocio(), true);

		
		Label precoLabel = new Label("Preço (R$):");
		panel.add(precoLabel, 280, 185);
		precoTextBox = new TextBox();
		panel.add(precoTextBox, 360, 185);
		precoTextBox.setSize("160px", "22px");
		precoTextBox.setText("" + demand.getPreco());
		
		Label nomeClienteLabel = new Label("Nome do cliente:");
		panel.add(nomeClienteLabel, 5, 215);
		nomeClienteTextBox = new TextBox();
		panel.add(nomeClienteTextBox, 100, 215);
		nomeClienteTextBox.setSize("420px", "22px");
		nomeClienteTextBox.setText(demand.getNomeCliente());
		
		Label emailLabel = new Label("E-mail:");
		panel.add(emailLabel, 5, 245);
		emailTextBox = new TextBox();
		panel.add(emailTextBox, 100, 245);
		emailTextBox.setSize("160px", "22px");
		emailTextBox.setText(demand.getEmailCliente());

		Label telefoneLabel = new Label("Telefone:");
		panel.add(telefoneLabel, 280, 245);
		telefoneTextBox = new TextBox();
		panel.add(telefoneTextBox, 360, 245);
		telefoneTextBox.setSize("160px", "22px");
		telefoneTextBox.setText(demand.getTelefone());
		
		Label moderadoLabel = new Label("Moderado:");
		panel.add(moderadoLabel, 5, 275);
		moderadoListBox = new ListBox();
		panel.add(moderadoListBox, 100, 275);
		moderadoListBox.setSize("160px", "22px");
		moderadoListBox.addItem("Sim");
		moderadoListBox.addItem("Não");
		if(demand.isJahModerado()){
			moderadoListBox.setItemSelected(0, true);
		}else{
			moderadoListBox.setItemSelected(1, true);
		}
		
		Label tipoNegocioLabel = new Label("Tipo de negócio:");
		panel.add(tipoNegocioLabel, 280, 275);
		tipoNegociolistBox = new ListBox();
		panel.add(tipoNegociolistBox, 360, 275);
		tipoNegociolistBox.setSize("160px", "22px");
		tipoNegociolistBox.addItem("Vender");
		tipoNegociolistBox.addItem("Alugar");
		tipoNegociolistBox.setItemSelected(demand.getTipoNegocio(), true);

		this.add(panel);
		this.setSize("570px", "390px");
		panel.setSize("550px", "370px");
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}
	

	public String getEstadoTextBox() {
		return estadoTextBox.getText();
	}

	public String getCidadeTextBox() {
		return cidadeTextBox.getText();
	}

	public String getBairroTextBox() {
		return bairroTextBox.getText();
	}

	public String getRuaTextBox() {
		return ruaTextBox.getText();
	}

	public Integer getNumeroTextBox() {
		return Integer.parseInt(numeroTextBox.getText());
	}

	public String getNomeTextBox() {
		return nomeTextBox.getText();
	}

	public Float getAreaConstruidaTextBox() {
		return Float.parseFloat(areaConstruidaTextBox.getText());
	}

	public Float getAreaTotalTextBox() {
		return Float.parseFloat(areaTotalTextBox.getText());
	}

	public Integer getGaragemTextBox() {
		return Integer.parseInt(garagemTextBox.getText());
	}

	public Integer getQuartosTextBox() {
		return Integer.parseInt(quartosTextBox.getText());
	}

	public Integer getSuitesTextBox() {
		return Integer.parseInt(suitesTextBox.getText());
	}

	public Integer getBanheirosTextBox() {
		return Integer.parseInt(banheirosTextBox.getText());
	}

	public String getTipoTextBox() {
		int index = tipoTextBox.getSelectedIndex();
		return tipoTextBox.getItemText(index);
	}

	public Float getPrecoTextBox() {
		return Float.parseFloat(precoTextBox.getText());
	}

	public String getNomeClienteTextBox() {
		return nomeClienteTextBox.getText();
	}

	public String getEmailTextBox() {
		return emailTextBox.getText();
	}

	public String getModeradoListBox() {
		int index = moderadoListBox.getSelectedIndex();
		return moderadoListBox.getItemText(index);
	}

	public String getTipoNegociolistBox() {
		int index = tipoNegociolistBox.getSelectedIndex();
		return tipoNegociolistBox.getItemText(index);
	}

	public String getTelefoneTextBox() {
		return telefoneTextBox.getText();
	}

}
