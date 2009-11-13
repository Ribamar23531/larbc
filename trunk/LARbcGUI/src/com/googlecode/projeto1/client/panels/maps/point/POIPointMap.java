package com.googlecode.projeto1.client.panels.maps.point;


import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.beans.PointBean;
import com.googlecode.projeto1.client.beans.Type;
import com.googlecode.projeto1.client.panels.maps.MappingWindow;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.core.NameValuePair;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class POIPointMap extends MappingWindow{
	
	private Marker marker;
	private Type pointType;
	private PointMapSubPanel pointMapSubPanel;
	private PointBean clickedPointBean;
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	
	public POIPointMap(Type pointType){
		super();
		myMap.clearOverlays();		
		this.marker = null;
		this.clickedPointBean = null;
		this.pointType = pointType;
		this.setTitle("Pontos de Interesse");		
	}
	
	public void setSavePointMap(){
		this.myMap.setSize("625px", "370px");
		this.setSize("650px", "590px");
		pointMapSubPanel = new PointMapSubPanel(getSaveButton(), getRemoveButton());
		this.add(pointMapSubPanel);
		this.addMapEvent();
		setButtonsEnabled(false);
		loadPoints1();
	}
	
	public void setRemovePointMap(){
		this.myMap.setSize("625px", "370px");
		this.setSize("650px", "590px");
		pointMapSubPanel = new PointMapSubPanel(getRemoveButton());
		this.add(pointMapSubPanel);
		setButtonsEnabled(false);
		loadPoints2();
	}

	private void addMapEvent() {		
		myMap.addMapClickHandler(new MapClickHandler() {
			public void onClick(final MapClickEvent clickEvent) {
				if(marker == null){
					setButtonsEnabled(true);
					marker = getMarker(clickEvent.getLatLng());					
					myMap.addOverlay(marker);
				}
			}
		});
	}
	
//	private MarkerDoubleClickHandler getDoubleClickHandler(final Marker marker) {		
//		return new MarkerDoubleClickHandler(){
//
//			public void onDoubleClick(MarkerDoubleClickEvent event) {
//				MessageBox.show(new MessageBoxConfig() {
//
//					{
//						setTitle("Remover Marcador");
//						setMsg("Você deseja realmente remover esse marcador?");
//						setIconCls(MessageBox.QUESTION);
//						setButtons(MessageBox.YESNO);
//						setButtons(new NameValuePair[] {
//								new NameValuePair("yes", "Sim"),
//								new NameValuePair("no", "Não") });
//						setCallback(new MessageBox.PromptCallback() {
//							public void execute(String btnID, String text) {
//								if (btnID.equals("yes")) {
//									myMap.removeOverlay(marker);
//									POIPointMap.this.marker = null;
//									createPointSubPanel.setSaveButtonEnabled(false);
//								}
//
//							}							
//						});
//					}
//				});				
//				
//			}
//			
//		};
//	}
	
	private MarkerOptions getMarkerOptions() {
		MarkerOptions options = MarkerOptions.newInstance();
		options.setDraggable(true);
		return options;
	}
	
	private Marker getMarker(LatLng point){
		Marker marker = new Marker(point, getMarkerOptions());		
//		marker.addMarkerDoubleClickHandler(getDoubleClickHandler(marker));
		return marker;			
	}	

	private Button getSaveButton() {
		Button okButton = new Button("Salvar");
		okButton.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {
				if(marker == null){
					MessageBox.alert("Favor escolha uma posição ou feche a janela");
				}else{
					String name = pointMapSubPanel.getName();
					String obs = pointMapSubPanel.getObs();
					if(name.equals("")){
						MessageBox.alert("Favor preencher o campo nome");
					}else{
						savePoint(name, obs);
						hide();
					}										
				}
				
			}
		});
//		okButton.addListener(new ButtonListenerAdapter(){
//			public void onClick(Button button, EventObject e) {
//				if(marker == null){
//					MessageBox.alert("Favor escolha uma posição ou feche a janela");
//				}else{
//					savePoint();
//					hide();					
//				}
//			}		
//
//		});
		return okButton;
	}
	
	private Button getRemoveButton() {
		Button removeButton = new Button("Remover");
		removeButton.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {
				MessageBox.show(new MessageBoxConfig() {

					{
						setTitle("Remover Marcador");
						setMsg("Você deseja realmente remover o marcador?");
						setIconCls(MessageBox.QUESTION);
						setButtons(MessageBox.YESNO);
						setButtons(new NameValuePair[] {
								new NameValuePair("yes", "Sim"),
								new NameValuePair("no", "Não") });
						setCallback(new MessageBox.PromptCallback() {
							public void execute(String btnID, String text) {
								if (btnID.equals("yes")) {									
									if(clickedPointBean != null){
										removePoint();
									}
								}

							}
																				
						});
					}
				});		
				
			}
		});
		return removeButton;
	}
	
	private void setButtonsEnabled(boolean enabled){
		pointMapSubPanel.setSaveButtonEnabled(enabled);
		pointMapSubPanel.setRemoveButtonEnabled(enabled);
	}
	
	private void savePoint(String name, String obs) {
		PERSISTENCE_SERVICE.savePoint(getPointBean(name, obs), new AsyncCallback<Boolean>() {
			
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
	
	private void removePoint() {
		PERSISTENCE_SERVICE.removePoint(clickedPointBean, new AsyncCallback<Boolean>() {
			
			public void onSuccess(Boolean success) {
				if(success.booleanValue()){
					myMap.removeOverlay(marker);
					POIPointMap.this.marker = null;
					setButtonsEnabled(false);
					pointMapSubPanel.clearName();
					pointMapSubPanel.clearObs();
					clickedPointBean = null;
					MessageBox.alert("Ponto removido com sucesso");
				}else{
					MessageBox.alert("Não foi possível remover o ponto");
				}
				
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Ouve um erro ao remover. Erro: " + arg0);
				
			}
		});
		
	}
	
	private PointBean getPointBean(String name, String obs) {
		double latitude = marker.getLatLng().getLatitude();
		double longitude = marker.getLatLng().getLongitude();		
		return new PointBean(name, obs, pointType, latitude, longitude);
	}

	private void loadPoints1() {
		PERSISTENCE_SERVICE.getPoints(new AsyncCallback<List<PointBean>>() {
			
			public void onSuccess(List<PointBean> points) {
				for (PointBean pointBean : points) {
					if(pointBean.getType() == pointType){
						LatLng point = LatLng.newInstance(pointBean.getLatitude(), pointBean.getLongitude());
						Marker m = new Marker(point, getOptions());
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
	
	private void loadPoints2() {
		PERSISTENCE_SERVICE.getPoints(new AsyncCallback<List<PointBean>>() {
			
			public void onSuccess(List<PointBean> points) {
				for (PointBean pointBean : points) {
					if(pointBean.getType() == pointType){
						LatLng point = LatLng.newInstance(pointBean.getLatitude(), pointBean.getLongitude());
						Marker m = new Marker(point, getOptions());
						m.addMarkerClickHandler(getMarkerClickHandler(pointBean, m));
//						m.setImage(Util.ESCOLA_PATH);					
						myMap.addOverlay(m);						
					}
				}
				
			}
			
			private MarkerClickHandler getMarkerClickHandler(final PointBean pointBean, final Marker m) {
				return new MarkerClickHandler(){

					public void onClick(MarkerClickEvent event) {
						marker = m;
						pointMapSubPanel.setName(pointBean.getName());
						pointMapSubPanel.setObs(pointBean.getObs());
						setButtonsEnabled(true);
						clickedPointBean = pointBean;
					}
					
				};				
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

}
