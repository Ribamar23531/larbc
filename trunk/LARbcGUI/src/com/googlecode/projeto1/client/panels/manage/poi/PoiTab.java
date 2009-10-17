package com.googlecode.projeto1.client.panels.manage.poi;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RadioButton;

public class PoiTab extends AbsolutePanel{
	
	public PoiTab(){
		super();		
		this.setSize("433px", "342px");
		{
			HTML explanationHtml = new HTML("New HTML", true);
			explanationHtml.setText("Escolha qual tipo de ponto de interesse voc\u00EA deseja adicionar ou remover");
			this.add(explanationHtml, 5, 5);
		}
		{
			RadioButton rdbtnEscola = new RadioButton("POI", "Escola");
			this.add(rdbtnEscola, 5, 54);
		}
		{
			RadioButton rdbtnUniversidade = new RadioButton("POI", "Universidade");
			this.add(rdbtnUniversidade, 5, 86);
		}
		{
			RadioButton rdbtnViaPrincipalDe = new RadioButton("POI", "Via Principal de Acesso");
			this.add(rdbtnViaPrincipalDe, 5, 118);
		}
		{
			RadioButton rdbtnAreaVerde = new RadioButton("POI", "\u00C1rea Verde");
			this.add(rdbtnAreaVerde, 5, 150);
		}
		{
			RadioButton rdbtnShoppingCenter = new RadioButton("POI", "Shopping Center");
			this.add(rdbtnShoppingCenter, 5, 182);
		}
		{
			RadioButton rdbtnSetorIndustrial = new RadioButton("POI", "Setor Industrial");
			this.add(rdbtnSetorIndustrial, 5, 214);
		}
		{
			Button okButton = new Button("New button");
			okButton.setText("OK");
			this.add(okButton, 5, 246);
			okButton.setSize("195px", "34px");
		}
	}

}
