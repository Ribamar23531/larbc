package com.googlecode.projeto1.client.panels.maps;


import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Polygon;
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
public class POIPolygonMap extends MappingWindow{
		
	private int qtePoints;	
	private Polygon polygon;
	private LatLng[] points;
	private final int MAX_POINTS = 50;
	
	public POIPolygonMap(){
		super();
		myMap.clearOverlays();
		points = new LatLng[MAX_POINTS];
		this.polygon = new Polygon(points);		
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
					myMap.removeOverlay(polygon);
					polygon = new Polygon(points);
					myMap.addOverlay(polygon);
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
