package com.googlecode.projeto1.client.panels.manage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

public class MappingWindow extends Window{
	
	private static final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	private MapWidget myMap;
	private int qteMarkers;
	
	public MappingWindow(){
		super();	
		this.setTitle("Selecione o local do imóvel");		
		this.setClosable(true);
		this.setPlain(true);		
		this.setPaddings(5);  
		this.setButtonAlign(Position.CENTER);
		this.addButton(getOkButton());
		this.addButton(getCancelButton());
		this.setResizable(true);
		this.setCloseAction(Window.HIDE);  
		this.setPlain(true);
		qteMarkers = 0;
		myMap = getMap();		
		RootPanel mapPanel = RootPanel.get("map_div");
		mapPanel.add(myMap);
		this.add(mapPanel);		
		this.setSize("650px", "500px");
		this.setResizable(false);
	}	

	private MapWidget getMap() {
		MapWidget map = new MapWidget(LatLng.newInstance(-7.22, -35.88), 13);
		Marker marker;
		map.setSize("630px", "500px");
		map.setUIToDefault();
		map.setContinuousZoom(true);
		map.setScrollWheelZoomEnabled(true);
		map.addMapClickHandler(new MapClickHandler() {
			public void onClick(final MapClickEvent clickEvent) {
				

			}
		});
		return map;
	}

	private Button getOkButton() {
		Button okButton = new Button("Ok");
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				
			}

		});
		return okButton;
	}
	
	private Button getCancelButton() {
		Button cancelButton = new Button("Cancelar");
		cancelButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				
			}

		});
		return cancelButton;
	}

}
