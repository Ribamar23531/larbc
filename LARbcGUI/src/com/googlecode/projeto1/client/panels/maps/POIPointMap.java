package com.googlecode.projeto1.client.panels.maps;


import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerDoubleClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.projeto1.client.beans.PointBean;
import com.googlecode.projeto1.client.beans.Type;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.NameValuePair;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class POIPointMap extends MappingWindow{
	
	private Marker marker;
	private Type pointType;
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	
	public POIPointMap(Type pointType){
		super();
		myMap.clearOverlays();
		this.marker = null;
		this.pointType = pointType;
		this.setTitle("Pontos de Interesse");		
		this.addButton(getSaveButton());		
		this.addMapEvent();
		loadPoints();
	}	

	private void addMapEvent() {		
		myMap.addMapClickHandler(new MapClickHandler() {
			public void onClick(final MapClickEvent clickEvent) {
				if(marker == null){					
					marker = getMarker(clickEvent.getLatLng());					
					myMap.addOverlay(marker);
				}
			}
		});
	}
	
	private MarkerDoubleClickHandler getDoubleClickHandler(final Marker marker) {		
		return new MarkerDoubleClickHandler(){

			public void onDoubleClick(MarkerDoubleClickEvent event) {
				MessageBox.show(new MessageBoxConfig() {

					{
						setTitle("Remover Marcador");
						setMsg("Você deseja realmente remover esse marcador?");
						setIconCls(MessageBox.QUESTION);
						setButtons(MessageBox.YESNO);
						setButtons(new NameValuePair[] {
								new NameValuePair("yes", "Sim"),
								new NameValuePair("no", "Não") });
						setCallback(new MessageBox.PromptCallback() {
							public void execute(String btnID, String text) {
								if (btnID.equals("yes")) {
									myMap.removeOverlay(marker);
									POIPointMap.this.marker = null;
								}

							}							
						});
					}
				});				
				
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
		marker.addMarkerDoubleClickHandler(getDoubleClickHandler(marker));
		return marker;			
	}	

	private Button getSaveButton() {
		Button okButton = new Button("Salvar");
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				if(marker == null){
					MessageBox.alert("Favor escolha uma posição ou feche a janela");
				}else{
					savePoint();
					hide();					
				}
			}		

		});
		return okButton;
	}		
	
	private void savePoint() {
		PERSISTENCE_SERVICE.savePoint(new PointBean(pointType, marker.getLatLng().toString()), new AsyncCallback<Boolean>() {
			
			public void onSuccess(Boolean success) {
				if(success.booleanValue()){
					MessageBox.alert("Ponto salvo com sucesso");
				}else{
					MessageBox.alert("Não foi possível salvar o ponto");
				}
				
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Ouve um erro ao salvar. Erro: " + arg0);
				
			}
		});
		
	}
	
	private void loadPoints() {
		PERSISTENCE_SERVICE.getPoints(new AsyncCallback<List<PointBean>>() {
			
			public void onSuccess(List<PointBean> points) {
				for (PointBean pointBean : points) {
					if(pointBean.getType() == pointType){
						String location = pointBean.getLocation();					
						Marker m = new Marker(getPoint(location), getOptions());
//						m.setImage(Util.ESCOLA_PATH);					
						myMap.addOverlay(m);						
					}
				}
				
			}
			
			private MarkerOptions getOptions() {
				MarkerOptions options = MarkerOptions.newInstance();
//				options.setIcon(Icon.newInstance(Util.ESCOLA_PATH));
				return options;
			}

			public void onFailure(Throwable arg0) {
				MessageBox.alert("Ouve um erro ao salvar. Erro: " + arg0);
				
			}
		});
		
	}
	
	private LatLng getPoint(String location){
		String[] aux = location.split(",");
		double lat = Double.parseDouble(aux[0].substring(1, aux[0].length()));
		double lng = Double.parseDouble(aux[1].substring(1, aux[0].length()));
		return LatLng.newInstance(lat, lng);
	}

}
