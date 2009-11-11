package com.googlecode.projeto1.client.panels.maps;


import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.PolylineMouseOutHandler;
import com.google.gwt.maps.client.event.PolylineMouseOverHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.projeto1.client.beans.LineBean;
import com.googlecode.projeto1.client.beans.Type;
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
public class POILineMap extends MappingWindow{
	
	private Polyline line;
	private boolean mouseOverLine;
	private Type lineType;
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	
	public POILineMap(Type lineType){
		super();
		mouseOverLine = false;
		this.line = null;
		this.lineType = lineType;
		this.setTitle("Pontos de Interesse");		
		this.addButton(getSaveButton());
		this.addMapEvent();
		loadLines();
	}

	private PolylineMouseOverHandler getPolylineMouseOverHandler() {
		return new PolylineMouseOverHandler(){

			public void onMouseOver(PolylineMouseOverEvent event) {
				mouseOverLine = true;
				
			}
			
		};
	}
	
	private PolylineMouseOutHandler getPolylineMouseOutHandler() {
		return new PolylineMouseOutHandler(){

			public void onMouseOut(PolylineMouseOutEvent event) {
				mouseOverLine = false;				
			}
			
		};
	}	

	private void addMapEvent() {
		myMap.addMapClickHandler(new MapClickHandler() {
			public void onClick(final MapClickEvent clickEvent) {
				if(line == null){
					LatLng[] array = new LatLng[1];
					array[0] = clickEvent.getLatLng();
					line = new Polyline(array);
					line.addPolylineMouseOverHandler(getPolylineMouseOverHandler());
					line.addPolylineMouseOutHandler(getPolylineMouseOutHandler());
					myMap.addOverlay(line);
					line.setEditingEnabled(true);
				}
				if(!mouseOverLine){
					line.insertVertex(line.getVertexCount(), clickEvent.getLatLng());					
				}
			}			
			
		});
	}	

	private Button getSaveButton() {
		Button okButton = new Button("Salvar");
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				if(line == null){
					MessageBox.alert("Favor crie uma linha ou feche a janela");
				}else{
					saveLine();
					hide();
				}
			}		

		});
		return okButton;
	}
	
	private LineBean getLineBean() {
		List<Double> latitudes = new ArrayList<Double>();
		List<Double> longitudes = new ArrayList<Double>();
		for (int i = 0; i < line.getVertexCount(); i++) {
			latitudes.add(line.getVertex(i).getLatitude());
			longitudes.add(line.getVertex(i).getLongitude());
		}
		return new LineBean(lineType, latitudes, longitudes);
	}

	private void saveLine() {
		PERSISTENCE_SERVICE.saveLine(getLineBean(), new AsyncCallback<Boolean>() {
			
			public void onSuccess(Boolean result) {
				if(result.booleanValue()){
					MessageBox.alert("Linha salva com sucesso");
				}else{
					MessageBox.alert("Não foi possível salvar a linha");
				}
				
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Ouve um erro ao salvar. Erro: " + arg0);
			}
		});
		
	}
	
	private void loadLines() {
		PERSISTENCE_SERVICE.getLines(new AsyncCallback<List<LineBean>>() {
			
			public void onSuccess(List<LineBean> lines) {
				for (LineBean line : lines) {
					if(line.getType() == lineType){
						List<Double> latitudes = line.getLatitudes();
						List<Double> longitudes = line.getLongitudes();
						LatLng[] points = new LatLng[latitudes.size()];						
						for (int i = 0; i < latitudes.size(); i++) {					
							points[i] = LatLng.newInstance(latitudes.get(i), longitudes.get(i));							
						}
						Polyline p = new Polyline(points);
						p.setEditingEnabled(false);
						myMap.addOverlay(p);						
					}
				}
				
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Não foi possível carregar as linhas da base de dados");
				
			}
		});
		
	}	

}
