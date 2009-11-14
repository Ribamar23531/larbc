package com.googlecode.projeto1.client.panels.maps;


import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.PolygonClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.beans.PolygonBean;
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
public class PolygonMap extends MappingWindow{
		
	private Polygon polygon;
	private ArrayList<LatLng> points;
	private Type polygonType;
	private SubPanelMap subPanelMap;
	private PolygonBean clickedPolygonBean;
	private boolean isRemoveMap;
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	
	public PolygonMap(Type polygonType){
		super();
		myMap.clearOverlays();
		this.isRemoveMap = false;
		points = new ArrayList<LatLng>();
		polygon = null;
		this.polygonType = polygonType;
		this.setTitle("Pontos de Interesse");			
	}
	
	public void setSavePolygonMap(){
		this.addMapEvent();	
		this.isRemoveMap = false;
		this.myMap.setSize("625px", "370px");
		this.setSize("650px", "590px");		
		subPanelMap = new SubPanelMap(getSaveButton(), getUndoButton());
		this.add(subPanelMap);
		this.addMapEvent();
		setButtonsEnabled(false);
		loadPolygons();
	}	

	public void setRemovePolygonMap(){
		this.isRemoveMap = true;
		this.myMap.setSize("625px", "370px");
		this.setSize("650px", "590px");
		subPanelMap = new SubPanelMap(getRemoveButton());
		this.add(subPanelMap);
		setButtonsEnabled(false);
		loadPolygons();
	}

	private void addMapEvent() {
		myMap.addMapClickHandler(new MapClickHandler() {
			public void onClick(final MapClickEvent clickEvent) {
				LatLng point = clickEvent.getLatLng();
				int size = points.size();
				if(size > 0){
					if(!points.get(points.size()-1).equals(point)){
						addPoint(clickEvent);
					}
				}else{
					addPoint(clickEvent);
				}				
			}

			private void addPoint(MapClickEvent clickEvent) {
				points.add(clickEvent.getLatLng());
				if(polygon != null){
					myMap.removeOverlay(polygon);
				}
				polygon = new Polygon(getPoints());
				myMap.addOverlay(polygon);
				setButtonsEnabled(true);	
				
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
		okButton.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {				
				if(polygon == null){
					MessageBox.alert("Favor demarque uma área ou feche a janela");
				}if(points.size() < 3){
					MessageBox.alert("Uma área deve ter pelo menos 3 pontos");
				}else{
					if(subPanelMap.getName().equals("")){
						MessageBox.alert("Favor preencher o campo nome");
					}else{
						savePolygon();
						hide();						
					}
				}
				
			}
		});		
		return okButton;
	}
	
	private void savePolygon() {
		PERSISTENCE_SERVICE.savePolygon(getPolygonBean(), new AsyncCallback<Boolean>() {
			
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
					if(polygon.getType() == polygonType){
						List<Double> latitudes = polygon.getLatitudes();
						List<Double> longitudes = polygon.getLongitudes();
						LatLng[] points = new LatLng[latitudes.size()];
						for (int i = 0; i < latitudes.size(); i++) {					
							points[i] = LatLng.newInstance(latitudes.get(i), longitudes.get(i));							
						}
						Polygon p = new Polygon(points);
						p.setEditingEnabled(false);
						myMap.addOverlay(p);
						if(isRemoveMap){
//							p.addPolylineClickHandler(getPolylineClickHandler(line, p));
							p.addPolygonClickHandler(getPolygonClickHandler(polygon, p));
						}						
					}
				}
				
			}
			
			private PolygonClickHandler getPolygonClickHandler(final PolygonBean polygonBean, final Polygon p) {
				return new PolygonClickHandler(){

					public void onClick(PolygonClickEvent event) {
						PolygonMap.this.polygon = p;
						subPanelMap.setName(polygonBean.getName());
						subPanelMap.setObs(polygonBean.getObs());
						setButtonsEnabled(true);
						clickedPolygonBean = polygonBean;
						
					}
					
				};
			}

			public void onFailure(Throwable arg0) {
				MessageBox.alert("Não foi possível carregar as áreas verdes da base de dados");
				
			}
		});
		
	}

	private PolygonBean getPolygonBean() {
		PolygonBean polygonBean = new PolygonBean();
		polygonBean.setName(subPanelMap.getName());
		polygonBean.setObs(subPanelMap.getObs());
		polygonBean.setType(polygonType);
		List<Double> latitudes = new ArrayList<Double>();
		List<Double> longitudes = new ArrayList<Double>();
		for (int i = 0; i < polygon.getVertexCount(); i++) {
			latitudes.add(polygon.getVertex(i).getLatitude());
			longitudes.add(polygon.getVertex(i).getLongitude());
		}
		polygonBean.setLatitudes(latitudes);
		polygonBean.setLongitudes(longitudes);
		return polygonBean;
	}

	private Button getUndoButton() {
		Button okButton = new Button("Desfazer");
		okButton.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {
				if(polygon != null && points.size() > 0){
					points.remove(points.size() - 1);
					myMap.removeOverlay(polygon);
					polygon = new Polygon(getPoints());
					myMap.addOverlay(polygon);
				}
				if(points.size() == 0){
					setButtonsEnabled(false);
				}
				
			}
		});
		return okButton;
	}
	
	private Button getRemoveButton() {
		Button removeButton = new Button("Remover");
		removeButton.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {
				MessageBox.show(new MessageBoxConfig() {

					{
						setTitle("Remover Linha");
						setMsg("Você deseja realmente remover?");
						setIconCls(MessageBox.QUESTION);
						setButtons(MessageBox.YESNO);
						setButtons(new NameValuePair[] {
								new NameValuePair("yes", "Sim"),
								new NameValuePair("no", "Não") });
						setCallback(new MessageBox.PromptCallback() {
							public void execute(String btnID, String text) {
								if (btnID.equals("yes")) {									
									if(clickedPolygonBean != null){
										removePolygon();
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
	
	private void removePolygon() {
		PERSISTENCE_SERVICE.removePolygon(clickedPolygonBean, new AsyncCallback<Boolean>() {
			
			public void onSuccess(Boolean success) {
				if(success.booleanValue()){
					myMap.removeOverlay(polygon);					
					setButtonsEnabled(false);
					subPanelMap.clearName();
					subPanelMap.clearObs();
					clickedPolygonBean = null;
					MessageBox.alert("Area removida com sucesso");
				}else{
					MessageBox.alert("Não foi possível remover a área");
				}
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Ouve um erro ao remover. Erro: " + arg0);				
			}
		});
		
	}	
	
	private void setButtonsEnabled(boolean enabled){
		subPanelMap.setSaveButtonEnabled(enabled);
		subPanelMap.setRemoveButtonEnabled(enabled);
	}	

}
