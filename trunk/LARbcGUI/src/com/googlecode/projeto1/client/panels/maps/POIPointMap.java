package com.googlecode.projeto1.client.panels.maps;


import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerDoubleClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
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
	private int qtePoints;	
	
	public POIPointMap(){
		super();
		myMap.clearOverlays();		
		qtePoints = 0;
		this.setTitle("Pontos de Interesse");		
		this.addButton(getSaveButton());	
		this.addMapEvent();
	}
	
	private void addMapEvent() {		
		myMap.addMapClickHandler(new MapClickHandler() {
			public void onClick(final MapClickEvent clickEvent) {
				if(qtePoints == 0){
					marker = getMarker(clickEvent.getLatLng());					
					myMap.addOverlay(marker);
					qtePoints++;
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
