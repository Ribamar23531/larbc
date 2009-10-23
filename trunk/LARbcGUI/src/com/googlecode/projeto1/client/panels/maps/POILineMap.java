package com.googlecode.projeto1.client.panels.maps;


import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Polyline;
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
public class POILineMap extends MappingWindow{
	
	private int qtePoints;
	private Polyline line;	
	private LatLng[] points;
	private final int MAX_POINTS = 50;
	
	public POILineMap(){
		super();
		myMap.clearOverlays();		
		this.line = new Polyline(new LatLng[20]);		
		points = new LatLng[MAX_POINTS];
		qtePoints = 0;
		this.setTitle("Pontos de Interesse");		
		this.addButton(getSaveButton());	
		this.addMapEvent();
	}

	private void addMapEvent() {
		myMap.addMapClickHandler(new MapClickHandler() {
			public void onClick(final MapClickEvent clickEvent) {
				if (qtePoints >= MAX_POINTS) {
					MessageBox.alert("Você atingiu a quantidade máxima de pontos");
				} else {
					points[qtePoints] = clickEvent.getLatLng();
					qtePoints++;
					// myMap.removeOverlay(line);
					myMap.clearOverlays();
					line = new Polyline(points);
					myMap.addOverlay(line);
					System.out.println(this.hashCode());
				}

			}
		});
	}	

	private Button getSaveButton() {
		Button okButton = new Button("Salvar");
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				hide();
			}

		});
		return okButton;
	}		
	

}
