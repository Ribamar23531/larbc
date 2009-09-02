package com.googlecode.projeto1.client.panels.manage;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerDragEndHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

public class MappingWindow extends Window{
	
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
		this.setResizable(true);
		this.setCloseAction(Window.HIDE);  
		this.setPlain(true);
		myMap = getMap();		
		RootPanel mapPanel = RootPanel.get("map_div");
		mapPanel.add(myMap);
		this.add(mapPanel);		
		this.setSize("650px", "500px");
		this.setResizable(false);
	}	

	private MapWidget getMap() {
		qteMarkers = 0;		
		final MapWidget map = new MapWidget(LatLng.newInstance(-7.22, -35.88), 13);		
		map.setSize("630px", "500px");
		map.setUIToDefault();
		map.setContinuousZoom(true);
		map.setScrollWheelZoomEnabled(true);
		map.addMapClickHandler(new MapClickHandler() {
			public void onClick(final MapClickEvent clickEvent) {
				if(qteMarkers == 0){
					qteMarkers++;
					Marker marker = new Marker(clickEvent.getLatLng(), getMarkerOptions());
					marker.addMarkerDragEndHandler(getDragEndHandler(marker));
					map.addOverlay(marker);
					getLocation(marker);
				}
			}

			private MarkerDragEndHandler getDragEndHandler(final Marker marker) {				
				return new MarkerDragEndHandler(){

					public void onDragEnd(MarkerDragEndEvent event) {
						getLocation(marker);
					}
					
				};
			}
			
			private void getLocation(Marker marker){
				String location = marker.getLatLng().toString();
				SelectedLocation.setLocation(location.substring(1, location.length() - 1));
			}

			private MarkerOptions getMarkerOptions() {
				MarkerOptions options = MarkerOptions.newInstance();
				options.setDraggable(true);
				return options;
			}
			
		});
		return map;
	}

	private Button getOkButton() {
		Button okButton = new Button("Ok");
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				if(SelectedLocation.getLocation().equals("")){
					MessageBox.alert("Favor ajustar coordenadas.");
				}else{
					hide();					
				}
			}

		});
		return okButton;
	}	

}
