package com.googlecode.projeto1.client.panels.maps;


import com.google.gwt.maps.client.event.MapClickHandler;
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
public class CreateCaseMap extends MappingWindow{
	
	public CreateCaseMap(){
		super();
		myMap.clearOverlays();
		setLocation();
		this.setTitle("Selecione o local do imóvel");		
		this.addButton(getOkButton());
		addMapEvent();
	}
	
	private void setLocation() {
		if(!SelectedLocation.getLocation().equals("")){
			setLocation(SelectedLocation.getLocation());
		}		
	}

	private void addMapEvent() {		
		myMap.addMapClickHandler(new MapClickHandler() {
			public void onClick(final MapClickEvent clickEvent) {
				if (SelectedLocation.getLocation().equals("")) {
					Marker marker = getMarker(clickEvent.getLatLng());
					SelectedLocation.setLocation(clickEvent.getLatLng().getLatitude() +
												" " + clickEvent.getLatLng().getLongitude());
					myMap.addOverlay(marker);
				}
			}
		});
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
		
	private void setLocation(String location){
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
