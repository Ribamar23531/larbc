package com.googlecode.projeto1.client.panels.manage.poi;


import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.panels.maps.POIMap;
import com.gwtext.client.widgets.MessageBox;

public class PoiTab extends AbsolutePanel{
	
	private RadioButton rdbtnEscola;
	private RadioButton rdbtnUniversidade;
	private RadioButton rdbtnViaPrincipalDe;
	private RadioButton rdbtnAreaVerde;
	private RadioButton rdbtnShoppingCenter;
	private RadioButton rdbtnSetorIndustrial;
	private Button okButton;
	
	public PoiTab(){
		super();

		HTML explanationHtml = new HTML("New HTML", true);
		explanationHtml
				.setText("Escolha qual tipo de ponto de interesse voc\u00EA deseja adicionar ou remover");
		this.add(explanationHtml, 5, 5);

		rdbtnEscola = new RadioButton("POI", "Escola");
		this.add(rdbtnEscola, 5, 54);

		rdbtnUniversidade = new RadioButton("POI", "Universidade");
		this.add(rdbtnUniversidade, 5, 86);

		rdbtnViaPrincipalDe = new RadioButton("POI", "Via Principal de Acesso");
		this.add(rdbtnViaPrincipalDe, 5, 118);

		rdbtnAreaVerde = new RadioButton("POI", "\u00C1rea Verde");
		this.add(rdbtnAreaVerde, 5, 150);

		rdbtnShoppingCenter = new RadioButton("POI", "Shopping Center");
		this.add(rdbtnShoppingCenter, 5, 182);

		rdbtnSetorIndustrial = new RadioButton("POI", "Setor Industrial");
		this.add(rdbtnSetorIndustrial, 5, 214);
		
		okButton = getOkButton();
		this.add(okButton, 5, 246);

	}
	
	private void showPointKindMap(Widget widget){
		POIMap poiMap = new POIMap();
		poiMap.setPointKind();
		poiMap.show(widget.getElement());
	}
	
	private void showLineKindMap(Widget widget){
		POIMap poiMap = new POIMap();
		poiMap.setLineKind();
		poiMap.show(widget.getElement());
	}
	
	private void showAreaKindMap(Widget widget){
		POIMap poiMap = new POIMap();
		poiMap.setAreaKind();
		poiMap.show(widget.getElement());
	}

	private Button getOkButton() {
		Button button = new Button("OK");
		button.addClickListener(new ClickListener() {

			public void onClick(Widget arg0) {				
				if(rdbtnEscola.isChecked()){
					showPointKindMap(arg0);
				}else if(rdbtnUniversidade.isChecked()){
					showPointKindMap(arg0);
				}else if(rdbtnViaPrincipalDe.isChecked()){
					showLineKindMap(arg0);
				}else if(rdbtnShoppingCenter.isChecked()){
					showPointKindMap(arg0);
				}else if(rdbtnAreaVerde.isChecked()){
					showAreaKindMap(arg0);
				}else if(rdbtnSetorIndustrial.isChecked()){
					
				}else{
					MessageBox.alert("Favor selecionar alguma das opções");
				}
				
			}
			
		});
		button.setSize("195px", "34px");
		return button;
	}

}
