package com.googlecode.projeto1.client.panels.maps;


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
public class PointMap extends MappingWindow{
	
	private Marker marker;
	private Type pointType;
	private SubPanelMap subPanelMap;
	private PointBean clickedPointBean;
	private boolean isRemoveMap;
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	
	public PointMap(Type pointType){
		super();
		this.isRemoveMap = false;
		this.marker = null;
		this.clickedPointBean = null;
		this.pointType = pointType;
		this.setTitle("Pontos de Interesse");		
	}
	
	public void setSavePointMap(){
		this.isRemoveMap = false;
		this.myMap.setSize("625px", "370px");
		this.setSize("650px", "590px");
		subPanelMap = new SubPanelMap(getSaveButton(), getRemoveMarkerButton());
		setButtonsEnabled(false);
		this.add(subPanelMap);
		this.addMapEvent();
		loadPoints();
	}

	public void setRemovePointMap(){
		this.isRemoveMap = true;
		this.myMap.setSize("625px", "370px");
		this.setSize("650px", "590px");
		subPanelMap = new SubPanelMap(getRemovePointButton());
		setButtonsEnabled(false);
		this.add(subPanelMap);		
		loadPoints();
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
	
	private MarkerOptions getMarkerOptions() {
		MarkerOptions options = MarkerOptions.newInstance();
		options.setDraggable(true);
		return options;
	}
	
	private Marker getMarker(LatLng point){
		Marker marker = new Marker(point, getMarkerOptions());
		return marker;			
	}	

	private Button getSaveButton() {
		Button okButton = new Button("Salvar");
		okButton.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {
				if(marker == null){
					MessageBox.alert("Favor escolha uma posição ou feche a janela");
				}else{
					String name = subPanelMap.getName();
					String obs = subPanelMap.getObs();
					if(name.equals("")){
						MessageBox.alert("Favor preencher o campo nome");
					}else{
						savePoint(name, obs);
						hide();
					}
				}
				
			}
		});
		return okButton;
	}
	
	private Button getRemoveMarkerButton() {
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
									if(marker != null){
										myMap.removeOverlay(marker);
										marker = null;
										setButtonsEnabled(false);
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
	
	private Button getRemovePointButton() {
		Button removeButton = new Button("Remover");
		removeButton.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {
				MessageBox.show(new MessageBoxConfig() {

					{
						setTitle("Remover Ponto");
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
		subPanelMap.setSaveButtonEnabled(enabled);
		subPanelMap.setRemoveButtonEnabled(enabled);
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
					setButtonsEnabled(false);
					subPanelMap.clearName();
					subPanelMap.clearObs();
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
	
	private void loadPoints() {
		PERSISTENCE_SERVICE.getPoints(new AsyncCallback<List<PointBean>>() {
			
			public void onSuccess(List<PointBean> points) {
				for (PointBean pointBean : points) {
					if(pointBean.getType() == pointType){
						LatLng point = LatLng.newInstance(pointBean.getLatitude(), pointBean.getLongitude());
						Marker m = new Marker(point, getOptions());
						if(isRemoveMap){
							m.addMarkerClickHandler(getMarkerClickHandler(pointBean, m));							
						}
//						m.setImage(Util.ESCOLA_PATH);					
						myMap.addOverlay(m);						
					}
				}
				
			}
			
			private MarkerClickHandler getMarkerClickHandler(final PointBean pointBean, final Marker m) {
				return new MarkerClickHandler(){

					public void onClick(MarkerClickEvent event) {
						marker = m;
						subPanelMap.setName(pointBean.getName());
						subPanelMap.setObs(pointBean.getObs());
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
