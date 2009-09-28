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

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class MappingWindow extends Window{
	
	private MapWidget myMap;
	private int qteMarkers;	
	private boolean editable;
	private Marker marker;
	private static MappingWindow me =  null;
	private final LatLng CAMPINA_GRANDE_POINT = LatLng.newInstance(-7.22, -35.88);
	private final int ZOOM = 13;
	
	protected MappingWindow(boolean editable){
		super();
		SelectedLocation.setLocation("");
		this.editable = editable;
		if(editable){
			this.setTitle("Selecione o local do imóvel");			
		}else{
			this.setTitle("Mapa");
		}
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
	
	public static MappingWindow getInstance(boolean editable){
		if(me == null){
			me = new MappingWindow(editable);
		}
		me.clearMap();
		return me;
	}

	private MapWidget getMap() {
		qteMarkers = 0;		
		final MapWidget map = new MapWidget(CAMPINA_GRANDE_POINT, ZOOM);		
		map.setSize("630px", "500px");
		map.setUIToDefault();
		map.setContinuousZoom(true);
		map.setScrollWheelZoomEnabled(true);
		if(editable){
			map.addMapClickHandler(new MapClickHandler() {
				public void onClick(final MapClickEvent clickEvent) {
					if(qteMarkers == 0){
						qteMarkers++;
						marker = getMarker(clickEvent.getLatLng());						
						map.addOverlay(marker);
						setLocation(marker);
					}
				}					
				
				
				
			});			
		}
		return map;
	}
	
	private void setLocation(Marker marker){
		String location = 	marker.getLatLng().getLatitude() + 
							" " + marker.getLatLng().getLongitude();		
		SelectedLocation.setLocation(location);
	}
	
	private MarkerDragEndHandler getDragEndHandler(final Marker marker) {				
		return new MarkerDragEndHandler(){
			
			public void onDragEnd(MarkerDragEndEvent event) {
				setLocation(marker);
			}
			
		};
	}
	
	private MarkerOptions getMarkerOptions(boolean draggeble) {
		MarkerOptions options = MarkerOptions.newInstance();
		options.setDraggable(draggeble);
		return options;
	}
	
	private Marker getMarker(LatLng point){
		Marker marker = new Marker(point, getMarkerOptions(editable));
		if(editable){
			marker.addMarkerDragEndHandler(getDragEndHandler(marker));			
		}
		return marker;			
	}

	private Button getOkButton() {
		Button okButton = new Button("Ok");
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				if(SelectedLocation.getLocation().equals("") && editable){
					MessageBox.alert("Favor ajustar coordenadas.");
				}else{
					hide();					
				}
			}

		});
		return okButton;
	}
	
	public void clearMap(){
		SelectedLocation.setLocation("");
		qteMarkers = 0;
		this.myMap.clearOverlays();
		this.myMap.setCenter(CAMPINA_GRANDE_POINT);
		this.myMap.setZoomLevel(ZOOM);
	}
	
	public void setLocation(String location){
		try{
			double lat = Double.parseDouble(location.split(" ")[0]);
			double lng = Double.parseDouble(location.split(" ")[1]);			
			LatLng pointLocation = LatLng.newInstance(lat, lng);
			Marker m = getMarker(pointLocation);
			this.myMap.addOverlay(m);
			qteMarkers++;
			SelectedLocation.setLocation(location);
		}catch (Exception e) {
			MessageBox.alert("Não foi possível adicionar um ponto na localização " + location);
		}
	}

}
