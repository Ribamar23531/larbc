package com.googlecode.projeto1.client.panels.manage;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.Label;

public class ManagePanel extends Panel{
	
	private AbsolutePanel panel;
	
	public ManagePanel(){
		super();
		panel = new AbsolutePanel();		
		{
			TabPanel createTab = new TabPanel();
			createTab.setAnimationEnabled(true);
			panel.add(createTab, 5, 5);
			createTab.setSize("442px", "273px");
			{
				AbsolutePanel createPanel = new AbsolutePanel();
				createTab.add(createPanel, "Criar", false);
				createPanel.setSize("428px", "224px");
				{
					Label stateLabel = new Label("Estado:");
					createPanel.add(stateLabel, 308, 5);
				}
				{
					Label cityLabel = new Label("Cidade:");
					createPanel.add(cityLabel, 5, 5);
				}
				{
					Label bairroLabel = new Label("Bairro:");
					createPanel.add(bairroLabel, 5, 31);
				}
				{
					Label streetLabel = new Label("Rua:");
					createPanel.add(streetLabel, 5, 57);
				}
				{
					TextBox cityTextBox = new TextBox();
					createPanel.add(cityTextBox, 65, 5);
					cityTextBox.setSize("238px", "22px");
				}
				{
					TextBox textBox = new TextBox();
					createPanel.add(textBox, 65, 31);
					textBox.setSize("238px", "22px");
				}
				{
					TextBox textBox = new TextBox();
					createPanel.add(textBox, 65, 57);
					textBox.setSize("358px", "22px");
				}
				{
					Label numberLabel = new Label("N\u00FAmero:");
					createPanel.add(numberLabel, 308, 31);
				}
				{
					TextBox numberTextBox = new TextBox();
					createPanel.add(numberTextBox, 374, 31);
					numberTextBox.setSize("49px", "22px");
				}
				{
					ListBox comboBox = new ListBox();
					createPanel.add(comboBox, 374, 5);
					comboBox.setSize("49px", "27px");
				}
				{
					Label nameLabel = new Label("Nome:");
					createPanel.add(nameLabel, 5, 83);
				}
				{
					TextBox nameTextBox = new TextBox();
					createPanel.add(nameTextBox, 65, 84);
					nameTextBox.setSize("358px", "22px");
				}
				{
					Label areaConstruidaLabel = new Label("Area Constru\u00EDda:");
					createPanel.add(areaConstruidaLabel, 5, 111);
				}
				{
					TextBox areaConstruidaTextBox = new TextBox();
					createPanel.add(areaConstruidaTextBox, 157, 111);
					areaConstruidaTextBox.setSize("54px", "22px");
				}
				{
					Label areaTotalLabel = new Label("Area Total:");
					createPanel.add(areaTotalLabel, 235, 111);
				}
				{
					TextBox areaTotalTextBox = new TextBox();
					createPanel.add(areaTotalTextBox, 369, 111);
					areaTotalTextBox.setSize("54px", "22px");
				}
				{
					Label garageLabel = new Label("Vagas na Garagem:");
					createPanel.add(garageLabel, 5, 137);
				}
				{
					TextBox garageTextBox = new TextBox();
					createPanel.add(garageTextBox, 157, 138);
					garageTextBox.setSize("54px", "22px");
				}
				{
					Label roomsLabel = new Label("Qte de Quartos:");
					createPanel.add(roomsLabel, 235, 137);
				}
				{
					TextBox qteRoomsTextBox = new TextBox();
					createPanel.add(qteRoomsTextBox, 369, 138);
					qteRoomsTextBox.setSize("54px", "22px");
				}
				{
					Label qteSuitesLabel = new Label("Qte de Su\u00EDtes:");
					createPanel.add(qteSuitesLabel, 5, 163);
				}
				{
					TextBox qteSuitesTextBox = new TextBox();
					createPanel.add(qteSuitesTextBox, 157, 163);
					qteSuitesTextBox.setSize("54px", "22px");
				}
				{
					Label bathroomsLabel = new Label("Qte de Banheiros:");
					createPanel.add(bathroomsLabel, 235, 163);
				}
				{
					TextBox qteBathroomsTextBox = new TextBox();
					createPanel.add(qteBathroomsTextBox, 369, 163);
					qteBathroomsTextBox.setSize("54px", "22px");
				}
				{
					Label businesTypeLabel = new Label("Tipo de neg\u00F3cio:");
					createPanel.add(businesTypeLabel, 5, 189);
				}
				{
					ListBox typeComboBox = new ListBox();
					createPanel.add(typeComboBox, 157, 190);
					typeComboBox.setSize("54px", "27px");
				}
				{
					Label priceLabel = new Label("Pre\u00E7o: R$");
					createPanel.add(priceLabel, 235, 189);
				}
				{
					TextBox priceTextBox = new TextBox();
					createPanel.add(priceTextBox, 338, 189);
					priceTextBox.setSize("85px", "22px");
				}
			}
		}
		this.add(panel);
		this.setFrame(true);
	}

}
