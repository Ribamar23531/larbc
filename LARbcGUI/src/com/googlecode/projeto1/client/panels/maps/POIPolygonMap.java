package com.googlecode.projeto1.client.panels.maps;


import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.projeto1.client.beans.PolygonBean;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
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
		
	private Polygon polygon;
	private ArrayList<LatLng> points;
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	
	public POIPolygonMap(){
		super();
		myMap.clearOverlays();
		points = new ArrayList<LatLng>();
		polygon = null;
		this.setTitle("Pontos de Interesse");		
		this.addButton(getSaveButton());
		this.addButton(getUndoButton());
		this.addMapEvent();
		loadPolygons();
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
				if(polygon == null){
					MessageBox.alert("Favor demarque uma área ou feche a janela");
				}else{
					savePolygon();
					hide();
				}
			}

		});
		return okButton;
	}
	
	private void savePolygon() {
		PERSISTENCE_SERVICE.savePolygon(getPolygon(), new AsyncCallback<Boolean>() {
			
			public void onSuccess(Boolean result) {
				if(result.booleanValue()){
					MessageBox.alert("Area salva com sucesso");
				}else{
					MessageBox.alert("Não foi possível salvar a área");
				}
				
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Ouve um erro ao salvar. Erro: " + arg0);
				
			}
		});
	}
	
	private void loadPolygons() {
		PERSISTENCE_SERVICE.getPolygons(new AsyncCallback<List<PolygonBean>>() {
			
			public void onSuccess(List<PolygonBean> polygons) {
				for (PolygonBean polygon : polygons) {
					LatLng[] points = new LatLng[polygon.getLocation().size()];
					int index = 0;
					for (String location : polygon.getLocation()) {
						 points[index] = getPoint(location);
						 index++;
					}
					Polygon p = new Polygon(points);
					p.setEditingEnabled(false);
					myMap.addOverlay(p);
				}
				
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Não foi possível carregar as áreas verdes da base de dados");
				
			}
		});
		
	}
	
	private LatLng getPoint(String location){
		String[] aux = location.split(",");
		double lat = Double.parseDouble(aux[0].substring(1, aux[0].length()));
		double lng = Double.parseDouble(aux[1].substring(1, aux[1].length() - 1));
		return LatLng.newInstance(lat, lng);
	}
	
	private PolygonBean getPolygon() {
		List<String> points = new ArrayList<String>();
		for (int i = 0; i < polygon.getVertexCount(); i++) {
			points.add(polygon.getVertex(i).toString());
		}
		return new PolygonBean(points);
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
