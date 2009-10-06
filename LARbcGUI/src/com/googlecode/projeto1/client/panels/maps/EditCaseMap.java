package com.googlecode.projeto1.client.panels.maps;

import com.google.gwt.maps.client.event.MarkerDragEndHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.googlecode.projeto1.client.panels.manage.SelectedLocation;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class EditCaseMap extends MappingWindow{
	
	public EditCaseMap(){
		super();
		SelectedLocation.setLocation("");
		this.setTitle("Mapa");		
		this.addButton(getOkButton());
		myMap.clearOverlays();
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
	
	private MarkerOptions getMarkerOptions() {
		MarkerOptions options = MarkerOptions.newInstance();
		options.setDraggable(true);
		return options;
	}
	
	private Marker getMarker(LatLng point){
		Marker marker = new Marker(point, getMarkerOptions());
		marker.addMarkerDragEndHandler(getDragEndHandler(marker));				
		return marker;			
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
	
	public void clearMap(){
		SelectedLocation.setLocation("");		
		myMap.clearOverlays();
		setDefaultMap();
	}
	
	public void setLocation(String location){
		try{
			double lat = Double.parseDouble(location.split(" ")[0]);
			double lng = Double.parseDouble(location.split(" ")[1]);			
			LatLng pointLocation = LatLng.newInstance(lat, lng);
			Marker m = getMarker(pointLocation);
			myMap.addOverlay(m);			
			SelectedLocation.setLocation(location);
		}catch (Exception e) {
			MessageBox.alert("Não foi possível adicionar um ponto na localização " + location);
		}
	}

}
