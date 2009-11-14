package com.googlecode.projeto1.client.panels.maps;


import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.PolylineClickHandler;
import com.google.gwt.maps.client.event.PolylineMouseOutHandler;
import com.google.gwt.maps.client.event.PolylineMouseOverHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.beans.LineBean;
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
public class LineMap extends MappingWindow{
	
	private Polyline line;
	private boolean mouseOverLine;
	private Type lineType;
	private SubPanelMap subPanelMap;
	private LineBean clickedLineBean;
	private boolean isRemoveMap;
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	
	public LineMap(Type lineType){
		super();
		this.isRemoveMap = false;
		this.line = null;
		this.clickedLineBean = null;
		this.lineType = lineType;
		this.setTitle("Pontos de Interesse");		
	}
	
	public void setSaveLineMap(){
		this.isRemoveMap = false;
		this.myMap.setSize("625px", "370px");
		this.setSize("650px", "590px");
		mouseOverLine = false;
		subPanelMap = new SubPanelMap(getSaveButton(), getRemoveButton1());
		this.add(subPanelMap);
		this.addMapEvent();
		setButtonsEnabled(false);
		loadLines();
	}	

	public void setRemoveLineMap(){
		this.isRemoveMap = true;
		this.myMap.setSize("625px", "370px");
		this.setSize("650px", "590px");
		subPanelMap = new SubPanelMap(getRemoveButton2());
		this.add(subPanelMap);
		setButtonsEnabled(false);
		loadLines();
	}

	private void setButtonsEnabled(boolean enabled){
		subPanelMap.setSaveButtonEnabled(enabled);
		subPanelMap.setRemoveButtonEnabled(enabled);
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
					setButtonsEnabled(true);
				}
				if(!mouseOverLine){
					line.insertVertex(line.getVertexCount(), clickEvent.getLatLng());					
				}
			}			
			
		});
	}	

	private Button getSaveButton() {
		Button okButton = new Button("Salvar");
		okButton.addClickListener(new ClickListener() {

			public void onClick(Widget arg0) {
				if (line == null) {
					MessageBox.alert("Favor crie uma linha ou feche a janela");
				} else {
					if(subPanelMap.getName().equals("")){
						MessageBox.alert("Favor preencher o campo nome");
					}else{
						saveLine();
						hide();						
					}
				}
			}
		});
		return okButton;
	}
	
	private LineBean getLineBean() {
		String name = subPanelMap.getName();
		String obs = subPanelMap.getObs();
		List<Double> latitudes = new ArrayList<Double>();
		List<Double> longitudes = new ArrayList<Double>();
		for (int i = 0; i < line.getVertexCount(); i++) {
			latitudes.add(line.getVertex(i).getLatitude());
			longitudes.add(line.getVertex(i).getLongitude());
		}
		return new LineBean(name, obs, lineType, latitudes, longitudes);
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
						if(isRemoveMap){
							p.addPolylineClickHandler(getPolylineClickHandler(line, p));
						}
						myMap.addOverlay(p);						
					}
				}
				
			}
			
			private PolylineClickHandler getPolylineClickHandler(final LineBean lineBean, final Polyline p) {
				return new PolylineClickHandler(){

					public void onClick(PolylineClickEvent event) {
						line = p;
						subPanelMap.setName(lineBean.getName());
						subPanelMap.setObs(lineBean.getObs());
						setButtonsEnabled(true);
						clickedLineBean = lineBean;
					}
					
				};
			}

			public void onFailure(Throwable arg0) {
				MessageBox.alert("Não foi possível carregar as linhas da base de dados");
				
			}
		});
		
	}
	
	private Button getRemoveButton1() {
		Button removeButton = new Button("Remover");
		removeButton.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {
				MessageBox.show(new MessageBoxConfig() {

					{
						setTitle("Remover Linha");
						setMsg("Você deseja realmente remover essa linha?");
						setIconCls(MessageBox.QUESTION);
						setButtons(MessageBox.YESNO);
						setButtons(new NameValuePair[] {
								new NameValuePair("yes", "Sim"),
								new NameValuePair("no", "Não") });
						setCallback(new MessageBox.PromptCallback() {
							public void execute(String btnID, String text) {
								if (btnID.equals("yes")) {									
									if(line != null){
										myMap.removeOverlay(line);
										line = null;
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
	
	private Button getRemoveButton2() {
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
									if(clickedLineBean != null){
										removeLine();
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
	
	private void removeLine() {
		PERSISTENCE_SERVICE.removeLine(clickedLineBean, new AsyncCallback<Boolean>() {
			
			public void onSuccess(Boolean success) {
				if(success.booleanValue()){
					myMap.removeOverlay(line);					
					setButtonsEnabled(false);
					subPanelMap.clearName();
					subPanelMap.clearObs();
					clickedLineBean = null;
					MessageBox.alert("Linha removida com sucesso");
				}else{
					MessageBox.alert("Não foi possível remover a linha");
				}
			}
			
			public void onFailure(Throwable arg0) {
				MessageBox.alert("Ouve um erro ao remover. Erro: " + arg0);				
			}
		});
		
	}

}
