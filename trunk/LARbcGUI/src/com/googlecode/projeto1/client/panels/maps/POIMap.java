package com.googlecode.projeto1.client.panels.maps;


import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerDoubleClickHandler;
import com.google.gwt.maps.client.event.PolylineClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.maps.client.overlay.Polyline;
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
public class POIMap extends MappingWindow{
	
	private POIKind kind;
	private Marker marker;
	private int qtePoints;
	private Polyline line;
	private LatLng[] points;
	
	public POIMap(){
		super();
		myMap.clearOverlays();
		kind =  POIKind.POINT;
		qtePoints = 0;
		this.setTitle("Pontos de Interesse");		
		this.addButton(getSaveButton());	
		this.addMapEvent();
	}	
	
	public void setPointKind(){
		this.kind = POIKind.POINT;
	}
	
	public void setLineKind(){
		this.kind = POIKind.LINE;
//		this.line = new Polyline(new LatLng[20], "#111111", 100, 100.0, getPolylineOptions());
		this.line = new Polyline(new LatLng[20]);		
		this.line.addPolylineClickHandler(new PolylineClickHandler(){

			public void onClick(PolylineClickEvent event) {
				System.out.println("asfdasdf");		
			}
			
		});	
		points = new LatLng[20];
		qtePoints = 0;
//		line.setDrawingEnabled();
//		line.setEditingEnabled(true);
	}
	
//	private PolylineOptions getPolylineOptions() {
//		PolylineOptions options = PolylineOptions.newInstance();
//		return options;
//	}

	public void setAreaKind(){
		this.kind = POIKind.AREA;
	}

	private void addMapEvent() {		
		myMap.addMapClickHandler(new MapClickHandler() {
			public void onClick(final MapClickEvent clickEvent) {
				if(kind == POIKind.POINT && qtePoints == 0){
					marker = getMarker(clickEvent.getLatLng());					
					myMap.addOverlay(marker);
					qtePoints++;
				}else if(kind == POIKind.LINE){
					points[qtePoints] = clickEvent.getLatLng();					
					qtePoints++;
					Polyline p = new Polyline(points);
					if(line.getVertexCount() == 0){
						myMap.clearOverlays();
						myMap.addOverlay(p);						
					}
//					line.insertVertex(line.getVertexCount(), clickEvent.getLatLng());					
//					line.insertVertex(qtePoints, clickEvent.getLatLng());
//					qtePoints++;
//					if(line.getVertexCount() == 0){
//						myMap.addOverlay(line);						
//					}
//					LatLngBounds bounds = line.getBounds();
//					if(bounds == null){
//						line.insertVertex(line.getVertexCount(), clickEvent.getLatLng());						
//					}else if(!bounds.containsLatLng(clickEvent.getLatLng())){
//						line.insertVertex(line.getVertexCount(), clickEvent.getLatLng());
//					}
//					if(line.getVertexCount() == 0){
//						myMap.addOverlay(line);						
//					}
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
									qtePoints--;
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
				hide();
			}

		});
		return okButton;
	}		
	

}
