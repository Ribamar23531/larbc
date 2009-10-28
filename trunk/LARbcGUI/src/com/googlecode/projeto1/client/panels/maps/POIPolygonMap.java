package com.googlecode.projeto1.client.panels.maps;


import java.util.ArrayList;

import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Polygon;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class POIPolygonMap extends MappingWindow{
		
	private Polygon polygon;
	private ArrayList<LatLng> points;
	
	public POIPolygonMap(){
		super();
		myMap.clearOverlays();
		points = new ArrayList<LatLng>();
		polygon = null;
		this.setTitle("Pontos de Interesse");		
		this.addButton(getSaveButton());
		this.addButton(getUndoButton());
		this.addMapEvent();
	}

	private void addMapEvent() {
		myMap.addMapClickHandler(new MapClickHandler() {
			public void onClick(final MapClickEvent clickEvent) {
				points.add(clickEvent.getLatLng());
				if(polygon != null){
					myMap.removeOverlay(polygon);
				}
				polygon = new Polygon(getPoints());
				myMap.addOverlay(polygon);
			}
			
		});
	}
	
	private LatLng[] getPoints() {
		LatLng[] pointsArray = new LatLng[points.size()];
		for (int i = 0; i < points.size(); i++) {
			pointsArray[i] = points.get(i);
		}
		return pointsArray;
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
	
	private Button getUndoButton() {
		Button okButton = new Button("Desfazer");
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				if(polygon != null && points.size() > 0){
					points.remove(points.size() - 1);
					myMap.removeOverlay(polygon);
					polygon = new Polygon(getPoints());
					myMap.addOverlay(polygon);
				}
			}

		});
		return okButton;
	}
	

}
